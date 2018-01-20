package cn.com.greattimes.test;


import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.com.greattimes.handler.Impl.GetInitInfoHandlerImpl;
import cn.com.greattimes.service.queue.FlowBlockingQueue;
import cn.com.greattimes.service.queue.TuxedoBlockingQueue;
import cn.com.greattimes.support.log.LogBack;
import cn.com.greattimes.support.utils.PathUtils;

public class TestFlow {
	
	static Logger logger = LogBack.getLogger("snmp");
	private static ApplicationContext ctx;
	
	public static void main(String[] args) {
		
		String type = GetInitInfoHandlerImpl.type;
		String path ="file:"+PathUtils.getConfigPath() + "spring/spring-context.xml";
		ctx = new ClassPathXmlApplicationContext(path); 
		
		if ("1".equals(type)) {
			FlowBlockingQueue bean = ctx.getBean(FlowBlockingQueue.class);
			if ("start".equals(args[0])) {
				logger.info("开始启动");
				bean.transportData();
			}
			else if("stop".equals(args[0])){//args[0]数组必须是0  否则数组下表越界
				bean.shutdownService();
				((AbstractApplicationContext) ctx).close();
			}
		}else if ("2".equals(type)) {
			TuxedoBlockingQueue bean = ctx.getBean(TuxedoBlockingQueue.class);
			logger.info("开始启动");
			bean.transportData();
			
			
		}
		
	}
	
	
	
}



