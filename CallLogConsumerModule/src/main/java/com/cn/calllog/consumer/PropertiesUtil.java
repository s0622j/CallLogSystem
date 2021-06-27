package com.cn.calllog.consumer;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
    public static Properties props;
    static {
        try {
            //加载外部属性文件
            InputStream in = ClassLoader.getSystemResourceAsStream("kafka.properties");
//            Properties props = new Properties();
            props = new Properties();
            props.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getProp(String key){
        return props.getProperty(key);
    }
}
