/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import logs.FileIO;
import logs.PlayerMoveLog;
import logs.PlayerMoveLogVector;
import logs.SavedGame;

/**
 *
 * @author Ghaith
 */
public abstract class Game  implements Serializable{
    public GameType gameType;
    protected Player player1, player2;
    protected Grid grid;
    public Thread MainThread;
    public int defaultSheilds;
    private PlayerMoveLogVector vector;
    public Grid getGrid(){
        return this.grid;
    }
    
    public abstract void finishGame();
    public abstract void updateGame();
    
    public Game(int length, int width, int mines, int sheilds, int sheildsForPlayer, GameType gametype){
        this.defaultSheilds = sheildsForPlayer;
        this.vector = new PlayerMoveLogVector();
        
        this.MainThread = Thread.currentThread();
        this.gameType = gametype;
        this.grid = new Grid(length, width, mines, sheilds);
        if(this instanceof ConsoleGame){
            this.player1 = new ConsolePlayer(Color.BLUE, new Score(0), PlayerStatus.PLAYING, sheildsForPlayer);
            if(gametype == GameType.MULTI_PLAYER) 
                this.player2 = new ConsolePlayer(Color.RED, new Score(0), PlayerStatus.WAITING, sheildsForPlayer);
            else if(gametype == GameType.EASY)
                this.player2 = new RandomPlayer(Color.RED, new Score(0), PlayerStatus.WAITING, sheildsForPlayer);
            else
                this.player2 = new RandomPlayer(Color.RED, new Score(0), PlayerStatus.WAITING, sheildsForPlayer);
        }else{
            this.player1 = new GUIPlayer(Color.BLUE, new Score(0), PlayerStatus.PLAYING, sheildsForPlayer);
            if(gametype == GameType.MULTI_PLAYER) 
                this.player2 = new GUIPlayer(Color.RED, new Score(0), PlayerStatus.WAITING, sheildsForPlayer);
            else if(gametype == GameType.EASY)
                this.player2 = new RandomPlayer(Color.RED, new Score(0), PlayerStatus.WAITING, sheildsForPlayer);
            else
                this.player2 = new RandomPlayer(Color.RED, new Score(0), PlayerStatus.WAITING, sheildsForPlayer);
        }
        this.start();
        this.play();
    }
    
    public Game(SavedGame s){
        this.vector = new PlayerMoveLogVector();
        this.defaultSheilds = s.defualtSheilds;
        this.MainThread = Thread.currentThread();
        this.gameType = s.gameType;
        this.grid = new Grid(s.g);
        
        if(this instanceof ConsoleGame){
            this.player1 = new ConsolePlayer(Color.BLUE, new Score(0), PlayerStatus.PLAYING, defaultSheilds);
            if(gameType == GameType.MULTI_PLAYER) 
                this.player2 = new ConsolePlayer(Color.RED, new Score(0), PlayerStatus.WAITING, defaultSheilds);
            else if(gameType == GameType.EASY)
                this.player2 = new RandomPlayer(Color.RED, new Score(0), PlayerStatus.WAITING, defaultSheilds);
            else
                this.player2 = new RandomPlayer(Color.RED, new Score(0), PlayerStatus.WAITING, defaultSheilds);
        }else{
            this.player1 = new GUIPlayer(Color.BLUE, new Score(0), PlayerStatus.PLAYING, defaultSheilds);
            if(gameType == GameType.MULTI_PLAYER) 
                this.player2 = new GUIPlayer(Color.RED, new Score(0), PlayerStatus.WAITING, defaultSheilds);
            else if(gameType == GameType.EASY)
                this.player2 = new RandomPlayer(Color.RED, new Score(0), PlayerStatus.WAITING, defaultSheilds);
            else
                this.player2 = new RandomPlayer(Color.RED, new Score(0), PlayerStatus.WAITING, defaultSheilds);
        }
        this.start();
        System.out.println(s.vector.getVector().size());
        for (int i = 0; i < s.vector.getVector().size(); i++){
            PlayerMoveLog playerMoveLog = s.vector.getVector().get(i);
            try {
                Thread.sleep(playerMoveLog.time);
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.takeTurn(new PlayerMove(playerMoveLog.moveType, playerMoveLog.squarePlace));
            this.switchPlayers();
        }
        this.play();
    }
    
    public Player getPlayingPlayer(){
        if(this.player1.getStatus() == PlayerStatus.PLAYING)
            return player1;
        else 
            return player2;
    }
    public Player getWaitingPlayer(){
        if(this.player1.getStatus() == PlayerStatus.WAITING)
            return player1;
        else 
            return player2;
    }
    public void switchPlayers(){
        if(this.player1.getStatus() == PlayerStatus.WAITING){
            this.player1.setStatus(PlayerStatus.PLAYING);
            this.player2.setStatus(PlayerStatus.WAITING);
        }else{
            this.player1.setStatus(PlayerStatus.WAITING);
            this.player2.setStatus(PlayerStatus.PLAYING);
        }
    }
    public void takeTurn(PlayerMove playerMove){
        if(playerMove == null){
            this.updateGame();
            vector.add(new PlayerMoveLog(null, null));
            return;
        }
        
        PlayerMoveLog log = new PlayerMoveLog(playerMove.getMoveType(), playerMove.getSquarePlace());
        vector.add(log);
        
        Player playingPlayer = this.getPlayingPlayer();
        playerMove.setPlayer(playingPlayer);
        try{
            if(
                playerMove.getSquarePlace().i < 0 ||
                playerMove.getSquarePlace().i >= this.grid.length ||
                playerMove.getSquarePlace().j < 0 ||
                playerMove.getSquarePlace().j >= this.grid.width
               ) throw new IllegalSquareName();
            this.checkPlayerMove(playingPlayer, playerMove);
            grid.setSquareStatus(playerMove.getSquarePlace().i, playerMove.getSquarePlace().j , playerMove);
            playingPlayer.getScore().changeScore(GameRules.getScoreChange(playerMove));
        }catch(IllegalMoveException | IllegalSquareName e){
            playingPlayer.getScore().changeScore(GameRules.getWrongMoveScoreChange());
        }
        this.updateGame();
    }
    
    public abstract void start();
    
    public void play(){
        while(true){
            this.takeTurn(getPlayingPlayer().pickSquare(grid.length, grid.width));
            this.switchPlayers();
        }
    }
    public void checkPlayerMove(Player player, PlayerMove playerMove){
        playerMove.setPlayer(player);
        if(playerMove.getMoveType() == MoveType.FLAG)
            if(grid.getSquare(playerMove.getSquarePlace().i,playerMove.getSquarePlace().j).getMine() == Mine.MINE) 
                playerMove.setResult(MoveResult.RIGHT);
            else{
                if(player.getShelid() > 0){
                    playerMove.setResult(MoveResult.SHEILDED);
                    player.useSheild();
                }else{
                    playerMove.setResult(MoveResult.WRONG);
                }
                
            }
        else if(playerMove.getMoveType() == MoveType.UNFLAG)
            if(grid.getSquare(playerMove.getSquarePlace().i,playerMove.getSquarePlace().j).getMine() == Mine.MINE) {
                if(player.getShelid() > 0){
                    playerMove.setResult(MoveResult.SHEILDED);
                    player.useSheild();
                }else{
                    playerMove.setResult(MoveResult.WRONG);
                }
            }
            else
                playerMove.setResult(MoveResult.RIGHT);
        else
            if(grid.getSquare(playerMove.getSquarePlace().i,playerMove.getSquarePlace().j).getMine() == Mine.MINE) {
                if(player.getShelid() > 0){
                    playerMove.setResult(MoveResult.SHEILDED);
                    player.useSheild();
                }else{
                    playerMove.setResult(MoveResult.WRONG);
                }
            }
            else
                playerMove.setResult(MoveResult.RIGHT);
    }
    
    public void save(){
        FileIO.write("savedGame", new SavedGame(vector, this.defaultSheilds, this.grid, this.gameType));
    }
}
