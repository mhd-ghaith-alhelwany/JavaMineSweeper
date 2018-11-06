package minesweeper;

import java.util.InputMismatchException;
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
        int i = 0, j = 0, k = 0;
        try{
            i = scn.nextInt();
            j = scn.next().charAt(0) - 'a';
            k = scn.next().charAt(0);
        }catch(InputMismatchException e){
            return new PlayerMove(MoveType.OPEN, new SquarePlace(-1, -1));
        }
        
        MoveType type = GameRules.getMoveType((char)k);
        SquarePlace squarePlace = new SquarePlace(i, j);
        
        return new PlayerMove(type, squarePlace);
    }
    
}
