/***
@author Nicholas Maltbie
 ***/

 import java.awt.Color;

public class SelectionSortColor extends ColoredSort {

    public static final Color SORTED = Color.decode("#FFFF33"), COMP_LOW = Color.decode("#003366"),
        COMP_HIGH = Color.decode("#990000"), DEFAULT_COLOR = Color.decode("#BA9242");

    private int progress = 0;
    private int minIndex = 0;

    public SelectionSortColor() {
        super();
        for (int i = 0; i < array.length; i++)
            setColor(i, DEFAULT_COLOR);
    }

    // Makes one comparison per cycle
    public boolean cycle() {
        if (array[sortIndex] < array[minIndex]) {
            setColor(minIndex, DEFAULT_COLOR);
            minIndex = sortIndex;
            delay = 50;
            setColor(minIndex, COMP_LOW);
        }
        else
            setColor(sortIndex, DEFAULT_COLOR);
        sortIndex++;
        if (sortIndex == array.length) {
            delay = 100;
            setColor(minIndex, DEFAULT_COLOR);
            setColor(progress, SORTED);

            int temp = array[minIndex];
            array[minIndex] = array[progress];
            array[progress] = temp;
            progress++;
            if (progress == array.length - 2)
                return true;
            sortIndex = progress + 1;
            minIndex = progress;
            setColor(minIndex, COMP_LOW);
        }
        else {
            delay = 25;
           setColor(sortIndex, COMP_HIGH);
        }

        return false;
    }

    private int delay = 50;

    public int getDelay() {
        return delay;
    }

    public String getTitle() {
        return "Selection Sort";
    }

}
