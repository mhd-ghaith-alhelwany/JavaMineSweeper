package minesweeper;

import java.io.Serializable;

/**
 *
 * @author Ghaith
 */
public abstract class Player 
{
    private Color color;
    private PlayerStatus status;
    private Score score;
    private int sheild;
    private PlayerMove playerMove;
    
    public Player(Color color, Score score, PlayerStatus playerStatus, int sheild){
        this.color = color;
        this.score = score;
        this.status = playerStatus;
        this.sheild = sheild;
        this.playerMove = null;
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
    public int getShelid(){
        return this.sheild;
    }
    public void useSheild(){
        this.sheild--;
    }
    public void addSheild(){
        this.sheild++;
    }
    public void setPlayerMove(PlayerMove playerMove){
        this.playerMove = playerMove;
    }
    public PlayerMove getPlayerMove(){
        return this.playerMove;
    }
    
    public abstract PlayerMove pickSquare(int length, int width);
}
