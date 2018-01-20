package cn.com.greattimes.support.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import cn.com.greattimes.support.utils.PathUtils;


public class LogBack {
	
	private static final Logger logger = LoggerFactory.getLogger(LogBack.class);

	static{
		

		LoggerContext lc = (LoggerContext)LoggerFactory.getILoggerFactory();
        JoranConfigurator configurator = new JoranConfigurator();
        configurator.setContext(lc);
        lc.reset();
        try {
        	String path = PathUtils.getConfigPath()+"/logback.xml";
            configurator.doConfigure(path);
       } catch (JoranException e) {
            e.printStackTrace();
        }
        StatusPrinter.printInCaseOfErrorsOrWarnings(lc);
		
	}
		
	public static Logger getLogger() {
		return logger;
	}
	
	public static Logger getLogger(Class<?> clazz) {
		return LoggerFactory.getLogger(clazz);
	}

	public static Logger getLogger(String loggerName) {
		return LoggerFactory.getLogger(loggerName);
	}
}
