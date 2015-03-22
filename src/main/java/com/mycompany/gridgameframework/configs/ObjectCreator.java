/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gridgameframework.configs;

import com.mycompany.gridgameframework.Board;
import com.mycompany.gridgameframework.DefaultEndGameRule;
import com.mycompany.gridgameframework.DefaultTurnChangeRule;
import com.mycompany.gridgameframework.GameStats;
import com.mycompany.gridgameframework.InputValidator;
import com.mycompany.gridgameframework.PointsCalculator;
import com.mycompany.gridgameframework.Rule;
import com.mycompany.gridgameframework.configs.ConfigKeys;
import java.lang.instrument.IllegalClassFormatException;
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
            return (GameStats) getConstructor(properties.getProperty(GameStats.class.getSimpleName()), Date.class).newInstance(new Date());
        } catch (Exception ex) {
            Logger.getLogger(ObjectCreator.class.getName()).log(Level.INFO, "no custom game stats found, using the default class", ex);
        }
        return new GameStats(new Date());
    }

    public PointsCalculator createPointsCalculator() {
        try {
            Object pointsCalc = getConstructor(properties.getProperty(PointsCalculator.class.getSimpleName())).newInstance();
            if(pointsCalc instanceof PointsCalculator){
                return (PointsCalculator) pointsCalc;
            }else{
                 throw new IllegalClassFormatException();
            }
        } catch(IllegalClassFormatException ex) {
            Logger.getLogger(ObjectCreator.class.getName()).log(Level.SEVERE,
                         properties.getProperty(PointsCalculator.class.getSimpleName()) + 
                                 " does not implement PointsCalculator interface, no points will be calculated!");
            return null;
        }
        catch (Exception ex) {
            Logger.getLogger(ObjectCreator.class.getName()).log(Level.INFO, "No points calculator found. Points will not be calculated", ex);
            return null;
        } 
    }

    public InputValidator createInputValidator() {
        String noValidatorMsg = "No custom validator class found. All inputs will be accepted.";
        try {
            return (InputValidator) getConstructor(properties.getProperty(InputValidator.class.getSimpleName())).newInstance();
        } catch (Exception ex) {
            return null;
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
        if(rules.isEmpty()){
            rules.add(new DefaultEndGameRule());
            rules.add(new DefaultTurnChangeRule());
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
