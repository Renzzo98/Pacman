/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanapp;

/**
 *
 * @author csc190
 */
public class Timer {
    long startTime;
    
    public void start() // Start the timer
    {
        startTime = System.currentTimeMillis();
    }
    
    public double stop() // End the timer and get the final time
    {
        long endTime = System.currentTimeMillis();
        return endTime-startTime;
    }
}
