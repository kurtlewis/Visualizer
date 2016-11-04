/***
 *  @author Nicholas Maltbie
 *
 *  A colored sort adds a sall colored bar to the bottom of a sort that can
 *  be used to show information about the sort.
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

abstract public class ColoredSort extends Sort {

    /**
     * Default color of a colored marker.
     */
    public static final Color DEFAULT_COLOR = Color.GREEN;

    /**
     * Colors of the different indicies
     */
    private Color[] colors;

    /**
     * Constructs a colored sort with the color save positions initialized.
     */
    public ColoredSort() {
        super();
        colors = new Color[array.length];
        for(int i = 0; i < array.length; i++) {
            colors[i] = DEFAULT_COLOR;
        }
    }

    /**
     *  Gets the color of a marker at a given index.
     */
    public Color getColor(int index) {
        return colors[index];
    }

    /**
     *  Sets the color of a marker at a given index.
     */
    public void setColor(int index, Color color) {
        colors[index] = color;
    }

    public void paintVisualization(Graphics2D g2d) {
        super.paintVisualization(g2d);
        // Draw markers for graph representing notes.
        int barWidth = 800 / array.length;
        int barHeightScale = 800 / array.length;
        for (int i = 0; i < array.length; i++) {
            g2d.setColor(colors[i]);
            g2d.fillRect(100 + i * barWidth, 900 - barHeightScale, barWidth, barHeightScale);
        }
    }

}
