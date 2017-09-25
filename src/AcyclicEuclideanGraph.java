/**
 * Acyclic extension of the euclidean graph
 * @author Kurt Lewis
 * @see EuclideanGraph
 */

import java.awt.Color;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

public abstract class AcyclicEuclideanGraph extends EuclideanGraph {

    public AcyclicEuclideanGraph(String title) {
        super(title);
        breakCycles();
    }

    public AcyclicEuclideanGraph(String title, Color backgroundColor, Color titleColor, Color vertexColor, Color edgeColor) {
        super(title, backgroundColor, titleColor, vertexColor, edgeColor);
        breakCycles();
    }

    /**
     * Removes cycles from the graph generated in EuclideanGraph()
     */
    public void breakCycles() {
        for (Vertex v : vertices) {
            // TODO: Optimize and comment
            HashSet<Vertex> visitedVertices = new HashSet<Vertex>();
            HashSet<Edge> visitedEdges = new HashSet<Edge>();
            Stack<Edge> edgeStack = new Stack<Edge>();
            edgeStack.addAll(v.getEdges());
            visitedEdges.addAll(v.getEdges());
            while (!edgeStack.isEmpty()) {
                Edge e = edgeStack.pop();
                if (visitedVertices.contains(e.getVertexA())
                    && visitedVertices.contains(e.getVertexB())) {
                    edges.remove(e);
                    e.getVertexA().getEdges().remove(e);
                    e.getVertexB().getEdges().remove(e);
                } else {
                    for (Edge e2 : e.getVertexA().getEdges()) {
                        if (!visitedEdges.contains(e2)) {
                            visitedEdges.add(e2);
                            edgeStack.add(e2);
                        }
                    }
                    for (Edge e2 : e.getVertexB().getEdges()) {
                        if (!visitedEdges.contains(e2)) {
                            visitedEdges.add(e2);
                            edgeStack.add(e2);
                        }
                    }
                }
            }
        }
    }
}