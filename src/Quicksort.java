/***
@author Nicholas Maltbie
 ***/

import java.awt.Color;
import java.util.Stack;

public class Quicksort extends ColoredSort {

    public static final Color RIGHT = Color.decode("#990000"), LEFT = Color.decode("#003366"),
            PIVOT = Color.decode("#999900"), DEFAULT_COLOR = Color.decode("#BA9242"),
            SELECTED = Color.decode("#FFFF33");

    private int startl, left, startr, right, pivot;
    private int delay = 50;
    private Stack<Integer> commands = new Stack<>();

    public Quicksort() {
        super();
        for (int i = 0; i < array.length; i++)
            setColor(i, DEFAULT_COLOR);
        commands.push(0);
        commands.push(array.length - 1);
    }

    // Makes one swap per cycle
    public boolean cycle() {
        if (left >= right) {
            if (commands.isEmpty()) {
              delay = 500;
              return true;
            }
            right = commands.pop();
            startr = right;
            pivot = commands.pop();
            left = pivot + 1;
            startl = pivot;

            for(int i = 0; i < array.length; i++)
                setColor(i, DEFAULT_COLOR);

            setColor(pivot, PIVOT);

            for(int i = left + 1; i < right; i++)
              setColor(i, SELECTED);

            setColor(right, RIGHT);
            setColor(left, LEFT);
        }

        while (right >= left && array[right] > array[pivot]) {
            setColor(right, RIGHT);
            right--;
            if (right > left) {
              delay = 50;
              return false;
            }
        }
        while (left <= right && array[left] < array[pivot]) {
            setColor(left, LEFT);
            left++;
            if (left < right) {
              delay = 50;
              return false;
            }
        }

        if (right >= left) {
            delay = 250;
            int temp = array[left];
            array[left] = array[right];
            array[right] = temp;
        }
        else {
            delay = 500;
            int temp = array[pivot];
            array[pivot] = array[right];
            array[right] = temp;
            if (startr - right - 1 > 0) {
                commands.push(right + 1);
                commands.push(startr);
            }
            if (right - 1 - startl > 0) {
                commands.push(startl);
                commands.push(right - 1);
            }

        }

        return false;
    }

    public int getDelay() {
      return delay;
    }

    public String getTitle() {
        return "Quicksort";
    }
}
