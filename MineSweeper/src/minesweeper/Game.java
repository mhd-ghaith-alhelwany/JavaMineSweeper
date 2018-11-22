/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

/**
 *
 * @author Ghaith
 */
public abstract class Game {
    public GameType gameType;
    protected Player player1, player2;
    protected Grid grid;
    public Thread MainThread;
    public Grid getGrid(){
        return this.grid;
    }
    
    public abstract void finishGame();
    public abstract void updateGame();
    
    public Game(int length, int width, int mines, int sheilds, int sheildsForPlayer, GameType gametype){
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
                this.player2 = new AIPlayer(Color.RED, new Score(0), PlayerStatus.WAITING, sheildsForPlayer);
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
            return;
        }
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
        this.start();
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
}
