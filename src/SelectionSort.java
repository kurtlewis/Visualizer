/***
@author - Noah Bass
***/

import java.awt.Color;

public class SelectionSort extends Sort {

    public static final Color SORTED = Color.decode("#FFFF33"), COMP_LOW = Color.decode("#003366"),
        COMP_HIGH = Color.decode("#990000"), DEFAULT_COLOR = Color.decode("#BA9242"),
        UNSORTED = Color.decode("#F5B16D");

    private int minIndex, searchIndex;
    private int delay = 50;

    public SelectionSort() {
      super();
      for(int i = 0; i < array.length; i++)
        setMarkerColor(i, UNSORTED);
    }

    // Makes one comparison or swap per cycle
    public boolean cycle() {
        // no action to take if less than 2 in length
        // finished sorting if the sort Index is at the end of the array
        if (this.array.length < 2 || sortIndex == this.array.length - 1) {
            return true;
        }

        //if a new minimum was found, update the minimum index
        if (array[searchIndex] < array[minIndex]) {
            //unmark the old min and mark the new min
            setMarkerColor(minIndex, DEFAULT_COLOR);
            minIndex = searchIndex;
            delay = 50;
            setMarkerColor(minIndex, COMP_LOW);
        }
        else
            //umark this index
            setMarkerColor(searchIndex, DEFAULT_COLOR);
        //increment the current searchIndex of searching the array
        searchIndex++;

        //Once the end of the array has been reached, swap elements
        if (searchIndex == array.length) {
            delay = 250;
            //
            setMarkerColor(minIndex, DEFAULT_COLOR);
            setMarkerColor(sortIndex, SORTED);

            int temp = array[minIndex];
            array[minIndex] = array[sortIndex];
            array[sortIndex] = temp;
            sortIndex++;
            if (sortIndex == array.length - 1)
                return true;
            searchIndex = sortIndex + 1;
            minIndex = sortIndex;
            for(int i = searchIndex; i < array.length; i++) {
              setMarkerColor(i, UNSORTED);
            }
            setMarkerColor(minIndex, COMP_LOW);
        }
        //If not at the end of the array yet, keep searching
        else {
            delay = 50;
            setMarkerColor(sortIndex, COMP_HIGH);
        }

        return false;
    }

    public String getTitle() {
        return "Selection Sort";
    }

    // Increase delay between cycles to better display swaps
    public int getDelay() {
        return delay;
    }
}
