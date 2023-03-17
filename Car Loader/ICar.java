/**
 * This interface defines getter methods for each car's data attributes
 * and is implemented by classes that represent a car and its associated
 * data.
 */
public interface ICar extends Comparable<ICar>{
    /**
     * Returns the make of the car.
     * @return make of the car
     */
    String getMake();

  /**
   * Returns the price of the car as a string.
   *
   * @return price of the car as a string
   */
  String getPrice();

  /**
   * Returns the license plate of the car.
   *
   * @return license plate of the car
   */
  String getPlate();

  /**
   * Returns the year of the car as a string.
   *
   * @return year of the car as a string
   */
  String getYear();

  /**
   * Returns the model of the car.
   *
   * @return model of the car
   */
  String getModel();

    /**
     * Returns information of the ICar as a string;
     * @return
     */
    String toString();

}
