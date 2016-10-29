/***********************************************************************
 @author Kurt Lewis 
************************************************************************/
import java.awt.EventQueue;
import javax.swing.JFrame;

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
        }
        for (int i = 0; i < args.length; i++) {
            try {
                Visualizer visualization = (Visualizer)ClassLoader.getSystemClassLoader().loadClass(args[i]).newInstance();
                visualization.setSize(getWidth(), getHeight());
                add(visualization);
                visualization.start();
                remove(visualization);
            } catch (Exception e) {
                dispose();
                System.out.println("ERROR - class " +args[i] + " could not be found. Please ensure it has been compiled.");
            }
            
        }
    }

    public static void main(String[] args) {
        MainFrame main = new MainFrame();
        main.run(args);
    }
}