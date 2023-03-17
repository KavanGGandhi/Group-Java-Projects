import java.util.Iterator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class AlgorithmEngineerTests {

  /**
   * This test if LicensePlateChecker verifies valid licenses
   * 
   * @return boolean of if all tests pass
   */
  @Test
  public void test1() {

    AELicensePlateChecker checker = new AELicensePlateChecker();

    // valid license plate should return true
    boolean output1 = checker.validate("111AAA");
    assertEquals(output1, true);

    // one of first characters is not a number, should return false;
    boolean output2 = checker.validate("1?1AAA");
    assertEquals(output2,false);

    // length is not 6, return false
    boolean output3 = checker.validate("11111AAA");
    assertEquals(output3, false);
  }

  /**
   * This test method tests the functionality of the in-order iterator for the RedBlackTree, mainly
   * checking the hasNext and next() functionality
   * 
   * @return boolean of if the iterator works properly
   */
  @Test
  public void test2() {
    AERedBlackTree tree = new AERedBlackTree();
    
    Iterator<AECar> itEmpty = tree.iterator();
    assertEquals(itEmpty.hasNext(), false);

    tree.insert(new AECar("333CCC"));
    tree.insert(new AECar("433CCC"));
    tree.insert(new AECar("233CCC"));

    String expected = "233CCC 333CCC 433CCC ";
    String iterated = "";
    
    Iterator<AECar> it = tree.iterator();
    
    assertEquals(it.hasNext(), true);
    
    String carPlate = it.next().getPlate();
    String expectedPlate = "233CCC";

    assertEquals(carPlate, expectedPlate);

  }

  /**
   * This test method tests the functionality of the in-order iterator for the RedBlackTree, mainly
   * if it returns data in the correct order
   * 
   * @return boolean of if the iterator works properly
   */
  @Test
  public void test3() {
    AERedBlackTree tree = new AERedBlackTree();
    tree.insert(new AECar("333CCC"));
    tree.insert(new AECar("433CCC"));
    tree.insert(new AECar("233CCC"));

    String expected = "233CCC 333CCC 433CCC ";
    String iterated = "";

    Iterator<AECar> it = tree.iterator();
    while (it.hasNext()) {
      iterated += (it.next().getPlate() + " ");
    }

    assertEquals(expected, iterated);

  }

  /**
   * This test method makes sure the remove method doesn't remove anything if the data isn't found
   * in the rbt
   * 
   * @return boolean of if remove works as planned
   */
  @Test
  public void test4() {
    AERedBlackTree tree = new AERedBlackTree();
    tree.insert(5);
    tree.insert(2);
    tree.insert(9);
    tree.insert(1);
    tree.insert(3);
    tree.insert(7);
    tree.insert(10);

    String output1 = tree.remove(12);
    String expected1 = "12 is not stored in this tree";
    assertEquals(output1, expected1);

    assertEquals(tree.size, 7);

  }

  /**
   * This method tests whether the remove method successfully removes data from the rbt
   * 
   * @return boolean of if remove works as intended
   */
  @Test
  public void test5() {
    AERedBlackTree tree = new AERedBlackTree();
    tree.insert(5);
    tree.insert(2);
    tree.insert(9);
    tree.insert(1);
    tree.insert(3);
    tree.insert(7);
    tree.insert(10);


    tree.remove(10);

    String expectedToLevel = "[ 5, 2, 9, 1, 3, 7 ]";

    assertEquals(expectedToLevel, tree.toLevelOrderString());
    tree.remove(1);

    String expectedInOrder = "[ 2, 3, 5, 7, 9 ]";
    
    assertEquals(expectedInOrder, tree.toInOrderString());
    
  }

}

