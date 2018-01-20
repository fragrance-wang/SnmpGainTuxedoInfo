package cn.com.greattimes.service.tomapper.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import cn.com.greattimes.service.queue.TuxedoBlockingQueue;
import cn.com.greattimes.support.log.LogBack;
import cn.com.greattimes.support.utils.TuxedoUtils;

@Service
public class TuxedoInfoServiceImpl implements Runnable{
	
	static Logger logger = LogBack.getLogger("snmp");
	
	@Override
	public void run() {
		logger.debug("开始获取数据");
		Map<String, String> map;
			map = TuxedoUtils.getTuxedoInfo();
		if (map ==null) {
			map =new HashMap<>();
		}
		logger.debug("获取数据完成放到map中");
		if(logger.isDebugEnabled()){
			for(String value:map.values()){
				logger.debug("map中的数据"+value);
			}
		}
		
		try {
			TuxedoBlockingQueue.tuxedoQueue.put(map);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	
	
}
