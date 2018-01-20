package cn.com.greattimes.support.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import org.slf4j.Logger;

import cn.com.greattimes.support.log.LogBack;
import cn.com.greattimes.support.tuxedo.SnmpManager;
import cn.com.greattimes.support.tuxedo.SnmpRequest;

public class TuxedoUtils {
	
	static Logger logger = LogBack.getLogger("snmp");
	
	public static String tuxedoIp;// 设备IP 地址
	
	private static String NowTime ;//采集  信息 时间

	private static int version;
	
	private static int port;
	
	private static String community;
	
	private static SnmpRequest request;
	
	private static SnmpManager manager;

	private static Operation operation = null;
	
	static SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
	
	static {
		tuxedoIp = ReadTuxedoProUtils.getKey("tuxedoIp");
		community = ReadTuxedoProUtils.getKey("tuxedoCommunity");
		version = Integer.parseInt(ReadTuxedoProUtils.getKey("tuxedoVersion"));
		port = Integer.parseInt(ReadTuxedoProUtils.getKey("tuxedoPort"));
	}
	 
   public static Map<String, String> getTuxedoInfo() {
	   setNowTime();
	   logger.info("开始获取数据的时间："+NowTime);
        operation = createOperation();
        Map<String, String> map=null;
        if (operation != null) {
           map  = operation.start();
        }
        //数据获取完成，添加时间戳
        setNowTime();
		return map;
    }
	
	
    public static String getNowTime() {
		return NowTime;
	}


    public static void setNowTime(){
		// 计算并为时间和最终流量赋值
		Calendar c = Calendar.getInstance ();
		
		NowTime = sdf.format (c.getTime ()); // 当前时间
	}

	//进行tuxedo获取信息的操作
	private static Operation createOperation() {
        if (port <= 0 || port >= 65536) {
        	logger.error("Port Number Error");
        }

        // Create SNMP manager
        try {
            if (version < 3) {
                
                if (version == 1) {
                    manager = SnmpManager.createSNMPv1(tuxedoIp, port, community);
                } else {
                    manager = SnmpManager.createSNMPv2c(tuxedoIp, port, community);
                }
            } else {
            	logger.error("暂不支持v3版本");
            }
        } catch (Exception e) {
        	logger.error("SNMP Error");
        }

        // Create request
//    String oid = "1.3.6.1.4.1.140.300.7.1.1.3";
      String oid = "1.3.6.1.4.1.140.300.7.1.1"; // 作getall操作,getnext操作
     
        request = new SnmpRequest(oid);
        return new Operation(manager, request);
    }
	

    
    
}  
    
    