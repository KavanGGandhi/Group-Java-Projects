// --== CS400 Project Two File Header ==--
// Name: Rishi natraj
// CSL Username: natraj
// Email: rnatraj@wisc.edu
// Lecture #: <003 @2:25pm>


import java.io.FileNotFoundException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;



public class CarLoader implements ICarLoader{

    /**
     * This method loads the list of cars from a XML file.
     *
     * @param filepathToXML path to the XML file relative to the executable
     * @return a list of book objects
     * @throws FileNotFoundException
     */
    @Override
    public ArrayList<ICar> loadCars(String filepathToXML) throws FileNotFoundException {
        ArrayList<ICar> carsList=new ArrayList<>();
        try {
            File xmlFile = new File(filepathToXML);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            NodeList cars = doc.getElementsByTagName("car");
            for (int temp = 0; temp < cars.getLength(); temp++) {
                Node n = cars.item(temp);
                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    Element carX = (Element) n;
                    String make=carX.getElementsByTagName("make").item(0).getTextContent();
                    String plate=carX.getElementsByTagName("plate").item(0).getTextContent();
                    String model=carX.getElementsByTagName("model").item(0).getTextContent();
                    String year=carX.getElementsByTagName("year").item(0).getTextContent();
                    String price=carX.getElementsByTagName("price").item(0).getTextContent();
                    ICar car=new Car(plate, year, make, model, price);
                    carsList.add(car);
                }

            }
        } catch (Exception e) {
            return null;
        }
        return carsList;
    }
}
