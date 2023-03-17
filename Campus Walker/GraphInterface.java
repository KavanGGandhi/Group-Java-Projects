// --== CS400 Project Three File Header ==--
// Name: Sarthak Agrawal
// CSL Username: sarthak
// Email: skagrawal@wisc.edu
// Lecture #: <003 @2:25pm>

import java.util.List;

public interface GraphInterface<NodeType, EdgeType extends Number> extends GraphADT<NodeType, EdgeType>, Iterable<NodeType> {
    
    /**
     * @param start
     * Uses Dijkistra's algorithm to find all the shortest paths to reach every node from the starting node
     * @return a list of paths, each path is a Nodes
     */
    public List<List<NodeType>> getAllPaths(NodeType start);
    
    /**
     * @param start
     * Uses Dijkistra's algorithm to find all the shortest paths cost to reach every node from the starting node
     * @return a list of costs
     */
    public List<Double> getAllPathsCost(NodeType start);

}