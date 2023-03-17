// --== CS400 Project Three File Header ==--
// Name: Rishi Natraj
// CSL Username: natraj
// Email: rnatraj@wisc.edu
// Lecture #: <003 @2:25pm>

public interface FrontendInterface {
    /**
     * This method starts the command loop for the frontend, and will
     * terminate when the user exists the app.
     */
    public void runCommandLoop();

    //the following are helper methods for runCommandLoop

    /**
     * prints main menu to allow user to select an option
     */
    public void displayMainMenu();

    /**
     * The method to run the menu for the all paths and distances from a single building
     */
    public void allPathsAndDistances();

    /**
     * The method to run the menu for a single path and distance from one building to another
     */
    public void singlePathAndDistance();

    /**
     * The method to run the menu for the closest building to a single building
     */
    public void closestBuilding();

    /**
     * The method to run the menu for the farthest building to a single building
     */
    public void farthestBuilding();

    /**
     * The method to run the menu to show list of buildings.
     */
    public void displayAllBuildings();


}
