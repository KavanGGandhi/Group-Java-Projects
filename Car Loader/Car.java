// --== CS400 Project Two File Header ==--
// Name: Rishi natraj
// CSL Username: natraj
// Email: rnatraj@wisc.edu
// Lecture #: <003 @2:25pm>
public class Car implements ICar{
    @Override
    public String toString() {
        return year+" "+make+" "+model+", Plate: "+plate+", Price: $"+price;

    }

    private String make;
    private String price;
    private String plate;
    private String year;
    private String model;

    public Car(String plate, String year, String make, String model, String price){
        this.plate=plate;
        this.year=year;
        this.make=make;
        this.model=model;
        this.price=price;
    }
    /**
     * Returns the make of the car.
     *
     * @return make of the car
     */
    @Override
    public String getMake() {
        return make;
    }

    /**
     * Returns the price of the car as a string.
     *
     * @return price of the car as a string
     */
    @Override
    public String getPrice() {
        return price;
    }

    /**
     * Returns the license plate of the car.
     *
     * @return license plate of the car
     */
    @Override
    public String getPlate() {
        return plate;
    }

    /**
     * Returns the year of the car as a string.
     *
     * @return year of the car as a string
     */
    @Override
    public String getYear() {
        return year;
    }

    /**
     * Returns the model of the car.
     *
     * @return model of the car
     */
    @Override
    public String getModel() {
        return model;
    }

    /**
     * Compares this ICar to another ICar by license plate
     *
     * @param o
     * @return less than 0 if this ICar's plate less than o's plate, 0 if plates equal,
     * greater than 0 is this plate greater than o's plate
     */
    @Override
    public int compareTo(ICar o) {
        ICar other=(ICar) o;
        return this.getPlate().compareTo(other.getPlate());
    }
}
