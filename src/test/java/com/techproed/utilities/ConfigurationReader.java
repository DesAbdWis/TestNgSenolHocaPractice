package com.techproed.utilities;


import java.io.FileInputStream;
import java.util.Properties;

public class ConfigurationReader {
    public static Properties properties;

    static {
        try{
            String path="configuration.properties";
            FileInputStream input=new FileInputStream(path);
            properties=new Properties();
            properties.load(input);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static String get(String keyword){
        return properties.getProperty(keyword);
    }
}
