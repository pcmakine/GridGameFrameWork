/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gridgameframework;

import com.mycompany.gridgameframework.configs.ConfigLoader;
import com.mycompany.gridgameframework.gui.DefaultGui;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author Pete
 */
public class Main {
    
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        List list = Arrays.asList(ConfigLoader.getProperties().getProperty("rules").split(","));
        System.out.println(list);
        Board board = new Board(10, 10);
        System.out.println(board);
        
        final DefaultGui gui = new DefaultGui(board.getWidth(), board.getHeight());
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                gui.init();
            }
        });
    }
}
