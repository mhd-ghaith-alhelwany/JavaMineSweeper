package minesweeper;

/**
 *
 * @author Ghaith
 */
public class Grid {
    public int length, width, mines;
    private Square grid[][];
    private int sheilds;
    public Grid(int length, int width, int mines, int sheilds){
        this.length = length;
        this.width = width;
        this.mines = mines;
        this.sheilds = sheilds;
        this.grid = new Square[length][width];
        initializeGrid();
    }
    
    public boolean validSquare(int i, int j){
        if(i < 0) return false;
        if(j < 0) return false;
        if(i >= this.length) return false;
        if(j >= this.width) return false;
        return true;
    }
    
    private void initializeGrid(){
        for(int i = 0; i < this.length; i++){
            for(int j = 0; j < this.width; j++){
                this.grid[i][j] = new Square(i, j, Mine.EMPTY);
            }
        }
        int mines = this.mines;
        int sheilds = this.sheilds;
        
        while(mines != 0){
            int i = (int)(Math.random() * (this.length));
            int j = (int)(Math.random() * (this.width));
            if(grid[i][j].setMine() == true) mines--;
        }
        while(sheilds != 0){
            int i = (int)(Math.random() * (this.length));
            int j = (int)(Math.random() * (this.width));
            if(grid[i][j].setSheild()== true) sheilds--;
        }
        int moves[] = {0, 1, -1};
        for(int i = 0; i < this.length; i++)
            for(int j = 0; j < this.width; j++){
                int count = 0;
                for(int k = 0; k < 3; k++)
                    for(int l = 0; l < 3; l++)
                        if(validSquare(i + moves[k], j + moves[l]))
                            if(grid[i + moves[k]][j + moves[l]].getMine() == Mine.MINE) 
                                count++;
                grid[i][j].setSurroundingMines(count);
            }
    }
    
    public Square getSquare(int i, int j){
        return grid[i][j];
    }
    
    private boolean visited[][];
    private void floodFill(int A, int B, Player p){
        if(this.grid[A][B].getMine() == Mine.SHEILD) p.addSheild();
        this.grid[A][B].setEmpty();
        int moves[] = {0, 1, -1};
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                int a = A + moves[i];
                int b = B + moves[j];
                if(validSquare(a, b) && !visited[a][b] && this.grid[a][b].getMine() != Mine.MINE){
                    this.visited[a][b] = true;
                    try{
                        this.grid[a][b].setSquareState(new PlayerMove(MoveType.OPEN, null));
                    }catch(IllegalMoveException e){}
                    if(this.grid[a][b].getSurroundingMines() == 0)
                        floodFill(a, b, p);
                }
            }
        }
    }
    public boolean stopGame(){
        for(int i = 0; i < this.length; i++)
            for(int j = 0; j < this.width; j++)
                if(this.grid[i][j].getSquareStatus() == SquareStatus.CLOSED)
                    return false;
        return true;
    }
    public void setSquareStatus(int i, int j, PlayerMove playerMove) throws IllegalMoveException{
        this.grid[i][j].setSquareState(playerMove);
        if(playerMove.getMoveType() == MoveType.OPEN && this.getSquare(i, j).getMine() != Mine.MINE && this.getSquare(i, j).getSurroundingMines() == 0){
            
            if(this.grid[i][j].getMine() == Mine.SHEILD) playerMove.getPlayer().addSheild();
            this.grid[i][j].setEmpty();
            
            this.visited = new boolean[this.length][this.width];
            this.floodFill(i, j, playerMove.getPlayer());
        }
    }
}
