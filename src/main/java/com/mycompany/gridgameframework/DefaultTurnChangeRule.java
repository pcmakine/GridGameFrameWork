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
public class DefaultTurnChangeRule extends Rule{

    @Override
    public boolean check(Game game, boolean inputValid) {
        if(inputValid && !game.isGameOver() && !game.isPaused() &&game.isStarted()){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void onSuccessfullCheck(Game game) {
        game.newTurn();
    }

    @Override
    public void onUnSuccessfullCheck(Game game) {
    }
    
}
