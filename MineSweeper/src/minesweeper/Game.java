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
    
    public Game(int length, int width, int mines, GameType gametype){
        this.grid = new Grid(length, width, mines);
        this.player1 = new ConsolePlayer(Color.BLUE, new Score(0), PlayerStatus.WAITING);
        if(gametype == GameType.MULTI_PLAYER) 
            this.player2 = new ConsolePlayer(Color.RED, new Score(0), PlayerStatus.WAITING);
        this.start();
    }
    
    public void start(){
        while(true){
            this.updateGame();
            PlayerMove player1Move = player1.pickSquare();
            this.checkPlayerMove(player1, player1Move);
            if(player1Move.getMoveResult() != null && grid.setSquareStatus(player1Move.getSquarePlace().i, player1Move.getSquarePlace().j , player1Move))
                player1.getScore().changeScore(GameRules.getScoreChange(player1Move));
            else
                player1.getScore().changeScore(GameRules.getWrongMoveScoreChange());
            
            this.updateGame();
            PlayerMove player2Move = player2.pickSquare();
            this.checkPlayerMove(player2, player2Move);
            if(player2Move.getMoveResult() != null && grid.setSquareStatus(player2Move.getSquarePlace().i, player2Move.getSquarePlace().j , player2Move))
                player2.getScore().changeScore(GameRules.getScoreChange(player2Move));
            else
                player2.getScore().changeScore(GameRules.getWrongMoveScoreChange());
            
        }
    }
    
    public void checkPlayerMove(Player player, PlayerMove playerMove){
        if(!grid.validSquare(playerMove.getSquarePlace().i, playerMove.getSquarePlace().j)){
            playerMove.setResult(null);
            return;
        }
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
    
    
    public abstract void updateGame();
}
