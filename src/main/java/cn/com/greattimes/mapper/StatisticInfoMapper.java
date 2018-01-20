package cn.com.greattimes.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.greattimes.support.bean.InterInfo;

public interface StatisticInfoMapper {


	
	void insertInfo(@Param(value="listInfo")List<InterInfo> listInfo,@Param(value="tableName")String tableName);

	void createTableIfNotExist(@Param(value="tableName")String tableName);

	void createSampleTable();
	

}
