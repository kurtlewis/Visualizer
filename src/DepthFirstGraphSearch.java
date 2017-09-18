/***
 @author Kurt Lewis
 ***/

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashSet;

public class DepthFirstGraphSearch extends EuclideanGraph {
    
    public DepthFirstGraphSearch() {
        super("Depth First Graph Search");
    }

    @Override
    public boolean cycle() {
        return false;
    }

    @Override
    public int getDelay() {
        return 100;
    }


}