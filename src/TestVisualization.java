/******************************************************************
    @author - Kurt Lewis
    This is a test class for testing different functionality of the visualizer
******************************************************************/

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class TestVisualization extends Visualizer {
    private Rectangle rect1, rect2, rect3, rect4;

    public TestVisualization() {
        setBackground(Color.BLACK);
        rect1 = new Rectangle(300, 300, 50, 50);
        rect2 = new Rectangle(MainFrame.BOARD_WIDTH - 350, 300, 50, 50);
        rect3 = new Rectangle(MainFrame.BOARD_WIDTH - 350, MainFrame.BOARD_HEIGHT - 350, 50, 50);
        rect4 = new Rectangle(300, MainFrame.BOARD_HEIGHT - 350, 50, 50);
    }

    public void cycle() {
        rect1.setLocation((int)rect1.getX() + 1, (int)rect1.getY() + 1);
        rect2.setLocation((int)rect2.getX() - 1, (int)rect2.getY() + 1);
        rect3.setLocation((int)rect3.getX() - 1, (int)rect3.getY() - 1);
        rect4.setLocation((int)rect4.getX() + 1, (int)rect4.getY() - 1);
        if (rect1.getX() > MainFrame.BOARD_WIDTH - 350 && rect1.getY() > MainFrame.BOARD_HEIGHT - 350) {
            Rectangle tempRect = rect1;
            rect1 = rect3;
            rect3 = tempRect;
            tempRect = rect2;
            rect2 = rect4;
            rect4 = tempRect;
        }
    }

    public void paintVisualization(Graphics2D g2d) {
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
