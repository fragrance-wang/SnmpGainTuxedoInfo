package cn.com.greattimes.support.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

public class ReadFlowProUtils { 
    private static Properties prop = new Properties(); 
    static { 
        try { 
        	//实现只改配置文件，而不需要重启项目的方法
        	String path = PathUtils.getConfigPath()+"/flow.properties";
        	FileInputStream fileInputStream = new FileInputStream(new File(path));
           // prop.load(ReadFlowProUtils.class.getClassLoader().getResourceAsStream("conf/flow.properties")); 
            //prop.load(new FileInputStream(new File("F:/conf/flow.properties"))); 
            prop.load(fileInputStream); 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
    } 
   
    public static String getKey(String key) { 
        return (String) prop.get(key); 
    } 
    
    public static String[] getKeyNames(){
    	Set<Object> keyValue = prop.keySet();
		Iterator<Object> it = keyValue.iterator();  
		StringBuffer sb= new StringBuffer();
		while(it.hasNext()){
			String next = (String) it.next();
			sb.append(next).append(":");
		}
       return sb.toString().split(":");
    }
}
