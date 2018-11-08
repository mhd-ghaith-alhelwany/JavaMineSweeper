/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;


public class RandomPlayer extends ComputerPlayer {

    public RandomPlayer(Color color, Score score, PlayerStatus playerStatus) {
        super(color, score, playerStatus);
    }

    @Override
    public PlayerMove pickSquare(int length, int width){
        int i = (int)(Math.random() * (length));
        int j = (int)(Math.random() * (width));
        return new PlayerMove(MoveType.OPEN, new SquarePlace(i, j));
    }
    
}
