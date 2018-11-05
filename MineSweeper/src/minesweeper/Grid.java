package minesweeper;

/**
 *
 * @author Ghaith
 */
public class Grid {
    private int length, width, mines;
    private Square grid[][];
    
    public Grid(int length, int width, int mines){
        this.length = length;
        this.width = width;
        this.mines = mines;
        this.grid = new Square[length][width];
        initializeGrid();
    }
    
    private void initializeGrid(){
        int mines = this.mines;
        while(mines != 0){
            int i = (int)(Math.random() * (this.width + 1));
            int j = (int)(Math.random() * (this.length + 1));
            if(grid[i][j].setMine() == true) mines--;
        }
    }
}
