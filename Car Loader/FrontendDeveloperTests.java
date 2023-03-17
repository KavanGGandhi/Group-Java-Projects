// --== CS400 Project One File Header ==--
// Name: Kavan Gandhi
// CSL Username: kavan
// Email: kgandhi4@wisc.edu
// Lecture #: <003 @2:25pm>
// Notes to Grader: <any optional extra notes to your grader>

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class implements tester methods for the FrontendInterface
 * implementation.
 *
 * @author Kavan Gandhi
 */
public class FrontendDeveloperTests {

  /**
   * JUnit test that tests whether the main menu is correctly displayed
   */
  @Test
  public void testInitialDisplayLoop() {
    TextUITester tester = new TextUITester("");
    Scanner scnr = new Scanner(System.in);
    IterableRBTADT<ICar> rbt = new AERedBlackTree<ICar>();
    BackendInterface backend = new Backend(rbt);
    LicensePlateCheckerInterface checker = new AELicensePlateChecker();
    Frontend testFrontend = new Frontend(scnr, backend, checker);

    testFrontend.displayMainMenu();
    String[] output = tester.checkOutput().split("\n");

    assertEquals(output[0], "You are in the Main Menu:");
  }

  /**
   * JUnit test that tests whether setting the minimum price through the frontend
   * UI works as intended
   */
  @Test
  public void testSetMin() {
    TextUITester tester = new TextUITester("3\n1500\n6\n");
    Scanner scnr = new Scanner(System.in);
    IterableRBTADT<ICar> rbt = new AERedBlackTree<ICar>();
    BackendInterface backend = new Backend(rbt);
    LicensePlateCheckerInterface checker = new AELicensePlateChecker();
    Frontend testFrontend = new Frontend(scnr, backend, checker);
    testFrontend.runCommandLoop();

    assertEquals(1500, backend.getMinPrice());
  }

  /**
   * JUnit test that tests whether setting the maximum price through the frontend
   * UI works as intended
   */
  @Test
  public void testSetMax() {
    TextUITester tester = new TextUITester("4\n2000\n6\n");
    Scanner scnr = new Scanner(System.in);
    IterableRBTADT<ICar> rbt = new AERedBlackTree<ICar>();
    BackendInterface backend = new Backend(rbt);
    LicensePlateCheckerInterface checker = new AELicensePlateChecker();
    Frontend testFrontend = new Frontend(scnr, backend, checker);
    testFrontend.runCommandLoop();

    String[] output = tester.checkOutput().split("\n");

    assertEquals(2000, backend.getMaxPrice());
  }

  /**
   * JUnit test that tests whether searching by make, and then not specifying the
   * make results in the expected output
   */
  @Test
  public void testSearchByMake() {
    TextUITester tester = new TextUITester("1\n\n6\n");
    Scanner scnr = new Scanner(System.in);
    IterableRBTADT<ICar> rbt = new AERedBlackTree<ICar>();
    BackendInterface backend = new Backend(rbt);
    LicensePlateCheckerInterface checker = new AELicensePlateChecker();
    Frontend testFrontend = new Frontend(scnr, backend, checker);
    testFrontend.runCommandLoop();
    String[] output = tester.checkOutput().split("\n");

    // there should be 18 lines of output
    assertEquals(output.length, 18);
  }

  /**
   * JUnit test that tests whether exiting the application works as intended
   */
  @Test
  public void testExitApplication() {
    TextUITester tester = new TextUITester("6\n");
    Scanner scnr = new Scanner(System.in);
    IterableRBTADT<ICar> rbt = new AERedBlackTree<ICar>();
    BackendInterface backend = new Backend(rbt);
    LicensePlateCheckerInterface checker = new AELicensePlateChecker();
    Frontend testFrontend = new Frontend(scnr, backend, checker);
    testFrontend.runCommandLoop();
    String[] output = tester.checkOutput().split("\n");

    // there should be 8 lines of output
    assertEquals(output.length, 8);
  }

  /**
   * JUnit integration test that tests whether setting max changes the max in the backend object
   */
  @Test
  public void testGetMaxIntegration() {
    TextUITester tester = new TextUITester("4\n21000\n6\n");
    Scanner scnr = new Scanner(System.in);
    IterableRBTADT<ICar> rbt = new AERedBlackTree<ICar>();
    BackendInterface backend = new Backend(rbt);
    LicensePlateCheckerInterface checker = new AELicensePlateChecker();
    Frontend testFrontend = new Frontend(scnr, backend, checker);
    testFrontend.runCommandLoop();

    String[] output = tester.checkOutput().split("\n");

    assertEquals(21000, backend.getMaxPrice());
  }

  /**
   * JUnit integration test that tests whether setting min changes the min in the backend object
   */
  @Test
  public void testGetMinIntegration() {
    TextUITester tester = new TextUITester("3\n10000\n6\n");
    Scanner scnr = new Scanner(System.in);
    IterableRBTADT<ICar> rbt = new AERedBlackTree<ICar>();
    BackendInterface backend = new Backend(rbt);
    LicensePlateCheckerInterface checker = new AELicensePlateChecker();
    Frontend testFrontend = new Frontend(scnr, backend, checker);
    testFrontend.runCommandLoop();

    String[] output = tester.checkOutput().split("\n");

    assertEquals(10000, backend.getMinPrice());
  }

  /**
   * JUnit code review test that tests the Data Wrangler's Car object constructor
   */
  @Test
  public void testCarObjCodeReviewOfDataWranger() {
    Car testCar = new Car("plate", "year", "make", "model", "price");

    assertEquals(testCar.getPlate(), "plate");
    assertEquals(testCar.getYear(), "year");
    assertEquals(testCar.getMake(), "make");
    assertEquals(testCar.getModel(), "model");
    assertEquals(testCar.getPrice(), "price");
  }

  /**
   * JUnit code review test that tests the Data Wrangler's Car toString method
   */
  @Test
  public void testCarToStringCodeReviewOfDataWranger() {
    Car testCar = new Car("plate", "year", "make", "model", "price");
    String testString = testCar.toString();

    assertEquals(testString, "year make model, Plate: plate, Price: $price");
  }
}
