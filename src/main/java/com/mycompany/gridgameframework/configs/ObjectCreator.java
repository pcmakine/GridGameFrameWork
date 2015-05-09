/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gridgameframework.configs;

import com.mycompany.gridgameframework.*;
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

    public ObjectCreator() {
        this.properties = ConfigLoader.getProperties();
    }

    public GameStats createGameStats() {
        try {
            return (GameStats) getConstructor(properties.getProperty(GameStats.class.getSimpleName()), Date.class).newInstance(new Date());
        } catch (Exception ex) {
            Logger.getLogger(ObjectCreator.class.getName()).log(Level.INFO, "no custom game stats found, using the default class", ex);
        }
        return new GameStats(createPointsCalculator());
    }

    public PointsCalculator createPointsCalculator() {
        try {
            Object pointsCalc = getConstructor(properties.getProperty(PointsCalculator.class.getSimpleName())).newInstance();
            if (pointsCalc instanceof PointsCalculator) {
                return (PointsCalculator) pointsCalc;
            } else {
                throw new IllegalClassFormatException();
            }
        } catch (IllegalClassFormatException ex) {
            Logger.getLogger(ObjectCreator.class.getName()).log(Level.SEVERE,
                    properties.getProperty("Points calculator needs to implement"
                            + PointsCalculator.class.getSimpleName())
                    + " interface. No points will be calculated!");
            return null;
        } catch (Exception ex) {
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
        String ruleStr = properties.getProperty(ConfigKeys.RULES);
        List<String> ruleClasses = new ArrayList();
        if(ruleStr != null){
            ruleClasses = Arrays.asList(ruleStr.split(","));
        }
        List<Rule> rules = new ArrayList();
        for (String className : ruleClasses) {
            try {
                rules.add((Rule) getConstructor(className).newInstance());
            } catch (Exception ex) {
                Logger.getLogger(ObjectCreator.class.getName()).log(Level.SEVERE, "Unexpected error in creating rules", ex);
            }
        }
        if (rules.isEmpty()) {
            rules.add(new DefaultEndGameRule());
            rules.add(new DefaultTurnChangeRule());
        }
        return rules;
    }

    public BoardI createBoard() {
        try {
            return (BoardI) getConstructor(properties.getProperty(Board.class.getSimpleName()), int.class, int.class).newInstance(ConfigLoader.getGridWidth(), ConfigLoader.getGridHeight());
        } catch (Exception ex) {
            try {
                return new Board(ConfigLoader.getGridWidth(), ConfigLoader.getGridHeight());
            } catch(IllegalArgumentException e){
                Logger.getLogger(ObjectCreator.class.getName()).log(Level.SEVERE, "", e);
                System.exit(1);
            }
            catch (Exception e) {
                Logger.getLogger(ObjectCreator.class.getName()).log(
                        Level.SEVERE, "Could not find the board dimensions in the configs file, creating "
                        + "a board with default dimensions: width: " + Board.DEFAULT_WIDTH
                        + ", height: " + Board.DEFAULT_HEIGHT, e);
                return new Board(Board.DEFAULT_WIDTH, Board.DEFAULT_HEIGHT);
            }
        }
        return null;
    }

    public BoardComponent createBoardComponent(int x, int y) {
        try {
            return (BoardComponent) getConstructor(properties.getProperty(BoardComponent.class.getSimpleName()), int.class, int.class).newInstance(x, y);
        } catch (Exception ex) {
            Logger.getLogger(ObjectCreator.class.getName()).log(
                    Level.SEVERE, "No custom board component provided, using the default"
                    + " square implementation", ex);
            return new Square(x, y);
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
