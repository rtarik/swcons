/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package poet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import graph.Graph;

/**
 * A graph-based poetry generator.
 * 
 * <p>GraphPoet is initialized with a corpus of text, which it uses to derive a
 * word affinity graph.
 * Vertices in the graph are words. Words are defined as non-empty
 * case-insensitive strings of non-space non-newline characters. They are
 * delimited in the corpus by spaces, newlines, or the ends of the file.
 * Edges in the graph count adjacencies: the number of times "w1" is followed by
 * "w2" in the corpus is the weight of the edge from w1 to w2.
 * 
 * <p>For example, given this corpus:
 * <pre>    Hello, HELLO, hello, goodbye!    </pre>
 * <p>the graph would contain two edges:
 * <ul><li> ("hello,") -> ("hello,")   with weight 2
 *     <li> ("hello,") -> ("goodbye!") with weight 1 </ul>
 * <p>where the vertices represent case-insensitive {@code "hello,"} and
 * {@code "goodbye!"}.
 * 
 * <p>Given an input string, GraphPoet generates a poem by attempting to
 * insert a bridge word between every adjacent pair of words in the input.
 * The bridge word between input words "w1" and "w2" will be some "b" such that
 * w1 -> b -> w2 is a two-edge-long path with maximum-weight weight among all
 * the two-edge-long paths from w1 to w2 in the affinity graph.
 * If there are no such paths, no bridge word is inserted.
 * In the output poem, input words retain their original case, while bridge
 * words are lower case. The whitespace between every word in the poem is a
 * single space.
 * 
 * <p>For example, given this corpus:
 * <pre>    This is a test of the Mugar Omni Theater sound system.    </pre>
 * <p>on this input:
 * <pre>    Test the system.    </pre>
 * <p>the output poem would be:
 * <pre>    Test of the system.    </pre>
 * 
 * <p>PS2 instructions: this is a required ADT class, and you MUST NOT weaken
 * the required specifications. However, you MAY strengthen the specifications
 * and you MAY add additional methods.
 * You MUST use Graph in your rep, but otherwise the implementation of this
 * class is up to you.
 */
public class GraphPoet {
    
    private final Graph<String> graph = Graph.empty();
    
    // Abstraction function:
    //   Affinity graph
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    /**
     * Create a new poet with the graph from corpus (as described above).
     * 
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */
    public GraphPoet(File corpus) throws IOException {
        FileReader reader = new FileReader(corpus);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line, lastWord = null, source = null, target = null;
        String[] words = null;
        while ((line = bufferedReader.readLine()) != null) {
            words = line.split("\\s+");
            for (int i = 0; i < words.length; i++) {
                if (i == 0 && lastWord != null) {
                    source = lastWord;
                    target = words[0];
                } else if (i > 0) {
                    source = words[i - 1];
                    target = words[i];
                }
                if (source != null && target != null) {
                    int weight = graph.set(source.toLowerCase(), target.toLowerCase(), 0);
                    graph.set(source.toLowerCase(), target.toLowerCase(), weight + 1);
                }
            }
            lastWord = words[words.length - 1];
        }
        bufferedReader.close();      
    }
    
    // TODO checkRep
    
    /**
     * Generate a poem.
     * 
     * @param input string from which to create the poem
     * @return poem (as described above)
     */
    public String poem(String input) {
        StringBuilder builder = new StringBuilder();
        String[] words = input.split("\\s+");
        String first, second;
        String middle;
        int maxWeight;
        builder.append(words[0]);

        for (int i = 0; i < words.length - 1; i++) {
            middle = "";
            maxWeight = 0;
            first = words[i];
            second = words[i + 1];
            
            for (Map.Entry<String, Integer> middleVertex : graph.targets(first.toLowerCase()).entrySet()) {
                for (Map.Entry<String, Integer> endVertex : graph.targets(middleVertex.getKey()).entrySet()) {
                    if (endVertex.getKey().equals(second.toLowerCase())) {
                        if (middleVertex.getValue() + endVertex.getValue() > maxWeight) {
                            middle = middleVertex.getKey();
                            maxWeight = middleVertex.getValue() + endVertex.getValue();
                        }
                    }
                }
            }
            if (middle.isEmpty()) {
                builder.append(" ").append(second);
            } else {
                builder.append(" ").append(middle).append(" ").append(second);
            }
        }
        return builder.toString();
    }
    
    /**
     * Size of the graph
     * @return the number of vertices (i.e words) in the corpus
     */
    public int size() {
        return graph.vertices().size();
    }
    
    // TODO toString()
    
}
