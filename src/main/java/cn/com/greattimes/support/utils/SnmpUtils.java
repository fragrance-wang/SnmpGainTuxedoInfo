package cn.com.greattimes.support.utils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cn.com.greattimes.handler.Impl.GetInitInfoHandlerImpl;

public class SnmpUtils {
	
	//去除数组中的重复元素
	public static String[] removeRepeatedata(String[] strs){
		List<String> list = Arrays.asList(strs);
		Set<String> set = new HashSet<String>(list);
		Iterator<String> it = set.iterator();  
		StringBuffer sb= new StringBuffer();
		while(it.hasNext()){
			String next = it.next();
			sb.append(next).append(":");
		}
		String[] split2 = sb.toString().split(":");
		return split2;
		
	}
	
	public static void main(String[] args) {
		
		
		String gnerateTableNames = SnmpUtils.gnerateTableNames(0);
		System.out.println(gnerateTableNames);
		
		
	}
	
	
	
	/*
	 * 根据偏移量生成数据库表表名字符串
	 * @param offset 偏移量
	 * -1：上一个月
	 * 0：当前月
	 * 1：下一个月
	 */
	public static String gnerateTableNames(int offset){
		String type = GetInitInfoHandlerImpl.type;
		String tableName;
		if ("1".equals(type)) {
			tableName ="AUTO_INTERFACESINFO_";
		}else if("2".equals(type)){
			tableName ="AUTO_TUXEDOINFO_";
		}else{
			tableName="error";
		}
		
		
		//创建一个代表当前日期时间的  日期对象
		Calendar calendar = Calendar.getInstance();
		
		//让日历对象应用偏移量
		calendar.add(Calendar.MONTH, offset);
		
		//获取与当前日历对应的date对象
		Date time = calendar.getTime();
		
		//将对象转换为相应的格式
		tableName = tableName + new SimpleDateFormat("yyyy_MM").format(time);
//		System.out.println(tableName);
		return tableName;
	}
	
}
