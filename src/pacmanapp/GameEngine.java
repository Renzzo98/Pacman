/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanapp;

import java.util.ArrayList;

/**
 *
 * @author csc190
 */
public class GameEngine {
    protected ArrayList<Sprite> arrSprites = new ArrayList<Sprite>();
    protected API api;
    protected Pacman myPacman;
    
    public GameEngine(API api){
        this.api = api;
        myPacman = new Pacman(0,0); 
    }
    
    public void oneRound(){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void loadMap(){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void handleKey(API api){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    protected void collisionDetect(){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    protected void updateAll(){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    protected void drawAll(){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
