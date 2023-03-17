
public class AECar implements ICar{
  
  String make;
  String plate;
  
  public AECar(String plate) {
    this.plate = plate;
  }
  
  public int compareTo(ICar o) {
    return this.plate.compareTo(((AECar)o).plate);
  }

  public String getMake() {
    
    return "Mitsubishi";
  }

  public String getPrice() {
    return "20000";
  }

  public String getPlate() {
    return this.plate;
  }

  public String getYear() {
    return "2018";
  }

  public String getModel() {
    return "Lancer";
  }

}

