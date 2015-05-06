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
public interface Rule {
    public boolean check(Game game, boolean inputValid);
    public void onSuccessfullCheck(Game game);
    public void onUnSuccessfullCheck(Game game);
}
