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
public class Main {
    
    public static void main(String[] args) {
            
        new ConfigLoader().printProperties();
        
        Board board = new Board(2, 3);
        System.out.println(board);
    }
}
