/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class FilterTest {

    /*
     * 
     * writtenBy testing strategy:
     * Partition by:
     * tweets list size: 1, > 1
     * results: 0, 1, > 1
     * 
     * inTimespan testing strategy:
     * Partition by:
     * tweets list size > 1
     * results: 0, 1, > 1
     * 
     * containing testing strategy
     * Partition by:
     * results: 0, 1, >1
     */
    
    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    
    private static final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype", d2);
    private static final Tweet tweet3 = new Tweet(3, "Alyssa", "@bbitdiddle so looking forward to it! yay!!", d2);
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    // Covers tweets list size > 1
    // results: 1
    @Test
    public void testWrittenByMultipleTweetsSingleResult() {
        List<Tweet> writtenBy = Filter.writtenBy(Arrays.asList(tweet1, tweet2), "alyssa");
        
        assertEquals("expected singleton list", 1, writtenBy.size());
        assertTrue("expected list to contain tweet", writtenBy.contains(tweet1));
    }
    
    // Covers tweets list size > 1
    // results: 0
    @Test
    public void testWrittenByMultipleTweetsNoResult() {
        List<Tweet> writtenBy = Filter.writtenBy(Arrays.asList(tweet1, tweet2), "tarik");
        
        assertTrue("expected empty list", writtenBy.isEmpty());
    }
    
    // Covers tweets list size > 1
    // results: > 1
    @Test
    public void testWrittenByMultipleTweetsMultipleResults() {
        List<Tweet> writtenBy = Filter.writtenBy(Arrays.asList(tweet1, tweet3), "alyssa");
        
        assertEquals("expected two elements list", 2, writtenBy.size());
        assertTrue("expected list to contain tweets", writtenBy.containsAll(Arrays.asList(tweet1, tweet3)));
        assertEquals("expected same order", 0, writtenBy.indexOf(tweet1));
    }
    
    // Covers tweets list size = 1
    // results: 0
    @Test
    public void testWrittenBySingleTweetNoResult() {
        List<Tweet> writtenBy = Filter.writtenBy(Arrays.asList(tweet1), "tarik");
        
        assertTrue("expected empty list", writtenBy.isEmpty());
    }
    
    // Covers tweets list size = 1
    // results: 1
    @Test
    public void testWrittenBySingleTweetSingleResult() {
        List<Tweet> writtenBy = Filter.writtenBy(Arrays.asList(tweet1), "alyssa");
        
        assertEquals("expected singleton list", 1, writtenBy.size());
        assertTrue("expected list to contain tweet", writtenBy.contains(tweet1));
    }
    
    // Covers results > 1
    @Test
    public void testInTimespanMultipleResults() {
        Instant testStart = Instant.parse("2016-02-17T09:00:00Z");
        Instant testEnd = Instant.parse("2016-02-17T12:00:00Z");
        
        List<Tweet> inTimespan = Filter.inTimespan(Arrays.asList(tweet1, tweet2), new Timespan(testStart, testEnd));
        
        assertFalse("expected non-empty list", inTimespan.isEmpty());
        assertTrue("expected list to contain tweets", inTimespan.containsAll(Arrays.asList(tweet1, tweet2)));
        assertEquals("expected same order", 0, inTimespan.indexOf(tweet1));
    }
    
    // Covers results = 1
    @Test
    public void testInTimespanSingleResult() {
        Instant testStart = Instant.parse("2016-02-17T09:00:00Z");
        Instant testEnd = Instant.parse("2016-02-17T10:30:00Z");
        
        List<Tweet> inTimespan = Filter.inTimespan(Arrays.asList(tweet1, tweet2), new Timespan(testStart, testEnd));
        
        assertEquals("expected singleton list", 1, inTimespan.size());
    }
    
    // Covers results = 0
    @Test
    public void testInTimespanNoResult() {
        Instant testStart = Instant.parse("2016-02-17T10:25:00Z");
        Instant testEnd = Instant.parse("2016-02-17T10:30:00Z");
        
        List<Tweet> inTimespan = Filter.inTimespan(Arrays.asList(tweet1, tweet2), new Timespan(testStart, testEnd));
        
        assertTrue("expected empty list", inTimespan.isEmpty());
    }
    
    // Covers results > 1
    @Test
    public void testContainingMultipleResults() {
        List<Tweet> containing = Filter.containing(Arrays.asList(tweet1, tweet2), Arrays.asList("talk"));
        
        assertFalse("expected non-empty list", containing.isEmpty());
        assertTrue("expected list to contain tweets", containing.containsAll(Arrays.asList(tweet1, tweet2)));
        assertEquals("expected same order", 0, containing.indexOf(tweet1));
    }
    
    // Covers results = 1
    @Test
    public void testContainingSingleResult() {
        List<Tweet> containing = Filter.containing(Arrays.asList(tweet3, tweet1), Arrays.asList("Talk"));
        
        assertTrue("expected list to contain tweets", containing.contains(tweet1));
        assertEquals("expected singleton list", 1, containing.size());
    }
    
    //Covers results = 0
    @Test
    public void testContainingNoResult() {
        List<Tweet> containing = Filter.containing(Arrays.asList(tweet2, tweet3, tweet1), Arrays.asList("chloup"));
        
        assertTrue("expected empty list", containing.isEmpty());
    }

    /*
     * Warning: all the tests you write here must be runnable against any Filter
     * class that follows the spec. It will be run against several staff
     * implementations of Filter, which will be done by overwriting
     * (temporarily) your version of Filter with the staff's version.
     * DO NOT strengthen the spec of Filter or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in Filter, because that means you're testing a stronger
     * spec than Filter says. If you need such helper methods, define them in a
     * different class. If you only need them in this test class, then keep them
     * in this test class.
     */

}
