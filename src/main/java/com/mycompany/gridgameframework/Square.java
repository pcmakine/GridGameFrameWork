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
public class Square<E> implements BoardComponent<E> {
    private String content;
    private int numOfChanges;
    private E hint;
    protected int x;
    protected int y;
    protected List<String> errors;
    
    public Square(int x, int y){
        this.x = x;
        this.y = y;
        this.errors = new ArrayList();
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public boolean setContent(String content) {
        InputValidator validator = ValidatorService.getService().getValidator();
        boolean valid = true;
        if(validator != null){
            valid = validator.validateInput(this, content);
        }
        if(valid){
            this.content = content;
        }
        return valid;
    }

    @Override
    public int getNumOfChanges() {
        return numOfChanges;
    }

    @Override
    public E getHint() {
        return hint;
    }

    @Override
    public void setHint(E hint) {
        this.hint = hint;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public List<String> getErrors() {
        return errors;
    }

    protected void setError(List<String> errors) {
        this.errors = errors;
    }
  
}
