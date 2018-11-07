package minesweeper;

import java.util.HashMap;
import java.util.Map;
import javafx.util.Pair;

/**
 *
 * @author Ghaith
 */
public class GameRules {
    private static final Map<Pair<MoveType, MoveResult>, Integer> scoreRules = new HashMap<Pair<MoveType, MoveResult>, Integer>();
    private static final Map<Pair<SquareStatus, Mine>, Integer> printChars = new HashMap<Pair<SquareStatus, Mine>, Integer>();
    
    static{
        scoreRules.put(new Pair<>(MoveType.FLAG, MoveResult.RIGHT), +10);
        scoreRules.put(new Pair<>(MoveType.FLAG, MoveResult.WRONG), -20);
        scoreRules.put(new Pair<>(MoveType.OPEN, MoveResult.RIGHT), +20);
        scoreRules.put(new Pair<>(MoveType.OPEN, MoveResult.WRONG), -100);
        scoreRules.put(new Pair<>(MoveType.UNFLAG, MoveResult.WRONG), -20);
        scoreRules.put(new Pair<>(MoveType.UNFLAG, MoveResult.RIGHT), +30);
        
        printChars.put(new Pair<>(SquareStatus.CLOSED, Mine.MINE), (int)'-');
        printChars.put(new Pair<>(SquareStatus.CLOSED, Mine.EMPTY), (int)'-');
        printChars.put(new Pair<>(SquareStatus.FLAGGED, Mine.EMPTY), (int)'!');
        printChars.put(new Pair<>(SquareStatus.FLAGGED, Mine.MINE), (int)'!');
        printChars.put(new Pair<>(SquareStatus.OPEN, Mine.MINE), (int)'*');
        printChars.put(new Pair<>(SquareStatus.OPEN, Mine.EMPTY), 0);
    }
    
    public static char getPrintChar(SquareStatus squareStatus, Mine mine){
        int c = printChars.get(new Pair<>(squareStatus, mine));
        return (char)c;
    }
    public static MoveType getMoveType(char c){
        switch (c) {
            case 'f':
                return MoveType.FLAG;
            case 'u':
                return MoveType.UNFLAG;
            default:
                return MoveType.OPEN;
        }
    }
    public static int getWrongMoveScoreChange(){
        return -50;
    }
    public static int getScoreChange(PlayerMove playerMove){
        return scoreRules.get(new Pair<>(playerMove.getMoveType(), playerMove.getMoveResult()));
    }
}
