package cn.com.greattimes.support.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadTypeProUtils { 
    private static Properties prop = new Properties(); 
    static { 
        try { 
        	//实现只改配置文件，而不需要重启项目的方法
        	String path = PathUtils.getConfigPath()+"/type.properties";
        	FileInputStream fileInputStream = new FileInputStream(new File(path));
            prop.load(fileInputStream); 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
    } 
   
    public static String getKey(String key) { 
        return (String) prop.get(key); 
   
    } 
}
