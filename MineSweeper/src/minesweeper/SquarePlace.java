/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import java.io.Serializable;

/**
 *
 * @author Ghaith
 */
public class SquarePlace implements Serializable{
    public int i, j;

    public SquarePlace(int i, int j) {
        this.i = i;
        this.j = j;
    }
    
}
