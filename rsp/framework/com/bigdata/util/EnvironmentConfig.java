package com.bigdata.util;

import java.io.*;
import java.util.*;

public class EnvironmentConfig {
    static EnvironmentConfig ec;// 创建对象ec
    private static Hashtable<String, Properties> register = new Hashtable<String, Properties>();// 静态对象初始化[在其它对象之前]

    private EnvironmentConfig() {
        super();
    }

    public static EnvironmentConfig getInstance() {
        if (ec == null)
            ec = new EnvironmentConfig();// 创建EnvironmentConfig对象
        return ec;// 返回EnvironmentConfig对象
    }

    public Properties getProperties(String fileName) {// 传递配置文件路径
        InputStream is = null;// 定义输入流is
        Properties p = null;
        try {
            p = (Properties) register.get(fileName);// 将fileName存于一个HashTable

            if (p == null) {
                try {
                    is = new FileInputStream(fileName);// 创建输入流
                } catch (Exception e) {
                    if (fileName.startsWith("/"))
                        // 用getResourceAsStream()方法用于定位并打开外部文件。
                        is = EnvironmentConfig.class.getResourceAsStream(fileName);
                    else
                        is = EnvironmentConfig.class.getResourceAsStream("/" + fileName);
                }
                p = new Properties();
                p.load(is);// 加载输入流
                register.put(fileName, p);// 将其存放于HashTable缓存
                is.close();// 关闭输入流
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return p;// 返回Properties对象
    }

    public String getPropertyValue(String fileName, String strKey) {
        Properties p = getProperties(fileName);
        try {
            return (String) p.getProperty(strKey);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
}