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

    public GameStats(Date startTime) {
        this.sessionStartTime = startTime;
    }

    public abstract int calculatePoints();

    public void pauseGame() {
        updateGameTime();
        sessionStartTime = null;
    }

    public void endGame(Date endTime) {
        this.endTime = endTime;
        updateGameTime();
        sessionStartTime = null;
    }

    private void updateGameTime() {
        gameTime = gameTime + sessionDuration();
    }

    public long sessionDuration() {
        if (sessionStartTime != null) {
            return (new Date().getTime() - sessionStartTime.getTime()) / 1000;
        }
        return 0;
    }

    public void startNextTurn() {
        playedTurns++;
    }

    public long getGameTimeInSeconds() {
        return gameTime / 1000 + sessionDuration();
    }

    public Date getEndTime() {
        return endTime;
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

}
