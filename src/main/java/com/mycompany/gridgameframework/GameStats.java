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
    protected long gameTime;
    private Date sessionStartTime;
    private Date endTime;
    protected int playedTurns;
    
    public GameStats(Date startTime){
        this.sessionStartTime = startTime;
    }

    public long getGameTimeInSeconds(boolean running) {
        if(running){
            return gameTime/1000 + sessionDuration();
        }else{
            return gameTime/1000;
        }
    }

    public Date getStartTime() {
        return sessionStartTime;
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
    
    public void pauseGame(){
        gameTime = gameTime + sessionDuration();
    }
    
    public abstract int  calculatePoints();

    public long sessionDuration() {
        return (new Date().getTime() - sessionStartTime.getTime())/1000;
    }
}
