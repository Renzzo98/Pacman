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
    
    public void draw(API api); // Draw the sprite
    
    public void update(); // Update the sprite location
    
    public int getX(); // get the sprite's X

    public int getY(); // get the sprite's Y
            
    public int getW(); // get the sprite's W
   
    public int getH(); // get the sprite's H
 
  
    
}
