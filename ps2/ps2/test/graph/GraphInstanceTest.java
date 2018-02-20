/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import java.util.Collections;

import org.junit.Test;

/**
 * Tests for instance methods of Graph.
 * 
 * <p>PS2 instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {
    
    // Testing strategy
    //  add(L vertex)
    //  graph with vertex, graph without vertex
    
    //  set(L source, L target, int weight);
    //  vertex already existing, vertex not existing
    //  weight 0, > 0
    //  
    //  remove(L vertex)
    //  no vertex in the graph
    //  vertex in the graph but no edges touching the vertex
    //  vertex with at least an edge coming out of the vertex
    //  vertex with at least an edge coming in to the vertex
    //
    //  sources(L target)
    //  no edges to target, at least one edge going to target
    //
    //  targets(L source)
    //  no edges from source, at least one edge from source
    
    /**
     * Overridden by implementation-specific test classes.
     * 
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testInitialVerticesEmpty() {
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }
    
    // Covers both add to a graph with the given vertex and without
    @Test
    public void testAdd() {
        Graph<String> graph = emptyInstance();
        assertTrue("expected graph to not contain the vertex", graph.add("a"));
        assertFalse("expected graph to contain the vertex", graph.add("a"));
        assertEquals("expected graph to contain one vertex", 1, graph.vertices().size());
        
    }
    
    // Covers all possible combinations
    @Test
    public void testSet() {
        Graph<String> graph = emptyInstance();
        // weight 0 on non existing vertex
        assertEquals("expected return value to be zero", 0, graph.set("a",  "b", 0));
        // weight > 0 on non existing vertex
        assertEquals("expected return value to be zero", 0, graph.set("a",  "b",  1));
        // weight > 0 on existing vertex
        assertEquals("expected return value to be one", 1, graph.set("a",  "b",  2));
        // weight 0 on existing vertex
        assertEquals("expected return value to be 2", 2, graph.set("a",  "b",  0));
    }
    
    // Covers vertex not already existing in the graph
    @Test
    public void testRemoveNoVertex() {
        Graph<String> graph = emptyInstance();
        assertFalse("expected return false value", graph.remove("a"));
    }
    
    // Covers vertex in the graph but touching no edges
    @Test
    public void testRemoveVertexNoEdges() {
        Graph<String> graph = emptyInstance();
        graph.add("a");
        assertEquals("expected graph to contain the vertex", 1, graph.vertices().size());
        assertTrue("expected return true value", graph.remove("a"));
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }
    
    // Covers source vertex in the graph
    @Test
    public void testRemoveSourceVertex() {
        Graph<String> graph = emptyInstance();
        graph.set("a",  "b",  1);
        assertEquals("expected graph to contain two vertices", 2, graph.vertices().size());
        assertTrue("expected return true value", graph.remove("a"));
        assertFalse("expected vertex removed", graph.vertices().contains("a"));
    }
    
    // Covers target vertex in the graph
    @Test
    public void testRemoveTargetVertex() {
        Graph<String> graph = emptyInstance();
        graph.set("a",  "b",  1);
        assertEquals("expected graph to contain two vertices", 2, graph.vertices().size());
        assertTrue("expected return true value", graph.remove("b"));
        assertFalse("expected vertex removed", graph.vertices().contains("b"));
    }
    
    // Covers no edges from source
    @Test
    public void testTargetsEmpty() {
        Graph<String> graph = emptyInstance();
        assertTrue("expected empty map", graph.targets("a").isEmpty());
    }
    
    // Covers at least one edge from source
    @Test
    public void testTargetsNonEmpty() {
        Graph<String> graph = emptyInstance();
        graph.set("a",  "b",  1);
        graph.set("a",  "c",  2);
        assertEquals("expected weight 2", 2, (int) graph.targets("a").get("c"));
    }
    
    // Covers no edges to target
    @Test
    public void testSourcesEmpty() {
        Graph<String> graph = emptyInstance();
        assertTrue("expected empty map", graph.sources("a").isEmpty());
    }
    
 // Covers at least one edge to target
    @Test
    public void testSourcesNonEmpty() {
        Graph<String> graph = emptyInstance();
        graph.set("b",  "a",  1);
        graph.set("c",  "a",  2);
        assertEquals("expected weight 1", 1, (int) graph.sources("a").get("b"));
    }
    
}
