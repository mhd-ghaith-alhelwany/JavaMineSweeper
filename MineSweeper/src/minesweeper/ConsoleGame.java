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

    public ConsoleGame(int length, int width, int mines, GameType gametype) {
        super(length, width, mines, gametype);
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
    
}
