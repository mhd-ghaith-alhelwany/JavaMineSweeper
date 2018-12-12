/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import logs.FileIO;
import logs.SavedGame;
import minesweeper.GUIGame;
import  minesweeper.GameType;
/**
 *
 * @author Ghaith
 */
public class Settings extends JFrame implements Serializable{
    
//    private int width;
//    private int length;
//    private int mines;
//    private int sheilds;
//    private int defaultSheilds;
//    private int singlePlayer;
//    private GameType type;
    
    public Settings(){
        this.setDefaultCloseOperation(Frame.EXIT_ON_CLOSE);
        this.setSize(480, 360);
        
        JPanel p = new JPanel();
        p.setBorder(new EmptyBorder(20, 20, 20, 20));
        p.setLayout(new GridLayout(7, 1));
        
        JComboBox type = new JComboBox(GameType.values());
        JButton save = new JButton("SAVE");
        
        InputPanel lengthInput = new InputPanel("Length                  ", new JTextField());
        InputPanel widthInput = new InputPanel("Width                    ", new JTextField());
        InputPanel minesInput = new InputPanel("Mines                    ", new JTextField());
        InputPanel sheildsInput = new InputPanel("Sheilds                 ", new JTextField());
        InputPanel defaultSheildsInput = new InputPanel("Default Sheilds   ", new JTextField());
        InputPanel typeInput = new InputPanel("Type                      ", type);
        InputPanel saveInput = new InputPanel("                               ", save);
        
        p.add(lengthInput);
        p.add(widthInput);
        p.add(minesInput);
        p.add(sheildsInput);
        p.add(defaultSheildsInput);
        p.add(typeInput);
        p.add(saveInput);
        
        this.add(p, BorderLayout.CENTER);
        this.setVisible(true);
        
        save.addMouseListener(new MouseListener() {
            public void mousePressed(MouseEvent e) {
                try{
                    int length = Integer.parseInt((String)lengthInput.getValue());
                    int width = Integer.parseInt((String)widthInput.getValue());
                    int mines = Integer.parseInt((String)minesInput.getValue());
                    int sheilds = Integer.parseInt((String)sheildsInput.getValue());
                    int defaultSheilds = Integer.parseInt((String)defaultSheildsInput.getValue());
                    GameType type = (GameType)typeInput.getValue();
                    save(length, width, mines, sheilds, defaultSheilds, type);
                }catch(Exception ex){
                    System.out.println("error");
                }
            }
            public void mouseClicked(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        });
    }
    
    public void save(int length, int width, int mines, int sheilds, int defaultSheilds, GameType type){
        //GUIGame Game = new GUIGame(length, width, mines, sheilds, defaultSheilds, type);
        new GUIGame(10, 10, 10, 10, 2,  GameType.EASY);
        this.dispose();
    }
    
    public class InputPanel extends JPanel{
        Component c;
        public InputPanel(String label, Component c){
            this.c = c;
            this.setLayout(new BorderLayout());
            this.setBorder(new EmptyBorder(10, 0, 0, 0));
            JLabel l = new JLabel(label, SwingConstants.CENTER);
            
            Box b = Box.createHorizontalBox();
            b.add(l);
            b.add(c);
            
            this.add(b, BorderLayout.CENTER);
            this.setVisible(true);
        }
        public Object getValue(){
            if(c instanceof JTextField){
                JTextField t = (JTextField)c;
                return t.getText();
            }else if(c instanceof JComboBox){
                JComboBox t = (JComboBox)c;
                return t.getSelectedItem();
            }
            return null;
        }
    }
}