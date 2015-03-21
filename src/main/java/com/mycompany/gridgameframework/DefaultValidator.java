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
public class DefaultValidator extends InputValidator {
    
    protected List<String> isValid(Square square, String input){
        ArrayList errors = new ArrayList();
        errors.add("some errors");
        return errors;
    }
}
