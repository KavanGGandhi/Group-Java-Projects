import java.util.ArrayList;

/**
 * This interface defines search functionality for the frontend to use.
 */

public interface BackendInterface {

    /**
     * Sets the max price of cars that can be returned.
     *
     * @param maxPrice
     */
    public void setMaxPrice(int maxPrice);

    /**
     * Returns the max price filter that has been set
     *
     * @return the max price filter
     */
    public int getMaxPrice();

    /**
     * Sets the min price of cars that can be returned.
     *
     * @param minPrice
     */
    public void setMinPrice(int minPrice);

    /**
     * Returns the min price filter that has been set
     *
     * @return the min price filter
     */
    public int getMinPrice();

    /**
     * Adds a car into the database of cars
     * @param car
     * @return true if the remove was successful, false otherwise
     */
    public boolean addCar(ICar car);

    /**
     * Removes the car from the tree
     * @param car
     * @return true if the remove was successful, false otherwise
     */
    public boolean removeCar(ICar car);

    /**
     * Resets the max price filter
     *
     */

    public void resetMaxPrice();

    /**
     * Returns the number of cars currently stored
     *
     * @return the number of cars in the backend
     *
     */
    public int getNumCars();

    /**
     * Resets the min price filter
     *
     */
    public void resetMinPrice();

    /**
     * Searches for the car by the license plate
     *
     * @param plate
     * @return the ICar with the corresponding license plate, null if not found
     */
    public ICar searchByLicensePlate(String plate);

    /**
     * Searches for any cars that have the make specified, filters by min price and max price
     *
     * @param make
     * @return a list of cars that match the make specified
     */
    public ArrayList<ICar> searchByMake(String make);

}
