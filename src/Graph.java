/**
 * Extends visualizer to provide the core components of a graph visualization. Does not create or implement graph generation
 * @author Kurt Lewis
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.RenderingHints;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashSet;

 public abstract class Graph extends Visualizer {
    protected ArrayList<Vertex> vertices;
    protected HashSet<Edge> edges;
    private Color titleColor, backgroundColor;
    private String title;
    protected final static int MAX_X = Visualizer.DRAW_WIDTH;
    // subtract 100 for drawing of title
    protected final static int MAX_Y = Visualizer.DRAW_HEIGHT - 100;

    public Graph(String title) {
        this(title, Color.BLACK, Color.WHITE);
    }

    /**
     * Main Constructor for graph class
     */
    public Graph(String title, Color backgroundColor, Color titleColor) {
        this.titleColor = titleColor;
        this.backgroundColor = backgroundColor;
        this.title = title;
        vertices = new ArrayList<Vertex>();
        edges = new HashSet<Edge>();
    }

    @Override
    public void paintVisualization(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Draw Background
        g2d.setColor(backgroundColor);
        g2d.fillRect(0, 0, Visualizer.DRAW_WIDTH, Visualizer.DRAW_HEIGHT);

        // Draw font
        Font font = new Font("Helvetica", Font.BOLD, 60);
        g2d.setFont(font);
        g2d.setColor(titleColor);
        FontMetrics metr = this.getFontMetrics(font);
        g2d.drawString(title, (Visualizer.DRAW_WIDTH - metr.stringWidth(title)) / 2, 100);

        // Paint vertices
        for (Edge e : edges) {
            e.paint(g2d);
        }

        // Paint vertices second to overlap color of edges
        for (Vertex v : vertices) {
            v.paint(g2d);
        }

        
    }


    /**
     * Provides an implementation of a vertex, containing points, size, and color
     * @author Kurt Lewis
     */
    protected class Vertex {
        private ArrayList<Edge> edges;
        private int x, y;
        private Color color;
        private int diameter;

        /**
         * Main constructor for Vertex
         */
        public Vertex(int x, int y) {
            this.x = x;
            this.y = y;
            diameter = 10;
            edges = new ArrayList<Edge>();
        }

        public Vertex(int x, int y, Color color) {
            this(x, y);
            this.color = color;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        /**
         * Adds edge to end of edge list
         * @see Vertex.addEdgeWeightSorted
         */
        public void addEdge(Edge e) {
            edges.add(e);
        }

        /**
         * Adds edge to edge list as ordered by weight. If building an ordered list of edges,
         * this is the method that must be used to build the edge list.
         */
        public void addEdgeWeightSorted(Edge e) {
            // Add edges in sorted order by weight
            if (edges.isEmpty()) {
                // There is nothing in the list, so add the edge in the first spot
                edges.add(e);
            } else {
                for (int i = 0; i < edges.size(); i++) {
                    if (e.getWeight() < edges.get(i).getWeight()) {
                        edges.add(i, e);
                        return;
                    }
                }
                // This edge is the longer than the rest of the list
                edges.add(e);
            }
        }

        public ArrayList<Edge> getEdges() {
            return edges;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public int getDiameter() {
            return diameter;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof Vertex)) return false;
            Vertex v = (Vertex) obj;
            return (x == v.getX() && y ==v.getY());
        }

        public void paint(Graphics2D g2d) {
            g2d.setColor(color);
            g2d.fillOval(x, y, diameter, diameter);
        }

    }

    /**
     * Implementation of Edge class. Contains weight, vertices, and color
     * @author Kurt Lewis
     */
    protected class Edge {
        double weight;
        Vertex a, b;
        Color color;
        boolean showWeight = false;;

        public Edge(Vertex a, Vertex b) {
            this.a = a;
            this.b = b;
            weight = Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2));
        }

        public Edge(Vertex a, Vertex b, Color color) {
            this(a, b);
            this.color = color;
        }

        public Edge(Vertex a, Vertex b, double weight) {
            this.a = a;
            this.b = b;
            this.weight = weight;
        }

        public Edge(Vertex a, Vertex b, double weight, Color color) {
            this(a, b, weight);
            this.color = color;
        }

        public double getWeight() {
            return weight;
        }

        public Vertex getVertexA() {
            return a;
        }

        public Vertex getVertexB() {
            return  b;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        /**
         * Enable for weight to be shown near the center of an edge
         */
        public void setShowWeight(boolean showWeight) {
            this.showWeight = showWeight;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof Edge)) return false;
            Edge e = (Edge) obj;
            return ((a.equals(e.getVertexA()) || a.equals(e.getVertexB()) ) && b.equals(e.getVertexA()) || b.equals(e.getVertexB()));
        }

        public void paint(Graphics2D g2d) {
            // Save the old stroke so we can reset it
            Stroke old = g2d.getStroke();
            g2d.setStroke(new BasicStroke(3));
            g2d.setColor(color);
            int offset = a.getDiameter() /2;
            g2d.drawLine(a.getX() + offset, a.getY() + offset, b.getX() + offset, b.getY() + offset);

            // Prep Font for drawing weight
            if (showWeight) {
                Font font = new Font("Helvetica", Font.PLAIN, 15);
                g2d.setFont(font);
                g2d.setColor(titleColor);
                // Build weight as a string, with a max length of 5 characters
                String weightStr = Double.toString(weight).subSequence(0, Math.min(Double.toString(weight).length(), 5))
                        .toString();
                g2d.drawString(weightStr, (a.getX() + b.getX()) / 2, (a.getY() + b.getY()) / 2);
            }

            // Reset the stroke to the old stroke
            g2d.setStroke(old);
        }

        /**
         * Return strue if given edge intersects with this edge
         * @return true if given edges intersect
         */
        public boolean intersects(Edge e) {
            // The larger x value of this edge must be greater than the minimum x value of the given edge
            // The smaller x value of this edge must be smaller than the larger x value of the given edge
            if (Math.max(a.getX(), b.getX()) > Math.min(e.getVertexA().getX(), e.getVertexB().getX()) 
                && Math.min(a.getX(), b.getX()) < Math.max(e.getVertexA().getX(), e.getVertexB().getX())) {
                // The larger y value of this edge must be greater than the smaller y value of the given edge
                // The smaller y value of this edge must be smaller than the larger y value of the given edge
                if (Math.max(a.getY(), b.getY()) > Math.min(e.getVertexA().getY(), e.getVertexB().getY()) 
                    && Math.min(a.getY(), b.getY()) < Math.max(e.getVertexA().getY(), e.getVertexB().getY())) {
                    return true;
                }
            }
            // Did not intersect, so return false
            return false;
        }
    }
 }