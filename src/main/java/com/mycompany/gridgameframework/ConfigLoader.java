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

    private Properties loadProperties() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/java/com/mycompany/gridgameframework/configs.txt"));
        } catch (IOException e) {
            System.out.println("error in loading the config file");
        }
        return properties;

    }

    public void printProperties() {
        Properties properties = loadProperties();
        for (String key : properties.stringPropertyNames()) {
            String value = properties.getProperty(key);
            System.out.println(key + " => " + value);
        }
    }

}
