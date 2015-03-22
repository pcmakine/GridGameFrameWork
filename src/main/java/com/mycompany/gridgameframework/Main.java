/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gridgameframework;

import com.mycompany.gridgameframework.configs.ConfigLoader;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

/**
 *
 * @author Pete
 */
public class Main {
    
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            int num = ConfigLoader.getGridHeight();
        System.out.println(ConfigLoader.getGridHeight());
        Board board = new Board(2, 3);
        System.out.println(board);
    }
}
