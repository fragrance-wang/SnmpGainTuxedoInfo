package cn.com.greattimes.service.tomapper.impl;


import org.springframework.stereotype.Service;

import cn.com.greattimes.support.utils.FlowUtils;
@Service
public class InitInfoServiceImpl{

	public String[] initInfo() {
		//String[] oids = {NetworkPortOID.IFINDEX,NetworkPortOID.IFDESCR,NetworkPortOID.IFTYPE,NetworkPortOID.IFMTU,NetworkPortOID.IFSPEED,NetworkPortOID.IFPHYSADDRESS,NetworkPortOID.IFADMINSTATUS,NetworkPortOID.IFOUTQLEN,NetworkPortOID.IFSPECIFIC};
		return FlowUtils.GetInitInterNum();
	}

}
