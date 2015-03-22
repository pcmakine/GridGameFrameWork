/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gridgameframework;

/**
 *
 * @author Pete
 */
public class DefaultEndGameRule extends Rule{

    @Override
    public boolean check(Game game, boolean inputValid) {
        return game.getBoard().isFilled();
    }

    @Override
    public void onSuccessfullCheck(Game game) {
        game.endGame();
    }

    @Override
    public void onUnSuccessfullCheck(Game game) {
    }
    
}
