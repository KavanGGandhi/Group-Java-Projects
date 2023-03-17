import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// --== CS400 Project Three File Header ==--
// Name: Michael Dinh
// CSL Username: mdinh
// Email: mtdinh@wisc.edu
// Lecture #: <003 @2:25pm>
public class dwGraph<String, Integer> implements GraphInterface{

    ArrayList<String> edgeList;
    ArrayList<String> vertexList;

    public dwGraph(){

        edgeList = new ArrayList<String>();
        vertexList = new ArrayList<String>();

    }


    @Override
    public boolean insertVertex(Object data) {

        for (int i=0; i<vertexList.size(); i++){
            if (( ((String)data) ).equals(vertexList.get(i))){
                return false;
            }
        }

        if (!((String)data).equals(null) && (!((String)data).equals(""))) {
            vertexList.add((String) data);
            return true;
        }
        else{ return false;}

    }

    @Override
    public boolean removeVertex(Object data) {
        for (int i=0; i<vertexList.size(); i++){
            if (vertexList.get(i).equals(data)){
                vertexList.remove(i);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean insertEdge(Object source, Object target, Number weight) {

        if ((!((String)source).equals(null) && (!((String)source).equals(""))) && (!((String)target).equals(null) && (!((String)target).equals("")))) {

            String edgeToAdd = (String) (((String)source) + " -- " + ((String)target) + ": " + ((String)weight.toString()));

            for (int i=0; i< edgeList.size(); i++){
                if (edgeToAdd.equals(edgeList.get(i))){
                    return false;
                }

            }

            edgeList.add((String) (((String)source) + " -- " + ((String)target) + ": " + ((String)weight.toString())));
            return true;
        }
        else{ return false;}
    }

    @Override
    public boolean removeEdge(Object source, Object target) {

        return true;

    }

    @Override
    public boolean containsVertex(Object data) {
        return false;
    }

    @Override
    public boolean containsEdge(Object source, Object target) {
        return false;
    }

    @Override
    public Number getWeight(Object source, Object target) {
        return null;
    }

    @Override
    public List shortestPath(Object start, Object end) {
        return null;
    }

    @Override
    public double getPathCost(Object start, Object end) {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return (vertexList.size() == 0);
    }

    @Override
    public int getEdgeCount() {
        return edgeList.size();
    }

    @Override
    public int getVertexCount() {
        return vertexList.size();
    }

    @Override
    public List<List> getAllPaths(Object start) {
        return null;
    }

    @Override
    public List<Double> getAllPathsCost(Object start) {
        return null;
    }

    @Override
    public Iterator iterator() {
        return null;
    }
}
