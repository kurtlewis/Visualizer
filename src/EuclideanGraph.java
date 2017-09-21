/***
 @author Kurt Lewis
 ***/

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

public abstract class EuclideanGraph extends Graph {
    protected Color edgeColor, vertexColor;

    public EuclideanGraph(String title) {
        super(title);
        buildGraph();
    }

    public EuclideanGraph(String title, Color backgroundColor, Color titleColor, Color vertexColor, Color edgeColor) {
        super(title, backgroundColor, titleColor);
        this.vertexColor = vertexColor;
        this.edgeColor = edgeColor;
        buildGraph();
    }


    /*
     * Builds a connected graph with a random number of vertices
     */
    private void buildGraph() {
        Random generator  = new Random();
        // Randomly determine ideal number of vertices
        int numVertices = generator.nextInt(50) + 50;
        // Will be used for removing Vertices too far away from the rest of the graph.
        HashSet<Vertex> verticeSet = new HashSet<Vertex>();
        // padding so vertices arent on the edge of the screen
        int edgePadding = 30;

        // Generate random Vertices and add them to both the member variable list and the scope-local set
        while (vertices.size() < numVertices) {
            // Add DRAW_HEIGHT  - MAX_Y to the Y coordinate because the top pixels
            // are dedicated to the title - offset the y coordinate accordingly
            Vertex v = new Vertex(generator.nextInt(MAX_X - 2 * edgePadding) + edgePadding, 
                                  generator.nextInt(MAX_Y - 2 *edgePadding) + Visualizer.DRAW_HEIGHT - MAX_Y + edgePadding,
                                  vertexColor);

            // Don't allow for Vertices on top of each other
            if (!verticeSet.contains(v))
            {
                verticeSet.add(v);
                vertices.add(v);
            }      
        }
        
        // Add edges until either a connected graph is found or there are a 
        // set amount of edges
        int consecutiveFailures = 0;
        while (!verticeSet.isEmpty() && edges.size() < vertices.size() * 3 && consecutiveFailures < 1000) {
            Vertex vertexA = vertices.get(generator.nextInt(vertices.size()));
            Vertex vertexB = vertices.get(generator.nextInt(vertices.size()));

            Edge e = new Edge(vertexA, vertexB, edgeColor);

            boolean intersects = false;
            for (Edge edgeTwo : edges) {
                if (e.intersects(edgeTwo)) {
                    intersects = true;
                    break;
                }
            }
            if (intersects) {
                consecutiveFailures++;
                continue;
            }

            // By experimentation, the optimal max weight seems to be around 1/5 the size of the draw area
            // This optimizes number of nodes
            if (!edges.contains(e) && e.getWeight() < MAX_Y / 5 && !vertexA.equals(vertexB)) {
                edges.add(e);
                vertexA.addEdgeWeightSorted(e);
                vertexB.addEdgeWeightSorted(e);
                if (verticeSet.contains(vertexA)) {
                    verticeSet.remove(vertexA);
                }
                if (verticeSet.contains(vertexB)) {
                    verticeSet.remove(vertexB);
                }
                consecutiveFailures = 0;
            } else {
                consecutiveFailures++;
            }

        }

        // If a Vertex couldn't be removed from the set, remove it from the graph
        // because it's too far away or was just unlucky
        for (Vertex v: verticeSet) {
            vertices.remove(v);
        }
        
    }
}