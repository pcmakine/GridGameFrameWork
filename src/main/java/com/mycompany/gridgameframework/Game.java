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
public class Game implements UserInteractionObserver{
    
    private Board board;
    private List<Rule> rules;
    private Rule turnChangeRule;
    private String name;
    private GameStats stats;
    private InputValidator validator;
    private boolean gameOver;
    private boolean paused;
    
    public Game(String name, int gridWidth, int gridHeight){
        this.name = name;
        this.board = new Board(gridWidth, gridHeight);
    }
    
    public void startGame(){
        //  this.stats = new GameStats(new Date());
    }

    @Override
    public void onUserInteraction(int x, int y, String input) {
        boolean validInput = board.setUserInputAt(x, y, input);
        
        for(Rule rule: rules){
            rule.check(this);
        }
    }
    
    public void endGame(){
        gameOver = true;
    }
    
    public boolean isPaused(){
        return paused;
    }
    
    public void pauseGame(){
        paused = true;
    }
}
