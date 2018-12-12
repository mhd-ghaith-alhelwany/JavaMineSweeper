/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import logs.FileIO;
import logs.SavedGame;

/**
 *
 * @author Ghaith
 */
public class MineSweeper {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //new GUI.Settings();
       // new GUIGame(10, 10, 10, 10, 2,  GameType.EASY);
        SavedGame g = FileIO.read("D://savedGame.bin");
        new GUIGame(g);
    }
    
}
    