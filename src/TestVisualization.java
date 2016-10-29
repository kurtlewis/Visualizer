/******************************************************************
    @author - Kurt Lewis
    This is a test class for testing different functionality of the visualizer
******************************************************************/

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class TestVisualization extends Visualizer {
    private Rectangle rect1, rect2, rect3, rect4;
    private int counter;

    public TestVisualization() {
        rect1 = new Rectangle(300, 300, 100, 100);
        rect2 = new Rectangle(Visualizer.DRAW_WIDTH - 400, 300, 100, 100);
        rect3 = new Rectangle(Visualizer.DRAW_WIDTH - 400, Visualizer.DRAW_HEIGHT - 400, 100, 100);
        rect4 = new Rectangle(300, Visualizer.DRAW_HEIGHT - 400, 100, 100);
        counter = 0;
    }

    public boolean cycle() {
        // move rectangles
        rect1.setLocation((int)rect1.getX() + 1, (int)rect1.getY() + 1);
        rect2.setLocation((int)rect2.getX() - 1, (int)rect2.getY() + 1);
        rect3.setLocation((int)rect3.getX() - 1, (int)rect3.getY() - 1);
        rect4.setLocation((int)rect4.getX() + 1, (int)rect4.getY() - 1);
        //reset rectangles, or end the visualization if 5 swaps have been made
        if (rect1.getX() > Visualizer.DRAW_WIDTH - 400 && rect1.getY() > Visualizer.DRAW_HEIGHT - 400) {
            Rectangle tempRect = rect1; 
            rect15 = rect3;
            rect3 = tempRect;
            tempRect = rect2;
            rect2 = rect4;
            rect4 = tempRect;
            if (counter > 5) {
                return true;
            }
            counter++;
        }
        return false;
    }

    public void paintVisualization(Graphics2D g2d) {
        // The background needs to be set manually, as drawing now occurs on an image rather than the actual component
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, Visualizer.DRAW_WIDTH, Visualizer.DRAW_HEIGHT);
        g2d.setColor(Color.CYAN);
        g2d.fill(rect1);
        g2d.fill(rect2);
        g2d.fill(rect3);
        g2d.fill(rect4);
    }

    public int getDelay() {
        return 25;
    }
}
