/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.Set;

import org.junit.Test;

public class ExtractTest {

    /*
     * 
     * getTimespan testing strategy:
     * Partition by:
     * Tweets list size: 1, > 1
     * All tweets have the same timestamp, at least two tweets at different times
     * 
     * getMentionedUsers testing strategy:
     * Partition by:
     * tweets list size: 1, > 1
     * mentioned users: 0, 1, > 1
     */
         
    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    
    private static final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype", d2);
    private static final Tweet tweet3 = new Tweet(3, "tarik", "Hey @Bbitdiddle I sent you an email at bitdiddle@mit.edu", d1);
    private static final Tweet tweet4 = new Tweet(4, "tarik", "@alyssa @bbitdiddle  Who is this rivest person anyway?", d2);
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    
    @Test
    public void testGetTimespanTwoTweets() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet2));
        
        assertEquals("expected start", d1, timespan.getStart());
        assertEquals("expected end", d2, timespan.getEnd());
    }
    
    // Covers tweets list size = 1
    @Test
    public void testGetTimespanSingleTweet() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1));
        
        assertEquals("expected start and end", timespan.getStart(), timespan.getEnd());
    }
    
    // Covers tweets list size > 1
    // All tweets have the same Timestamp
    @Test
    public void testGetTimespanTwoTweetsSameTime() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet3));
        
        assertEquals("expected start and end", timespan.getStart(), timespan.getEnd());
        assertEquals("expected tweets time", timespan.getStart(), d1);
    }
    
    // Covers tweets list size > 1
    // At least two tweets at different times
    @Test
    public void testGetTimespanDifferentTimes() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet2, tweet3));
        
        assertEquals("expected start", d1, timespan.getStart());
        assertEquals("expected end", d2, timespan.getEnd());
    }
    
    // Covers tweets list size = 1
    // Mentioned users = 0
    @Test
    public void testGetMentionedUsersSingleTweetNoMention() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet1, tweet2));
        
        assertTrue("expected empty set", mentionedUsers.isEmpty());
    }
    
 // Covers tweets list size > 1
 // Mentioned users = 0
    @Test
    public void testGetMentionedUsersMultipleTweetsNoMention() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet1, tweet2));
        
        assertTrue("expected empty set", mentionedUsers.isEmpty());
    }
    
 // Covers tweets list size = 1
 // Mentioned users = 1
    @Test
    public void testGetMentionedUsersSingleTweetOneMentionedUser() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet3));
        
        assertEquals("expected one mentioned user", mentionedUsers.size(), 1);
    }
    
 // Covers tweets list size = 1
 // Mentioned users > 1
       @Test
       public void testGetMentionedUsersSingleTweetMultipleMentionedUsers() {
           Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet4));
           
           assertEquals("expected one mentioned user", mentionedUsers.size(), 2);
       }
       
 // Covers tweets list size > 1
 // Mentioned users > 1 and duplicate with different case
       @Test
       public void testGetMentionedUsersMultipleTweetsMultipleMentionedUsers() {
           Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet3, tweet1, tweet4));
           
           assertEquals("expected one mentioned user", mentionedUsers.size(), 2);
       }
             

    /*
     * Warning: all the tests you write here must be runnable against any
     * Extract class that follows the spec. It will be run against several staff
     * implementations of Extract, which will be done by overwriting
     * (temporarily) your version of Extract with the staff's version.
     * DO NOT strengthen the spec of Extract or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in Extract, because that means you're testing a
     * stronger spec than Extract says. If you need such helper methods, define
     * them in a different class. If you only need them in this test class, then
     * keep them in this test class.
     */

}
