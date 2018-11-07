package minesweeper;

/**
 *
 * @author Ghaith
 */
public abstract class Player {
    private Color color;
    private PlayerStatus status;
    private Score score;
    
    public Player(Color color, Score score, PlayerStatus playerStatus){
        this.color = color;
        this.score = score;
        this.status = playerStatus;
    }
    public Color getColor(){
        return this.color;
    }
    public Score getScore(){
        return this.score;
    }
    public PlayerStatus getStatus(){
        return this.status;
    }
    public void setStatus(PlayerStatus playerStatus){
        this.status = playerStatus;
    }
    public abstract PlayerMove pickSquare(int length, int width) throws IllegalSquareName;
}
