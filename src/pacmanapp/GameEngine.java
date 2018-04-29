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
       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    protected void collisionDetect() {
        for (Sprite s1 : this.arrSprites) {
            for (Sprite s2 : this.arrSprites) {
                if (s1 != s2) {
                   //if ((x.x <= (y.x + y.w))&& (x.x >= y.x) && (x.y <= (y.y + y.h)) && (x.y >= y.y)) {
                   if (isContact(s1.getX(),s1.getY(),s1.getW(),s1.getH(),s2.getX(),s2.getY(),s2.getW(),s2.getH())){
                        if (s1 instanceof Pacman && !(s2 instanceof Pacman)) {
                            Pacman p = (Pacman) s1;
                            PacDot d = (PacDot) s2;
                            d.eaten = true;
                        } else {
                            Pacman p = (Pacman) s2;
                            PacDot d = (PacDot) s1;
                            d.eaten = true;
                        }
                         if (s1 instanceof Pacman && s2 instanceof Pacman) {
                            Pacman p1 = (Pacman) s1;
                            Pacman p2 = (Pacman) s2;
                            p1.dead = true;
                            p2.dead = true;
                        }else if (s1 instanceof PacDot && s2 instanceof PacDot){
                            PacDot d1 = (PacDot) s1;
                            PacDot d2 = (PacDot) s2;
                        }
                    }
                }
            }
        }
    }//collisionDetect
    
    protected boolean pointinRect(int x, int y, int x2, int y2, int w ,int h)
    {
        if (x2 <= x && x <= (x2+w))
        {
            if (y2 <= y && y <= (y2+h))
            {
                return true;
            }
        }
        return false;
    }//pointinRect
    
      protected boolean isContact(int x, int y, int w,int h,int x2,int y2,int w2,int h2)
    {
        boolean lefttop = false, leftbot = false, righttop = false, rightbot = false;
        lefttop = pointinRect(x, y, x2, y2, w2, h2);
        leftbot = pointinRect(x, y+h, x2, y2, w2, h2);
        righttop = pointinRect(x+w, y, x2, y2, w2, h2);
        rightbot = pointinRect(x+w, y+h, x2, y2, w2, h2);
        if (lefttop || leftbot || righttop || rightbot)
        {
            return true;
        }else{
            return false;
        }

         
    }//isContact
    
    protected void updateAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    protected void drawAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
