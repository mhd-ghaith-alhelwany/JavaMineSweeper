/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import minesweeper.GameType;
import minesweeper.Mine;
import minesweeper.MoveType;
import minesweeper.PlayerMove;
import minesweeper.SquareStatus;

/**
 *
 * @author Ghaith
 */
public class Square extends JPanel implements MouseListener{
    minesweeper.SquarePlace place;
    minesweeper.Game g;
    JLabel label = new JLabel("");
    public Square(minesweeper.Game g, int i, int j){
        this.g = g;
        this.addMouseListener(this);
        this.setBorder(new LineBorder(Color.BLACK));
        this.place = new minesweeper.SquarePlace(i, j);
        this.setBackground(Color.CYAN);
        this.add(label);
    }
    
    public void changeBackground(){
        switch (g.getGrid().getSquare(place.i, place.j).getSquareStatus()){
            case OPEN:
                if(this.g.getGrid().getSquare(place.i, place.j).getMine() == Mine.EMPTY){
                    this.setBackground(Color.GREEN);
                    label.setText(g.getGrid().getSquare(place.i, place.j).getSurroundingMines() +"");
                }
                else if(this.g.getGrid().getSquare(place.i, place.j).getMine() == Mine.SHEILD){
                    this.setBackground(Color.magenta);
                    label.setText(g.getGrid().getSquare(place.i, place.j).getSurroundingMines() +"");
                }else
                    this.setBackground(Color.RED);
                break;
            case FLAGGED:
                this.setBackground(Color.YELLOW);
                break;
            case CLOSED:
                this.setBackground(Color.CYAN);
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("fuck");
        g.getPlayingPlayer().setPlayerMove(new PlayerMove(e.getButton() == 1? MoveType.OPEN: g.getGrid().getSquare(place.i, place.j).getSquareStatus() == SquareStatus.CLOSED? MoveType.FLAG : MoveType.UNFLAG, place));
        g.MainThread.interrupt();
        g.updateGame();
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
