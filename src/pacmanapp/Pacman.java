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
public class Pacman extends MovingSprite implements Sprite {
    protected int x, y, sx, sy, w, h;
    protected boolean dead = false;
    protected String[] arrPics = {
        "Pacman1.png", "Pacman2.png","Pacman3.png"
    };
    
    protected int picIdx = 0;
    
    public Pacman(int x, int y, int sx, int sy, int w, int h)
    {
        this.x = x;
        this.y = y;
        this.sx = sx;
        this.sy = sy;
        this.w = w;
        this.h = h;
        this.mx = x;
        this.my = y;
    }
    
    @Override
    public int getX()
    {
        return this.x;
    }

    @Override
    public int getY()
    {
       return this.y;
    }
         
    @Override
    public int getW()
    {
        return this.w;
    }
   
    @Override
    public int getH()
    {
        return this.h;
    }
    
    @Override
    public int getSX()
    {
        return this.sx;
    }
      
    @Override
    public int getSY()
    {
        return this.sy;
    }
    
 
    public int getMX(){
        return this.mx;
    }
    
    public int getMY(){
        return this.my;
    }
    
    @Override
    public void setX(int x){
        this.x = x;
        this.setMX();
    }
    
    @Override
    public void setY(int y){
        this.y= y;
        this.setMY();
    }
    
    public void setMX(){
        this.mx = this.x/50;
    }
   
    public void setMY(){
        this.my = this.y/50;
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
        if(counter%25 == 0)
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
