package cn.com.greattimes.service.tomapper;

import java.util.List;

import cn.com.greattimes.support.bean.InterInfo;

public interface InsertFlowDataService {
	
	void ifNotExistCreateInterName(String str);
	
	
	void insertFlowData(List<InterInfo> listInfo);


	void createSampleTable();



}
