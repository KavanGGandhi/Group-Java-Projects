import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Instances of this interface can be used to load car data from a XML file.
 */
public interface ICarLoader {

    /**
     * This method loads the list of cars from a XML file.
     * @param filepathToXML path to the XML file relative to the executable
     * @return a list of book objects
     * @throws FileNotFoundException
     */
    ArrayList<ICar> loadCars(String filepathToXML) throws FileNotFoundException;

}
