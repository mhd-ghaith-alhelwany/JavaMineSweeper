/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

/**
 *
 * @author Ghaith
 */
public abstract class ComputerPlayer extends Player{
    
    public ComputerPlayer(Color color, Score score, PlayerStatus playerStatus) {
        super(color, score, playerStatus);
    }
    
}
