import java.util.ArrayList;

public class Backend implements BackendInterface{

    private int minPrice;
    private int maxPrice;
    private IterableRBTADT<ICar> rbt;

    public Backend(IterableRBTADT<ICar> rbt) {
        this.rbt = rbt;
        this.maxPrice = 0;
        this.minPrice = 0;
    }


    @Override
    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    @Override
    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    @Override
    public int getMinPrice() {
        return minPrice;
    }

    @Override
    public int getMaxPrice() {
        return maxPrice;
    }

    @Override
    public boolean addCar(ICar car) {
        try {
            rbt.insert(car);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
    }


    @Override
    public boolean removeCar(ICar car) {
        try {
            rbt.remove(car);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }

    }

    @Override
    public void resetMaxPrice() {
        maxPrice = -1;
    }

    @Override
    public int getNumCars() {
        return rbt.size();
    }

    @Override
    public void resetMinPrice() {
        minPrice = -1;
    }

    @Override
    public ICar searchByLicensePlate(String plate) {
        ICar dummy = new Car(plate, null, null, null, null);
        // Creates a dummy Car for searching purposes.

        return rbt.search(dummy);
    }

    @Override
    public ArrayList<ICar> searchByMake(String make) {
        ArrayList<ICar> cars = new ArrayList<>();

        for (ICar c: rbt) {
            if (make.equals("") || c.getMake().toLowerCase().equals(make)) {
                // System.out.println(c.getMake()); 
                // Checks if the make of car matches the make to search by
                int price = Integer.parseInt(c.getPrice()); // Converts the price into an integer
                if (!(minPrice > price || (maxPrice < price && maxPrice != 0))) {
                    cars.add(c);
                    
                }
            }
        }

        return cars;
    }
}
