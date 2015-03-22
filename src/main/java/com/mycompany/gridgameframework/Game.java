/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gridgameframework;

import com.mycompany.gridgameframework.configs.ObjectCreator;
import com.mycompany.gridgameframework.configs.ConfigLoader;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pete
 */
public class Game implements UserInteractionObserver {

    private static final Game INSTANCE = new Game();
    private Board board;
    private List<Rule> rules;
    private Rule turnChangeRule;
    private String name;
    private GameStats stats;
    private boolean paused;
    private boolean gameOver;
    private boolean started;

    private Game() {
    /*    try {
            this.board = new Board(ConfigLoader.getGridWidth(), ConfigLoader.getGridHeight());
            ObjectCreator creator = new ObjectCreator(ConfigLoader.getProperties());
            this.turnChangeRule = creator.createTurnChangeRule();
            this.rules = creator.createRules();
            this.stats = creator.createGameStats();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        } catch (InstantiationException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }*/
    }

    public static Game getGame() {
        return INSTANCE;
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
        name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void onUserInteraction(int x, int y, String input) {
        boolean validInput = board.setUserInputAt(x, y, input);
        checkRules(validInput);
    }

    private void checkRules(boolean inputValid) {
        for (Rule rule : rules) {
            if (rule.check(this, inputValid)) {
                rule.onSuccessfullCheck(this);
            } else {
                rule.onUnSuccessfullCheck(this);
            }
        }
        if (turnChangeRule != null) {
            if (turnChangeRule.check(this, inputValid)) {
                turnChangeRule.onSuccessfullCheck(this);
            } else {
                turnChangeRule.onUnSuccessfullCheck(this);
            }
        }
    }

}
