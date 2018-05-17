/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanapp;

import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author csc190
 */
public class GameEngine {

    protected Pacman playerPacman;
    protected ArrayList<Sprite> arrSprites = new ArrayList<Sprite>();
    protected ArrayList<Sprite> arrMapTiles = new ArrayList<Sprite>(); ///ADDED new arraylist for map tiles
    protected Map map = new Map(1000, 1000);
    protected Score HS = new Score();

    public void loadMap() {
        map.loadMap(); ////creates grid system using Map class
        FileReader filereader = null;
        int xCoord = 0;
        int yCoord = 0;
        try {
            String mapFile = "images/map.txt";
            String line = null;
            filereader = new FileReader(mapFile);
            BufferedReader bufferReader = new BufferedReader(filereader);
            try {
                while ((line = bufferReader.readLine()) != null) {
                    for (int i = 0; i < line.length(); i++) {
                        char c = line.charAt(i);
                        if (c == 'W') {
                            Wall wall = new Wall(xCoord, yCoord, 50, 50);
                            this.register(wall);
                            wall.draw(api);
                        } else if (c == 'P') {
                            Pacman man1 = new Pacman(xCoord + 5, yCoord + 5, 0, -1, 40, 40);
                            this.register(man1);
                            this.playerPacman = man1;
                            man1.draw(api);
                        } else if (c == '_') {
                            PacDot pd = new PacDot(xCoord + 20, yCoord + 20, 15, 15);
                            this.register(pd);
                            pd.draw(api);
                        } else if (c == 'G') {
                            Pinky ghost1 = new Pinky(xCoord + 5, yCoord + 5, 0, 0, 35, 35, this.playerPacman, this.map);
                            this.register(ghost1);
                            ghost1.draw(api);
                        } else if (c == 'B'){
                            Blinky ghost2 = new Blinky(xCoord + 5, yCoord + 5, 0, 0, 35, 35, this.playerPacman, this.map);
                            this.register(ghost2);
                            ghost2.draw(api);
                        }else if (c == 'C'){
                            Clyde ghost3 = new Clyde(xCoord + 5, yCoord + 5, 0, 0, 35, 35, this.playerPacman, this.map);
                            this.register(ghost3);
                            ghost3.draw(api);
                        }else if (c == 'I'){
                            Inky ghost4 = new Inky(xCoord + 5, yCoord + 5, 0, 0, 35, 35, this.playerPacman, this.map);
                            this.register(ghost4);
                            ghost4.draw(api);
                        }else if (c == 'F'){
                            Fruit fr = new Fruit(xCoord+5, yCoord+5, 35, 35);
                            this.register(fr);
                            fr.draw(api);
                        }
                        xCoord += 50;
                    }
                    xCoord = 0;
                    yCoord += 50;
                }
            } catch (IOException ex) {
                Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                filereader.close();
            } catch (IOException ex) {
                Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void register(Sprite s) {
        if (s instanceof Wall) {
            this.arrMapTiles.add(s);
        } else {
            this.arrSprites.add(s);
        }
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
        for (Sprite s : this.arrMapTiles) {
            s.draw(api);
        }
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
        MapTile[] mts = map.getNeighbors(this.playerPacman.getMapX(), this.playerPacman.getMapY());
        boolean collisionDetect = false;
        
               /* for (Sprite s : this.arrMapTiles) {
                    if (this.isCollapse(s.getX(), s.getY(), s.getW(), s.getH(), this.playerPacman.getX(), this.playerPacman.getY(), this.playerPacman.getW(), this.playerPacman.getH())) {
                        
                        if (mts[0].s instanceof Wall) {
                            if (p1.bounced == false) {
                                p1.sx = 0;
                                p1.sy = 0;
                                p1.y = p1.y + 2;
                                p1.bounced = true;
                            }
                        }
                        if (mts[1].s instanceof Wall) {
                            if (p1.bounced == false) {
                                p1.sx = 0;
                                p1.sy = 0;
                                p1.x = p1.x - 2;
                                p1.bounced = true;
                            }
                        }
                        if (mts[2].s instanceof Wall) {
                            if (p1.bounced == false) {
                                p1.sx = 0;
                                p1.sy = 0;
                                p1.y = p1.y - 2;
                                p1.bounced = true;
                            }
                        }
                        if (mts[3].s instanceof Wall) {
                            if (p1.bounced == false) {
                                p1.sx = 0;
                                p1.sy = 0;
                                p1.x = p1.x + 2;
                                p1.bounced = true;
                            }
                        }
                    }
                }
            */
        switch (key) {
            case UP:
                if (!(mts[0].s instanceof Wall)) {
                    this.playerPacman.setDirection(0, -1);
                    this.playerPacman.bounced = false;
                }
                break;

            case DOWN:
                if (!(mts[2].s instanceof Wall)) {
                    this.playerPacman.setDirection(0, 1);
                    this.playerPacman.bounced = false;
                }
                break;

            case LEFT:
                if (!(mts[3].s instanceof Wall)) {
                    this.playerPacman.setDirection(-1, 0);
                    this.playerPacman.bounced = false;
                }
                break;

            case RIGHT:
                if (!(mts[1].s instanceof Wall)) {
                    this.playerPacman.setDirection(1, 0);
                    this.playerPacman.bounced = false;
                }
                break;
        }
    }

    protected void collisionDetect() {
        for (Sprite s1 : this.arrSprites) {
            for (Sprite s2 : this.arrSprites) {
                if (s1 != s2) {
                    if (isCollapse(s1.getX(), s1.getY(), s1.getW(), s1.getH(), s2.getX(), s2.getY(), s2.getW(), s2.getH())) {
                        if (s1 instanceof Pacman && s2 instanceof PacDot) {
                            Pacman p = (Pacman) s1;
                            PacDot d = (PacDot) s2;
                            d.isEaten(HS);
                        } else if (s1 instanceof PacDot && s2 instanceof Pacman) {
                            Pacman p = (Pacman) s2;
                            PacDot d = (PacDot) s1;
                            d.eaten = true;
                        } else if (s1 instanceof Pacman && s2 instanceof Pacman) {
                            Pacman p1 = (Pacman) s1;
                            Pacman p2 = (Pacman) s2;
                            p1.dead = true;
                            p2.dead = true;
                        } else if (s1 instanceof PacDot && s2 instanceof PacDot) {
                            PacDot d1 = (PacDot) s1;
                            PacDot d2 = (PacDot) s2;
                        } else if (s1 instanceof Pacman && s2 instanceof Fruit)
                        {
                            Pacman p1 = (Pacman) s1;
                            Fruit f1 = (Fruit) s2;
                            f1.isEaten(HS);
                        }
                    }
                }
            }
            if (s1 instanceof Pacman) {
                for (Sprite s : this.arrMapTiles) {
                    if (isCollapse(s.getX(), s.getY(), s.getW(), s.getH(), s1.getX(), s1.getY(), s1.getW(), s1.getH())) {
                        Pacman p1 = (Pacman) s1;
                        MapTile[] mts = map.getNeighbors(p1.getMapX(), p1.getMapY());
                        if (mts[0].s instanceof Wall) {
                            if (p1.bounced == false) {
                                p1.sx = 0;
                                p1.sy = 0;
                                p1.y = p1.y + 2;
                                p1.bounced = true;
                            }
                        }
                        if (mts[1].s instanceof Wall) {
                            if (p1.bounced == false) {
                                p1.sx = 0;
                                p1.sy = 0;
                                p1.x = p1.x - 2;
                                p1.bounced = true;
                            }
                        }
                        if (mts[2].s instanceof Wall) {
                            if (p1.bounced == false) {
                                p1.sx = 0;
                                p1.sy = 0;
                                p1.y = p1.y - 2;
                                p1.bounced = true;
                            }
                        }
                        if (mts[3].s instanceof Wall) {
                            if (p1.bounced == false) {
                                p1.sx = 0;
                                p1.sy = 0;
                                p1.x = p1.x + 2;
                                p1.bounced = true;
                            }
                        }
                    }
                }
            }
            if (s1 instanceof Pinky) {
                for (Sprite s : this.arrMapTiles) {
                    if (isCollapse(s.getX(), s.getY(), s.getW(), s.getH(), s1.getX(), s1.getY(), s1.getW(), s1.getH())) {
                        Pinky p1 = (Pinky) s1;
                        MapTile[] mts = map.getNeighbors(p1.getMapX(), p1.getMapY());
                      if (mts[0].s instanceof Wall) {
                            if (p1.bounced == false) {
                                p1.sx = 0;
                                p1.sy = 0;
                                p1.y = p1.y + 2;
                                p1.bounced = true;
                            }
                        }
                        if (mts[1].s instanceof Wall) {
                            if (p1.bounced == false) {
                                p1.sx = 0;
                                p1.sy = 0;
                                p1.x = p1.x - 2;
                                p1.bounced = true;
                            }
                        }
                        if (mts[2].s instanceof Wall) {
                            if (p1.bounced == false) {
                                p1.sx = 0;
                                p1.sy = 0;
                                p1.y = p1.y - 2;
                                p1.bounced = true;
                            }
                        }
                        if (mts[3].s instanceof Wall) {
                            if (p1.bounced == false) {
                                p1.sx = 0;
                                p1.sy = 0;
                                p1.x = p1.x + 2;
                                p1.bounced = true;
                            }
                        }
                    }
                }
            }
        }
    }

    /*protected boolean pointinRect(int x, int y, int x2, int y2, int w, int h) {
        if (x2 <= x && x <= (x2 + w)) {
            if (y2 <= y && y <= (y2 + h)) {
                return true;
            }
        }
        return false;
    }*/
    protected boolean isCollapse(int x, int y, int w, int h, int x2, int y2, int w2, int h2) {
        //boolean lefttop = false, leftbot = false, righttop = false, rightbot = false;
        if (x <= x2 && x + w >= x2 && y <= y2 && y + h >= y2) {
            return true;//lefttope
        }
        if (x <= x2 && x + w >= x2 && y <= y2 + h2 && y + h >= y2 + h2) {
            return true;//leftbot
        }
        if (x <= x2 + w2 && x + w >= x2 + w2 && y <= y2 && y + h >= y2) {
            return true;//righttop
        }
        if (x <= x2 + w2 && x + w >= x2 + w2 && y <= y2 + h2 && y + h >= y2 + h2) {
            return true; //rightbot
        } else {
            return false;
        }

    }

    public int endCondition() {
        return 0;
    }

    protected void updateAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    protected void drawAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
