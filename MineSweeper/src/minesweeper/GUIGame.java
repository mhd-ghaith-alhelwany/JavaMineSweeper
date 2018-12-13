/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import GUI.Frame;
import GUI.Settings;
import java.io.Serializable;
import logs.FileIO;
import logs.SavedGame;
import logs.ScoreLog;
import logs.ScoreLogVector;

/**
 *
 * @author Ghaith
 */
public class GUIGame extends Game implements Serializable{
    Frame f;
    public GUIGame(int length, int width, int mines, int sheilds, int sheildsForPlayer, GameType gametype, String name) {
        super(length, width, mines, sheilds, sheildsForPlayer, gametype, name);
    }
    public GUIGame(SavedGame s){
        super(s);
    }

    @Override
    public void updateGame() {
        f.updateGame();
        if(this.grid.stopGame()) f.finishGame();
    }

    @Override
    public void finishGame() {
        ScoreLogVector scoreboard = FileIO.readScoreboard("scoreboard.bin");
        scoreboard.vector.add(new ScoreLog(this.player1.getScore().getScore(), this.name));
        FileIO.writeScoreboard("scoreboard", scoreboard);
    }

    @Override
    public void start() {
        this.f = new Frame(this);
    }
    
}
