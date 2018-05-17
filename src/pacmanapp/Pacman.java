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
public class Pacman implements Sprite {
    protected int x, y, sx, sy, w, h;
    protected boolean dead = false;
    protected String[] arrPics = {
        "Pacman1.png", "Pacman2.png","Pacman3.png"
    };
    protected int picIdx = 0;
    protected boolean bounced = false; //has pacman recently been bounced away from a wall 
    //protected boolean Hbounced = false; //has pacman recently been bounced away from a wall vertically
    
    public Pacman(int x, int y, int sx, int sy, int w, int h)
    {
        this.x = x;
        this.y = y;
        this.sx = sx;
        this.sy = sy;
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
    
    public int getSX()
    {
        return this.sx;
    }
       
    public int getSY()
    {
        return this.sy;
    }
    
    public int getMapX(){
        return this.x/50;
    }
    
    public int getMapY(){
        return this.y/50;
    }
    
    @Override
    public void draw(API api)
    {
        if(dead!=true){
        String pic = this.arrPics[this.picIdx];
        api.drawImg(pic,x,y,w,h);
        }
    }
    
    private int counter = 0;
    
    
    @Override
    public void update() {
        counter++;
        this.x += this.sx;
        this.y += this.sy;
        if(counter%10 == 0)
        {
            this.picIdx = (picIdx+1)%this.arrPics.length;
        }
       
    }
    
    public void setDirection(int sx, int sy)
    {
        this.sx = sx;
        this.sy = sy;
    }
}
