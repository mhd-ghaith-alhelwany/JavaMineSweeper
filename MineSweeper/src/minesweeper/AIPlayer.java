/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;


public class AIPlayer extends ComputerPlayer {

    public AIPlayer(Color color, Score score, PlayerStatus playerStatus, int sheilds) {
        super(color, score, playerStatus, sheilds);
    }

    @Override
    public PlayerMove pickSquare(int length, int width) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
