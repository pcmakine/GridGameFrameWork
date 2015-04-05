/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gridgameframework.gui;

import com.mycompany.gridgameframework.Game;
import com.mycompany.gridgameframework.UserInteractionObserver;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Pete
 */
public class DefaultGui {

    private JFrame frame;
    private int boardWidth;
    private int boardHeight;
    private JPanel boardArea;
    private GuiSquare[][] board;
    private UserInteractionObserver observer;

    public DefaultGui(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        board = new GuiSquare[boardHeight][boardWidth];
        this.observer = Game.getGame();
    }

    public void init() {
        frame = new JFrame();
        frame.setTitle("Testi");
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        //  frame.setLayout(new GridLayout(boardHeight, boardWidth));
        boardArea = new JPanel(new GridLayout(boardHeight + 1, boardWidth + 1));

        Game game = Game.getGame();

        for (int i = 0; i < boardHeight + 1; i++) {
            for (int j = 0; j < boardWidth + 1; j++) {
                if (i == 0 || j == 0) {
                    if (i == 0 && j != 0) {
                        createLabel("" + j, boardArea);
                    }else if(j == 0 && i != 0){
                        createLabel("" + i, boardArea);
                    }else{
                        JLabel label = new JLabel();
                        boardArea.add(label);
                    }
                }else {
                    addGuiSquare(i - 1, j - 1, game);
                }
            }
        }
        frame.add(boardArea);
        frame.setVisible(true);
        frame.pack();
    }
    
    private void createLabel(String text, JPanel container){
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        container.add(label);
    }

    private void addGuiSquare(int i, int j, Game game) {
        GuiSquare square = null;
        try {
            square = new GuiSquare(game.getBoard().getSquareAt(j, i));
            //panel.add(new JLabel("(" + j + ", " + i + ")"));
        } catch (IOException ex) {
            Logger.getLogger(DefaultGui.class.getName()).log(Level.SEVERE, "Check the path for "
                    + "the board component's image. Board component at ("
                    + game.getBoard().getSquareAt(j, i).getX() + ", "
                    + game.getBoard().getSquareAt(j, i).getY() + ")", ex);
            System.exit(1);
        }
        board[i][j] = square;
        boardArea.add(square.getPanel());
    }

}
