import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;

// --== CS400 Project Three File Header ==--
// Name: Michael Dinh
// CSL Username: mdinh
// Email: mtdinh@wisc.edu
// Lecture #: <003 @2:25pm>

public class dwGraphLoader implements IGraphLoader{

    @Override
    public GraphInterface<String, Integer> load(String filepathToDot) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File(filepathToDot));

        GraphInterface<String, Integer> graph = new Graph<String, Integer>();

        Hashtable<String, String> nameConverter = new Hashtable<>();

        ArrayList<String> nodeList = new ArrayList<String>();

        while (scanner.hasNextLine()) {

            String current = scanner.nextLine();

            // If true, this line has an edge
            if (current.contains("--")){

                String currentTrimmed = current.trim();
                String[] currentArray = currentTrimmed.split(" -- ", 2);

                String node1 = currentArray[0];

                String[] arrayWithNode2AndWeight = currentArray[1].split(" ", 2);

                String node2 = arrayWithNode2AndWeight[0].trim();

                String weightUntrimmed = arrayWithNode2AndWeight[1].split(" ", 2)[1];

                Integer weight = Integer.parseInt(weightUntrimmed.split("]")[0]);

                // keep track of if node1 and node2 found in edge are already in the node list
                boolean alreadyContainsNode1 = false;
                boolean alreadyContainsNode2 = false;



                for (int i=0; i < nodeList.size(); i++){
                    if (nodeList.get(i).equals(node1)){
                        alreadyContainsNode1 = true;
                    }
                    if (nodeList.get(i).equals(node2)){
                        alreadyContainsNode2 = true;
                    }
                }

                if (!alreadyContainsNode1){
                    nodeList.add(node1);
                }
                if(!alreadyContainsNode2){
                    nodeList.add(node2);
                }


                graph.insertEdge(nameConverter.get(node1), nameConverter.get(node2), weight);
                graph.insertEdge(nameConverter.get(node2), nameConverter.get(node1), weight);

            } else if (current.contains("label=")) {
                String curr = current.trim();
                String[] parts = curr.split(" \\[label= \"", 2);
                parts[1] = parts[1].subSequence(0, parts[1].length()-3).toString();

                nameConverter.put(parts[0], parts[1]);
                graph.insertVertex(parts[1]);
            }
        }

        // for (int i=0; i < nodeList.size(); i++){
        //     graph.insertVertex(nodeList.get(i));
        // }


        return graph;
    }
}
