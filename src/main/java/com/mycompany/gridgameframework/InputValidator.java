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
public abstract class InputValidator {

    public boolean validateInput(Square square, String input) {
        List<String> errors = isValid(square, input);
        square.setError(errors);
        
        return errors.isEmpty();
    }

    protected List<String> isValid(Square square, String input) {
        return new ArrayList();
    }
}
