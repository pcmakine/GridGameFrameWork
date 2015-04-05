/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gridgameframework;

import com.mycompany.gridgameframework.configs.ObjectCreator;
import com.mycompany.gridgameframework.configs.ConfigLoader;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Pete
 */
public class Game {

    private BoardI board;
    private List<Rule> rules;
    private String name;
    private GameStats stats;
    private boolean paused;
    private boolean gameOver;
    private boolean started;

    public Game() {
        ObjectCreator creator = new ObjectCreator();
        this.board = creator.createBoard();
        this.rules = creator.createRules();
        this.stats = creator.createGameStats();
        stats.startGame(new Date());
    }

    public boolean isStarted() {
        return started;
    }

    public void startGame() {
        started = true;
    }

    public boolean isPaused() {
        return paused;
    }

    public void pauseGame() {
        stats.pauseGame();
        paused = true;
    }
    
    public void resumeGame(){
        stats.resumeGame();
        paused = false;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public BoardI getBoard() {
        return board;
    }
    
    public GameStats getStats(){
        return stats;
    }
    
    public void setStats(GameStats stats){
        this.stats = stats;
    }

    public boolean onUserInput(int x, int y, String input) {
        boolean validInput = board.setUserInputAt(x, y, input);
        checkRules(validInput);
        return validInput;
    }

    private void checkRules(boolean inputValid) {
        for (Rule rule : rules) {
            if (rule.check(this, inputValid)) {
                rule.onSuccessfullCheck(this);
            } else {
                rule.onUnSuccessfullCheck(this);
            }
        }
    }
}
