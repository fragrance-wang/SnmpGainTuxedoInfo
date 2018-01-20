package cn.com.greattimes.service.queue;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import cn.com.greattimes.handler.Impl.TuxedoInfoHandlerImpl;

@Component
public class TuxedoBlockingQueue {
	
//	@Autowired
//	private TuxedoInfoServiceImpl tuxedoInfoServiceImpl;//相当于生产者
	@Autowired
	private TuxedoInfoHandlerImpl tuxedoInfoHandlerImpl;//相当于消费者
	
	public static LinkedBlockingDeque<Map<String, String>> tuxedoQueue = new LinkedBlockingDeque<>(500);
	
//	private ExecutorService service = Executors.newCachedThreadPool();
	private ExecutorService service = Executors.newSingleThreadExecutor();
	
	public void transportData() {
			
//		service.submit(tuxedoInfoServiceImpl);
		service.submit(tuxedoInfoHandlerImpl);
	}
	
	
	
}
