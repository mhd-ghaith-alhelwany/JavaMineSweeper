package minesweeper;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Ghaith
 */
public class ConsolePlayer extends Player{
    
    public ConsolePlayer(Color color, Score score, PlayerStatus playerStatus, int sheild) {
        super(color, score, playerStatus, sheild);
    }

    @Override
    public PlayerMove pickSquare(int length, int width){
        Scanner scn = new Scanner(System.in);
        int i = 0, j = 0, k = 0;
        try{
            i = scn.nextInt();
            j = scn.next().charAt(0) - 'a';
            k = scn.next().charAt(0);
        }catch(InputMismatchException e){
        }
        
        MoveType type = GameRules.getMoveType((char)k);
        SquarePlace squarePlace = new SquarePlace(i, j);
        
        return new PlayerMove(type, squarePlace);
    }
    
}
