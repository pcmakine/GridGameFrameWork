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
public class Board {

    private int width;
    private int height;
    private Square[][] squares;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        squares = new Square[height][width];
        init();
    }

    private void init() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Square square = new Square(x, y);
                initContent(square);
                squares[y][x] = square;
            }
        }
    }

    protected void initContent(Square square){};

    public Square[][] getSquares() {
        return squares;
    }

    public Square getSquareAt(int x, int y) throws IllegalArgumentException {
        if(!coordsOnBoard(x, y)){
            throw new IllegalArgumentException();
        }
        return squares[y][x];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean setUserInputAt(int x, int y, String input) {
        Square square = getSquareAt(x, y);
        square.setContent(input);
        return square.getErrors().isEmpty();
    }

    private boolean coordsOnBoard(int x, int y) {
        return x >= 0 && y >= 0 && x <= width && y <= height;
    }
    
    public boolean isFilled(){
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if(getSquareAt(j, i).getContent().isEmpty()){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                sb.append("[" + getSquareAt(j, i).x + ", " + getSquareAt(j, i).y + "]");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
