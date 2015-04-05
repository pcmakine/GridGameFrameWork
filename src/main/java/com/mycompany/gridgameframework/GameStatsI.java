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
public interface GameStatsI {

    int calculatePoints();

    void endGame(Date endTime);

    Date getEndTime();

    long getGameTimeInSeconds();

    int getPlayedTurns();

    Date getStartTime();

    void pauseGame();

    void resumeGame();

    long sessionDuration();

    void setPlayedTurns(int playedTurns);

    void startGame(Date startTime);

    void startNextTurn();
    
}
