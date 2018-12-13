package logs;

import java.io.Serializable;
import minesweeper.MoveType;
import minesweeper.SquarePlace;

/**
 *
 * @author Ghaith
 */

public class PlayerMoveLog implements Serializable{ 
    public MoveType moveType;
    public SquarePlace squarePlace;
    public int time;
    
    public PlayerMoveLog(MoveType moveType, SquarePlace squarePlace){
        this.moveType = moveType;
        this.squarePlace = squarePlace;
    }
}
 