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
public abstract class Game {
    private GameType gameType;
    private Player player1, player2;
    private Grid grid;
    
    public Game(int length, int width, int mines, GameType gametype){
        grid = new Grid(length, width, mines);
    }
    
    public void start(){
        while(true){
            SquarePlace s = player1.pickSquare();
            this.updateGame();
        }
    }
    public abstract void updateGame();
}
