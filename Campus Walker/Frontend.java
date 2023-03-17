// --== CS400 Project Three File Header ==--
// Name: Rishi Natraj
// CSL Username: natraj
// Email: rnatraj@wisc.edu
// Lecture #: <003 @2:25pm>

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Frontend implements FrontendInterface{

    private Scanner scanner;
    private BackendInterface<String,Integer> backend;
    private ArrayList<String> buildings=new ArrayList<>();
    private ArrayList<String> buildingsLowerCase = new ArrayList<>();

    /**
     * Constructor for frontend object
     *
     * @param scanner    The input Scanner with System.in
     * @param backend The backend object
     */
    public Frontend(Scanner scanner, BackendInterface<String,Integer> backend) {
        this.scanner = scanner;
        this.backend = backend;
        buildings.add("Science Hall");
        buildings.add("QQ's");
        buildings.add("Union South");
        buildings.add("Memorial Union");
        buildings.add("Mosse Humanities");
        buildings.add("Comp Sci Building");
        buildings.add("Van Vleck");
        buildings.add("Chemistry Building");
        buildings.add("Grainger Hall");
        buildings.add("Engineering Hall");
        buildings.add("Van Hise");
        buildings.add("UW Hospital");
        buildings.add("Dejope");
        buildings.add("Camp Randall");
        buildings.add("Kohl Center");
        buildings.add("Gordon's");
        buildings.add("Chadbourne");
        buildings.add("Sellery");
        buildings.add("Smith");
        buildings.add("Witte");
        buildings.add("Bascom Hall");
        buildings.add("Cole Hall");
        buildings.add("State Capital");
        buildings.add("Bradley");
        for(String building:buildings){
            buildingsLowerCase.add(building.toLowerCase());
        }
    }
    /**
     * This method starts the command loop for the frontend, and will
     * terminate when the user exists the app.
     */
    @Override
    public void runCommandLoop() {
        System.out.println("Welcome to Campus Walk Time!\n");
        String userInput="";
        while(!userInput.equals("6")){
            displayMainMenu();
            userInput=scanner.nextLine();
            if (userInput.equals("1")){
                allPathsAndDistances();;
            } else if (userInput.equals("2")){
                singlePathAndDistance();
            } else if (userInput.equals("3")){
                closestBuilding();
            } else if (userInput.equals("4")){
                farthestBuilding();
            } else if (userInput.equals("5")){
                displayAllBuildings();
            }
        }
        System.out.println("Goodbye!");

    }

    /**
     * prints main menu to allow user to select an option
     */
    @Override
    public void displayMainMenu() {
        System.out.println("You are in the main menu:\n" +
                "        1) Distance From 1 Building to All Others\n" +
                "        2) Distance Between 2 Buildings\n" +
                "        3) Closest Building\n" +
                "        4) Farthest Building\n" +
                "        5) Buildings List\n" +
                "        6) Exit Application");
    }

    /**
     * The method to run the menu for the all paths and distances from a single building
     */
    @Override
    public void allPathsAndDistances() {
        System.out.println("You are in the Distance From 1 Building to All Others menu:");
        System.out.print("        Enter a Building for the starting point: ");
        String startBuildingLowerCase=scanner.nextLine().toLowerCase();
        while(!buildingsLowerCase.contains(startBuildingLowerCase)){
            System.out.print("Sorry, that is not a valid building. Please try again: ");
            startBuildingLowerCase=scanner.nextLine().toLowerCase();
        }
        String startBuilding=buildings.get(buildingsLowerCase.indexOf(startBuildingLowerCase));
        System.out.println("Results ("+startBuilding+"): ");
        List<List<String>> paths = backend.getAllPathsBackend(startBuilding);
        List<Pair<List<String>, Integer>> pathsWithCost=backend.getAllPathsCost(paths);
        for (Pair<List<String>, Integer> pathWithCost:pathsWithCost){
            System.out.println(pathWithCost.getKey().get(0)+" to "+pathWithCost.getKey().get(pathWithCost.getKey().size()-1)+" will take "+pathWithCost.getValue()*100/60/100.0+" minutes.");
        }
    }

    /**
     * The method to run the menu for a single path and distance from one building to another
     */
    @Override
    public void singlePathAndDistance() {
        System.out.println("You are in the Distance Between 2 Buildings menu:");
        System.out.print("        Enter a Building for the starting point: ");
        String startBuildingLowerCase=scanner.nextLine().toLowerCase();
        while(!buildingsLowerCase.contains(startBuildingLowerCase)){
            System.out.print("Sorry, that is not a valid building. Please try again: ");
            startBuildingLowerCase=scanner.nextLine().toLowerCase();
        }
        System.out.print("        Enter a Building for the ending point: ");
        String endBuildingLowerCase=scanner.nextLine().toLowerCase();
        while(!buildingsLowerCase.contains(endBuildingLowerCase)){
            System.out.print("Sorry, that is not a valid building. Please try again: ");
            endBuildingLowerCase=scanner.nextLine().toLowerCase();
        }
        String startBuilding=buildings.get(buildingsLowerCase.indexOf(startBuildingLowerCase));
        String endBuilding=buildings.get(buildingsLowerCase.indexOf(endBuildingLowerCase));
        System.out.println("Results ("+startBuilding+" to "+endBuilding+"): ");
        List<String> path = backend.getShortestPath(startBuilding,endBuilding);
        List<List<String>> paths = new ArrayList<>();
        paths.add(path);
        List<Pair<List<String>, Integer>> pathsWithCost=backend.getAllPathsCost(paths);
        for (Pair<List<String>, Integer> pathWithCost:pathsWithCost){
            System.out.println(pathWithCost.getKey().get(0)+" to "+pathWithCost.getKey().get(pathWithCost.getKey().size()-1)+" will take "+pathWithCost.getValue()*100/60/100.0+" minutes.");
        }
    }

    /**
     * The method to run the menu for the closest building to a single building
     */
    @Override
    public void closestBuilding() {
        System.out.print("You are in the Closest Building Menu:\n" +
                "        Enter a Building for the starting point: ");
        String buildingLowerCase=scanner.nextLine().toLowerCase();
        while(!buildingsLowerCase.contains(buildingLowerCase)){
            System.out.print("Sorry, that is not a valid building. Please try again: ");
            buildingLowerCase=scanner.nextLine().toLowerCase();
        }
        String building=buildings.get(buildingsLowerCase.indexOf(buildingLowerCase));
        Pair<String,Integer> p=backend.getClosestBuilding(building);
        System.out.println("Results ("+building+"): ");
        System.out.println(p.getKey()+" is the closest building to "+building+", and it is "+p.getValue()*100/60/100.0+" minutes away.");
    }

    /**
     * The method to run the menu for the farthest building to a single building
     */
    @Override
    public void farthestBuilding() {
        System.out.print("You are in the Farthest Building Menu:\n" +
                "        Enter a Building for the starting point: ");
        String buildingLowerCase=scanner.nextLine().toLowerCase();
        while(!buildingsLowerCase.contains(buildingLowerCase)){
            System.out.print("Sorry, that is not a valid building. Please try again: ");
            buildingLowerCase=scanner.nextLine().toLowerCase();
        }
        String building=buildings.get(buildingsLowerCase.indexOf(buildingLowerCase));
        Pair<String,Integer> p=backend.getFarthestBuilding(building);
        System.out.println("Results ("+building+"): ");
        System.out.println(p.getKey()+" is the farthest building to "+building+", and it is "+p.getValue()*100/60/100.0+" minutes away.");
    }

    /**
     * The method to run the menu to show list of buildings.
     */
    @Override
    public void displayAllBuildings() {
        System.out.println("You are in the Building List Menu:");
        for (String building:buildings){
            System.out.println(building);
        }
    }
}
