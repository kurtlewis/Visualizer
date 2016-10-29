# Visualizer
This project is a Java applet designed to run visualizations of different algorithms or simulations. It is designed with the idea that anyone can contribute a visualization for an algorithm of their own choosing!

In an effort to standardize, much of the overhead in drawing and animating has been abstracted.

## Building and Running
This project is intended to be built and run from the command line. 
 - Make sure you have a ```bin/``` folder
```
mkdir bin/
```
 - To compile from ```Visualizer/```
```
javac src/*.java -d bin/
```
 - To run a specific visualization from ```Visualizer/```
```
java -cp bin/ MainFrame [Specific class to run]
```
 - To run all visualizations in a cycling order from ```Visualizer/```
```
java -cp bin/ MainFrame
```

## How to contribute

Contributing is easy! Fork the repo on github, and create a new class in the ```src/``` directory

This class should extend the class ```Visualizer``` and implement its three important methods listed below.

Take a look at the [Java Graphic2D library](https://docs.oracle.com/javase/7/docs/api/java/awt/Graphics2D.html) for easy instructions and how to draw different shapes for a visualization of your algorithm! 

The classes TestVisualization and Sort are intended to be examples of how to extend Visualizer.

Once you've created your visualization and tested it, submit a pull request. Please document your code well, and make sure no artifacts are left from any IDE you might use.

```
/***
Override this class to paint your visualization. java.awt.Graphics2D is a great library for drawing
basic shapes and text.

Draw using the public constants in the Visualizer class DRAW_WIDTH and DRAW_HEIGHT. You can draw assuming
a 1000 by 1000 square canvas, which the Visualizer will then resize as needed to the necessary size of the
JFrame that holds it. 

Drawing actually occurs on an image - this means the user does not have access to
JPanel (and other such Component children classes) methods such as setBackground(Color color)
***/
public abstract void paintVisualization(Graphics2D g2d);

/***
Basic actions should be handled by this method. 
For example, one sorting action would occur per cycle call
Returns
    False: Has not completed visualization
    True: Has completed visualization and a new visualization can be loaded
***/
public abstract boolean cycle();

/***
The value returned by this function determines how fast your visualization occurs.
The delay is the number of milliseconds between cycles and paints. The smaller the value the quicker the 
visualization will occur.
***/
public abstract int getDelay();
```

