 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;

/**
 *
 * @author Ghaith
 */
public class Grid extends JPanel{
    public Grid(minesweeper.Game g){
        this.setLayout(new GridLayout(g.getGrid().length, g.getGrid().width));
        this.setBackground(Color.BLACK);
        for(int i = 0; i < g.getGrid().length; i++)
            for(int j = 0; j < g.getGrid().width; j++){
                this.add(new Square(g, i, j));
            }
    }
}
