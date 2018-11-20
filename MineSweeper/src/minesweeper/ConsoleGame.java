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
public class ConsoleGame extends Game{

    public ConsoleGame(int length, int width, int mines, int sheildsForPlayer, int sheilds, GameType gametype) {
        super(length, width, mines, sheilds, sheildsForPlayer, gametype);
    }

    @Override
    public void updateGame() {
        System.out.print("--");
        for(int i = 0; i < grid.width; i++) System.out.print((char)(i + 'a'));
        System.out.println("");
        for(int i = 0; i < grid.length; i++){
            System.out.print(i + "|");
            for(int j = 0; j < grid.width; j++){
                Square square = grid.getSquare(i, j);
                char c = GameRules.getPrintChar(square.getSquareStatus(), square.getMine());
                if(c == 0) System.out.print(square.getSurroundingMines());
                else System.out.print(c);
            }
            System.out.println("");
        }
        System.out.println("");
    }

    @Override
    public void finishGame() {
        System.out.println("Game over");
        System.out.print("Player 1: ");
        System.out.println(player1.getScore().getScore());
        System.out.print("Player 2: ");
        System.out.println(player2.getScore().getScore());
        
    }
    
    @Override
    public void start() {
        while(true){
            this.updateGame();
            PlayerMove playerMove = this.getPlayingPlayer().pickSquare(this.grid.length, this.grid.width);
            this.takeTurn(playerMove);
            if(this.grid.stopGame()){
                this.finishGame();
                break;
            }
        }
    }
    
}
