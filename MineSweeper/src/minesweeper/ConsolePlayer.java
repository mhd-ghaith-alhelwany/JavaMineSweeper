package minesweeper;

import java.util.Scanner;

/**
 *
 * @author Ghaith
 */
public class ConsolePlayer extends Player{
    public Scanner scn = new Scanner(System.in);
    
    public ConsolePlayer(Color color, Score score, PlayerStatus playerStatus) {
        super(color, score, playerStatus);
    }

    @Override
    public PlayerMove pickSquare() {
        int i = scn.nextInt();
        int j = scn.next().charAt(0) - 'a';
        int k = scn.next().charAt(0);
        
        MoveType type = GameRules.getMoveType((char)k);
        SquarePlace squarePlace = new SquarePlace(i, j);
        
        return new PlayerMove(type, squarePlace);
    }
    
}
