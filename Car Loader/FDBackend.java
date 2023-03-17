// --== CS400 Project One File Header ==--
// Name: Kavan Gandhi
// CSL Username: kavan
// Email: kgandhi4@wisc.edu
// Lecture #: <003 @2:25pm>
// Notes to Grader: <any optional extra notes to your grader>

import java.util.ArrayList;

/**
 * This class implements a placeholder version of BackendInterface with the
 * necessary methods for testing
 *
 * @author Kavan Gandhi
 */
public class FDBackend implements BackendInterface {

    int maxPrice = 0;
    int minPrice = 0;

    @Override
    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    @Override
    public int getMaxPrice() {
        return maxPrice;
    }

    @Override
    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    @Override
    public int getMinPrice() {
        return minPrice;
    }

    @Override
    public void addCar(ICar car) {

    }

    @Override
    public void removeCar(ICar car) {

    }

    @Override
    public ICar searchByLicensePlate(String plate) {
        return null;
    }

    @Override
    public ArrayList<ICar> searchByMake(String make) {
        ArrayList<ICar> returnList = new ArrayList<ICar>();
        return returnList;
    }

}
