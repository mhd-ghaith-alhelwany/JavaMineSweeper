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
    protected GameType gameType;
    protected Player player1, player2;
    protected Grid grid;
    
    public abstract void finishGame();
    public abstract void updateGame();
    public abstract void startGame();
    
    public Game(int length, int width, int mines, GameType gametype){
        this.grid = new Grid(length, width, mines);
        if(this instanceof ConsoleGame){
            this.player1 = new ConsolePlayer(Color.BLUE, new Score(0), PlayerStatus.WAITING);
            if(gametype == GameType.MULTI_PLAYER) 
                this.player2 = new ConsolePlayer(Color.RED, new Score(0), PlayerStatus.WAITING);
            else if(gametype == GameType.EASY)
                this.player2 = new RandomPlayer(Color.RED, new Score(0), PlayerStatus.WAITING);
        }else{
            this.player1 = new GUIPlayer(Color.BLUE, new Score(0), PlayerStatus.WAITING);
            if(gametype == GameType.MULTI_PLAYER) 
                this.player2 = new GUIPlayer(Color.RED, new Score(0), PlayerStatus.WAITING);
            else if(gametype == GameType.EASY)
                this.player2 = new RandomPlayer(Color.RED, new Score(0), PlayerStatus.WAITING);
        }
        this.start();
    }
    
    public void takeTurn(Player player){
        this.updateGame();
            try{
                PlayerMove player1Move = player.pickSquare(this.grid.length, this.grid.width);
                this.checkPlayerMove(player, player1Move);
                grid.setSquareStatus(player1Move.getSquarePlace().i, player1Move.getSquarePlace().j , player1Move);
                player.getScore().changeScore(GameRules.getScoreChange(player1Move));
            }catch(IllegalSquareName|IllegalMoveException e){
                player.getScore().changeScore(GameRules.getWrongMoveScoreChange());
            }
    }
    public void start(){
        this.startGame();
        while(true){
            this.takeTurn(player1);
            if(this.grid.stopGame()){
                this.finishGame();
                break;
            }
            this.updateGame();
            
            this.takeTurn(player2);
            if(this.grid.stopGame()){
                this.finishGame();
                break;
            }
            this.updateGame();
        }
    }
    
    public void checkPlayerMove(Player player, PlayerMove playerMove){
        playerMove.setPlayer(player);
        if(playerMove.getMoveType() == MoveType.FLAG)
            if(grid.getSquare(playerMove.getSquarePlace().i,playerMove.getSquarePlace().j).getMine() == Mine.MINE) 
                playerMove.setResult(MoveResult.RIGHT);
            else
                playerMove.setResult(MoveResult.WRONG);
        else if(playerMove.getMoveType() == MoveType.UNFLAG)
            if(grid.getSquare(playerMove.getSquarePlace().i,playerMove.getSquarePlace().j).getMine() == Mine.MINE) 
                playerMove.setResult(MoveResult.WRONG);
            else
                playerMove.setResult(MoveResult.RIGHT);
        else
            if(grid.getSquare(playerMove.getSquarePlace().i,playerMove.getSquarePlace().j).getMine() == Mine.MINE) 
                playerMove.setResult(MoveResult.WRONG);
            else
                playerMove.setResult(MoveResult.RIGHT);
    }
}
