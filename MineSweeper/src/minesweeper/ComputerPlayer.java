/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import java.io.Serializable;

/**
 *
 * @author Ghaith
 */
public abstract class ComputerPlayer extends Player implements Serializable{
    
    public ComputerPlayer(Color color, Score score, PlayerStatus playerStatus, int sheild) {
        super(color, score, playerStatus, sheild);
    }
    
}
