package minesweeper;

/**
 *
 * @author Ghaith
 */
public class Grid {
    public int length, width, mines;
    private Square grid[][];
    
    public Grid(int length, int width, int mines){
        this.length = length;
        this.width = width;
        this.mines = mines;
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
        while(mines != 0){
            int i = (int)(Math.random() * (this.length));
            int j = (int)(Math.random() * (this.width));
            if(grid[i][j].setMine() == true) mines--;
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
    private void floodFill(int A, int B){
        int moves[] = {0, 1, -1};
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                int a = A + moves[i];
                int b = B + moves[j];
                if(validSquare(a, b) && !visited[a][b] && this.grid[a][b].getMine() == Mine.EMPTY){
                    this.visited[a][b] = true;
                    this.grid[a][b].setSquareState(new PlayerMove(MoveType.OPEN, null));
                    if(this.grid[a][b].getSurroundingMines() == 0)
                        floodFill(a, b);
                }
            }
        }
    }
    
    public boolean setSquareStatus(int i, int j, PlayerMove playerMove){
        boolean done = this.grid[i][j].setSquareState(playerMove);
        if(playerMove.getMoveType() == MoveType.OPEN && done){
            this.visited = new boolean[this.length][this.width];
            this.floodFill(i, j);
        }
        return done;
    }
}
