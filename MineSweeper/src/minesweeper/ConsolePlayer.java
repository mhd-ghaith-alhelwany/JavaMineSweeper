
package minesweeper;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 *
 * @author Ghaith
 */
public class ConsolePlayer extends Player implements Serializable{
    
    public ConsolePlayer(Color color, Score score, PlayerStatus playerStatus, int sheild) {
        super(color, score, playerStatus, sheild);
    }

    @Override
    public PlayerMove pickSquare(int length, int width){
        System.out.println(this.getColor());
        ScannerThread scn = new ScannerThread(Thread.currentThread());
        Thread t = new Thread(scn);
        t.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            if(scn.i == -1 || scn.j == -1 || scn.k == -1) return null;
            MoveType type = GameRules.getMoveType((char)scn.k);
            SquarePlace squarePlace = new SquarePlace(scn.i, scn.j);
            t.stop();
            return new PlayerMove(type, squarePlace);
        }
        t.stop();
        return null;
    }
    public class ScannerThread implements Runnable{
        public int i = -1, j = -1, k = -1;
        Thread t;
        public ScannerThread(Thread t){
            this.t = t;
        }
        @Override
        public void run() {
            Scanner scn = new Scanner(System.in);
            try{
                i = scn.nextInt();
                j = scn.next().charAt(0) - 'a';
                k = scn.next().charAt(0);
            }catch(InputMismatchException e){}
            finally{
                t.interrupt();
            }
            
        }
    }
}
