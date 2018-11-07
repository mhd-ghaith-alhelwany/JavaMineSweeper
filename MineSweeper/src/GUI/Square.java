/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author Ghaith
 */
public class Square extends JPanel implements MouseListener{
    minesweeper.SquarePlace place;
    minesweeper.Game g;
    public Square(minesweeper.Game g, int i, int j){
        this.g = g;
        this.place = new minesweeper.SquarePlace(i, j);
        this.setBorder(new LineBorder(Color.BLACK));
        this.setBackground(Color.BLUE);
        this.addMouseListener(this);
    }
    
    public void changeColor(){
        this.setBackground(Color.RED);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        g.takeTurn();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
