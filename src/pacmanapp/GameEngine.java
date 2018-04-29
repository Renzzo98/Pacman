/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanapp;

import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author csc190
 */
public class GameEngine {

    protected Pacman playerPacman; // The player's Sprite
    protected ArrayList<Sprite> arrSprites = new ArrayList<Sprite>(); // An Array of Sprites Registered
    protected API api; // An instance of API

    public void loadMap() // Load the map and the sprites layer by layer
    {
        Pacman man1 = new Pacman(100, 100, 1, 0, 100, 100);
        //Pacman man2 = new Pacman(300, 300, 0, -1, 100, 100);
        PacDot dot1 = new PacDot(300, 300,100,100);
        //PacDot dot1 = new PacDot(200,200, 50, 50);
        this.register(man1); // register a Pacman
        //this.register(man2);
        this.register(dot1); // register a Pacdot
        //this.register(dot1);
        this.playerPacman = man1; // make the Pacman, the player.
    }

    public void register(Sprite s) // Add any sprites to the gameEngine
    {
        this.arrSprites.add(s);
    }

    public void delete(Sprite s) // Delete any sprites from the gameEngine 
    {
        this.arrSprites.remove(s);
    }

    public GameEngine(API api) // Adding an instance of the API to the gameEngine 
    {
        this.api = api;
    }

    public void oneRound() // What the gameEngine does every frame
    {
        Timer timer = new Timer();
        timer.start(); // start the timer
        api.clear(); // clear the sprites
        this.collisionDetect(); // see if any sprites had a collision
        for (Sprite s : this.arrSprites) {
            s.update(); // update all the sprites' location
            s.draw(api); // draw all the sprites again
        }
        double du = timer.stop(); // get the timer for one round
        System.out.println("one Round: " + du + " milliseconds");
    }

    public enum KEY // Define KEY phases
    {
        UP, DOWN, LEFT, RIGHT
    };

    public void handleKey(KEY key) // What to do if specfic keys are pressed
    {
        switch (key) {
            case UP:
                this.playerPacman.setDirection(0, -1); // Change the pacman's speed to go up
                break;

            case DOWN:
                this.playerPacman.setDirection(0, 1); // Change the pacman's speed to go down
                break;

            case LEFT:
                this.playerPacman.setDirection(-1, 0); // Change the pacman's speed to go left
                break;

            case RIGHT:
                this.playerPacman.setDirection(1, 0); // Change the pacman's speed to go right
                break;
        }
    }//handlekey

    protected void collisionDetect() // what to do if a sprite object collision with another sprite object
    {
        for (Sprite s1 : this.arrSprites) {
            for (Sprite s2 : this.arrSprites) {
                if (s1 != s2) {
                   //if ((x.x <= (y.x + y.w))&& (x.x >= y.x) && (x.y <= (y.y + y.h)) && (x.y >= y.y)) {
                   if (isContact(s1.getX(),s1.getY(),s1.getW(),s1.getH(),s2.getX(),s2.getY(),s2.getW(),s2.getH())){
                        if (s1 instanceof Pacman && !(s2 instanceof Pacman)) //if one is Pacman and the other is not, make Pacman eat him
                        {
                            Pacman p = (Pacman) s1;
                            PacDot d = (PacDot) s2;
                            d.eaten = true;
                        } else //else, the first one is pacdot and the other one is pacman
                        {
                            Pacman p = (Pacman) s2;
                            PacDot d = (PacDot) s1;
                            d.eaten = true;
                        }
                        if (s1 instanceof Pacman && s2 instanceof Pacman) // if they're both pacman, eat each other
                        {
                            Pacman p1 = (Pacman) s1;
                            Pacman p2 = (Pacman) s2;
                            p1.dead = true;
                            p2.dead = true;
                        }else if (s1 instanceof PacDot && s2 instanceof PacDot) // if one is Pacman and the other is a pacdot, make the pacman eat the pacdot
                        {
                            PacDot d1 = (PacDot) s1;
                            PacDot d2 = (PacDot) s2;
                        }
                    }
                }
            }
        }
    }//collisionDetect
    
    protected boolean pointinRect(int x, int y, int x2, int y2, int w ,int h) // Test a point to another object's 'Rect'
    {
        if (x2 <= x && x <= (x2+w)) // testing boundaries with the x axis
        {
            if (y2 <= y && y <= (y2+h)) // testing boundaries with y axis
            {
                return true;
            }
        }
        return false;
    }//pointinRect
    
    protected boolean isContact(int x, int y, int w,int h,int x2,int y2,int w2,int h2) //Test all four corner of an object to another object's field
    {
        boolean lefttop = false, leftbot = false, righttop = false, rightbot = false;
        lefttop = pointinRect(x, y, x2, y2, w2, h2); // testing the top left corner of the sprite's object boundaries
        leftbot = pointinRect(x, y+h, x2, y2, w2, h2); // testing the bottom left corner of the sprite's object boundaries
        righttop = pointinRect(x+w, y, x2, y2, w2, h2); // testing the top right corner of the sprite's object boundaries
        rightbot = pointinRect(x+w, y+h, x2, y2, w2, h2); // testing the bottom right corner of the sprite's object boundaries
        if (lefttop || leftbot || righttop || rightbot)
        {
            return true; // if another corner is in contact with the other sprite; return true
        }else{
            return false; // if not, return false
        }

         
    }//isContact
    
    protected void updateAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    protected void drawAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
