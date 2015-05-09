/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gridgameframework;

import com.mycompany.gridgameframework.configs.ConfigLoader;
import com.mycompany.gridgameframework.configs.ObjectCreator;
import java.util.Date;
import java.util.Observable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Pete
 */
public class GameStats extends Observable implements GameStatsI {

    protected long gameTime;
    protected Date sessionStartTime;
    protected Date endTime;
    protected int playedTurns;
    protected PointsCalculator pointsCalc;
    protected ScheduledExecutorService timerExecutor;
    protected Future timerTask;
    protected int points;
    protected boolean playerWon;
    protected boolean playerLost;

    public GameStats(PointsCalculator pointsCalc) {
        this.pointsCalc = pointsCalc;
        this.playerWon = false;
        this.playerLost = false;
    }

    @Override
    public int calculatePoints() {
        if (pointsCalc != null) {
            this.points = pointsCalc.calculatePoints(this);
            return points;
        }
        return -1;
    }

    @Override
    public void startGame(Date startTime) {
        this.sessionStartTime = startTime;
        timerExecutor = Executors.newSingleThreadScheduledExecutor();
        startScheduledUpdates();
    }

    @Override
    public void pauseGame() {
        updateGameTime();
        sessionStartTime = null;
        timerTask.cancel(true);
    }

    @Override
    public void resumeGame() {
        sessionStartTime = new Date();
        startScheduledUpdates();
    }

    @Override
    public void endGame(Date endTime, boolean playerWon) {
        this.endTime = endTime;
        updateGameTime();
        sessionStartTime = null;
        timerTask.cancel(true);
        timerExecutor.shutdown();
        this.calculatePoints();
        this.playerWon = playerWon;
        this.playerLost = !playerWon;
        setChanged();
        notifyObservers(getGameTimeInSeconds());
    }

    protected void updateGameTime() {
        gameTime = gameTime + sessionDuration();
    }

    protected void startScheduledUpdates() {
        timerTask = timerExecutor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                setChanged();
                notifyObservers(getGameTimeInSeconds());
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    @Override
    public long sessionDuration() {
        if (sessionStartTime != null) {
            return (new Date().getTime() - sessionStartTime.getTime()) / 1000;
        }
        return 0;
    }

    @Override
    public void endTurn() {
        playedTurns++;
    }

    @Override
    public long getGameTimeInSeconds() {
        return gameTime / 1000 + sessionDuration();
    }

    @Override
    public Date getEndTime() {
        return endTime;
    }

    @Override
    public Date getStartTime() {
        return sessionStartTime;
    }

    @Override
    public int getPlayedTurns() {
        return playedTurns;
    }

    @Override
    public void setPlayedTurns(int playedTurns) {
        this.playedTurns = playedTurns;
    }

}
