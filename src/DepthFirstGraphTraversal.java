/***
 @author Kurt Lewis
 Traverses all edges & vertices connected to the randomly selected starting vertex
 ***/

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;
import java.util.ArrayList;
import java.util.Stack;
import java.util.HashSet;

public class DepthFirstGraphTraversal extends EuclideanGraph {
    private Stack<Edge> edgeStack;
    private HashSet<Vertex> visitedVertices;
    private HashSet<Edge> visitedEdges;

    public DepthFirstGraphTraversal() {
        super("Depth First Graph Traversal");
        edgeStack = new Stack<Edge>();
        visitedVertices = new HashSet<Vertex>();
        visitedEdges = new HashSet<Edge>();

        // Select random vertice to start on
        Random generator = new Random();
        Vertex v = vertices.get(generator.nextInt(vertices.size()));
        v.setColor(selectedVertexColor);
        visitedVertices.add(v);
        edgeStack.addAll(v.getEdges());
    }

    @Override
    public boolean cycle() {
        // If nothing is in the stack, the traversal is over
        if (edgeStack.empty()) {
            return true;
        }
        Edge e = edgeStack.pop();

        // Mark edge as visited
        visitedEdges.add(e);
        e.setColor(selectedEdgeColor);

        // Determine if the vertice has been visited
        Vertex v = null;
        if (!visitedVertices.contains(e.getVertexA())) {
            v = e.getVertexA();
        } else if (!visitedVertices.contains(e.getVertexB())) {
            v = e.getVertexB();
        }
        if (v != null) {
            // A vertice was found that hasn't been visited
            for (Edge newEdge : v.getEdges()) {
                // Only add edges that haven't been visited yet
                if (!visitedEdges.contains(newEdge)) {
                    edgeStack.add(newEdge);
                }
            }
            // Mark vertice as visited
            v.setColor(selectedVertexColor);
            visitedVertices.add(v);
        }
        return false;
    }

    @Override
    public int getDelay() {
        return 200;
    }


}