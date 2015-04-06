/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gridgameframework.gui;

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
    private int boardWidth;
    private int boardHeight;
    private JPanel boardArea;
    private JPanel statsArea;
    private JLabel timeLabel;
    private GuiSquare[][] board;
    private UserInteractionObserver observer;

    public MainWindow(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        board = new GuiSquare[boardHeight][boardWidth];
        this.observer = GameController.getController();
    }

    public void init() {
        createFrame();
        createComponents();
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

    protected void createComponents() {
        createBoardArea();
        createStatsArea();
    }

    protected void createBoardArea() {
        boardArea = new JPanel(new GridLayout(boardHeight + 1, boardWidth + 1));
        for (int i = 0; i < boardHeight + 1; i++) {
            for (int j = 0; j < boardWidth + 1; j++) {
                if ((i == 0 || j == 0)) {
                    addCoordLabel(j, i, boardArea);
                } else {
                    addGuiSquare(j - 1, i - 1);
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

    private void addGuiSquare(int x, int y) {
        Game game = GameController.getController().getGame();
        GuiSquare square = null;
        try {
            square = new GuiSquare(game.getBoard().getSquareAt(x, y));
            //panel.add(new JLabel("(" + j + ", " + i + ")"));
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, "Check the path for "
                    + "the board component's image. Board component at ("
                    + game.getBoard().getSquareAt(x, y).getX() + ", "
                    + game.getBoard().getSquareAt(x, y).getY() + ")", ex);
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
