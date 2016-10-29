/***
@author - Kurt Lewis
***/

public class BubbleSort extends Sort {

    // Makes one swap per cycle
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

    public String getTitle() {
        return "Bubble Sort";
    }

}