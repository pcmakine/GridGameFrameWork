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
    public static int DEFAULT_WIDTH = 10;
    public static int DEFAULT_HEIGHT = 10;
    
    private int width;
    private int height;
    private BoardComponent[][] squares;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        squares = new BoardComponent[height][width];
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

    public BoardComponent[][] getSquares() {
        return squares;
    }

    public BoardComponent getSquareAt(int x, int y) throws IllegalArgumentException {
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
        BoardComponent square = getSquareAt(x, y);
        square.setContent(input);
        return square.getErrors().isEmpty();
    }

    private boolean coordsOnBoard(int x, int y) {
        return x >= 0 && y >= 0 && x <= width && y <= height;
    }
    
    public boolean isFilled(){
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if(getSquareAt(j, i).getContent() == null || getSquareAt(j, i).getContent().isEmpty()){
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
                sb.append("[" + getSquareAt(j, i).getX() + ", " + getSquareAt(j, i).getY() + "]");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
