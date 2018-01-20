package cn.com.greattimes.support.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.TransportMapping;
import org.snmp4j.mp.MessageProcessingModel;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.PDUFactory;
import org.snmp4j.util.TableEvent;
import org.snmp4j.util.TableUtils;

import cn.com.greattimes.support.log.LogBack;
import cn.com.greattimes.support.oid.NetworkPortOID;


public class FlowUtils {
	
	static Logger logger = LogBack.getLogger();
	
	public static String[] num;//交换机 的  接口名字， 数字表示
	// 定义该字段  获取所有的接口信息 
	public static HashSet<String>  numSet = new HashSet<String>();
	public static String IpAddress;// 设备IP 地址
	private static String NowTime ;// 端口流量的采集  时间
	private static Address targetAddress = null;
	public static Snmp snmp = null;
	private static CommunityTarget target;
	@SuppressWarnings("rawtypes")
	public static TransportMapping transport = null;
	
	
	// 取当前时间
	public static String getNowTime () {
		return NowTime ;
	}
	
	
	@SuppressWarnings("unchecked")
	private static void init(){
		targetAddress = GenericAddress.parse ("udp:" + ReadFlowProUtils.getKey("Ip") + "/"+ReadFlowProUtils.getKey("Port"));
		try {
			transport = new DefaultUdpTransportMapping ();
			snmp = new Snmp(transport);
			transport.listen ();// 监听
		} catch (IOException e) {
			e.printStackTrace();
		}
	};
	
	private static void setNowTime(){
		// 计算并为时间和最终流量赋值
		Calendar c = Calendar.getInstance ();
		SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd HH:mm");
//		SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		NowTime = sdf.format (c.getTime ()); // 当前时间\
	}
	
	/**
	 * 创建对象communityTarget，用于返回target
	 */
	private static CommunityTarget createDefault() {
		
		target = new CommunityTarget ();
		target.setCommunity (new OctetString (ReadFlowProUtils.getKey("Community"))); // 设置共同体名
		target.setAddress (targetAddress); // 设置目标Agent 地址
		target.setRetries (Integer.parseInt(ReadFlowProUtils.getKey("Retries"))); // 重试次数
		target.setTimeout (Long.parseLong(ReadFlowProUtils.getKey("Timeout"))); // 超时设置
		target.setVersion (Integer.parseInt(ReadFlowProUtils.getKey("Version"))); // 版本
		return target;
	}
	
	
	
	//发送请求，获取数据
	public static List<TableEvent> getTableUtilsData(String[] oids){
		TableUtils tableUtils = new TableUtils(snmp, new PDUFactory() {
			public PDU createPDU(Target arg0) {
				PDU request = new PDU();
				request.setType(PDU.GET);
//				request.setType(PDU.GETBULK);
				return request;
			}

			public PDU createPDU(MessageProcessingModel arg0) {
				return null;
			}
		});
		
		OID[] columns = new OID[oids.length];
		for (int i = 0; i < oids.length; i++)
			columns[i] = new OID(oids[i]);
		List<TableEvent> list = tableUtils.getTable(target, columns, null, null);
		for (TableEvent tableEvent : list) {
			logger.info(tableEvent.toString());
		}
		return list;
	}
	
	//不单单是获取  接口的数量    因为端口不一定是从1 开始的   可能从三开始
	public static String[] GetInitInterNum () {
		//156服务器对应的oid信息
		String[] oids = {"1.3.6.1.2.1.2.2.1.1"};
		
		try {
			
			init();
			
			target = createDefault();
			
			List<TableEvent> list = getTableUtilsData(oids);
			
			//对数据进行处理
			for (TableEvent event : list) {
				VariableBinding[] values = event.getColumns();
				
				String oid = values[0].getOid().toString();
				
				String value = values[0].getVariable().toString();
				if (oid.contains(NetworkPortOID.IFINDEX)) {
					numSet.add(value);
				}
				
			}
			
			StringBuilder strb = new StringBuilder();
			Iterator<String> iterator = numSet.iterator();
			
			while (iterator.hasNext()) {
				String next = iterator.next();
				strb.append(next).append(":");
			}
			
			num = strb.toString().split(":");
			
			//num = list.size();
			
		} catch (Exception e) {
			e.printStackTrace ();
		}finally {
			closeStream();
		}
		return num;
	}
	
	
	
	// 获取所有端口的所有数据
	public static Map<String, String> GetDataByOID (String[] oids ) {
		//将获取的数据信息   存放到map集合中key为OID   value为获取的信息
		Map<String, String> map = new HashMap<String, String>();
//		System.out.println("_______----------");
		try {
			
			
			init();
			target = createDefault();
			//long start = System.currentTimeMillis();
			
			List<TableEvent> list = getTableUtilsData(oids);
//			logger.error(list.toString());
			
			
//			long end = System.currentTimeMillis();
//			System.out.println("time consume:"+(end-start)+"ms");
			
			setNowTime();
			
			//遍历集合，获取数据
			for (TableEvent event : list) {
				VariableBinding[] values = event.getColumns();
				//System.out.println(values);
				String oid = values[0].getOid().toString();
//				System.out.println(oid);
				String value = values[0].getVariable().toString();
				
//				System.out.println(oid+"------>"+value);
				logger.debug("oid:{},--->,value{}",oid.toString(),value.toString());
				map.put(oid, value);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace ();
		}finally {
			closeStream();
		}
		return map;
	}
	
	//关闭流
	public static void closeStream(){
		try {
			
			snmp.close();
			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
