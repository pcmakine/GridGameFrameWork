/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gridgameframework;

import java.util.Date;

/**
 *
 * @author Pete
 */
public abstract class GameStats {
    long gameTime;
    Date startTime;
    int playedTurns;
    
    public GameStats(Date startTime){
        this.startTime = startTime;
    }

    public long getGameTime() {
        return gameTime;
    }

    public Date getStartTime() {
        return startTime;
    }
    
    public int getPlayedTurns() {
        return playedTurns;
    }

    public void setPlayedTurns(int playedTurns) {
        this.playedTurns = playedTurns;
    }
    
    public void startNextTurn(){
        playedTurns++;
    }
    
    public abstract int  calculatePoints();
}
