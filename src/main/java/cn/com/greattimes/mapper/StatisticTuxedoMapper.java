package cn.com.greattimes.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.greattimes.support.bean.IndexObject;
import cn.com.greattimes.support.bean.TuxedoTQueueInfo;

public interface StatisticTuxedoMapper {
	
	void insertInfo(@Param(value="listInfo")List<TuxedoTQueueInfo> listInfo,@Param(value="tableName")String tableName);

	void createTableIfNotExist(@Param(value="tableName")String tableName);

	void createSampleTable();
	
	
	//将最新的时间戳更新到moudletimestamp数据表中
	void insertLastedTime(@Param(value="tqueueTime")long tqueueTime);
	
	void createTuxedoInfoIndex();
	
	List<IndexObject> selectIndex();
	
	
	//如果不存在就创建moudletimestamp数据表
	void createMoudletimestamp();
}
