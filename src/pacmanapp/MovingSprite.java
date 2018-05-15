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
public abstract class MovingSprite {
    
    protected int x, y, w, h, sx, sy, mx, my;
    
    protected void setDirection(int x, int y)
    {
        
    }
    
    public boolean setDir(int dir){
        //setDir(int)
        return true;
    }
    
    public abstract int getSX();
    public abstract int getSY();
    public abstract int getX();
    public abstract int getY();
    public abstract void setX(int x);
    public abstract void setY(int y);
}