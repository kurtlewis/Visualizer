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
        /* Things that need to happen - 
         - new instance of class
         - set size of class to frame width and height
         - add class to board
         - call start on class
         - remove board once start runs
        */
        if (args.length == 0) {
            //fill args with a list of classes in the bin/ folder
            // this will prrrrooobbbaaabbbllllyyy only work when run from Visualizer/ folder
            File[] files = new File("bin/").listFiles();
            args = new String[files.length];
            for (int i = 0; i < files.length; i++) {
                args[i] = files[i].toString().substring(4, files[i].toString().length()-6);
            }
        }
        for (int i = 0; i < args.length; i++) {
            try {
                Object obj = ClassLoader.getSystemClassLoader().loadClass(args[i]).newInstance(); 
                // Currently broken - this needs to reject abstract classes such as Visualizer itself. Try making the above line create a 
                // Class<?> instead.
                if (Visualizer.class.isAssignableFrom(obj.getClass()) && !Modifier.isAbstract(obj.getClass().getModifiers())) {
                    Visualizer visualization = (Visualizer)obj;
                    visualization.setSize(getWidth(), getHeight());
                    add(visualization);
                    visualization.start();
                    remove(visualization);
                }
                //Visualizer visualization = (Visualizer)ClassLoader.getSystemClassLoader().loadClass(args[i]).newInstance();          
            } catch (Exception e) {
                System.out.println("ERROR - class " +args[i] + " could not be found. Please ensure it has been compiled.");
                System.exit(0);
            }
            
        }
    }

    public static void main(String[] args) {
        MainFrame main = new MainFrame();
        main.run(args);
    }
}