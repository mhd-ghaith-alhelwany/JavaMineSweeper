package minesweeper;

/**
 *
 * @author Ghaith
 */
public class Square {
    private SquarePlace squarePlace;
    private Mine mine;
    private SquareStatus squareStatus;
    private int surroundingMines;
    
    public Square(int i, int j, Mine mine){
        this.squarePlace = new SquarePlace(i, j);
        this.mine = mine;
        this.squareStatus = SquareStatus.CLOSED;
    }
    
    public SquareStatus getSquareStatus(){
        return this.squareStatus;
    }
    
    public void setSurroundingMines(int surroundingMines){
        this.surroundingMines = surroundingMines;
    }
    
    public int getSurroundingMines(){
        return this.surroundingMines;
    }
    
    public boolean setMine(){
        if(this.mine == Mine.MINE) return false;
        else this.mine = Mine.MINE;
        return true;
    }
    
    public boolean setSquareState(PlayerMove playerMove){
        if(playerMove.getMoveType() == MoveType.FLAG)
            if(this.squareStatus == SquareStatus.CLOSED){
                this.squareStatus = SquareStatus.FLAGGED;
                return true;
            }else return false;
        else if(playerMove.getMoveType() == MoveType.UNFLAG)
            if(this.squareStatus == SquareStatus.FLAGGED){
                this.squareStatus = SquareStatus.CLOSED;
                return true;
            }else return false;
        else
            if(this.squareStatus == SquareStatus.CLOSED){
                this.squareStatus = SquareStatus.OPEN;
                return true;
            }else return false;
    }
    
    public Mine getMine(){
        return this.mine;
    }
    
}
