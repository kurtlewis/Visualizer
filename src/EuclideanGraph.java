/***
 @author Kurt Lewis
 ***/

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Random;
import java.util.ArrayList;

public abstract class EuclideanGraph extends Visualizer {
    private String title;
    private ArrayList<Vertice> vertices;
    private Color titleColor, backgroundColor;
    private Color verticeColor, selectedVerticeColor;
    private Color edgeColor, selectedEdgeColor;
    private final static double MAX_X = Visualizer.DRAW_WIDTH;
    // subtract 100 for drawing of title
    private final static double MAX_Y = Visualizer.DRAW_HEIGHT - 100;

    public EuclideanGraph(String title) {
        this.title = title;

        // Set default colors
        backgroundColor = Color.BLACK;
        titleColor = Color.WHITE;
        verticeColor = Color.WHITE;
        selectedVerticeColor = Color.CYAN;
        edgeColor = Color.GRAY;
        selectedEdgeColor = Color.MAGENTA;

        vertices = new ArrayList<Vertice>();
        buildGraph();
    }


    private void buildGraph() {
        Random generator  = new Random();
        int numVertices = generator.nextInt(50) + 50;
        for (int i = 0; i < numVertices; i++) {
            vertices.add(new Vertice(generator.nextInt(MAX_X), generator.nextInt(MAX_Y)));
        }

        // build a set of vertices and randomly add edges until a connected graph is reached

    }


    protected class Vertice {
        private ArrayList<Edge> edges;
        private int x, y;

        public Vertice(int x, int y) {
            this.x = x;
            this.y = y;
            edges = new ArrayList<Edge>();
        }

        public int getX(){
            return x;
        }

        public int getY() {
            return y;
        }

        public void addEdge(Edge e) {
            edges.add(e);
        }

        public ArrayList<Edges> getEdges() {
            return edges;
        }

        @Override
        public boolean equals(Vertice v) {
            return (x == v.getX() && y ==v.getY());
        }

    }

    protected class Edge {
        int weight;
        Vertice a, b;

        public Edge(Vertice a, Vertice b) {
            this.a = a;
            this.b = b;
        }

        public Edge(Vertice a, Vertice b, int weight) {
            this.a = a;
            this.b = b;
            this.weight = weight;
        }
    }
}