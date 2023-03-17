

import java.util.ArrayList;
import java.util.List;

public class FDBackend<NodeType, EdgeType extends Number> implements BackendInterface<NodeType, EdgeType>{


    /**
     * Returns the shortest path between start Node and end Node.
     * Uses Dijkstra's shortest path algorithm to find the shortest path.
     * Allows Frontend Developer access to this method implemented by Algorithm Engineer.
     *
     * @param start the data item in the starting vertex for the path
     * @param end   the data item in the destination vertex for the path
     * @return list of data item in nodes in order on the shortest path between the start node and end node, including both start and end
     */
    @Override
    public List<NodeType> getShortestPath(NodeType start, NodeType end) {
        List<NodeType> path=new ArrayList<NodeType>();
        path.add((NodeType) start);
        path.add((NodeType) end);
        return path;
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
        List<NodeType> path1=new ArrayList<NodeType>();
        path1.add((NodeType) start);
        path1.add((NodeType) "Engineering Hall");
        List<NodeType> path2=new ArrayList<>();
        path2.add((NodeType) start);
        path2.add((NodeType) "Union South");
        List<List<NodeType>> paths=new ArrayList<>();
        paths.add(path1);
        paths.add(path2);
        return paths;
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
        List<Pair<List<NodeType>, Integer>> pathCosts=new ArrayList<>();
        for (List path:pathsList){
            Pair<List<NodeType>, Integer> p = new Pair<>(path, 10);
            pathCosts.add(p);
        }
        return pathCosts;
    }

    /**
     * Returns the node that is the closest to the start node.
     *
     * @param start the starting node
     * @return The closest node to the start node
     */
    @Override
    public Pair<NodeType, Integer> getClosestBuilding(NodeType start) {
        return new Pair<NodeType, Integer>((NodeType) "Engineering Hall", 10);
    }

    /**
     * Returns the node that is the farthest from the start node.
     *
     * @param start the starting node
     * @return The farthest node from the start node
     */
    @Override
    public Pair<NodeType, Integer> getFarthestBuilding(NodeType start) {
        return new Pair<NodeType, Integer>((NodeType) "Union South", 30);
    }
}
