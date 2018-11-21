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
        System.out.println("while");
        try{
            Thread.sleep(2000);
        }catch(Exception e){
            System.out.println("interrupted");
        }
        PlayerMove playerMove = this.playerMove;
        this.playerMove = null;
        return playerMove;
    }
    
}
