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
    
    public void setEmpty(){
        this.mine = Mine.EMPTY;
    }
    
    public boolean setMine(){
        if(this.mine == Mine.MINE || this.mine == Mine.SHEILD) return false;
        else this.mine = Mine.MINE;
        return true;
    }
    
    public boolean setSheild(){
        if(this.mine == Mine.MINE || this.mine == Mine.SHEILD) return false;
        else this.mine = Mine.SHEILD;
        return true;
    }
    
    public void setSquareState(PlayerMove playerMove) throws IllegalMoveException{
        if(playerMove.getMoveType() == MoveType.FLAG)
            if(this.squareStatus == SquareStatus.CLOSED){
                this.squareStatus = SquareStatus.FLAGGED;
                return;
            }else throw new IllegalMoveException();
        else if(playerMove.getMoveType() == MoveType.UNFLAG)
            if(this.squareStatus == SquareStatus.FLAGGED){
                this.squareStatus = SquareStatus.CLOSED;
                return;
            }else throw new IllegalMoveException();
        else
            if(this.squareStatus == SquareStatus.CLOSED){
                this.squareStatus = SquareStatus.OPEN;
                return;
            }else throw new IllegalMoveException();
    }
    
    public Mine getMine(){
        return this.mine;
    }
    
}
