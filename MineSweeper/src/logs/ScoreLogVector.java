/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logs;

import java.io.Serializable;
import java.util.Vector;

/**
 *
 * @author Ghaith
 */
public class ScoreLogVector implements Serializable{
    public Vector<ScoreLog> vector;
    public ScoreLogVector(){
        vector = new Vector<ScoreLog>();
    }
}
