public class BDCar implements ICar{
    public String make;
    public String model;
    public String plate;
    public String price;
    public String year;

    public BDCar(String make, String model, String plate, String price, String year) {
        this.make = make;
        this.model = model;
        this.plate = plate;
        this.price = price;
        this.year = year;
    }

    @Override
    public String getMake() {
        return make;
    }

    @Override
    public String getPrice() {
        return price;
    }

    @Override
    public String getPlate() {
        return plate;
    }

    @Override
    public String getYear() {
        return year;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public int compareTo(ICar o) {
        return plate.compareTo(o.getPlate());
    }
}
