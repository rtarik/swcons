/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * Tests for the static methods of Commands.
 */
public class CommandsTest {
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    
    @Test
    public void testDifferentiate() {
        assertTrue("Expected differentiated expression",
                Commands.differentiate("x*y", "x").equals("((1)*(y))+((x)*(0))"));
    }
    
    @Test
    public void testSimplify() {
        Map<String, Double> environment = new HashMap<>();
        environment.put("x", 1.0);
        assertEquals("expected simplified expression to be 1*y",
                Commands.simplify("x * y", environment),
                "(1)*(y)");
        environment.put("y",  2.0);
        assertEquals("expected simplified expression to be 2",
                Commands.simplify("x * y", environment),
                "2");
    }
    
}
