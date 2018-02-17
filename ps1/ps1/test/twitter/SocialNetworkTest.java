/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class SocialNetworkTest {

    /*
     * testing guessFollowsGraph strategy:
     * Partition by:
     * graph type: empty, no follows, with follows
     * 
     * testing influencers strategy:
     * Partition by:
     * graph type: empty, with follows
     */
    
    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    
    private static final Tweet tweet1 = new Tweet(1, "alyssa", "@Bbitdiddle @Tarik is it reasonable to talk about rivest so much?", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "@eric check this out, rivest talk in 30 minutes #hype", d2);
    private static final Tweet tweet3 = new Tweet(3, "tarik", "Who is this rivest person anyway?", d2);
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    // Covers graph type empty
    @Test
    public void testGuessFollowsGraphEmpty() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(new ArrayList<>());
        
        assertTrue("expected empty graph", followsGraph.isEmpty());
    }
    
    // Covers graph no followers
    @Test
    public void testGuessFollowsGraphNoFollows() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet3));
        
        assertTrue("expected empty set of followers", followsGraph.get("tarik").isEmpty());
    }
    
    // Covers graph with followers
    @Test
    public void testGuessFollowsGraphWithFollows() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet3, tweet1, tweet2));
        
        assertTrue("expected zero follows for tarik", followsGraph.get("tarik").isEmpty());
        assertEquals("expected two follows for alyssa", 2, followsGraph.get("alyssa").size());
        assertEquals("expected one follow for bbitdiddle", 1, followsGraph.get("bbitdiddle").size());
    }
    
    // Covers empty graph
    @Test
    public void testInfluencersEmpty() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        
        assertTrue("expected empty list", influencers.isEmpty());
    }
    
    // Covers with follows case
    @Test
    public void testInfluencersWithFollows() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        Set<String> tarikFollows = new HashSet<>();
        tarikFollows.add("alyssa");
        tarikFollows.add("bbitdiddle");
        Set<String> alyssaFollows = new HashSet<>();
        alyssaFollows.add("bbitdiddle");
        followsGraph.put("tarik",  tarikFollows);
        followsGraph.put("alyssa", alyssaFollows);
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        
        assertEquals("expected at index 0", 0, influencers.indexOf("bbitdiddle"));
        assertEquals("expected at index 1", 1, influencers.indexOf("alyssa"));
        assertEquals("expected at index 2", 2, influencers.indexOf("tarik"));
    }

    /*
     * Warning: all the tests you write here must be runnable against any
     * SocialNetwork class that follows the spec. It will be run against several
     * staff implementations of SocialNetwork, which will be done by overwriting
     * (temporarily) your version of SocialNetwork with the staff's version.
     * DO NOT strengthen the spec of SocialNetwork or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in SocialNetwork, because that means you're testing a
     * stronger spec than SocialNetwork says. If you need such helper methods,
     * define them in a different class. If you only need them in this test
     * class, then keep them in this test class.
     */

}
