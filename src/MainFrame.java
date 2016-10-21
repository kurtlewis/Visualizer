/***********************************************************************
 @author Kurt Lewis 
************************************************************************/

import javax.swing.JFrame;

public class MainFrame extends JFrame {
    // TODO - determine default display size
    public final int BOARDWIDTH = 900, BOARDHEIGHT = 900;

    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        setSize(BOARDWIDTH, BOARDHEIGHT);
        setLocationRelativeTo(null);
        setTitle("Visualizations");
        setResizable(true);
        setVisible(true);
    }

    public void run() {
        // TODO - put logic here to run multiple and any Visualizations

    }

    public static void main(String[] args) {
        new MainFrame();
    }
}