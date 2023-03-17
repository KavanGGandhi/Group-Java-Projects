// --== CS400 Project Three File Header ==--
// Name: Kavan Gandhi
// CSL Username: kavan
// Email: kgandhi4@wisc.edu
// Lecture #: <003 @2:25pm>

import java.util.ArrayList;
import java.util.List;

/**
 * This interface defines methods that get the paths from one node to all nodes, as well as the weights of those paths.
 * This can then be used by the Frontend.
 * 
 * @param NodeType the data type stored at each graph node
 * @param EdgeType the data type stored at each graph edge as a Number whose doubleValue() method returns a value >=0.0
 */
public interface BackendInterface<NodeType,EdgeType extends Number> {

  /**
   * Returns the shortest path between start Node and end Node.
   * Uses Dijkstra's shortest path algorithm to find the shortest path.
   * Allows Frontend Developer access to this method implemented by Algorithm Engineer.
   *
   * @param start the data item in the starting vertex for the path
   * @param end the data item in the destination vertex for the path
   * @return list of data item in nodes in order on the shortest path between the start node and end node, including both start and end
   */
  
  public List<NodeType> getShortestPath(NodeType start, NodeType end);
  
  /**
   * Takes a starting node, and returns a list of paths (which themselves are a list of nodes).
   * Each path details the path from the start node to another node, and there 
   * is a path from the starting node to all other nodes. 
   * Allows Frontend Developer access to this method implemented by Algorithm Engineer.
   * 
   * @param start The starting node
   * @return A list that contains a list of paths from the start node to all other nodes
   */
  public List<List<NodeType>> getAllPathsBackend(NodeType start);

  /**
   * Returns a list of Integers, which are the total weights/costs of each of the paths in the pathsList.
   * Each element of the returned list corresponds to an element of the pathsList.
   *
   * @param pathsList The list of paths. Each path is a list of nodes.
   * @return The list of Integers containing the weights of each path in pathsList, as well as the path
   */
  public List<Pair<List<NodeType>, Integer>> getAllPathsCost(List<List<NodeType>> pathsList);

  /**
   * Returns the node that is the closest to the start node.
   *
   * @param start the starting node
   * @return The closest node to the start node
   */
  public Pair<NodeType, Integer> getClosestBuilding(NodeType start);

  /**
   * Returns the node that is the farthest from the start node.
   *
   * @param start the starting node
   * @return The farthest node from the start node
   */
  public Pair<NodeType, Integer> getFarthestBuilding(NodeType start);

}
