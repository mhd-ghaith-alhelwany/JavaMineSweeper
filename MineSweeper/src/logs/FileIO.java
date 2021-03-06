package logs;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

/**
 *
 * @author Ghaith
 */
public class FileIO {
    public static void writeScoreboard(String name, ScoreLogVector savedGame){
        new Thread() {
            @Override
            public void run() {
                ObjectOutputStream os = null;
                try {
                    os = new ObjectOutputStream(new FileOutputStream(name + ".bin"));
                } catch (IOException ex) {
                    System.out.println(ex.getCause());
                    return;
                }
                try{
                    os.writeObject(savedGame);
                }catch(IOException ex){
                    System.out.println(ex.getClass());
                }
            }
        }.start();
    }
    public static ScoreLogVector readScoreboard(String name){
        ObjectInputStream is = null;
        
        try {
            is = new ObjectInputStream(new FileInputStream(name));
        } catch (IOException ex) {
            System.err.println("file doesn't exist");
            return null;
        }
        try{
            ScoreLogVector g = (ScoreLogVector)is.readObject();
            return g;
        }catch(IOException | ClassNotFoundException ex){
            System.err.println("Wrong file opened");
            return null;
        }
    }
    public static void write(String name, SavedGame savedGame){
        new Thread() {
            @Override
            public void run() {
                ObjectOutputStream os = null;
                try {
                    os = new ObjectOutputStream(new FileOutputStream(name + ".bin"));
                } catch (IOException ex) {
                    System.out.println(ex.getCause());
                    return;
                }
                try{
                    os.writeObject(savedGame);
                }catch(IOException ex){
                    System.out.println(ex.getClass());
                }
            }
        }.start();
    }
    public static SavedGame read(String name){
        ObjectInputStream is = null;
        
        try {
            is = new ObjectInputStream(new FileInputStream(name));
        } catch (IOException ex) {
            System.err.println("file doesn't exist");
            return null;
        }
        try{
            SavedGame g = (SavedGame)is.readObject();
            return g;
        }catch(IOException | ClassNotFoundException ex){
            System.err.println("Wrong file opened");
            return null;
        }
    }
}
