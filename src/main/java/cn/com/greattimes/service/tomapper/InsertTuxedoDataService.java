package cn.com.greattimes.service.tomapper;

import java.util.List;

import cn.com.greattimes.support.bean.TuxedoTQueueInfo;

public interface InsertTuxedoDataService {
	
	void ifNotExistCreateInterName(String str);
	
	
	void insertTuxedoData(List<TuxedoTQueueInfo> listInfo, long tqueueTime);


	void createSampleTable();



}
