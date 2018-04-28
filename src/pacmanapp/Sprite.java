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
public interface Sprite {
    
    public void draw(API api);
    
    public void update();
    
    public int getX();

    public int getY();
            
    public int getW();
   
    public int getH();
 
  
    
}
