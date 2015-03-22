/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gridgameframework.configs;

import com.mycompany.gridgameframework.Board;
import com.mycompany.gridgameframework.DefaultTurnChangeRule;
import com.mycompany.gridgameframework.GameStats;
import com.mycompany.gridgameframework.InputValidator;
import com.mycompany.gridgameframework.PointsCalculator;
import com.mycompany.gridgameframework.Rule;
import com.mycompany.gridgameframework.configs.ConfigKeys;
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

    public GameStats createGameStats() {
        try {
            return (GameStats) getConstructor(properties.getProperty(ConfigKeys.GAMESTATS), Date.class).newInstance(new Date());
        } catch (Exception ex) {
            Logger.getLogger(ObjectCreator.class.getName()).log(Level.INFO, "no custom game stats found, using the default class", ex);
        }
        return new GameStats(new Date());
    }

    public PointsCalculator createPointsCalculator() {
        try {
            return (PointsCalculator) getConstructor(properties.getProperty(ConfigKeys.POINTS_CALCULATOR)).newInstance();
        } catch (Exception ex) {
            Logger.getLogger(ObjectCreator.class.getName()).log(Level.INFO, "No points calculator found. Points will not be calculated", ex);
            return null;
        } 
    }

    public InputValidator createInputValidator() {
        String noValidatorMsg = "No custom validator class found. All inputs will be accepted.";
        try {
            return (InputValidator) getConstructor(properties.getProperty(ConfigKeys.INPUTVALIDATOR)).newInstance();
        } catch (Exception ex) {
            return null;
        }
    }

    public Rule createTurnChangeRule() {
        String noCustomRuleMsg = "No custom turn change rule foudn. Using the default rule";
        try {
            return (Rule) getConstructor(properties.getProperty(ConfigKeys.TURN_CHANGE_RULE)).newInstance();
        } catch (Exception ex) {
            Logger.getLogger(ObjectCreator.class.getName()).log(Level.INFO, noCustomRuleMsg, ex);
            return new DefaultTurnChangeRule();
        }
    }

    public List<Rule> createRules() {
        List<String> ruleClasses = Arrays.asList(properties.getProperty(ConfigKeys.RULES).split(","));
        List<Rule> rules = new ArrayList();
        for (String className : ruleClasses) {
            try {
                rules.add((Rule) getConstructor(className).newInstance());
            } catch (Exception ex) {
                Logger.getLogger(ObjectCreator.class.getName()).log(Level.SEVERE, "Unexpected error in creating rules", ex);
            } 
        }
        return rules;
    }

    public Board createBoard() {
        try {
            return (Board) getConstructor(properties.getProperty(ConfigKeys.BOARD), int.class, int.class).newInstance(ConfigLoader.getGridWidth(), ConfigLoader.getGridHeight());
        } catch (Exception ex) {
            return new Board(ConfigLoader.getGridWidth(), ConfigLoader.getGridHeight());
        }
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
