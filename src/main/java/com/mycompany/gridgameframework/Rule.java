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
public abstract class Rule {
    public abstract boolean check(Game game, boolean inputValid);
    public abstract void onSuccessfullCheck(Game game);
    public abstract void onUnSuccessfullCheck(Game game);
    
}
