/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gridgameframework;

import com.mycompany.gridgameframework.configs.ObjectCreator;
import java.util.List;

/**
 *
 * @author Pete
 */
public class Board implements BoardI {

    public static int DEFAULT_WIDTH = 10;
    public static int DEFAULT_HEIGHT = 10;
    public static int MAX_WIDTH = 30;
    public static int MAX_HEIGHT = 30;
    public static int MIN_WIDTH = 2;
    public static int MIN_HEIGHT = 2;

    private int width;
    private int height;
    private BoardComponent[][] squares;

    public Board(int width, int height) throws IllegalArgumentException {
        if (width < MIN_WIDTH || width > MAX_WIDTH || height < MIN_HEIGHT
                || height > MAX_HEIGHT) {
            throw new IllegalArgumentException("The given board dimensions are illegal. Max width: " + MAX_WIDTH
                    + ", Max height: " + MAX_HEIGHT + ", Min width: " + MIN_WIDTH
                    + ", Min height: " + MIN_HEIGHT + ". Given width: " + width
                    + ". Given height: " + height);
        }
        this.width = width;
        this.height = height;
        squares = new BoardComponent[height][width];
        init();
    }

    private void init() {
        ObjectCreator creator = new ObjectCreator();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                
                BoardComponent square = creator.createBoardComponent(x, y);
                initContent(square);
                squares[y][x] = square;
            }
        }
    }

    protected void initContent(BoardComponent square){};

    @Override
    public BoardComponent[][] getSquares() {
        return squares;
    }

    @Override
    public BoardComponent getSquareAt(int x, int y) throws IllegalArgumentException {
        if (!coordsOnBoard(x, y)) {
            throw new IllegalArgumentException();
        }
        return squares[y][x];
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public boolean setUserInputAt(int x, int y, String input) {
        BoardComponent square = getSquareAt(x, y);
        square.setContent(input);
        return square.getErrors().isEmpty();
    }

    private boolean coordsOnBoard(int x, int y) {
        return x >= 0 && y >= 0 && x <= width && y <= height;
    }

    @Override
    public boolean isFilled() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (getSquareAt(j, i).getContent() == null || getSquareAt(j, i).getContent().isEmpty()) {
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
