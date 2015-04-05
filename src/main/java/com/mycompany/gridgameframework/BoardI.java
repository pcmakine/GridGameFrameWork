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
public interface BoardI {

    int getHeight();

    BoardComponent getSquareAt(int x, int y) throws IllegalArgumentException;

    BoardComponent[][] getSquares();

    int getWidth();

    boolean isFilled();

    boolean setUserInputAt(int x, int y, String input);
    
}
