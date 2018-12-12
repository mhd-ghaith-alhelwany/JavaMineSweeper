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
public class PlayerMove implements Serializable{
    private int time;
    private MoveResult moveResult;
    private MoveType moveType;
    private SquarePlace squarePlace;
    private Player player;
    
    public PlayerMove(MoveResult moveResult, MoveType moveType, SquarePlace squarePlace, Player player){
        this.moveResult = moveResult;
        this.moveType = moveType;
        this.squarePlace = squarePlace;
        this.player = player;
    }
    
    public PlayerMove(MoveType moveType, SquarePlace squarePlace){
        this.moveType = moveType;
        this.squarePlace = squarePlace;
    }
    
    public void setResult(MoveResult moveResult){
        this.moveResult = moveResult;
    }
    public void setPlayer(Player player){
        this.player = player;
    }
    
    public MoveType getMoveType(){
        return this.moveType;
    }
    
    public MoveResult getMoveResult(){
        return this.moveResult;
    }
    public SquarePlace getSquarePlace(){
        return this.squarePlace;
    }
    public Player getPlayer(){
        return this.player;
    }
    public void setTime(int time){
        this.time = time;
    }
    public int getTime(){
        return this.time;
    }
}
