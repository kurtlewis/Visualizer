/****************************************************
 @author Kurt Lewis

 This class is intended to allow for any given class to be run as a visualized
 algorithm. Children of this class MUST override the abstract methods. All other actions for
 configuring the JPanel are handled by this class
****************************************************/
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ReplicateScaleFilter;
import java.awt.image.FilteredImageSource;
import java.awt.Toolkit;
import javax.swing.JPanel;


public abstract class Visualizer extends JPanel implements Runnable {
    private Thread animator;
    public final static int DRAW_WIDTH = 1000, DRAW_HEIGHT = 1000;

    
    /***
        Override this class to paint your visualization. java.awt.Graphics2D is a great library for drawing
        basic shapes and text.

        Draw using the public constants in the Visualizer class DRAW_WIDTH and DRAW_HEIGHT. You can draw assuming
        a 1000 by 1000 square canvas, which the Visualizer will then resize as needed to the necessary size of the
        JFrame that holds it. 

        Drawing actually occurs on an image - this means the user does not have access to
        JPanel (and other such Component children classes) methods such as setBackground(Color color)
    ***/
    public abstract void paintVisualization(Graphics2D g2d);

    /***
        Basic actions should be handled by this method. 
        For example, one sorting action would occur per cycle call
        Returns
            False: Has not completed visualization
            True: Has completed visualization and a new visualization can be loaded
    ***/
    public abstract boolean cycle();

    /***
        The value returned by this function determines how fast your visualization occurs.
        The delay is the number of milliseconds between cycles and paints. The smaller the value the quicker the 
        visualization will occur.
    ***/
    public abstract int getDelay();


    /***
        Starts the visualizer animation/cycle thread and waits for it to finish before returning control
    ***/
    public void start() {
        animator = new Thread(this);
        animator.start();
        try {
            animator.join();
        } catch (InterruptedException e) {
            System.out.println("Caught InterruptedException: " + e.getMessage());
        }
    }

    @Override
    public void addNotify() {
        super.addNotify();
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        // Have the extending class draw on an image so that it can be resized as needed
        Image bufferImage = createImage(DRAW_WIDTH, DRAW_HEIGHT);
        // Give control to visualization to draw on buffer
        paintVisualization((Graphics2D)bufferImage.getGraphics());
        int width = getWidth();
        int height = getHeight(); 
        // Scale and resize the image
        ReplicateScaleFilter scale = new ReplicateScaleFilter(width, height);
        FilteredImageSource fis = new FilteredImageSource(bufferImage.getSource(), scale);
        Image croppedImage = createImage(fis);
        g.drawImage(croppedImage, 0, 0, null);
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void run() {
        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();
        boolean done;

        while (true) {
            done = cycle();
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
            
            // Animation has finished, kill thread
            if (done) {
                return;
            }
        }
    }


}