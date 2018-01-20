package cn.com.greattimes.service.quartz;

import javax.annotation.Resource;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import cn.com.greattimes.handler.Impl.GetInitInfoHandlerImpl;
import cn.com.greattimes.service.tomapper.InsertFlowDataService;
import cn.com.greattimes.service.tomapper.InsertTuxedoDataService;
import cn.com.greattimes.support.utils.SnmpUtils;

/**
 * 添加石英任务，定时间下个月的数据表
 * 每月15号自动建下个月的表
 * @author Administrator
 *
 */
public class CreateTableNameScheduler extends QuartzJobBean{
	@Resource
	private InsertFlowDataService insertFlowDataService;
	@Resource
	private InsertTuxedoDataService insertTuxedoDataService;
	
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		String type = GetInitInfoHandlerImpl.type;
		String tableName =SnmpUtils.gnerateTableNames(1);
		if ("1".equals(type)) {
			
			try {
				insertFlowDataService.ifNotExistCreateInterName(tableName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("2".equals(type)){
			try {
				insertTuxedoDataService.ifNotExistCreateInterName(tableName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	//必须有set方法，不然无法注入
	public void setInsertFlowDataService(InsertFlowDataService insertFlowDataService) {
		this.insertFlowDataService = insertFlowDataService;
	}

	public void setInsertTuxedoDataService(InsertTuxedoDataService insertTuxedoDataService) {
		this.insertTuxedoDataService = insertTuxedoDataService;
	}
	
	
	
	
}
