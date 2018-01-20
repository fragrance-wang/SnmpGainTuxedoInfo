package cn.com.greattimes.handler.Impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import cn.com.greattimes.handler.StatisticInfoHandler;
import cn.com.greattimes.service.queue.FlowBlockingQueue;
import cn.com.greattimes.service.tomapper.InsertFlowDataService;
import cn.com.greattimes.support.bean.Demand;
import cn.com.greattimes.support.bean.InterInfo;
import cn.com.greattimes.support.log.LogBack;
import cn.com.greattimes.support.utils.FlowUtils;

@Controller
public class StatisticInfoHandlerImpl implements StatisticInfoHandler, Runnable{
	
	
	static Logger logger = LogBack.getLogger();
	@Autowired
	private InsertFlowDataService insertFlowDataService;
	@Autowired
	private GetInitInfoHandlerImpl getPortInfoHandlerImpl;


	
	@Override
	public void run() {
		// 获取接口的数量
		String[] initInterNum = GetInitInfoHandlerImpl.initInterNum;
		Map<String, InterInfo> insetMap = new HashMap<String, InterInfo>();
		
		for (int i = 0; i < initInterNum.length; i++) {
			InterInfo interInfo = new InterInfo();
			insetMap.put(initInterNum[i], interInfo);
		}
		
		
		Map<String, String> map;
		while (true) {
		
			try {
				
				map = FlowBlockingQueue.queue.take();
				for (Map.Entry<String, String> entry : map.entrySet()) {
					String key = entry.getKey();
					String value = entry.getValue();
//					System.out.println(key +"----》"+value);
					String[] indexs = key.split("\\.");
					
					//取最后一位  oid作为  接口
					String s = indexs[indexs.length - 1];
					// 取倒数第二位  oid  作为   接口信息类型
					String identification = indexs[indexs.length - 2];
					
					String nowTime = FlowUtils.getNowTime();
					SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd HH:mm");
					long nowTimeForLong = 0;
					try {
						Date parse = sdf.parse(nowTime);
						nowTimeForLong = parse.getTime();
//						System.out.println(nowTimeForLong+"-------------");
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
					insetMap.get(s).setNowTime(nowTime);
					insetMap.get(s).setNowTimeForLong(nowTimeForLong);
					
					
					switch (identification) {
					case "1":
						insetMap.get(s).setIfIndex(value);
						break;
					case "2":
						insetMap.get(s).setIfDescr(value);
						break;
					case "3":
						insetMap.get(s).setIfType(value);
						break;
					case "4":
						insetMap.get(s).setIfMtu(value);
						break;
					case "5":
						insetMap.get(s).setIfSpeed(value);
						break;
					case "6":
						insetMap.get(s).setIfPhysAddress(value);
						break;
					case "7":
						insetMap.get(s).setIfAdminStatus(value);
						break;
					case "8":
						insetMap.get(s).setIfOperStatus(value);
						break;
					case "9":
						insetMap.get(s).setIfLastChange(value);
						break;
					case "10":
						insetMap.get(s).setIfInOctets(value);
						break;
					case "11":
						insetMap.get(s).setIfInUcastPkts(value);
						break;
					case "12":
						insetMap.get(s).setIfInNUcastPkts(value);
						break;
					case "13":
						insetMap.get(s).setIfInDiscards(value);
						break;
					case "14":
						insetMap.get(s).setIfInErrors(value);
						break;
					case "15":
						insetMap.get(s).setIfInUnknownProtos(value);
						break;
					case "16":
						insetMap.get(s).setIfOutOctets(value);
						break;
					case "17":
						insetMap.get(s).setIfOutUcastPkts(value);
						break;
					case "18":
						insetMap.get(s).setIfOutNUcastPkts(value);
						break;
					case "19":
						insetMap.get(s).setIfOutDiscards(value);
						break;
					case "20":
						insetMap.get(s).setIfOutErrors(value);
						break;
					case "21":
						insetMap.get(s).setIfOutQLen(value);
						break;
					case "22":
						insetMap.get(s).setIfSpecific(value);
						break;
					}
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//			System.out.println("aaaaaaaa");
			//将map中的数据的value值  即对象  放到list集合中
			List<InterInfo> listInfo = new ArrayList<InterInfo>();
			List<Demand> demands = getPortInfoHandlerImpl.getDesrc();
			//String[] keyNames = ReadifDesrcProUtils.getKeyNames();
			List<String> keynames = new ArrayList<>();
			for (Demand demand : demands) {
				
				String ifDescr = demand.getIfDescr();
				keynames.add(ifDescr);
			}
			
			for (int i = 0; i < initInterNum.length; i++) {
				String ifDescr = insetMap.get(initInterNum[i]).getIfDescr();
				
				for (String string : keynames) {
					if (string.equals(ifDescr)) {
						listInfo.add(insetMap.get(initInterNum[i]));
					}
				}
				
			}
			if (listInfo.size() == 0) {
				System.out.println("请配置正确的接口信息");
				break;
			}
			for (InterInfo interInfo : listInfo) {
				System.out.println(interInfo);
			}
			logger.info("向数据库中插入数据");
			try{
				insertFlowDataService.insertFlowData(listInfo);
			}catch(Exception e){
				e.printStackTrace();
			}
		
		}
	}

	
	// 流量信息分析
	public List<String> statisticInfo() {
		return null;
		
		
	}


	

}
