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
        for(int i = 0; i < this.array.length; i++) {
            this.setMarkerColor(i, UNSORTED_COLOR);
        }
    }

    // Makes one comparison or swap per cycle
    public boolean cycle() {
        // no action to take if less than 2 in length
        // finished sorting if the sort Index is at the end of the array
        if (this.array.length < 2 || this.sortIndex == this.array.length - 1) {
            return true;
        }

        // if a new minimum was found, update the minimum index
        if (this.array[this.searchIndex] < this.array[this.minIndex]) {
            // unmark the old min and mark the new min
            this.setMarkerColor(this.minIndex, DEFAULT_COLOR);
            this.minIndex = this.searchIndex;
            this.setMarkerColor(this.minIndex, COMP_LOW_COLOR);
        }
        else {
            // umark this index
            this.setMarkerColor(this.searchIndex, DEFAULT_COLOR);
        }

        // increment the current searchIndex of searching the array
        this.searchIndex++;

        // once the end of the array has been reached, swap elements
        if (this.searchIndex == this.array.length) {
            this.setMarkerColor(this.minIndex, DEFAULT_COLOR);
            this.setMarkerColor(this.sortIndex, SORTED_COLOR);

            int temp = this.array[minIndex];
            this.array[this.minIndex] = this.array[this.sortIndex];
            this.array[this.sortIndex] = temp;

            this.sortIndex++;

            if (this.sortIndex == this.array.length - 1) {
                return true;
            }

            this.searchIndex = this.sortIndex + 1;
            this.minIndex = this.sortIndex;

            for(int i = searchIndex; i < this.array.length; i++) {
                this.setMarkerColor(i, UNSORTED_COLOR);
            }

            this.setMarkerColor(this.minIndex, COMP_LOW_COLOR);
        }
        // if not at the end of the array yet, keep searching
        else {
            this.setMarkerColor(this.sortIndex, COMP_HIGH_COLOR);
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
