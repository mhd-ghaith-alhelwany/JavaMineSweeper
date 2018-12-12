package logs;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;

/**
 *
 * @author Ghaith
 */
public class PlayerMoveLogVector implements Serializable{
    Vector<PlayerMoveLog> vector;
    public PlayerMoveLogVector(){
        this.vector = new Vector<PlayerMoveLog>();
    }
    public PlayerMoveLogVector(Vector<PlayerMoveLog> vector){
        this.vector = vector;
    }
    public void add(PlayerMoveLog playerMoveLog){
        vector.add(playerMoveLog);
    }
    public Vector<PlayerMoveLog> getVector(){
        return this.vector;
    }
    
    @Override
    public String toString(){
        return vector.toString();
    }
}
