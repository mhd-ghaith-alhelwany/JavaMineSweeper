package GUI;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ChoosePath extends JPanel{
    
    private JFileChooser fc;
    private JButton b;
    private String path;
    private String name;
    
    public ChoosePath(){
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {}
        
        fc = new JFileChooser();
        b = new JButton();
        
        if(fc.showOpenDialog(b) == fc.APPROVE_OPTION){
            path = fc.getSelectedFile().getAbsolutePath();
            name = fc.getSelectedFile().getName();
        }
        
    }
    public String getPath(){
        return this.path;
    }
    public String getName(){
        return this.name;
    }

}
