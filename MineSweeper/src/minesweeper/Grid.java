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
    
    private boolean validSquare(int i, int j){
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
}
