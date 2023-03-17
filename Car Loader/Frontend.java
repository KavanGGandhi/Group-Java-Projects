// --== CS400 Project One File Header ==--
// Name: Kavan Gandhi
// CSL Username: kavan
// Email: kgandhi4@wisc.edu
// Lecture #: <003 @2:25pm>
// Notes to Grader: <any optional extra notes to your grader>

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class implements the FrontendInterface methods
 *
 * @author Kavan Gandhi
 */
public class Frontend implements FrontendInterface {

  private Scanner scnr;
  private BackendInterface backend;
  private LicensePlateCheckerInterface checker;

  /**
   * Constructor for frontend object
   *
   * @param scnr    The input Scanner with System.in
   * @param backend The backend object
   * @param checker the license plate checker object
   */
  public Frontend(
    Scanner scnr,
    BackendInterface backend,
    LicensePlateCheckerInterface checker
  ) {
    this.scnr = scnr;
    this.backend = backend;
    this.checker = checker;
  }

  /**
   * This method starts the command loop for the frontend, and will
   * terminate when the user exists the app.
   */
  @Override
  public void runCommandLoop() {
    String userInput = "";
    while (!userInput.equals("6")) {
      displayMainMenu();
      userInput = scnr.nextLine();
      if (userInput.equals("1")) {
        searchByMake();
      } else if (userInput.equals("2")) {
        searchByLicensePlate();
      } else if (userInput.equals("3")) {
        System.out.println("You are in the Set Minimum Price Menu");
        System.out.print("\t\tCurrent minimum price: ");
        if (backend.getMinPrice() == 0) {
          System.out.println("none");
        } else {
          System.out.println(backend.getMinPrice());
        }
        System.out.println(
          "\t\tEnter a minimum price to set (leave empty for none): "
        );
        String minPrice = scnr.nextLine();
        if (!minPrice.equals("")) {
          try {
            Integer.parseInt(minPrice);
          } catch (Exception e) {
            System.out.println("Not a valid integer");
            continue;
          }
        }
        if (minPrice.equals("")) {
          backend.setMinPrice(0);
        } else {
          backend.setMinPrice(Integer.parseInt(minPrice));
        }
      } else if (userInput.equals("4")) {
        System.out.println("You are in the Set Maximum Price Menu");
        System.out.print("\t\tCurrent maximum price: ");
        if (backend.getMaxPrice() == 0) {
          System.out.println("none");
        } else {
          System.out.println(backend.getMaxPrice());
        }
        System.out.println(
          "\t\tEnter a maximum price to set (leave empty for none): "
        );
        String maxPrice = scnr.nextLine();
        if (!maxPrice.equals("")) {
          try {
            Integer.parseInt(maxPrice);
          } catch (Exception e) {
            System.out.println("Not a valid integer");
            continue;
          }
        }
        if (maxPrice.equals("")) {
          backend.setMaxPrice(0);
        } else {
          backend.setMaxPrice(Integer.parseInt(maxPrice));
        }
      } else if (userInput.equals("5")) {
        buyCar();
      } else if (!userInput.equals("6")) {
        System.out.println("Invalid input. Please enter a valid input");
      }
    }

    System.out.println("Goodbye!");
  }

  /**
   * Prints command options to System.out
   */
  @Override
  public void displayMainMenu() {
    System.out.println(
      "You are in the Main Menu:\n" +
      "\t\t1) General Search\n" +
      "\t\t2) Search By Plate\n" +
      "\t\t3) Set Minimum Price\n" +
      "\t\t4) Set Maximum Price\n" +
      "\t\t5) Buy a Car\n" +
      "\t\t6) Exit Application"
    );
  }

  /**
   * Displays a list of cars
   */
  @Override
  public void displayCars(List<ICar> cars) {
    for (int i = 0; i < cars.size(); i++) {
      System.out.println(i + 1 + ". " + cars.get(i).toString());
    }
  }

  /**
   * Reads liscence plate from System.in, displays results
   */
  @Override
  public void searchByLicensePlate() {
    System.out.println("You are in the Search by Plate Menu:");
    System.out.println("\t\tEnter a license plate to search for:");
    String plate = scnr.nextLine().toUpperCase();
    if (!checker.validate(plate)) {
      System.out.println("\t\tInvalid license plate.");
      return;
    }
    List<ICar> plateCarList = new ArrayList<ICar>();

    ICar car = backend.searchByLicensePlate(plate);
    
    if (car == null) {
      System.out.println("\t\tLicense Plate Not Found");
      return;
    }

    plateCarList.add(car);
    System.out.println("Matches");


    displayCars(plateCarList);
  }

  /**
   * Reads make from System.in (can be empty), displays results
   */
  @Override
  public void searchByMake() {
    System.out.println("You are in the General Search Menu:");
    System.out.println(
      "\t\tEnter a make to look up (leave empty for all makes): "
    );
    String make = scnr.nextLine().toLowerCase();
    String specialString = "";
    if (make.equals("")) {
      specialString = "No make";
    } else {
      specialString = make;
    }
    String minPrice = "";
    String maxPrice = "";
    if (backend.getMinPrice() == 0) {
      minPrice = "no min price";
    } else {
      minPrice = "min price: " + String.valueOf(backend.getMinPrice());
    }
    if (backend.getMaxPrice() == 0) {
      maxPrice = "no max price";
    } else {
      maxPrice = "max price: " + String.valueOf(backend.getMaxPrice());
    }
    System.out.println(
      "Matches (" + specialString + ", " + minPrice + ", " + maxPrice + ")"
    );
    List<ICar> makeCarList = backend.searchByMake(make);
    displayCars(makeCarList);
  }

  /**
   * Reads car to buy from System.in and removes it from the tree
   */
  @Override
  public void buyCar() {
    System.out.println("You are in the Buy a Car menu:");
    System.out.println("\t\tEnter a license plate of the product to buy: ");
    String plate = scnr.nextLine().toUpperCase();
    if (!checker.validate(plate)) {
      System.out.println("\t\tInvalid license plate.");
      return;
    }
    List<ICar> plateCarList = new ArrayList<ICar>();
    plateCarList.add(backend.searchByLicensePlate(plate));
    System.out.println("Matches");
    displayCars(plateCarList);
    backend.removeCar(backend.searchByLicensePlate(plate));
  }
}
