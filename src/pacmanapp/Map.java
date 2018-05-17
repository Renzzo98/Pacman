/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanapp;

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
public class Map {

    int w;
    int h;
    MapTile[][] map = new MapTile[20][20];

    public Map(int w, int h) {
        this.w = w;
        this.h = h;
    }

    public void loadMap() {
        FileReader filereader = null;
        int xCoord = 0;
        int yCoord = 0;
        int mapX = 0;
        int mapY = 0;
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
                            MapTile mt = new MapTile(xCoord, yCoord, 50, 50, wall);
                            this.map[mapX][mapY] = mt;

                        } else {
                            PacDot dot = new PacDot(xCoord, yCoord, 50, 50);
                            MapTile mt = new MapTile(xCoord, yCoord, 50, 50, dot);
                            this.map[mapX][mapY] = mt;
                        }
                        xCoord += 50;
                        mapX += 1;
                    }
                    xCoord = 0;
                    mapX = 0;
                    yCoord += 50;
                    mapY += 1;
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

    public MapTile[] getNeighbors(int x, int y) {
        MapTile[] neighbors = new MapTile[4];
        ///Up is 0, Right is 1, Down is 2, Left is 3

        if (y != 0) {
            neighbors[0] = map[x][y - 1];
        }
        else{
            neighbors[0] = map[x][0];
        }
        neighbors[1] = map[x + 1][y];
        neighbors[2] = map[x][y + 1];
        if (x != 0) {
            neighbors[3] = map[x - 1][y];
        }
         else{
            neighbors[3] = map[x][0];
        }
        

        return neighbors;
    }

}
