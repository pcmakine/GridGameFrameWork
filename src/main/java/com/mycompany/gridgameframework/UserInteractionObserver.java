/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gridgameframework;

import java.util.List;

/**
 *
 * @author Pete
 */
public interface UserInteractionObserver {
    public void onUserInteraction(int x , int y, String input);
}