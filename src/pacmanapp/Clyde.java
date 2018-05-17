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
public class Clyde implements Sprite {
    
    protected int x, y, sx, sy, w, h;
    protected boolean dead = false;
    protected String[] arrPics = {
        "Clyde.png"
    };
    protected int picIdx = 0;
    protected boolean bounced = false;
    protected Pacman player;
    protected Map map;

    public Clyde(int x, int y, int sx, int sy, int w, int h, Pacman player, Map map) {
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

    }
}
