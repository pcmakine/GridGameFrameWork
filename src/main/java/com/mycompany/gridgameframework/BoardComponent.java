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
public interface BoardComponent<E> {

    public String getContent();
    
    public boolean setContent(String content);

    public List<String> getErrors();
    
    public void setErrors(List<String> errors);

    public E getHint();
    
    public void setHint(E hint);

    public int getNumOfChanges();

    public int getX();

    public int getY();
}
