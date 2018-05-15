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
public class Clyde extends EnemySprite {
    
    public Clyde(int x, int y, int sx, int sy) {
        super(x, y, sx, sy);
        super.setPic(0);
    }
    
    @Override
    protected int AI(){
        return 3;
    }
}
