/***************************************************************************
 @author Kurt Lewis
***************************************************************************/
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Random;

public abstract class Sort extends Visualizer {
    protected int[] array;
    protected int sortIndex;
    private String title = "Sort";
    private Font font;

    public Sort() {
        array = new int[50];
        font = new Font("Helvetica", Font.BOLD, 60);

        //Randomizes array of 50 items
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
        title = getTitle();
    }

    public void paintVisualization(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Draw Background
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, Visualizer.DRAW_WIDTH, Visualizer.DRAW_HEIGHT);
        //draw font
        g2d.setFont(font);
        g2d.setColor(Color.WHITE);
        FontMetrics metr = this.getFontMetrics(font);  
        g2d.drawString(title, (Visualizer.DRAW_WIDTH - metr.stringWidth(title)) / 2, 100); 

        // Draw bars for bar graph representing sort
        int barWidth = 800 / array.length;
        int barHeightScale = 800 / array.length;
        g2d.setColor(Color.GREEN);
        for (int i = 0; i < array.length; i++) {
            int barHeight = barHeightScale*array[i];
            g2d.fillRect(100 + i * barWidth, 900 - barHeight, barWidth, barHeight);
        }
    }

    // Override this method to provide a title for the sort
    public abstract String getTitle();

    public int getDelay() {
        return 50;
    }
}