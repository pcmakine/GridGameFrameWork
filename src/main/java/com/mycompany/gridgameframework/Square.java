/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gridgameframework;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pete
 */
public class Square<E, T> {
    private String content;
    private int numOfChanges;
    private E tip;
    protected int x;
    protected int y;
    protected List<String> errors;
    
    public Square(int x, int y){
        this.x = x;
        this.y = y;
        this.errors = new ArrayList();
    }

    public String getContent() {
        return content;
    }

    public boolean setContent(String content) {
        boolean valid = InputValidator.validateInput(this, content);
        if(valid){
            this.content = content;
        }
        return valid;
    }

    public int getNumOfChanges() {
        return numOfChanges;
    }

    public E getTip() {
        return tip;
    }

    public void setTip(E tip) {
        this.tip = tip;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public List<String> getErrors() {
        return errors;
    }

    protected void setError(List<String> errors) {
        this.errors = errors;
    }
  
}
