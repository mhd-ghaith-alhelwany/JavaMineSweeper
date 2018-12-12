/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logs;

import java.io.Serializable;
import minesweeper.*;

/**
 *
 * @author Ghaith
 */
public class SavedGame implements Serializable{
    public PlayerMoveLogVector vector;
    public GameType gameType;
    public int defualtSheilds;
    public Grid g;
    public SavedGame(PlayerMoveLogVector vector, int d, Grid g, GameType t){
        this.g = g;
        this.gameType = t;
        this.vector = vector;
        this.defualtSheilds = d;
    }
}
