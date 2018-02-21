/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package poet;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {
    
    // Testing strategy
    //   Constructor
    //
    //   poem(String input)
    //   input is one word, input is multiple words
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    // Covers constructor
    @Test
    public void testGraphPoet() throws IOException {
        GraphPoet graphPoet = new GraphPoet(new File("test/poet/corpus_test.txt"));
        assertEquals("expected graph to have ten vertices", 10, graphPoet.size());
    }
    
    // Covers testing poem with one word input
    @Test
    public void testPoemOneWordInput() throws IOException {
        GraphPoet graphPoet = new GraphPoet(new File("test/poet/corpus_test.txt"));
        String poem = graphPoet.poem("SingleWordPoem");
        assertEquals("One word poem", "SingleWordPoem", poem);
    }
    
    // Covers testing poem with multiple word input
    @Test
    public void testPoemMultipleWordInput() throws IOException {
        GraphPoet graphPoet = new GraphPoet(new File("test/poet/corpus_test.txt"));
        String poem = graphPoet.poem("Seek to explore new and exciting synergies!");
        assertEquals("One word poem", "Seek to explore strange new life and exciting synergies!", poem);
    }
    // TODO tests
    
}
