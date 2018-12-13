/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logs;

import java.io.Serializable;

/**
 *
 * @author Ghaith
 */
public class ScoreLog implements Serializable{
    public int score;
    public String name;
    public ScoreLog(int score, String name){
        this.score = score;
        this.name = name;
    }
}
