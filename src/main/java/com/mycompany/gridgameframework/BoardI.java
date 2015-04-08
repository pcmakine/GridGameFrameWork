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

    public int getWidth();
    
    public int getHeight();

    public BoardComponent getSquareAt(int x, int y) throws IllegalArgumentException;

    public BoardComponent[][] getSquares();

    public boolean isFilled();

    public boolean setUserInputAt(int x, int y, String input);
    
}
