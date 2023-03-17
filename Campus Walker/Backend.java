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
public class Backend<NodeType,EdgeType extends Number> implements BackendInterface<NodeType,EdgeType>{

    private GraphInterface<NodeType, EdgeType> graph;

  /**
   * Backend constructor which takes in the AE's graph object
   * 
   * @param graph AE's graph object
   */
    public Backend(GraphInterface<NodeType, EdgeType> graph) {
        this.graph = graph;
    }

  /**
   * Returns the shortest path between start Node and end Node.
   * Uses Dijkstra's shortest path algorithm to find the shortest path.
   * Allows Frontend Developer access to this method implemented by Algorithm Engineer.
   *
   * @param start the data item in the starting vertex for the path
   * @param end the data item in the destination vertex for the path
   * @return list of data item in nodes in order on the shortest path between the start node and end node, including both start and end
   */
    @Override
    public List<NodeType> getShortestPath(NodeType start, NodeType end) {
        return graph.shortestPath(start,end);
    }

  /**
   * Takes a starting node, and returns a list of paths (which themselves are a list of nodes).
   * Each path details the path from the start node to another node, and there 
   * is a path from the starting node to all other nodes. 
   * Allows Frontend Developer access to this method implemented by Algorithm Engineer.
   * 
   * @param start The starting node
   * @return A list that contains a list of paths from the start node to all other nodes
   */
    @Override
    public List<List<NodeType>> getAllPathsBackend(NodeType start) {
        return graph.getAllPaths(start);
    }

  /**
   * Returns a list of Integers, which are the total weights/costs of each of the paths in the pathsList.
   * Each element of the returned list corresponds to an element of the pathsList.
   *
   * @param pathsList The list of paths. Each path is a list of nodes.
   * @return The list of Integers containing the weights of each path in pathsList, as well as the path
   */
    @Override
    public List<Pair<List<NodeType>, Integer>> getAllPathsCost(List<List<NodeType>> pathsList) {
        List<Pair<List<NodeType>, Integer>> returnList = new ArrayList<>();
        for (int i = 0; i < pathsList.size(); i++) {
            Integer pathCost = (int) graph.getPathCost(pathsList.get(i).get(0), pathsList.get(i).get(pathsList.get(i).size() - 1));
            returnList.add(new Pair(pathsList.get(i), pathCost));
        }
        return returnList;
    }

  /**
   * Returns the node that is the closest to the start node.
   *
   * @param start the starting node
   * @return The closest node to the start node
   */
    @Override
    public Pair<NodeType, Integer> getClosestBuilding(NodeType start) {
        List<List<NodeType>> allPaths = graph.getAllPaths(start);
        //initialized shortestNodeType and shortestPath to an arbritary NodeType value and path
        NodeType shortestNodeType = allPaths.get(0).get(allPaths.get(0).size() - 1);
        Integer shortestPath = (int) graph.getPathCost(start, shortestNodeType);
        //checks that shortestPath is not 0, and if it is, changes it until it is not 0
        if (shortestPath.equals(0)) {
            for (int i = 0; i < allPaths.size(); i++) {
                for (int j = 0; j < allPaths.get(i).size(); j++) {
                    shortestNodeType = allPaths.get(i).get(j);
                    shortestPath = (int) graph.getPathCost(start, shortestNodeType);
                if (!shortestPath.equals(0)) {
                    break;
                }
            }
            if (!shortestPath.equals(0)) {
                break;
            }
            }
        }
        Integer comparedPath;
        Pair<NodeType, Integer> closestPair = new Pair<NodeType, Integer>(shortestNodeType, shortestPath);

        //goes through all of the given paths to find the shortest
        for (int i = 0; i < allPaths.size(); i++) {
            for (int j = 0; j < allPaths.get(i).size(); j++) {
                comparedPath = (int) graph.getPathCost(start, allPaths.get(i).get(j));
                if (!comparedPath.equals(0) && comparedPath.compareTo(shortestPath) < 0) {
                    shortestPath = comparedPath;
                    closestPair = new Pair<NodeType, Integer>(allPaths.get(i).get(j), shortestPath);
                }
            }
        }

        return closestPair;
    }

  /**
   * Returns the node that is the farthest from the start node.
   *
   * @param start the starting node
   * @return The farthest node from the start node
   */
    @Override
    public Pair<NodeType, Integer> getFarthestBuilding(NodeType start) {
        List<List<NodeType>> allPaths = graph.getAllPaths(start);
        //initialized farthestNodeType and farthestPath to an arbritary NodeType value and path
        NodeType farthestNodeType = allPaths.get(0).get(allPaths.get(0).size() - 1);
        Integer farthestPath = (int) graph.getPathCost(start, farthestNodeType);
        //checks that farthestPath is not 0, and if it is, changes it until it is not 0
        if (farthestPath.equals(0)) {
            for (int i = 0; i < allPaths.size(); i++) {
                for (int j = 0; j < allPaths.get(i).size(); j++) {
                farthestNodeType = allPaths.get(i).get(j);
                farthestPath = (int) graph.getPathCost(start, farthestNodeType);
                if (!farthestPath.equals(0)) {
                    break;
                }
            }
            if (!farthestPath.equals(0)) {
                break;
            }
            }
        }
        Integer comparedPath;
        Pair<NodeType, Integer> farthestPair = new Pair<NodeType, Integer>(farthestNodeType, farthestPath);

        //goes through all of the given paths to find the farthest
        for (int i = 0; i < allPaths.size(); i++) {
            for (int j = 0; j < allPaths.get(i).size(); j++) {
                comparedPath = (int) graph.getPathCost(start, allPaths.get(i).get(j));
                if (!comparedPath.equals(0) && comparedPath.compareTo(farthestPath) > 0) {
                    farthestPath = comparedPath;
                    farthestPair = new Pair<NodeType, Integer>(allPaths.get(i).get(j), farthestPath);
                }
            }
        }

        return farthestPair;
    }

}