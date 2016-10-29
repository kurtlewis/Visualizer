/***********************************************************************
 @author Kurt Lewis 
************************************************************************/
import java.awt.EventQueue;
import java.io.File;
import javax.swing.JFrame;
import java.lang.reflect.Modifier;

public class MainFrame extends JFrame {
    // TODO - determine default display size
    public final static int BOARD_WIDTH = 900, BOARD_HEIGHT = 900;

    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        setSize(BOARD_WIDTH, BOARD_HEIGHT);
        setLocationRelativeTo(null);
        setTitle("Visualizations");
        setResizable(true);
        setVisible(true);
    }

    public void run(String[] args) {

        if (args.length == 0) {
            // If no arguments were supplied
            // fill args with a list of classes in the bin/ folder
            // this will prrrrooobbbaaabbbllllyyy only work when run from Visualizer/ folder
            File[] files = new File("bin/").listFiles();
            args = new String[files.length];
            for (int i = 0; i < files.length; i++) {
                //manually trim the strings. Definitely better ways to do this
                if (files[i].toString().endsWith(".class")) {
                    args[i] = files[i].toString().substring(4, files[i].toString().length()-6);
                }
            }
        }
        while (true) {
            for (int i = 0; i < args.length; i++) {
                try {
                    Class<?> myClass = ClassLoader.getSystemClassLoader().loadClass(args[i]); 
                    // Make sure class is a child class of Visualizer, and is not abstract
                    if (Visualizer.class.isAssignableFrom(myClass) && !Modifier.isAbstract(myClass.getModifiers())) {
                        Visualizer visualization = (Visualizer)myClass.newInstance();
                        visualization.setSize(getWidth(), getHeight());
                        add(visualization);
                        visualization.start();
                        remove(visualization);
                    }          
                } catch (Exception e) {
                    System.out.println("ERROR - class " +args[i] + " could not be found. Please ensure it has been compiled.");
                    System.exit(0);
                }
            
            }
        }
    }

    public static void main(String[] args) {
        MainFrame main = new MainFrame();
        main.run(args);
    }
}