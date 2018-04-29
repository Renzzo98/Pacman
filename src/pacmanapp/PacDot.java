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
public class PacDot implements Sprite{
    // Was the PacDot eaten?
    protected boolean eaten = false; //changes to true when collision is detected with pacman and is removed
    
    //Initialize all variables for Pacdot
    protected int x;
    protected int y;
    protected int w;
    protected int h;
    
    public PacDot(int x, int y, int w, int h)
    {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
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
 

    @Override
    public void update() {
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   @Override
    public void draw(API api)
    {
        if(eaten!=true){
        String pic = "blackdot.png";
        api.drawImg(pic,x,y,w,h);
        }
    }
    
}
