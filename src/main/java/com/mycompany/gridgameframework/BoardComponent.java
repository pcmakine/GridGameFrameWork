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

    String getContent();

    List<String> getErrors();

    E getHint();

    int getNumOfChanges();

    int getX();

    int getY();

    boolean setContent(String content);

    void setHint(E hint);
    
}
