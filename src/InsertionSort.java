/***
@author Nicholas Maltbie
 ***/

import java.awt.Color;

public class InsertionSort extends Sort {

    //Colors of different things to show the user
    public static final Color SORTED = Color.decode("#FFFF33"), INSERT_COMP =
        Color.decode("#003366"), NORMAL_COMP = Color.decode("#999900"),
        UNSORTED = Color.decode("#BA9242");

    //Current progress toward sorting the array
    private int progress = 0;
    //Held number while inserting
    private int temp;
    //Held color
    private Color tempColor = null;

    //Creates a selection sort with default colors
    public InsertionSort() {
        super();
        for (int i = 0; i < array.length; i++)
            setColor(i, UNSORTED);
        temp = array[0];
        sortIndex = 0;
    }

    // Makes one comparison per cycle
    public boolean cycle() {
        //If only one element is unsorted, the sorting is done
        if (progress == array.length)
            return true;

        if (sortIndex >= progress) {
            array[sortIndex] = temp;
            progress++;
            setColor(sortIndex, NORMAL_COMP);
            sortIndex = 0;
            if(progress < array.length) {
                temp = array[progress];
                setColor(progress, INSERT_COMP);
            }
            for(int i = 0; i < progress; i++) {
                setColor(i, SORTED);
            }
        }
        else {
            if (array[sortIndex] > temp) {
                int temp2 = array[sortIndex];
                array[sortIndex] = temp;
                temp = temp2;
                setColor(sortIndex, INSERT_COMP);
                delay = 50;
            }
            else {
                delay = 10;
                setColor(sortIndex, SORTED);
            }
            sortIndex++;
            tempColor = getColor(sortIndex);
            setColor(sortIndex, NORMAL_COMP);
        }

        //still sorting
        return false;
    }

    private int delay = 5;

    public int getDelay() {
        return delay;
    }

    public String getTitle() {
        return "Insertion Sort";
    }

}
