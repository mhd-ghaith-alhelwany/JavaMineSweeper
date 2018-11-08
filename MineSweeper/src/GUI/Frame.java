package GUI;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import minesweeper.Game;

public class Frame extends JFrame{
    Grid grid;
    JLabel label;
    Game game;
    public Frame (Game g){
        this.game = g;
        this.setDefaultCloseOperation(Frame.EXIT_ON_CLOSE);
        this.setSize(g.getGrid().width * 60, g.getGrid().length * 60);
        this.setLayout(new BorderLayout());
        this.grid = new Grid(g);
        this.add(grid, BorderLayout.CENTER);
        label = new JLabel("Current: 0| Waiting: 0");
        this.add(label, BorderLayout.SOUTH);
        this.setVisible(true);
    } 
    public void finishGame(){
        label.setText("Game over");
        this.revalidate();
    }
    public void updateGame(){
        grid.updateGrid();
        label.setText("Current: " + game.getPlayingPlayer().getScore().getScore() + "| Waiting: " + game.getWaitingPlayer().getScore().getScore());
        this.revalidate();
    }
}
