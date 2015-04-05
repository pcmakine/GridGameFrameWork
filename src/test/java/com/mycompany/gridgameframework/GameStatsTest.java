/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gridgameframework;

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pete
 */
public class GameStatsTest {
    
    public GameStatsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void sessionDurationGivesCorrectDuration() throws InterruptedException{
        GameStats stats = new GameStats(new Date(), new PointsCalculator() {
            public int calculatePoints(GameStats stats){
                return 0;
            }
        });
        
        Thread.sleep(3000);
        
        long duration = stats.sessionDuration();
        assertEquals(3, duration);
        
    }
    
}
