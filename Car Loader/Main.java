import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main class that runs the entire application
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        IterableRBTADT<ICar> rbt = new AERedBlackTree<ICar>();
        CarLoader loader = new CarLoader();
        ArrayList<ICar> cars = loader.loadCars("cars1.xml");

        BackendInterface backend = new Backend(rbt);

        for (ICar car: cars) {
            backend.addCar(car);
        }

        LicensePlateCheckerInterface checker = new AELicensePlateChecker();

        Scanner scanner = new Scanner(System.in);

        FrontendInterface frontend = new Frontend(scanner, backend, checker);

        frontend.runCommandLoop();
    }
}
