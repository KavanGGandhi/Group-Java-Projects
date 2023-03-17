// --== CS400 Project One File Header ==--
// Name: Kavan Gandhi
// CSL Username: kavan
// Email: kgandhi4@wisc.edu
// Lecture #: <003 @2:25pm>
// Notes to Grader: <any optional extra notes to your grader>

/**
 * This class implements a placeholder version of LicensePlateCheckerInterface
 * with the necessary methods for testing
 *
 * @author Kavan Gandhi
 */
public class FDLicensePlateChecker implements LicensePlateCheckerInterface {

  @Override
  public boolean validate(String LicensePlate) {
    if (LicensePlate.equals("129FFE")) {
      return true;
    } else {
      return false;
    }
  }
}
