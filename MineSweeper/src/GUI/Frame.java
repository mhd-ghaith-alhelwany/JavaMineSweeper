package GUI;

import java.awt.GridLayout;
import javax.swing.JFrame;
import minesweeper.Grid;

public class Frame extends JFrame{
    public Frame (int length, int width){
        this.setDefaultCloseOperation(Frame.EXIT_ON_CLOSE);
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.setLayout(new GridLayout(length, width));

        this.setVisible(true);
    }
    
    public void drawGrid(Grid g){
        this.add(new Square());
    }
}
