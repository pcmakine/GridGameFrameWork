/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gridgameframework;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pete
 */
public class ObjectCreator {

    private Properties properties;

    public ObjectCreator(Properties properties) {
        this.properties = properties;
    }

    public GameStats createGameStats() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        return (GameStats) getConstructor(properties.getProperty(ConfigKeys.GAMESTATS), Date.class).newInstance(new Date());
    }

    public InputValidator createInputValidator() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        return (InputValidator) getConstructor(properties.getProperty(ConfigKeys.INPUTVALIDATOR)).newInstance();
    }

    public Rule createTurnChangeRule() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        return (Rule) getConstructor(properties.getProperty(ConfigKeys.TURN_CHANGE_RULE)).newInstance();
    }

    public List<Rule> createRules() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        List<String> ruleClasses = Arrays.asList(properties.getProperty(ConfigKeys.RULES).split(","));
        List<Rule> rules = new ArrayList();
        for(String className : ruleClasses){
            rules.add((Rule) getConstructor(className).newInstance());
        }
        return rules;
    }

    private Constructor getConstructor(String className, Class... params) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class<?> clazz;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ObjectCreator.class.getName()).log(Level.SEVERE, "Check the " + className + " class name in the configs file", ex);
            throw ex;
        }
        return clazz.getConstructor(params);
    }

}
