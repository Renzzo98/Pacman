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
public class Score {
    
    protected int score;
    protected int dotscore = 100;
    protected int ghostscore = 250;
    
    public Score()
    {
        this.score = 0;
    }
    /*
    protected void dotEaten()
    {
        this.score+= dotscore;
    }
*/
}
