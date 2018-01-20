package cn.com.greattimes.service.listener;



import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import cn.com.greattimes.handler.Impl.GetInitInfoHandlerImpl;
import cn.com.greattimes.service.tomapper.InsertFlowDataService;
import cn.com.greattimes.service.tomapper.InsertTuxedoDataService;
import cn.com.greattimes.support.log.LogBack;

public class CreateInterfacesTableNameListener implements ApplicationListener<ContextRefreshedEvent>{
	static Logger logger = LogBack.getLogger();
//	@Autowired
//	private DemandMapper demandMapper;
	@Autowired
	private InsertFlowDataService insertFlowDataService;
	@Autowired
	private InsertTuxedoDataService insertTuxedoDataService;
	@Autowired
	private GetInitInfoHandlerImpl getInitInfoHandlerImpl;
	
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		String type = GetInitInfoHandlerImpl.type;
		if ("1".equals(type)) {
			insertFlowDataService.createSampleTable();
			
		}else if("2".equals(type)){
			insertTuxedoDataService.createSampleTable();
			
			getInitInfoHandlerImpl.createIndexForInfo();
		}else{
			logger.info("请配置正确的type类型");
		}
		
		
		
//		String[] oids = {NetworkPortOID.IFDESCR};
//		Map<String, String> dataMap = FlowUtils.GetDataByOID(oids);
//		List<String> list= new ArrayList<>();
//		for (Map.Entry<String, String> entry : dataMap.entrySet()) {
////			String key = entry.getKey();
//			String value = entry.getValue();
////			System.out.println(key+"--->"+value);
//			list.add(value);
//			
//		}
//		
//		demandMapper.insertDemand(list);
		
		
		
//		System.out.println("hahah");
		//创建数据库表明
//		String tableName = SnmpUtils.gnerateTableNames(0);
//		insertFlowDataService.ifNotExistCreateInterName(tableName);
		
		//项目启动的时候创建后三个月的数据库表
//		tableName = SnmpUtils.gnerateTableNames(1);
//		statisticInfoHandler.createInterName(tableName);
//		
//		
//		tableName = SnmpUtils.gnerateTableNames(2);
//		statisticInfoHandler.createInterName(tableName);
//		
//		tableName = SnmpUtils.gnerateTableNames(3);
//		statisticInfoHandler.createInterName(tableName);
//		
		
	}
	
	
	
	

}
