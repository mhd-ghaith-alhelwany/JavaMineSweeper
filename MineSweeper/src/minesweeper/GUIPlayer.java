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
    
    public GUIPlayer(Color color, Score score, PlayerStatus playerStatus, int sheild) {
        super(color, score, playerStatus, sheild);
    }

    @Override
    public PlayerMove pickSquare(int length, int width){
        try{
            Thread.sleep(5000);
        }catch(Exception e){
            return this.getPlayerMove();
        }
        return null;
    }
    
}
