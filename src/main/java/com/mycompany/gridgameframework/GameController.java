/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gridgameframework;

import com.mycompany.gridgameframework.gui.MainWindow;

/**
 *
 * @author Pete
 */
public class GameController implements UserInteractionObserver {
    private static GameController INSTANCE = new GameController();
    private Game game;
    
    private GameController(){
        this.game = new Game();
        
        final MainWindow gui = new MainWindow(game.getBoard().getWidth(), game.getBoard().getHeight());
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                gui.init();
            }
        });
        
    }
    
    public static GameController getController(){
        return INSTANCE;
    }

    @Override
    public boolean onUserInput(int x, int y, String input) {
        return game.onUserInput(x, y, input);
    }

    @Override
    public void onPauseClick() {
        game.pauseGame();
    }

    @Override
    public void onResumeClick() {
        game.resumeGame();
    }

    @Override
    public void onSaveGameClick() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Game getGame(){
        return game;
    }
    
}
