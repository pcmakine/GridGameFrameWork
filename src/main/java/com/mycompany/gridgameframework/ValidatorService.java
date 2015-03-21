/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gridgameframework;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pete
 */
public class ValidatorService {

    private static final ValidatorService INSTANCE = new ValidatorService();
    private InputValidator validator;

    private ValidatorService() {
        try {
            this.validator = new ObjectCreator(ConfigLoader.getProperties()).createInputValidator();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ValidatorService.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(ValidatorService.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        } catch (InstantiationException ex) {
            Logger.getLogger(ValidatorService.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ValidatorService.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(ValidatorService.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(ValidatorService.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
    }

    public static ValidatorService getService() {
        return INSTANCE;
    }

    public boolean validateInput(Square square, String input) {
        return validator.validateInput(square, input);
    }

}
