package cn.com.greattimes.service.quartz;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.com.greattimes.handler.Impl.GetInitInfoHandlerImpl;
import cn.com.greattimes.service.tomapper.impl.StatisticInfoServiceImpl;
import cn.com.greattimes.service.tomapper.impl.TuxedoInfoServiceImpl;
/**
 * @author Administrator
 *
 */
@Component
public class GainFlowDataSchduler {
	@Autowired
	private StatisticInfoServiceImpl statisticInfoServiceImpl;
	@Autowired
	private TuxedoInfoServiceImpl tuxedoInfoServiceImpl;
	
	static ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
	public void getDataRegular(){
		
		String type = GetInitInfoHandlerImpl.type;
		
		if ("1".equals(type)) {
//			new Thread(statisticInfoServiceImpl).start();
//			singleThreadExecutor.submit(statisticInfoServiceImpl);
			statisticInfoServiceImpl.run();
			
		}else if ("2".equals(type)) {
			
//			new Thread(tuxedoInfoServiceImpl).start();
//			singleThreadExecutor.submit(tuxedoInfoServiceImpl);
			
			tuxedoInfoServiceImpl.run();
		}else{
//			logger.error("请配置正确的type类型");
			return;
		}
	}
	
	public void shutdown(){
		singleThreadExecutor.shutdown();
	}
	
	
	
	
	
}
