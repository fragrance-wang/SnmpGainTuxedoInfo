package cn.com.greattimes.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.greattimes.support.bean.Demand;



public interface DemandMapper {
	
	List<Demand> getDescr();

	void insertDemand(@Param(value="list")List<String> list);
	
	
	
	
}
