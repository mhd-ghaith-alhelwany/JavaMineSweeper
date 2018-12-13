package GUI;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import minesweeper.Game;

public class Frame extends JFrame implements Serializable{
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
        
        label = new JLabel("Game started");
        this.add(label, BorderLayout.SOUTH);
        
        JButton saveButton = new JButton("Save");
        JButton quickSaveButton = new JButton("Quick Save");
        
        quickSaveButton.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                g.quickSave();
            }
            public void mousePressed(MouseEvent e){}
            public void mouseReleased(MouseEvent e){}
            public void mouseEntered(MouseEvent e){}
            public void mouseExited(MouseEvent e){}
        });
        
        saveButton.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                ChoosePath c = new ChoosePath();
                g.save(c.getPath());
            }
            public void mousePressed(MouseEvent e){}
            public void mouseReleased(MouseEvent e){}
            public void mouseEntered(MouseEvent e){}
            public void mouseExited(MouseEvent e){}
        });
        JPanel buttons = new JPanel();
        buttons.add(saveButton);
        buttons.add(quickSaveButton);
        this.add(buttons, BorderLayout.NORTH);
        
        this.setVisible(true);
    } 
    public void finishGame(){
        label.setText("Game over");
        this.revalidate();
    }
    public void updateGame(){
        grid.updateGrid();
        label.setText(game.getPlayingPlayer().getColor() + " Player playing. Sheilds: " + game.getPlayingPlayer().getShelid());
        this.revalidate();
    }
}
