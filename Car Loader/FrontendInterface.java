import java.util.List;

public interface FrontendInterface {
  /**
   * This method starts the command loop for the frontend, and will
   * terminate when the user exists the app.
   */
  public void runCommandLoop();

  // to help make it easier to test the functionality of this program,
  // the following helper methods will help support runCommandLoop():

  public void displayMainMenu(); // prints command options to System.out

  public void displayCars(List<ICar> cars); // displays a list of cars

  public void searchByLicensePlate(); // reads liscence plate from System.in, displays results

  public void searchByMake(); // reads make from System.in (can be empty), displays results

  public void buyCar(); // reads car to buy from System.in and removes it from the tree
}
