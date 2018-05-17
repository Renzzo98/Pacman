/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanapp;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author csc190
 */
public class Fruit implements Sprite {
    
    protected int ID;
    protected boolean eaten = false; //changes to true when collision is detected with pacman and is removed
    protected int x;
    protected int y;
    protected int w;
    protected int h;
    
    List<String> arrPic = Arrays.asList ("Apple.png", "Bannana.png","Strawberry.png","cherry.png");
    
    public Fruit(int x, int y, int w, int h)
    {
        Random rand = new Random();
        this.ID = rand.nextInt(4);
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
        String pic = arrPic.get(this.ID);
        api.drawImg(pic,x,y,w,h);
        }
    }
    
    
  public void isEaten(Score HS)
    {
        if (eaten!= true)
        {
            eaten = true;
            if (this.ID == 0)
            {
                HS.score += 250;
            }
            if (this.ID == 1)
            {
                HS.score += 350;
            }
            if (this.ID == 2)
            {
                HS.score += 500;
            }
            if (this.ID == 3)
            {
                HS.score += 750;
            }
        }
        
    }
}
