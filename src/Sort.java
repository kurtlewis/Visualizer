/***************************************************************************
 @author Kurt Lewis
***************************************************************************/
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Sort extends Visualizer {
    private int[] array;
    private int sortIndex;
    protected String title;

    public Sort() {
        array = new int[50];
        Random generator = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        for (int i = 0; i < array.length; i++) {
            int temp = array[i%array.length];
            int index = generator.nextInt(array.length);
            array[i%array.length] = array[index];
            array[index] = temp;
        }
        sortIndex = 0;
    }

    public void paintVisualization(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, Visualizer.DRAW_WIDTH, Visualizer.DRAW_HEIGHT);
        int barWidth = 800 / array.length;
        int barHeightScale = 800 / array.length;
        g2d.setColor(Color.GREEN);
        for (int i = 0; i < array.length; i++) {
            int barHeight = barHeightScale*array[i];
            g2d.fillRect(100 + i * barWidth, 900 - barHeight, barWidth, barHeight);
        }
    }

    public boolean cycle() {
        boolean sorted = false;
        while (array[sortIndex] < array[sortIndex+1]) {
            sortIndex++;
            if (sortIndex == array.length - 1) {
                sortIndex = 0;
                if (sorted) {
                    return true;
                } else {
                    sorted = true;
                }
            }
        }
        int temp = array[sortIndex];
        array[sortIndex] = array[sortIndex+1];
        array[sortIndex+1] = temp;
        return false;
    }

    public int getDelay() {
        return 50;
    }
}