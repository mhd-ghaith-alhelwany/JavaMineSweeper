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
        GameRules.initialize();
        this.start();
    }
    
    public void start(){
        while(true){
            this.updateGame();
            PlayerMove player1Move = player1.pickSquare();
            this.checkPlayerMove(player1, player1Move);
            if(grid.getSquare(player1Move.getSquarePlace().i, player1Move.getSquarePlace().j).setSquareState(player1Move))
                player1.getScore().changeScore(GameRules.getScoreChange(player1Move));
            else
                player1.getScore().changeScore(GameRules.getWrongMoveScoreChange());
            
            this.updateGame();
            PlayerMove player2Move = player1.pickSquare();
            this.checkPlayerMove(player1, player2Move);
            if(grid.getSquare(player2Move.getSquarePlace().i, player2Move.getSquarePlace().j).setSquareState(player2Move))
                player1.getScore().changeScore(GameRules.getScoreChange(player2Move));
            else
                player1.getScore().changeScore(GameRules.getWrongMoveScoreChange());
            
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
    
    
    public abstract void updateGame();
}
