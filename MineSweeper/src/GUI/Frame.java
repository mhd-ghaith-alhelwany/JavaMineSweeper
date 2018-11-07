package GUI;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import minesweeper.Game;

public class Frame extends JFrame{
    public Frame (Game g){
        this.setDefaultCloseOperation(Frame.EXIT_ON_CLOSE);
        this.setSize(g.getGrid().width * 60, g.getGrid().length * 60);
        this.setLayout(new BorderLayout());
        this.add(new Grid(g), BorderLayout.CENTER);
        this.add(new JLabel("FUCK YOU SHARIF"), BorderLayout.SOUTH);
        this.setVisible(true);
    }
}
