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
public class GUIPlayer extends Player{

    public GUIPlayer(Color color, Score score, PlayerStatus playerStatus) {
        super(color, score, playerStatus);
    }

    @Override
    public PlayerMove pickSquare(int length, int width) throws IllegalSquareName {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
