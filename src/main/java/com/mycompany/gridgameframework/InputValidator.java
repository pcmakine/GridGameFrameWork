/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gridgameframework;

import java.util.ArrayList;

/**
 *
 * @author Pete
 */
public class InputValidator {
    
    public boolean validateInput(Square square, String input){
        boolean isValid = isValid(square, input);
        if(isValid){
            setErrors(square, true);
        }else{
            setErrors(square, false);
        }
        return isValid;
    }
    
    protected boolean isValid(Square square, String input){
        return true;
    }
    
    protected void setErrors(Square square, boolean valid){
        ArrayList<String> errors = new ArrayList();
        if(!valid){
            errors.add("Invalid input");
        }
        square.setError(errors);
    }
}
