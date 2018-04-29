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

    protected Pacman playerPacman;
    protected ArrayList<Sprite> arrSprites = new ArrayList<Sprite>();

    public void loadMap() {
        Pacman man1 = new Pacman(100, 100, 1, 0, 100, 100);
        //Pacman man2 = new Pacman(300, 300, 0, -1, 100, 100);
        PacDot dot1 = new PacDot(300, 300,100,100);
        //PacDot dot1 = new PacDot(200,200, 50, 50);
        this.register(man1);
        //this.register(man2);
        this.register(dot1);
        //this.register(dot1);
        this.playerPacman = man1;
    }

    public void register(Sprite s) {
        this.arrSprites.add(s);
    }

    public void delete(Sprite s) {
        this.arrSprites.remove(s);
    }

    protected API api;

    public GameEngine(API api) {
        this.api = api;
    }

    public void oneRound() {
        Timer timer = new Timer();
        timer.start();
        api.clear();
        this.collisionDetect();
        for (Sprite s : this.arrSprites) {
            s.update();
            s.draw(api);
        }
        double du = timer.stop();
        System.out.println("one Round: " + du + " milliseconds");
    }

    public enum KEY {
        UP, DOWN, LEFT, RIGHT
    };

    public void handleKey(KEY key) {
        switch (key) {
            case UP:
                this.playerPacman.setDirection(0, -1);
                break;

            case DOWN:
                this.playerPacman.setDirection(0, 1);
                break;

            case LEFT:
                this.playerPacman.setDirection(-1, 0);
                break;

            case RIGHT:
                this.playerPacman.setDirection(1, 0);
                break;
        }
    }

    protected void collisionDetect() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.       
    }
    
    protected void updateAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    protected void drawAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
