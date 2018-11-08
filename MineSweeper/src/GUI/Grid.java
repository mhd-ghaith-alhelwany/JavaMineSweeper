 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;
import minesweeper.Game;

/**
 *
 * @author Ghaith
 */
public class Grid extends JPanel{
    Square grid[][];
    Game g;
    public Grid(minesweeper.Game g){
        this.g = g;
        grid = new Square[g.getGrid().length][g.getGrid().width];
        this.setLayout(new GridLayout(g.getGrid().length, g.getGrid().width));
        this.setBackground(Color.BLACK);
        for(int i = 0; i < g.getGrid().length; i++)
            for(int j = 0; j < g.getGrid().width; j++){
                grid[i][j] = new Square(g, i, j);
                this.add(grid[i][j]);
            }
    }
    public void updateGrid(){
        for(int i = 0; i < g.getGrid().length; i++)
            for(int j = 0; j < g.getGrid().width; j++){
                this.grid[i][j].changeBackground();
            }
    }
}
