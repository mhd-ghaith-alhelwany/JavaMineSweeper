package minesweeper;

/**
 *
 * @author Ghaith
 */
public class Score {
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
