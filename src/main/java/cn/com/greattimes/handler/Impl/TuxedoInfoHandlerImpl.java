package cn.com.greattimes.handler.Impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import cn.com.greattimes.handler.TuxedoInfoHandler;
import cn.com.greattimes.service.queue.TuxedoBlockingQueue;
import cn.com.greattimes.service.tomapper.InsertTuxedoDataService;
import cn.com.greattimes.support.bean.TuxedoTQueueInfo;
import cn.com.greattimes.support.log.LogBack;
import cn.com.greattimes.support.utils.TuxedoUtils;
@Controller
public class TuxedoInfoHandlerImpl implements TuxedoInfoHandler,Runnable{
	
	static Logger logger = LogBack.getLogger("snmp");
	
	@Autowired
	private InsertTuxedoDataService insertTuxedoDataService;
	
	
	@Override
	public void run() {
		Map<String, String> map;
		Map<String, TuxedoTQueueInfo> saveDateMap = new HashMap<String, TuxedoTQueueInfo>();
		//识别obj信息
//		StringBuffer buff = new  StringBuffer();
		HashSet< String >  set = new HashSet<>();
		long tqueueTime = 0;
		SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd HH:mm");
		long nowTimeForLong = 0;
		Date parse = null;
		while (true) {
			try {
				map = TuxedoBlockingQueue.tuxedoQueue.take();
				
				for (Map.Entry<String, String> entry : map.entrySet()) {
					String key = entry.getKey();
					String value = entry.getValue();
					
					if(logger.isDebugEnabled()){
						logger.debug(key +"---->"+value);
					}
					String[] split = key.split("\\.");
					//取第8位作为取得信息是Tqueue
					String Tqueue = split[8];
					//取第11位作为  识别取到的信息标识
					String identification = split[11];
					
					//截取属性不同的oid,识别是哪个对象
//					String obj = key.substring(28);
					StringBuffer sb = new StringBuffer();
					
					//获取 信息的时间
					String nowTime = TuxedoUtils.getNowTime();
					try {
						tqueueTime = sdf.parse(nowTime).getTime();
					} catch (ParseException e) {
						e.printStackTrace();
					}
					if ("7".equals(Tqueue)) {
						//截取后面数据作为key
						for (int i = 0; i < split.length; i++) {
							if (i>11) {
								sb.append(split[i]).append(".");
							}
						}
						String obj = sb.toString();
//						//取第12位作为  识别是哪个对象
//						String obj = split[12];
						set.add(obj);
						
						
						//如果map集合中如果没有数据   就新建向集合中放入数据
						if (!saveDateMap.containsKey(obj)) {
							saveDateMap.put(obj, new TuxedoTQueueInfo());
						}
						
						saveDateMap.get(obj).setNowTime(nowTime);
						saveDateMap.get(obj).setNowTimeForLong(tqueueTime);
					
//						buff.append(obj).append(":");
						switch (identification) {
						case "1":
							saveDateMap.get(obj).setTuxTqueueRqAddr(value);
							break;
						case "2":
							saveDateMap.get(obj).setTuxTqueueState(value);
							break;
						case "3":
							saveDateMap.get(obj).setTuxTqueueRqId(value);
							break;
						case "4":
							int parseInt = Integer.parseInt(value);
							saveDateMap.get(obj).setTuxTqueueSrvrCnt(parseInt);
							break;
						case "5":
							saveDateMap.get(obj).setTuxTqueueTotNqueued(value);
							break;
						case "6":
							saveDateMap.get(obj).setTuxTqueueTotWkQueued(value);
							break;
						case "7":
							saveDateMap.get(obj).setTuxTqueueSource(value);
							break;
						case "8":
							int parseInt1 = Integer.parseInt(value);
							saveDateMap.get(obj).setTuxTqueueNqueued(parseInt1);
							break;
						case "9":
							saveDateMap.get(obj).setTuxTqueueWkQueued(value);
							break;
	
						default:
							logger.error("数据异常");
							break;
						}
					
					}
					
				}
			if(logger.isDebugEnabled()){
				logger.debug("*****************************************************");
			}
				
			//如果娶不到数据，更新最新的时间戳
			if (map.entrySet().isEmpty()) {
				String nowTime = TuxedoUtils.getNowTime();
				//设置时间戳  
				try {
					parse = sdf.parse(nowTime);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				nowTimeForLong = parse.getTime();
				tqueueTime = nowTimeForLong;
			}
			
			
			}catch (Exception e) {
				e.printStackTrace();
			}
			
//			String[] split = buff.toString().split(":");
//			System.out.println(buff.toString());
			
//			String[] remoceRepeatedata = SnmpUtils.removeRepeatedata(split);
			
			List<TuxedoTQueueInfo> listInfo = new ArrayList<>();
//			for (int i = 0; i < saveDateMap.size(); i++) {
//				
//				listInfo.add(saveDateMap.get(remoceRepeatedata[i]));
//			}
			Iterator<String> iterator = set.iterator();
			while(iterator.hasNext()){
				String key = iterator.next();
//				System.out.println("key---->"+key);
				//把oid过滤了
				listInfo.add(saveDateMap.get(key));
			}
			
//			System.out.println("将获取到是数据放到list集合中");
//			for (TuxedoTQueueInfo tuxedoTQueueInfo : listInfo) {
//				System.out.println(tuxedoTQueueInfo+"\n---------------");
//			}
			
			//将集合中的数据插入到数据库中
			try {
				logger.info("数据获取完成的时间："+TuxedoUtils.getNowTime());
				TuxedoUtils.setNowTime();
				logger.info("向数据库中插入数据的时间"+TuxedoUtils.getNowTime());
				insertTuxedoDataService.insertTuxedoData(listInfo,tqueueTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	
	}
	
	
}
