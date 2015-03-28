/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gridgameframework.gui;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Pete
 */
public class DefaultGui {
    private JFrame frame;
    private int boardWidth;
    private int boardHeight;
    private JPanel[][] board;
    
    public DefaultGui(int boardWidth, int boardHeight){
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        board = new JPanel[boardHeight][boardWidth];
        
    }
    
    public void init(){
        frame = new JFrame();
        frame.setTitle("Testi");
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        
        frame.setLayout(new GridLayout(boardHeight, boardWidth));
        
        for(int i = 0; i < boardHeight; i++){
            for (int j = 0; j < boardWidth; j++) {
                JPanel panel = new JPanel();
                panel.add(new JLabel("(" + j + ", " + i + ")"));
                panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                board[i][j] = panel;
                frame.add(panel);
            }
        }
        
        frame.setVisible(true);
        frame.pack();
    }
}
