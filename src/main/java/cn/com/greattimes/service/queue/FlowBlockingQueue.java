package cn.com.greattimes.service.queue;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.com.greattimes.handler.Impl.StatisticInfoHandlerImpl;
import cn.com.greattimes.service.tomapper.impl.StatisticInfoServiceImpl;
@Component
public class FlowBlockingQueue {
//	@Autowired
//	private ScheduleStaticInfoServiceImpl scheduleStaticInfoServiceImpl;//相当于生产者
	
	@Autowired
	private StatisticInfoServiceImpl statisticInfoServiceImpl;//相当于生产者
	@Autowired
	private StatisticInfoHandlerImpl statisticInfoHandlerImpl;//相当于消费者
	
	public static LinkedBlockingDeque<Map<String, String>> queue = new LinkedBlockingDeque<>(500);
	
	private static ExecutorService service= Executors.newCachedThreadPool();
	
	public void transportData() {
		
			service.submit(statisticInfoServiceImpl);
			
			service.submit(statisticInfoHandlerImpl);
		
//		service.shutdown();
	}
	
	public void shutdownService(){
//		System.out.println(service);
//		queue.removeAll(queue);
		service.shutdown();
	}
	
}
