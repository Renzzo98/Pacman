/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanapp;

import java.util.Random;

/**
 *
 * @author csc190
 */
public class Pinky implements Sprite {

    protected int x, y, sx, sy, w, h;
    protected boolean dead = false;
    protected String[] arrPics = {
        "Pinky.png"
    };
    protected int picIdx = 0;
    protected boolean bounced = false;
    protected Pacman player;
    protected Map map;

    public Pinky(int x, int y, int sx, int sy, int w, int h, Pacman player, Map map) {
        this.x = x;
        this.y = y;
        this.sx = sx;
        this.sy = sy;
        this.w = w;
        this.h = h;
        this.player = player;
        this.map = map;
    }

    @Override
    public void draw(API api) {
        if (dead != true) {
            String pic = this.arrPics[this.picIdx];
            api.drawImg(pic, x, y, w, h);
        }
    }

    private int counter = 0;

    @Override
    public void update() {
        counter++;
        if (this.player != null) {
            this.AI();
        }
        this.x += this.sx;
        this.y += this.sy;
        if (counter % 25 == 0) {
            this.picIdx = (picIdx + 1) % this.arrPics.length;
        }

    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getW() {
        return this.w;
    }

    public int getH() {
        return this.h;
    }

    public int getSX() {
        return this.sx;
    }

    public int getSY() {
        return this.sy;
    }

    public int getMapX() {
        return this.x / 50;
    }

    public int getMapY() {
        return this.y / 50;
    }

    public void setDirection(int sx, int sy) {
        this.sx = sx;
        this.sy = sy;
    }

    public void AI() {
        Random rand = new Random();
        MapTile[] mts = map.getNeighbors(this.getMapX(), this.getMapY());
     
        if (this.player.getX() >= this .x) // Pacman is on the right
        {
            if (this.player.getY() <= this.y) // Pacman is on top of you
            {
               if(this.player.getY() == this.y)
               {
                if(!(mts[1].s instanceof Wall))// GO RIGHT
                {
                    this.setDirection(1,0);
                    this.bounced = false;
                } 
               }else{
                if(!(mts[0].s instanceof Wall))// GO UP
                {
                    this.setDirection(0,-1);
                    this.bounced = false;
                }
                if(!(mts[1].s instanceof Wall))// GO RIGHT
                {
                    this.setDirection(1,0);
                    this.bounced = false;
                }
              }  
            }else if(this.player.getY() >= this.y) // Pacman is below of you
            {
               if(!(mts[2].s instanceof Wall))// GO DOWN
               {
                    this.setDirection(0,1);
                    this.bounced = false;
               }else if(!(mts[1].s instanceof Wall))// GO RIGHT
               {
                    this.setDirection(1,0);
                    this.bounced = false;
               }
           }
        }else// Pacman is on the left
        {
            if (this.player.getY() <= this.y) // Pacman is on top of you
            {
                if(!(mts[0].s instanceof Wall))//GO UP
                {
                    this.setDirection(0,-1);
                    this.bounced = false;
                }else if(!(mts[3].s instanceof Wall)) // GO LEFT
                {
                    this.setDirection(-1,0);
                    this.bounced = false;
                }
                
            }else if(this.player.getY() >= this.y) // Pacman is below of you
            {
               if(!(mts[2].s instanceof Wall)) // Go DOWN
               {
                    this.setDirection(0,1);
                    this.bounced = false;
               }else if(!(mts[3].s instanceof Wall)) // GO LEFT
               {
                    this.setDirection(-1,0);
                    this.bounced = false;
               }
            }
        }
        
        /*
        if (rand.nextInt(2) == 1) {
            if (this.player.x < this.x) {
                if (!(mts[3].s instanceof Wall)) {
                    this.setDirection(-1, 0);
                    this.bounced = false;
                }
            } else {
                if (!(mts[1].s instanceof Wall)) {
                    this.setDirection(1, 0);
                    this.bounced = false;
                }
            }
        } else {
            if (this.player.y < this.y) {
                if (!(mts[0].s instanceof Wall)) {
                    this.setDirection(0, -1);
                    this.bounced = false;
                }
            } else {
                if (!(mts[2].s instanceof Wall)) {
                    this.setDirection(0, 1);
                    this.bounced = false;
                }
            }
        }
        */

    }

}
