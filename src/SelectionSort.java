/***
@author - Noah Bass
***/

import java.awt.Color;

public class SelectionSort extends Sort {
    private static final Color SORTED_COLOR = Color.decode("#FFFF33"),
                               COMP_LOW_COLOR = Color.decode("#003366"),
                               COMP_HIGH_COLOR = Color.decode("#990000"),
                               DEFAULT_COLOR = Color.decode("#BA9242"),
                               UNSORTED_COLOR = Color.decode("#F5B16D");

    private int minIndex = 0;
    private int searchIndex = 0;

    public SelectionSort() {
        super();

        // mark all columns color as unsorted
        for(int i = 0; i < array.length; i++) {
            setMarkerColor(i, UNSORTED_COLOR);
        }
    }

    // Makes one comparison or swap per cycle
    public boolean cycle() {
        // no action to take if less than 2 in length
        // finished sorting if the sort Index is at the end of the array
        if (array.length < 2 || sortIndex == array.length - 1) {
            return true;
        }

        // if a new minimum was found, update the minimum index
        if (array[searchIndex] < array[minIndex]) {
            // unmark the old min and mark the new min
            setMarkerColor(minIndex, DEFAULT_COLOR);
            minIndex = searchIndex;
            setMarkerColor(minIndex, COMP_LOW_COLOR);
        }
        else {
            // umark this index
            setMarkerColor(searchIndex, DEFAULT_COLOR);
        }

        // increment the current searchIndex of searching the array
        searchIndex++;

        // once the end of the array has been reached, swap elements
        if (searchIndex == array.length) {
            setMarkerColor(minIndex, DEFAULT_COLOR);
            setMarkerColor(sortIndex, SORTED_COLOR);

            int temp = array[minIndex];
            array[minIndex] = array[sortIndex];
            array[sortIndex] = temp;

            sortIndex++;

            if (sortIndex == array.length - 1) {
                return true;
            }

            searchIndex = sortIndex + 1;
            minIndex = sortIndex;

            for(int i = searchIndex; i < array.length; i++) {
                setMarkerColor(i, UNSORTED_COLOR);
            }

            setMarkerColor(minIndex, COMP_LOW_COLOR);
        }
        // if not at the end of the array yet, keep searching
        else {
            setMarkerColor(sortIndex, COMP_HIGH_COLOR);
        }

        return false;
    }

    public String getTitle() {
        return "Selection Sort";
    }

    // Increase delay between cycles to better display swaps
    public int getDelay() {
        return 200;
    }
}
