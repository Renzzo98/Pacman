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
public class EnemySprite extends MovingSprite implements Sprite {
   
    
    protected int AI(){
        return 0;
    }
    
    protected int picIdx = 0;
    
    
    protected String[] arrPics = {"Ghosts/Blinky/Right.png", "Ghosts/Inky/Right.png", "Ghosts/Pinky/Right.png", "Ghosts/Clyde/Right.png"};

    public EnemySprite(int x, int y, int sx, int sy) {
        this.x = x;
        this.y = y;
        this.w = 40;
        this.h = 40;
        this.sx = sx;
        this.sy = sy;
    }

    @Override
    public void update() {
        boolean val = this.setDir(this.AI());
        if(val){
            this.x += this.sx;
            this.y += this.sy;
        }
        //this,draw(this.api);
    }

    public void setDirectionalSpeed(int sx, int sy) {
        this.sx = sx;
        this.sy = sy;
    }

    @Override
    public void draw(API api) {
        String pic = this.arrPics[this.picIdx];
        api.drawImg(pic, x, y, 20, 20);
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public int getW() {
        return this.w;
    }

    @Override
    public int getH() {
        return this.h;
    }

    public void setPic(int num) {
        this.picIdx = num;
    }

    @Override
    public void setX(int x) {
        this.x = x;
        this.setMX();
    }

    @Override
    public void setY(int y) {
        this.y = y;
        this.setMY();

    }

    @Override
    public int getSX() {
        return this.sx;
    }

    @Override
    public int getSY() {
        return this.sy;
    }
    
    public int getMX(){
        return this.mx;
    }
    
    public int getMY(){
        return this.my;
    }
    
    public void setMX(){
       this.my = this.x/50;

    }
    
    public void setMY(){
       this.my = this.y/50;

    }

}

