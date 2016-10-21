/****************************************************
 @author Kurt Lewis

 This class is intended to allow for any given class to be run as a visualized
 algorithm. Children of this class MUST override the abstract methods. All other actions for
 configuring the JPanel are handled by this class
****************************************************/
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.Toolkit;

public abstract class Visualizer extends JPanel implements Runnable {
    private Thread animator;

    public Visualizer() {
        setSize(MainFrame.BOARD_WIDTH, MainFrame.BOARD_HEIGHT);
    }

    /***
        Override this class to paint your visualization. java.awt.Graphics2D is a great library for drawing
        basic shapes and text.
    ***/
    public abstract void paintVisualization(Graphics2D g2d);

    /***
        Basic actions should be handled by this method. 
        For example, one sorting action would occur per cycle call
    ***/
    public abstract void cycle();

    /***
        The value returned by this function determines how fast your visualization occurs.
        The delay is the number of milliseconds between cycles and paints. The smaller the value the quicker the 
        visualization will occur.
    ***/
    public abstract int getDelay();


    @Override
    public void addNotify() {
        super.addNotify();

        animator = new Thread(this);
        animator.start();
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        paintVisualization(g2d);
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void run() {
        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (true) {
            cycle();
            repaint();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = getDelay() - timeDiff;

            if (sleep < 0) {
                sleep = 2;
            }

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("Error - Interrupted: " + e.getMessage());
            }

            beforeTime = System.currentTimeMillis();
        }
    }


}