package cn.com.greattimes.service.tomapper.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import cn.com.greattimes.service.queue.FlowBlockingQueue;
import cn.com.greattimes.support.log.LogBack;
import cn.com.greattimes.support.oid.NetworkPortOID;
import cn.com.greattimes.support.utils.FlowUtils;


@Service
public class StatisticInfoServiceImpl implements Runnable {
	
	static Logger logger = LogBack.getLogger("snmp");
	
	public Map<String, String> insertInfo() {
		String[] oids = {NetworkPortOID.TOTALOID};
		logger.info("开始获取数据");
		return FlowUtils.GetDataByOID(oids);
	}

	@Override
	public void run() {
		
		Map<String, String> getDataMap = insertInfo();
		for(String value: getDataMap.values()){
			logger.debug(value);
		}
		try {
			FlowBlockingQueue.queue.put(getDataMap);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}


	




}
