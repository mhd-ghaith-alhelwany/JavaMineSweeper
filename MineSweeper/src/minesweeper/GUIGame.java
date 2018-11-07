/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import GUI.Frame;

/**
 *
 * @author Ghaith
 */
public class GUIGame extends Game {

    public GUIGame(int length, int width, int mines, GameType gametype) {
        super(length, width, mines, gametype);
    }

    @Override
    public void updateGame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void finishGame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void startGame() {
        Frame f = new Frame(this.grid.length, this.grid.width);
        f.drawGrid(grid);
    }
    
}
