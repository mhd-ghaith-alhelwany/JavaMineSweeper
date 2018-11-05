package minesweeper;

/**
 *
 * @author Ghaith
 */
public class Square {
    private SquarePlace squarePlace;
    private Mine mine;
    private SquareStatus squareStatus;
    
    public Square(int i, int j, Mine mine){
        this.squarePlace = new SquarePlace(i, j);
        this.mine = mine;
        this.squareStatus = squareStatus.CLOSED;
    }
    
    public boolean setMine(){
        if(this.mine == Mine.MINE) return false;
        else this.mine = Mine.MINE;
        return true;
    }
}
