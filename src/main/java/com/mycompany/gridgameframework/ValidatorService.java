/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gridgameframework;

import com.mycompany.gridgameframework.configs.ObjectCreator;
import com.mycompany.gridgameframework.configs.ConfigLoader;
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
        this.validator = new ObjectCreator(ConfigLoader.getProperties()).createInputValidator();

    }

    public static ValidatorService getService() {
        return INSTANCE;
    }

    public InputValidator getValidator() {
        return validator;
    }

}
