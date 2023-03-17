// --== CS400 Project Three File Header ==--
// Name: Kavan Gandhi
// CSL Username: kavan
// Email: kgandhi4@wisc.edu
// Lecture #: <003 @2:25pm>

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class with main method to run the app. 
 */
public class CampusWalker {

    public static void main(String[] args) throws FileNotFoundException {
        
        dwGraphLoader loader = new dwGraphLoader();

        GraphInterface<String, Integer> graph = loader.load("campusNetwork.dot");

        Backend<String, Integer> backend = new Backend<>(graph);

        Scanner scanner = new Scanner(System.in);

        Frontend frontend = new Frontend(scanner, backend);

        frontend.runCommandLoop();

        // The Data Wrangler's object must be initialized to read the data
        // The Algorithm Engineer's extension of the GraphADT must be initialized 
        // The Backend's object must be initialized
        // The Frontend's object must be initialized to finally run the application
    }
}