// --== CS400 Project Three File Header ==--
// Name: Kavan Gandhi
// CSL Username: kavan
// Email: kgandhi4@wisc.edu
// Lecture #: <003 @2:25pm>

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BackendDeveloperTests {

    private GraphInterface<String, Integer> graph;
    
    /**
     * Instantiate graph.
     */
    @BeforeEach
    public void createGraph() {
        graph = new BDGraph<String, Integer>();
        // insert vertices A-F
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E");
        graph.insertVertex("F");
        // insert edges
        graph.insertEdge("A","B",6);
        graph.insertEdge("A","C",2);
        graph.insertEdge("A","D",5);
        graph.insertEdge("B","E",1);
        graph.insertEdge("B","C",2);
        graph.insertEdge("C","B",3);
        graph.insertEdge("C","F",1);
        graph.insertEdge("D","E",3);
        graph.insertEdge("E","A",4);
        graph.insertEdge("F","A",1);
        graph.insertEdge("F","D",1);
    }

    /**
     * Checks the closest builduing to A.
     */
    @Test
    public void testClosestBuilduing() {
        Backend<String, Integer> testBackend = new Backend<String, Integer>(graph);
        assertEquals(testBackend.getClosestBuilding("A").getKey(), "C");
    }

    /**
     * Checks the farthest builduing from B.
     */
    @Test
    public void testFarthestBuilduing() {
        Backend<String, Integer> testBackend = new Backend<String, Integer>(graph);
        assertEquals(testBackend.getFarthestBuilding("B").getKey(), "A");
    }

    /**
     * Checks all paths from A.
     */
    @Test
    public void testGetAllPaths() {
        Backend<String, Integer> testBackend = new Backend<String, Integer>(graph);
        List<List<String>> allPaths = testBackend.getAllPathsBackend("A");
        assertTrue(allPaths.get(0).toString().equals("[A]"));
        assertTrue(allPaths.get(1).toString().equals("[A, C, F]"));
        assertTrue(allPaths.get(2).toString().equals( "[A, C, B, E]"));
        assertTrue(allPaths.get(3).toString().equals( "[A, C, F, D]"));
        assertTrue(allPaths.get(4).toString().equals( "[A, C]"));
        assertTrue(allPaths.get(5).toString().equals( "[A, C, B]"));
    }

    /**
     * Checks all path costs from A.
     */
    @Test
    public void testGetAllPathsCost() {
        Backend<String, Integer> testBackend = new Backend<String, Integer>(graph);
        List<List<String>> allPaths = testBackend.getAllPathsBackend("A");
        List<Pair<List<String>, Integer>> allPathCosts = testBackend.getAllPathsCost(allPaths);
        assertEquals(allPathCosts.get(0).getValue().toString(), "0");
        assertEquals(allPathCosts.get(1).getValue().toString(), "3");
        assertEquals(allPathCosts.get(2).getValue().toString(), "6");
        assertEquals(allPathCosts.get(3).getValue().toString(), "4");
        assertEquals(allPathCosts.get(4).getValue().toString(), "2");
        assertEquals(allPathCosts.get(5).getValue().toString(), "5");
    }

    /**
     * Checks the shortest path from A to E is given correctly.
     */
    @Test
    public void testShortestPath() {
        Backend<String, Integer> testBackend = new Backend<String, Integer>(graph);
        assertTrue(testBackend.getShortestPath("A", "E").toString().equals(
            "[A, C, B, E]"
        ));
    }

    /**
     * Checks the closest builduing to A using the Algorithm Engineer's Graph object
     */
    @Test
    public void testClosestBuilduingIntegration() {
        GraphInterface<String, Integer> integrationGraph = new Graph<String, Integer>();
        Backend<String, Integer> testBackend = new Backend<String, Integer>(integrationGraph);
        // insert vertices A-F
        integrationGraph.insertVertex("A");
        integrationGraph.insertVertex("B");
        integrationGraph.insertVertex("C");
        integrationGraph.insertVertex("D");
        integrationGraph.insertVertex("E");
        integrationGraph.insertVertex("F");
        // insert edges
        integrationGraph.insertEdge("A","B",6);
        integrationGraph.insertEdge("A","C",2);
        integrationGraph.insertEdge("A","D",5);
        integrationGraph.insertEdge("B","E",1);
        integrationGraph.insertEdge("B","C",2);
        integrationGraph.insertEdge("C","B",3);
        integrationGraph.insertEdge("C","F",1);
        integrationGraph.insertEdge("D","E",3);
        integrationGraph.insertEdge("E","A",4);
        integrationGraph.insertEdge("F","A",1);
        integrationGraph.insertEdge("F","D",1);

        assertEquals(testBackend.getClosestBuilding("A").getKey(), "C");
    }

    /**
     * Checks the farthest builduing to A using the Algorithm Engineer's Graph object
     */
    @Test
    public void testFarthestBuilduingIntegration() {
        GraphInterface<String, Integer> integrationGraph = new Graph<String, Integer>();
        Backend<String, Integer> testBackend = new Backend<String, Integer>(integrationGraph);
        // insert vertices A-F
        integrationGraph.insertVertex("A");
        integrationGraph.insertVertex("B");
        integrationGraph.insertVertex("C");
        integrationGraph.insertVertex("D");
        integrationGraph.insertVertex("E");
        integrationGraph.insertVertex("F");
        // insert edges
        integrationGraph.insertEdge("A","B",6);
        integrationGraph.insertEdge("A","C",2);
        integrationGraph.insertEdge("A","D",5);
        integrationGraph.insertEdge("B","E",1);
        integrationGraph.insertEdge("B","C",2);
        integrationGraph.insertEdge("C","B",3);
        integrationGraph.insertEdge("C","F",1);
        integrationGraph.insertEdge("D","E",3);
        integrationGraph.insertEdge("E","A",4);
        integrationGraph.insertEdge("F","A",1);
        integrationGraph.insertEdge("F","D",1);

        assertEquals(testBackend.getFarthestBuilding("A").getKey(), "E");
    }

    /**
     * Checks the AE's implementation of shortest path
     */
    @Test
    public void CodeReviewOfAlgorithmEngineerPathFromAToE() {
        GraphInterface<String, Integer> integrationGraph = new Graph<String, Integer>();
        Backend<String, Integer> testBackend = new Backend<String, Integer>(integrationGraph);
        // insert vertices A-F
        integrationGraph.insertVertex("A");
        integrationGraph.insertVertex("B");
        integrationGraph.insertVertex("C");
        integrationGraph.insertVertex("D");
        integrationGraph.insertVertex("E");
        integrationGraph.insertVertex("F");
        // insert edges
        integrationGraph.insertEdge("A","B",6);
        integrationGraph.insertEdge("A","C",2);
        integrationGraph.insertEdge("A","D",5);
        integrationGraph.insertEdge("B","E",1);
        integrationGraph.insertEdge("B","C",2);
        integrationGraph.insertEdge("C","B",3);
        integrationGraph.insertEdge("C","F",1);
        integrationGraph.insertEdge("D","E",3);
        integrationGraph.insertEdge("E","A",4);
        integrationGraph.insertEdge("F","A",1);
        integrationGraph.insertEdge("F","D",1);

        assertTrue(integrationGraph.shortestPath("A", "E").toString().equals("[A, C, B, E]"));
    }

     /**
     * Checks the AE's implementation of getPathCost
     */
    @Test
    public void CodeReviewOfAlgorithmEngineerPathCostFromAToE() {
        GraphInterface<String, Integer> integrationGraph = new Graph<String, Integer>();
        Backend<String, Integer> testBackend = new Backend<String, Integer>(integrationGraph);
        // insert vertices A-F
        integrationGraph.insertVertex("A");
        integrationGraph.insertVertex("B");
        integrationGraph.insertVertex("C");
        integrationGraph.insertVertex("D");
        integrationGraph.insertVertex("E");
        integrationGraph.insertVertex("F");
        // insert edges
        integrationGraph.insertEdge("A","B",6);
        integrationGraph.insertEdge("A","C",2);
        integrationGraph.insertEdge("A","D",5);
        integrationGraph.insertEdge("B","E",1);
        integrationGraph.insertEdge("B","C",2);
        integrationGraph.insertEdge("C","B",3);
        integrationGraph.insertEdge("C","F",1);
        integrationGraph.insertEdge("D","E",3);
        integrationGraph.insertEdge("E","A",4);
        integrationGraph.insertEdge("F","A",1);
        integrationGraph.insertEdge("F","D",1);

        assertTrue(integrationGraph.getPathCost("A", "E") == 6);
    }
}
