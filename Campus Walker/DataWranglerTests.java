import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

// --== CS400 Project Three File Header ==--
// Name: Michael Dinh
// CSL Username: mdinh
// Email: mtdinh@wisc.edu
// Lecture #: <003 @2:25pm>
public class DataWranglerTests {

    public static void main (String[] args){
        // not needed
    }

    /**
     * This method tests if the amount of vertexes read in from the dot file is the expected amount
     * This ensures there are no duplicate vertices in the graph
     */
    @Test
    void test1() throws FileNotFoundException {

        GraphInterface<String, Integer> testGraph = null;
        dwGraphLoader loader = new dwGraphLoader();
        testGraph = loader.load("campusNetwork.dot");

        // assertEquals(testGraph.iterator()., 25);
    }

    /**
     * This method tests if the amount of edges read in from the dot file is the expected amount
     * This ensures there are no duplicate edges in the graph
     */
    @Test
    public void test2() throws FileNotFoundException {
        dwGraph testGraph = null;
        dwGraphLoader loader = new dwGraphLoader();
        testGraph = (dwGraph) loader.load("campusNetwork.dot");

        assertEquals(testGraph.edgeList.size(), 31);
    }

    /**
     * This method tests if vertices read in from the dot file are formatted correctly
     *
     */
    @Test
    public void test3() throws FileNotFoundException {
        dwGraph testGraph = null;
        dwGraphLoader loader = new dwGraphLoader();
        testGraph = (dwGraph) loader.load("campusNetwork.dot");

        assertEquals(testGraph.vertexList.get(0), "Bradley");

    }

    /**
     * This method tests if edges read in from the dot file are formatted correctly
     *
     */
    @Test
    public void test4() throws FileNotFoundException {

        dwGraph testGraph = null;
        dwGraphLoader loader = new dwGraphLoader();
        testGraph = (dwGraph) loader.load("campusNetwork.dot");

        assertEquals(testGraph.edgeList.get(0), "Bradley -- Cole: 223");
    }

    /**
     * This method tests and verifies that duplicate vertices and duplicate edges will not be added.
     *
     */
    @Test
    public void test5() throws FileNotFoundException {
        dwGraph testGraph = null;
        dwGraphLoader loader = new dwGraphLoader();
        testGraph = (dwGraph) loader.load("campusNetwork.dot");

        assertEquals(testGraph.vertexList.size(), 25);
        testGraph.insertVertex("Bradley");

        // size of vertex list should not change
        assertEquals(testGraph.vertexList.size(), 25);

        assertEquals(testGraph.edgeList.size(), 31);
        testGraph.insertEdge("Bradley", "Cole", 223);
        assertEquals(testGraph.edgeList.size(), 31);

    }
}
