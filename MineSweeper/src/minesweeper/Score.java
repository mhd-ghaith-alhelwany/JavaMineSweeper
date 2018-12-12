package minesweeper;

import java.io.Serializable;

/**
 *
 * @author Ghaith
 */
public class Score implements Serializable{
    private int score;
    public Score(int score){
        this.score = score;
    }
    public int getScore(){
        return this.score;
    }
    public void changeScore(int change){
        this.score += change;
    }
}
