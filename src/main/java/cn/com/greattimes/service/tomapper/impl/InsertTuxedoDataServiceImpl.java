package cn.com.greattimes.service.tomapper.impl;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.greattimes.mapper.StatisticTuxedoMapper;
import cn.com.greattimes.service.tomapper.InsertTuxedoDataService;
import cn.com.greattimes.support.bean.TuxedoTQueueInfo;
import cn.com.greattimes.support.log.LogBack;
import cn.com.greattimes.support.utils.SnmpUtils;
import cn.com.greattimes.support.utils.TuxedoUtils;
@Service
public class InsertTuxedoDataServiceImpl implements InsertTuxedoDataService {
	
	static Logger logger = LogBack.getLogger("snmp");
	
	@Autowired
	private StatisticTuxedoMapper statisticTuxedoMapper;
	
	
	@Override
	public void ifNotExistCreateInterName(String tableName) {
		statisticTuxedoMapper.createTableIfNotExist(tableName);
	}

	@Override
	public void insertTuxedoData(List<TuxedoTQueueInfo> listInfo,long tqueueTime) {
		//1.先检查  数据库表是否存在  如果不存在建表
		String tableName = SnmpUtils.gnerateTableNames(0);
		statisticTuxedoMapper.createTableIfNotExist(tableName);
		/*//2.将当前的取到的时间戳插入到moudletimestamp数据表中
			statisticTuxedoMapper.createMoudletimestamp();
			statisticTuxedoMapper.insertLastedTime(tqueueTime);*/
		
		//2.向数据库表中插入数据
		try {
			statisticTuxedoMapper.insertInfo(listInfo, tableName);
		} catch (Exception e) {
			logger.error("没有获取到数据，向数据库中插入数据失败"+e.getMessage());
		}finally{
			TuxedoUtils.setNowTime();
			logger.info("向数据库中插入完成数据的时间/当前的系统时间："+TuxedoUtils.getNowTime());
			
			//2.将当前的取到的时间戳插入到moudletimestamp数据表中
//			statisticTuxedoMapper.createMoudletimestamp();//如果不存在就创建表
//			statisticTuxedoMapper.insertLastedTime(tqueueTime);
			logger.info("开始更新表bpm_ln_moudletimestamp时间戳");
			try {
				statisticTuxedoMapper.insertLastedTime(tqueueTime);
			} catch (Exception e) {
				logger.error("更新表bpm_ln_moudletimestamp时间戳失败，失败原因："+e.getMessage());
				e.printStackTrace();
			}
			TuxedoUtils.setNowTime();
			logger.info("表bpm_ln_moudletimestamp中的时间戳更新完成，更新完成的时间戳为："+TuxedoUtils.getNowTime());
		}
	}

	@Override
	public void createSampleTable() {
		statisticTuxedoMapper.createSampleTable();

	}

}
