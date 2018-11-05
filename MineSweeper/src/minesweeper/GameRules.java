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
    
    public GameRules(){
        scoreRules.put(new Pair<>(MoveType.FLAG, MoveResult.RIGHT), +10);
        scoreRules.put(new Pair<>(MoveType.FLAG, MoveResult.WRONG), -20);
        scoreRules.put(new Pair<>(MoveType.OPEN, MoveResult.RIGHT), +20);
        scoreRules.put(new Pair<>(MoveType.OPEN, MoveResult.WRONG), -100);
        scoreRules.put(new Pair<>(MoveType.UNFLAG, MoveResult.WRONG), -20);
        scoreRules.put(new Pair<>(MoveType.UNFLAG, MoveResult.RIGHT), +30);
    }
    public static int getScoreChange(PlayerMove playerMove){
        return scoreRules.get(new Pair<>(playerMove.moveType, playerMove.moveResult));
    }
}
