package com.bigdata.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Tools {
	private static Logger logger = LoggerFactory.getLogger(Tools.class);
	
	/**
	 * 获得Job 配置
	 * @param property
	 * @return
	 */
	public String getQuartzConfigPath(String property){
		try {
			InputStream input=this.getClass().getClassLoader().getResourceAsStream("quartz.properties");
			Properties p=new Properties();
			p.load(input);
			return p.getProperty(property);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
		
	}
	public static void main(String[] args) {
		System.out.println(new Tools().getQuartzConfigPath("isRunJob"));
	}
}
