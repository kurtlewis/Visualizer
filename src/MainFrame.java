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
        run();
    }

    public void run() {
        // TODO - put logic here to run multiple and any Visualizations
        Visualizer test = new Sort();
        test.setSize(getWidth(), getHeight());
        add(test);
        test.start();
        remove(test);
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}