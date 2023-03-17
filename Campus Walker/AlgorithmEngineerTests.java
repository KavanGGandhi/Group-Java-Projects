// --== CS400 File Header Information ==--
// Name: Sarthak Agrawal
// Email: sperumal@wisc.edu
// Team: ED
// TA: Sujitha
// Lecturer: Florian
// Notes to Grader: none

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the implementation of CS400Graph for the individual component of
 * Project Three: the implementation of Dijsktra's Shortest Path algorithm.
 */
public class AlgorithmEngineerTests {

    private Graph<String, Integer> graph;

    /**
     * Instantiate graph.
     */
    @BeforeEach
    public void createGraph() {
        graph = new Graph<>();
        // insert vertices A-F
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        // insert edges
        graph.insertEdge("A", "B", 6);
        graph.insertEdge("A", "C", 2);
        graph.insertEdge("A", "D", 5);
        graph.insertEdge("B", "A", 1);
        graph.insertEdge("B", "C", 2);
        graph.insertEdge("C", "B", 3);
    }

    /**
     * Testing if the getAllPathsCost returns the correct distances.
     */
    @Test
    public void checkPathsCosts() {
        List<Double> res = graph.getAllPathsCost("A");

        if (res.size() != 3) {
            fail();
        }

        assertEquals("[2.0, 5.0, 5.0]", res.toString());
    }

    /**
     * Testing if the getAllPaths returns the correct paths.
     */
    @Test
    public void checkPaths() {
        List<List<String>> res = graph.getAllPaths("A");

        if (res.size() != 3) {
            fail();
        }

        assertEquals("[[A, C], [A, C, B], [A, D]]", res.toString());
    }

    /**
     * Checks whether the correct error is thrown when an invalid start node is passed into the function
     */
    @Test
    public void checkInvalidStart() {
        try {
            List<List<String>> res = graph.getAllPaths("E");
        } catch (NoSuchElementException e) {
            return;
        }

        fail();
    }

    /**
     * Testing the iterable part of Graph
     */
    @Test
    public void testIterable() {
        for (String node: graph) {
            if (!graph.containsVertex(node)) {
                fail();
            }
        }
    }

    /**
     * Testing the getAllPaths with a different start node
     */
    @Test
    public void checkPathsB() {
        List<List<String>> res = graph.getAllPaths("B");

        if (res.size() != 3) {
            fail();
        }

        assertEquals("[[B, A], [B, C], [B, A, D]]", res.toString());
    }
}
