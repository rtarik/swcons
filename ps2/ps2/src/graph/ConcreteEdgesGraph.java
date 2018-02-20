/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Graph.
 * 
 * <p>
 * PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph implements Graph<String> {

    private final Set<String> vertices = new HashSet<>();
    private final List<Edge> edges = new ArrayList<>();

    // Abstraction function:
    // Graph with the set of vertices and edges between them
    // Representation invariant:
    // for each edge, source and target are both in the vertices
    // Safety from rep exposure:
    // getters use a defensive copy

    public ConcreteEdgesGraph() {

    }

    private void checkRep() {
        for (Edge edge : edges) {
            if (!vertices.contains(edge.getSource()) || !vertices.contains(edge.getTarget())) {
                throw new IllegalStateException();
            }
        }
    }

    @Override
    public boolean add(String vertex) {
        boolean isInGraph = vertices.contains(vertex);
        vertices.add(vertex);
        checkRep();
        return !isInGraph;
    }

    @Override
    public int set(String source, String target, int weight) {
        Edge previousEdge = null;
        int result;
        for (Edge edge : edges) {
            if (edge.getSource().equals(source) && edge.getTarget().equals(target)) {
                previousEdge = edge;
                break;
            }
        }
        if (previousEdge == null && weight != 0) { // edges didn't exist before
            vertices.add(source);
            vertices.add(target);
            edges.add(new Edge(source, target, weight));
            result = 0;
        } else if (previousEdge == null && weight == 0) {
            result = 0;
        } else {
            edges.remove(previousEdge);
            if (weight != 0)
                edges.add(new Edge(source, target, weight));
            result = previousEdge.getWeight();
        }
        checkRep();
        return result;
    }

    @Override
    public boolean remove(String vertex) {
        boolean isInGraph = vertices.contains(vertex);
        vertices.remove(vertex);
        for (Iterator<Edge> iterator = edges.iterator(); iterator.hasNext();) {
            Edge edge = iterator.next();
            if (edge.getSource().equals(vertex) || edge.getTarget().equals(vertex)) {
                iterator.remove();
            }
        }
        return isInGraph;
    }

    @Override
    public Set<String> vertices() {
        return vertices;
    }

    @Override
    public Map<String, Integer> sources(String target) {
        Map<String, Integer> sourcesMap = new HashMap<>();
        for (Edge edge : edges) {
            if (edge.getTarget().equals(target)) {
                sourcesMap.put(edge.getSource(), edge.getWeight());
            }
        }
        return sourcesMap;
    }

    @Override
    public Map<String, Integer> targets(String source) {
        Map<String, Integer> targetsMap = new HashMap<>();
        for (Edge edge : edges) {
            if (edge.getSource().equals(source)) {
                targetsMap.put(edge.getTarget(), edge.getWeight());
            }
        }
        return targetsMap;
    }

    @Override
    public String toString() {
        return "ConcreteEdgesGraph with " + vertices.size() + " vertices and " + edges.size() + " edges";
    }

}

/**
 * Immutable. This class is internal to the rep of ConcreteEdgesGraph.
 * 
 * <p>
 * PS2 instructions: the specification and implementation of this class is up to
 * you.
 */
class Edge {

    private String source;
    private String target;
    private int weight;

    // Abstraction function:
    // Represents and edge going from source to target and a weight
    // Representation invariant:
    // source and target are non empty strings
    // weight is positive
    // Safety from rep exposure:
    // All fields are immutable and private

    public Edge(String source, String target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
        checkRep();
    }

    private void checkRep() {
        if (source.isEmpty() || target.isEmpty() || weight <= 0) {
            throw new IllegalStateException();
        }
    }

    // TODO methods
    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return source + "-(" + weight + ")->" + target;
    }

}
