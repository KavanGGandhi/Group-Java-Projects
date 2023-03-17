import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BackendDeveloperTests {

    IterableRBTADT<ICar> rbt;
    Backend backend;

    @BeforeEach
    public void before() {
        rbt = new BDIterableRBT<ICar>();
        backend = new Backend(rbt);
    }

    /**
     * Adds a few dummy cars into the car database using the
     */
    @Test
    public void Test1() {
        BDCar car1 = new BDCar(null, null, "plate1", null, null);
        BDCar car2 = new BDCar(null, null, "plate2", null, null);
        BDCar car3 = new BDCar(null, null, "plate3", null, null);

        backend.addCar(car1);
        backend.addCar(car2);
        backend.addCar(car3);
        assertEquals(rbt.size(), 3);
    }

    /**
     * Inserts a few cars into the backend and searches by Make
     */
    @Test
    public void Test2() {
        BDCar car1 = new BDCar("V1", null, "plate1", "10", null);
        BDCar car2 = new BDCar("V1", null, "plate2", "10", null);
        BDCar car3 = new BDCar("V2", null, "plate3", "10", null);

        backend.addCar(car1);
        backend.addCar(car2);
        backend.addCar(car3);

       assertEquals(2, backend.searchByMake("v1").size());
    }

    /**
     * Adds a few cars into the backend and searches by make with min and max price bounds
     */
    @Test
    public void Test3() {
        BDCar car1 = new BDCar("V1", null, "plate1", "10", null);
        BDCar car2 = new BDCar("V1", null, "plate2", "15", null);
        BDCar car3 = new BDCar("V2", null, "plate3", "10", null);
        BDCar car4 = new BDCar("V1", null, "plate4", "5", null);


        backend.addCar(car1);
        backend.addCar(car2);
        backend.addCar(car3);
        backend.addCar(car4);
        backend.setMaxPrice(12);
        backend.setMinPrice(8);

        assertEquals(1, backend.searchByMake("v1").size());
    }

    /**
     * Attempts to add a car with a duplicate plate and a car with a null plate.
     */
    @Test
    public void Test4() {
        BDCar car1 = new BDCar("V1", null, "copy", "10", null);
        BDCar car2 = new BDCar("V1", null, "copy", "15", null);
        BDCar car3 = new BDCar("V2", null, null, "10", null);


        System.out.println(backend.addCar(car1));
        assertEquals(1, rbt.size());

        assertFalse(backend.addCar(car2));
        assertFalse(backend.addCar(car3));
        assertEquals(1, rbt.size());
    }

    /**
     * Adds a few cars to the backend, searches by make and the price and then resets the price filter.
     */
    @Test
    public void Test5() {
        BDCar car1 = new BDCar("V1", null, "plate1", "10", null);
        BDCar car2 = new BDCar("V1", null, "plate2", "15", null);
        BDCar car3 = new BDCar("V2", null, "plate3", "10", null);
        BDCar car4 = new BDCar("V1", null, "plate4", "5", null);


        backend.addCar(car1);
        backend.addCar(car2);
        backend.addCar(car3);
        backend.addCar(car4);
        backend.setMaxPrice(12);
        backend.setMinPrice(8);

        assertEquals(1, backend.searchByMake("v1").size());
        backend.resetMinPrice();
        assertEquals(2, backend.searchByMake("v1").size());


    }
}
