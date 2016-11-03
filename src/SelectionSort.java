/***
@author - Noah Bass
***/

public class SelectionSort extends Sort {
    // Makes one swap per cycle
    public boolean cycle() {
        // no action to take if less than 2 in length
        // finished sorting if the sort index is at the end of the array
        if (this.array.length < 2 || this.sortIndex == this.array.length - 1) {
            return true;
        }

        // get the array minimum element index
        int minimumIndex = this.sortIndex;
        for (int i = this.sortIndex; i < this.array.length; i++) {
            if (this.array[i] < this.array[minimumIndex]) {
                minimumIndex = i;
            }
        }

        // check if the minimum index is the sort index
        // if so, the element is already in place
        if (minimumIndex == this.sortIndex) {
            this.sortIndex++;
            return false;
        }

        // swap the first element with the smallest element
        int temp = this.array[minimumIndex];
        this.array[minimumIndex] = this.array[sortIndex];
        this.array[sortIndex] = temp;

        this.sortIndex++;

        return false;
    }

    public String getTitle() {
        return "Selection Sort";
    }
    
    // Increase delay between cycles to better display swaps
    public int getDelay() {
        return 500;
    }
}
