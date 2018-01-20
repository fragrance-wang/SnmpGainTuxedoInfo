package cn.com.greattimes.service.tomapper.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.greattimes.mapper.StatisticInfoMapper;
import cn.com.greattimes.service.tomapper.InsertFlowDataService;
import cn.com.greattimes.support.bean.InterInfo;
import cn.com.greattimes.support.utils.SnmpUtils;
@Service
public class InsertFlowDataServiceImpl implements InsertFlowDataService {
	
	@Autowired
	private StatisticInfoMapper statisticInfoMapper;
	
	@Override
	public void insertFlowData(List<InterInfo> listInfo) {
		//1.先检查  数据库表是否存在  如果不存在建表
		String tableName = SnmpUtils.gnerateTableNames(0);
		statisticInfoMapper.createTableIfNotExist(tableName);
		//2.向数据库表中插入数据
		
		statisticInfoMapper.insertInfo(listInfo, tableName);
		
	}

	@Override
	public void ifNotExistCreateInterName(String str) {
		statisticInfoMapper.createTableIfNotExist(str);
		
	}

	@Override
	public void createSampleTable() {
//		System.out.println("创建表明");
		statisticInfoMapper.createSampleTable();
	}

}
