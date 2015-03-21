/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gridgameframework;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Pete
 */
public class ConfigLoader {
    private static final ConfigLoader INSTANCE = new ConfigLoader();
    private static final String PROPERTY_FILE = "configs.txt";
    private static Properties properties;
    
    private ConfigLoader(){
        loadProperties();
    }

    private Properties loadProperties() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/java/com/mycompany/gridgameframework/" + PROPERTY_FILE));
           // properties.load(new FileInputStream(PROPERTY_FILE)); //production version
        } catch (IOException e) {
            System.out.println("error in loading the config file");
        }
        return properties;
    }
    
    public static Properties getProperties(){
        return properties;
    }

    public static void printProperties() {
        for (String key : properties.stringPropertyNames()) {
            String value = properties.getProperty(key);
            System.out.println(key + " => " + value);
        }
    }

}
