package cn.com.greattimes.handler.Impl;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import cn.com.greattimes.mapper.DemandMapper;
import cn.com.greattimes.mapper.StatisticTuxedoMapper;
import cn.com.greattimes.support.bean.Demand;
import cn.com.greattimes.support.bean.IndexObject;
import cn.com.greattimes.support.log.LogBack;
import cn.com.greattimes.support.utils.FlowUtils;
import cn.com.greattimes.support.utils.ReadTypeProUtils;


@Controller
public class GetInitInfoHandlerImpl {
	
	static Logger logger = LogBack.getLogger();
	@Autowired
	private DemandMapper demandMapper;
	@Autowired
	private StatisticTuxedoMapper statisticTuxedoMapper;
	
	public static String[] initInterNum = null;
	
	public static String type = ReadTypeProUtils.getKey("type");
	
	//在注解中配置  使容器 一起动就进行此方法   获取交换机接口的数量
	public static void getinitNum(){
		if ("1".equals(type)) {
			initInterNum = FlowUtils.GetInitInterNum();
		}
		
	}
	
	public void createIndexForInfo(){
		if("2".equals(type)){
			List<String> indexNames = new ArrayList<>();
			List<IndexObject> selectIndex = statisticTuxedoMapper.selectIndex();
			for (int i = 0; i < selectIndex.size(); i++) {
				indexNames.add(selectIndex.get(i).getKey_name());
			}
			selectIndex = null;
			
			if (!(indexNames.contains("tuxedo_info_tuxTqueueRqAddr")&&indexNames.contains("tuxedo_info_nowTimeForLong")) ) {
				statisticTuxedoMapper.createTuxedoInfoIndex();
			}
			
		}
	}
	
	
	
	public List<Demand> getDesrc(){
		List<Demand> keyNames = demandMapper.getDescr();
		if (keyNames.isEmpty()) {
			logger.info("ifDescr信息为空");
		}
		return keyNames;
	}
	
	
	
	
	
	//创建一个map容器  存放对应数量的  数据对象
	/*public Map<String, InterInfo> mapContainer(){
		// 获取接口的数量
		String[] interNum = initNum();
		Map<String, InterInfo> interInfoMap = new HashMap<>();
		
		for (int i = 0; i < interNum.length; i++) {
			InterInfo interInfo = new InterInfo();
			interInfoMap.put(interNum[i], interInfo);
		}
		
		return interInfoMap;
	}*/
}
