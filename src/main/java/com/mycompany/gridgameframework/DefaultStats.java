/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gridgameframework;

import com.mycompany.gridgameframework.GameStats;
import java.util.Date;

/**
 *
 * @author Pete
 */
public class DefaultStats extends GameStats{

    public DefaultStats(Date startTime){
        super(startTime);
    }
    
    @Override
    public int calculatePoints() {
        return 55;
    }
    
}
