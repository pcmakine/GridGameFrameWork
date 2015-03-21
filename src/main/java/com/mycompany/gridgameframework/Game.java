/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gridgameframework;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Pete
 */
public class Game implements UserInteractionObserver {
    private Board board;
    private List<Rule> rules;
    private Rule turnChangeRule;
    private String name;
    private GameStats stats;
    private boolean paused;
    private boolean gameOver;
    private boolean started;

    public Game(String name, int gridWidth, int gridHeight) {
        this.name = name;
        this.board = new Board(gridWidth, gridHeight);
    }

    public boolean isStarted() {
        return started;
    }

    public void startGame() {
        started = true;
        //this.stats = new GameStats(new Date());
    }

    public boolean isPaused() {
        return paused;
    }

    public void pauseGame() {
        stats.pauseGame();
        paused = true;
    }

    public void newTurn() {
        stats.startNextTurn();
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void endGame() {
        stats.endGame(new Date());
        gameOver = true;
    }
    
    public void setName(String name){
        name = name;
    }
    
    public String getName(){
        return name;
    }

    @Override
    public void onUserInteraction(int x, int y, String input) {
        boolean validInput = board.setUserInputAt(x, y, input);

        for (Rule rule : rules) {
            rule.check(this);
        }
        if (turnChangeRule != null) {
            turnChangeRule.check(this);
        }
    }
}
