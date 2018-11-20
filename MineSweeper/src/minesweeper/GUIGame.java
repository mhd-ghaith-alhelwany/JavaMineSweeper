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
    Frame f;
    public GUIGame(int length, int width, int mines, int sheilds, int sheildsForPlayer, GameType gametype) {
        super(length, width, mines, sheilds, sheildsForPlayer, gametype);
    }

    @Override
    public void updateGame() {
        f.updateGame();
        if(this.grid.stopGame()) f.finishGame();
    }

    @Override
    public void finishGame() {
        
    }

    @Override
    public void start() {
        this.f = new Frame(this);
    }
    
}
