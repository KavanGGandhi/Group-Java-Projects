
public class AELicensePlateChecker implements LicensePlateCheckerInterface {

  // valid license plate is a String of length 6
  // 3 numbers, then 3 letters
  @Override
  public boolean validate(String LicensePlate) {

    // checks that the length of the plate is 6
    if (LicensePlate.length() != 6) {
      return false;
    }
    
    // verifies first 3 chars in plate are numbers
    if (!Character.isDigit(LicensePlate.charAt(0)) || !Character.isDigit(LicensePlate.charAt(1))
        || !Character.isDigit(LicensePlate.charAt(2))) {
      return false;
    }

    // verifies next 3 chars in plate are letters
    if (!Character.isLetter(LicensePlate.toUpperCase().charAt(3))
        || !Character.isLetter(LicensePlate.toUpperCase().charAt(4))
        || !Character.isLetter(LicensePlate.toUpperCase().charAt(5))) {
      return false;
    }

    return true;
  }

}

