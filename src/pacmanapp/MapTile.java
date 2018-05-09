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
public class MapTile {
    protected int x;
    protected int y;
    protected int w;
    protected int h;
    protected Sprite s;
    
    public MapTile(int x, int y, int w, int h, Sprite s)
    {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.s = s;
    }
    
    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }
            
    public int getW()
    {
        return this.w;
    }
   
    public int getH()
    {
        return this.h;
    }
    
    public Sprite getS()
    {
        return this.s;
    }
}
