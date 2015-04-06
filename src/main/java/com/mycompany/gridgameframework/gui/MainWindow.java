/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gridgameframework.gui;

import com.mycompany.gridgameframework.BoardI;
import com.mycompany.gridgameframework.Game;
import com.mycompany.gridgameframework.GameController;
import com.mycompany.gridgameframework.UserInteractionObserver;
import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Pete
 */
public class MainWindow {

    protected JFrame frame;
    protected JPanel boardArea;
    protected JPanel statsArea;
    protected JLabel timeLabel;
    protected GuiSquare[][] board;

    public MainWindow(int boardWidth, int boardHeight) {
        board = new GuiSquare[boardHeight][boardWidth];
    }

    public void init(UserInteractionObserver obs, BoardI board) {
        createFrame();
        createComponents(obs, board);
        addComponentsToFrame();
    }

    protected void createFrame() {
        frame = new JFrame();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setTitle("Easy Grid Game");
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
    }

    protected void createComponents(UserInteractionObserver obs, BoardI boardModel) {
        createBoardArea(obs, boardModel);
        createStatsArea();
    }

    protected void createBoardArea(UserInteractionObserver obs, BoardI boardModel) {
        boardArea = new JPanel(new GridLayout(boardModel.getHeight() + 1, boardModel.getWidth() + 1));
        for (int i = 0; i < boardModel.getHeight() + 1; i++) {
            for (int j = 0; j < boardModel.getWidth() + 1; j++) {
                if ((i == 0 || j == 0)) {
                    addCoordLabel(j, i, boardArea);
                } else {
                    addGuiSquare(obs, boardModel, j - 1, i - 1);
                }
            }
        }
    }

    private void addCoordLabel(int x, int y, JPanel container) {
        JLabel label;
        if (x == 0 && y == 0) {
            label = new JLabel();
        } else if (x == 0) {
            label = new JLabel("" + y, SwingConstants.CENTER);
        } else {
            label = new JLabel("" + x, SwingConstants.CENTER);
        }
        container.add(label);
    }

    private void addGuiSquare(UserInteractionObserver obs, BoardI boardModel, int x, int y) {
        GuiSquare square = null;
        try {
            square = new GuiSquare(obs, boardModel.getSquareAt(x, y));
            //panel.add(new JLabel("(" + j + ", " + i + ")"));
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, "Check the path for "
                    + "the board component's image. Board component at ("
                    + boardModel.getSquareAt(x, y).getX() + ", "
                    + boardModel.getSquareAt(x, y).getY() + ")", ex);
            System.exit(1);
        }
        board[y][x] = square;
        boardArea.add(square.getPanel());
    }
    
    private void createStatsArea(){
        statsArea = new JPanel(new BorderLayout());
        JLabel statsLabel = new JLabel("Stats");
        statsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statsArea.add(statsLabel, BorderLayout.NORTH);
        timeLabel = new JLabel("Elapsed time: 0");
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statsArea.add(timeLabel, BorderLayout.CENTER);
    }

    protected void addComponentsToFrame() {
        frame.add(boardArea, BorderLayout.CENTER);
        frame.add(statsArea, BorderLayout.SOUTH);
        frame.setVisible(true);
        frame.pack();
    }
    
    public void updateTimeLabel(long seconds){
        if(timeLabel != null){
            this.timeLabel.setText("Elapsed time: " + seconds);
            timeLabel.updateUI();
        }
        
        
    }
}
