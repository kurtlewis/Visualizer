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
    private Color barColor, backgroundColor, titleColor;

    /**
     * Colors of of markers at the different bar indicies.
     */
    private Color[] markerColors;

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
        barColor = Color.GREEN;
        backgroundColor = Color.BLACK;
        titleColor = Color.WHITE;

        //Setup the marker colors
        markerColors = new Color[array.length];
        for(int i = 0; i < array.length; i++) {
            markerColors[i] = barColor;
        }
    }

    public void paintVisualization(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Draw Background
        g2d.setColor(backgroundColor);
        g2d.fillRect(0, 0, Visualizer.DRAW_WIDTH, Visualizer.DRAW_HEIGHT);
        //draw font
        g2d.setFont(font);
        g2d.setColor(titleColor);
        FontMetrics metr = this.getFontMetrics(font);
        g2d.drawString(title, (Visualizer.DRAW_WIDTH - metr.stringWidth(title)) / 2, 100);

        // Draw bars for bar graph representing sort
        int barWidth = 800 / array.length;
        int barHeightScale = 800 / array.length;
        g2d.setColor(barColor);
        for (int i = 0; i < array.length; i++) {
            int barHeight = barHeightScale*array[i];
            g2d.fillRect(100 + i * barWidth, 900 - barHeight, barWidth, barHeight);
        }

        // Draw markers for graph representing notes.
        for (int i = 0; i < array.length; i++) {
            g2d.setColor(markerColors[i]);
            g2d.fillRect(100 + i * barWidth, 900 - barHeightScale, barWidth, barHeightScale);
        }
    }

    /**
     *  Gets the color of a marker at a given index.
     */
    public Color getMarkerColor(int index) {
        return markerColors[index];
    }

    /**
     *  Sets the color of a marker at a given index.
     */
    public void setMarkerColor(int index, Color color) {
        markerColors[index] = color;
    }


    // Override this method to provide a title for the sort
    public abstract String getTitle();

    public int getDelay() {
        return 50;
    }

    public void setBackgroundColor(Color color) {
        backgroundColor = color;
    }

    public void setTitleColor(Color color) {
        titleColor = color;
    }

    public void setBarColor(Color color) {
        barColor = color;
    }
}
