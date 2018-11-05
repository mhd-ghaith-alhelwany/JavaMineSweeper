package minesweeper;

/**
 *
 * @author Ghaith
 */
public class ConsolePlayer extends Player{

    public ConsolePlayer(Color color, Score score, PlayerStatus playerStatus) {
        super(color, score, playerStatus);
    }

    @Override
    public SquarePlace pickSquare() {
        return null;
    }
    
}
