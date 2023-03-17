// --== CS400 Project One File Header ==--
// Name: Kavan Gandhi
// CSL Username: kavan
// Email: kgandhi4@wisc.edu
// Lecture #: <003 @2:25pm>
// Notes to Grader: <any optional extra notes to your grader>

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * This class implements tester methods for Book and BookLoader.
 *
 * @author Kavan Gandhi
 */
public class DataWranglerTest {
  /**
   * Main method
   * 
   * @param args unused
   */
  public static void main(String[] args) {
    if (test1()) {
      System.out.println("DataWrangler Individual Test 1: passed");
    } else {
      System.out.println("DataWrangler Individual Test 1: failed");
    }
    if (test2()) {
      System.out.println("DataWrangler Individual Test 2: passed");
    } else {
      System.out.println("DataWrangler Individual Test 2: failed");
    }
    if (test3()) {
      System.out.println("DataWrangler Individual Test 3: passed");
    } else {
      System.out.println("DataWrangler Individual Test 3: failed");
    }
    if (test4()) {
      System.out.println("DataWrangler Individual Test 4: passed");
    } else {
      System.out.println("DataWrangler Individual Test 4: failed");
    }
    if (test5()) {
      System.out.println("DataWrangler Individual Test 5: passed");
    } else {
      System.out.println("DataWrangler Individual Test 5: failed");
    }
    if (test6()) {
      System.out.println("DataWrangler Integration Test 1: passed");
    } else {
      System.out.println("DataWrangler Integration Test 1: failed");
    }
    if (test7()) {
      System.out.println("DataWrangler Integration Test 2: passed");
    } else {
      System.out.println("DataWrangler Integration Test 2: failed");
    }
    if (test8()) {
      System.out.println("DataWrangler Partner (BackendDeveloper) Test 1: passed");
    } else {
      System.out.println("DataWrangler Partner (BackendDeveloper) Test 1: failed");
    }
    if (test9()) {
      System.out.println("DataWrangler Partner (BackendDeveloper) Test 2: passed");
    } else {
      System.out.println("DataWrangler Partner (BackendDeveloper) Test 2: failed");
    }
  }

  /**
   * Checks whether the getter methods for Book() work as expected.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean test1() {
    // tests the getter methods of the Book class
    {
      IBook testBook = new Book("Kavan Autobiography", "Kavan Gandhi", "9780767903820");
      if (!testBook.getTitle().equals("Kavan Autobiography")) {
        System.out.println("Problem in Book getter method");
        return false;
      }
      if (!testBook.getAuthors().equals("Kavan Gandhi")) {
        System.out.println("Problem in Book getter method");
        return false;
      }
      if (!testBook.getISBN13().equals("9780767903820")) {
        System.out.println("Problem in Book getter method");
        return false;
      }
    }
    return true;
  }

  /**
   * Checks whether the getter loadBooks() method works as expected in a normal case.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean test2() {
    // tests whether loadBooks() method works correctly when parsing a csv line without any quotes
    // or escaped quotes
    {
      try {
        FileWriter writer = new FileWriter("testbookcsv.csv");
        BookLoader testLoader = new BookLoader();
        writer.write(
            "bookID,title,authors,average_rating,isbn,isbn13,language_code,  num_pages,ratings_count,text_reviews_count,publication_date,publisher\r\n"
                + "1,Harry Potter and the Half-Blood Prince (Harry Potter  #6),J.K. Rowling/Mary GrandPré,4.57,0439785960,9780439785969,eng,652,2095690,27591,9/16/2006,Scholastic Inc.\r\n"
                + "2,Harry Potter and the Order of the Phoenix (Harry Potter  #5),J.K. Rowling/Mary GrandPré,4.49,0439358078,9780439358071,eng,870,2153167,29221,9/1/2004,Scholastic Inc.\r\n"
                + "4,Harry Potter and the Chamber of Secrets (Harry Potter  #2),J.K. Rowling,4.42,0439554896,9780439554893,eng,352,6333,244,11/1/2003,Scholastic\r\n"
                + "5,Harry Potter and the Prisoner of Azkaban (Harry Potter  #3),J.K. Rowling/Mary GrandPré,4.56,043965548X,9780439655484,eng,435,2339585,36325,5/1/2004,Scholastic Inc.\r\n"
                + "8,Harry Potter Boxed Set  Books 1-5 (Harry Potter  #1-5),J.K. Rowling/Mary GrandPré,4.78,0439682584,9780439682589,eng,2690,41428,164,9/13/2004,Scholastic\r\n"
                + "9,Unauthorized Harry Potter Book Seven News: \\\"Half-Blood Prince\\\" Analysis and Speculation,W. Frederick Zimmerman,3.74,0976540606,9780976540601,en-US,152,19,1,4/26/2005,Nimble Books\r\n"
                + "10,Harry Potter Collection (Harry Potter  #1-6),J.K. Rowling,4.73,0439827604,9780439827607,eng,3342,28242,808,9/12/2005,Scholastic");
        writer.close();
        List<IBook> testBookList = testLoader.loadBooks("testbookcsv.csv");
        if (!testBookList.get(0).getTitle()
            .equals("Harry Potter and the Half-Blood Prince (Harry Potter  #6)")) {
          System.out.println("Problem in loadBooks() method");
          return false;
        }
        if (!testBookList.get(0).getAuthors().equals("J.K. Rowling/Mary GrandPré")) {
          System.out.println("Problem in loadBooks() method");
          return false;
        }
        if (!testBookList.get(0).getISBN13().equals("9780439785969")) {
          System.out.println("Problem in loadBooks() method");
          return false;
        }
      } catch (IOException e) {
        // unexpected error
        return false;
      }
    }
    return true;
  }

  /**
   * Checks whether the getter loadBooks() method works as expected when parsing a line with escaped
   * quotes.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean test3() {
    // tests whether loadBooks() method works correctly when parsing a csv line with escaped quotes
    {
      try {
        FileWriter writer = new FileWriter("testbookcsv.csv");
        BookLoader testLoader = new BookLoader();
        writer.write(
            "bookID,title,authors,average_rating,isbn,isbn13,language_code,  num_pages,ratings_count,text_reviews_count,publication_date,publisher\r\n"
                + "1,Harry Potter and the Half-Blood Prince (Harry Potter  #6),J.K. Rowling/Mary GrandPré,4.57,0439785960,9780439785969,eng,652,2095690,27591,9/16/2006,Scholastic Inc.\r\n"
                + "2,Harry Potter and the Order of the Phoenix (Harry Potter  #5),J.K. Rowling/Mary GrandPré,4.49,0439358078,9780439358071,eng,870,2153167,29221,9/1/2004,Scholastic Inc.\r\n"
                + "4,Harry Potter and the Chamber of Secrets (Harry Potter  #2),J.K. Rowling,4.42,0439554896,9780439554893,eng,352,6333,244,11/1/2003,Scholastic\r\n"
                + "5,Harry Potter and the Prisoner of Azkaban (Harry Potter  #3),J.K. Rowling/Mary GrandPré,4.56,043965548X,9780439655484,eng,435,2339585,36325,5/1/2004,Scholastic Inc.\r\n"
                + "8,Harry Potter Boxed Set  Books 1-5 (Harry Potter  #1-5),J.K. Rowling/Mary GrandPré,4.78,0439682584,9780439682589,eng,2690,41428,164,9/13/2004,Scholastic\r\n"
                + "9,Unauthorized Harry Potter Book Seven News: \\\"Half-Blood Prince\\\" Analysis and Speculation,W. Frederick Zimmerman,3.74,0976540606,9780976540601,en-US,152,19,1,4/26/2005,Nimble Books\r\n"
                + "10,Harry Potter Collection (Harry Potter  #1-6),J.K. Rowling,4.73,0439827604,9780439827607,eng,3342,28242,808,9/12/2005,Scholastic");
        writer.close();
        List<IBook> testBookList = testLoader.loadBooks("testbookcsv.csv");
        if (!testBookList.get(5).getTitle().equals(
            "Unauthorized Harry Potter Book Seven News: \"Half-Blood Prince\" Analysis and Speculation")) {
          System.out.println("Problem in loadBooks() method");
          return false;
        }
        if (!testBookList.get(5).getAuthors().equals("W. Frederick Zimmerman")) {
          System.out.println("Problem in loadBooks() method");
          return false;
        }
        if (!testBookList.get(5).getISBN13().equals("9780976540601")) {
          System.out.println("Problem in loadBooks() method");
          return false;
        }
      } catch (IOException e) {
        // unexpected error
        return false;
      }
    }
    return true;
  }

  /**
   * Checks whether the getter loadBooks() method works as expected when parsing a line with not
   * escaped quotes.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean test4() {
    // tests whether loadBooks() method works correctly when parsing a csv line with quotes that
    // should be deleted
    {
      try {
        FileWriter writer = new FileWriter("testbookcsv.csv");
        BookLoader testLoader = new BookLoader();
        writer.write(
            "bookID,title,authors,average_rating,isbn,isbn13,language_code,  num_pages,ratings_count,text_reviews_count,publication_date,publisher\r\n"
                + "1,Harry Potter and the Half-Blood Prince (Harry Potter  #6),J.K. Rowling/Mary GrandPré,4.57,0439785960,9780439785969,eng,652,2095690,27591,9/16/2006,Scholastic Inc.\r\n"
                + "2,Harry Potter and the Order of the Phoenix (Harry Potter  #5),J.K. Rowling/Mary GrandPré,4.49,0439358078,9780439358071,eng,870,2153167,29221,9/1/2004,Scholastic Inc.\r\n"
                + "4,Harry Potter and the Chamber of Secrets (Harry Potter  #2),J.K. Rowling,4.42,0439554896,9780439554893,eng,352,6333,244,11/1/2003,Scholastic\r\n"
                + "5,Harry Potter and the Prisoner of Azkaban (Harry Potter  #3),J.K. Rowling/Mary GrandPré,4.56,043965548X,9780439655484,eng,435,2339585,36325,5/1/2004,Scholastic Inc.\r\n"
                + "8,Harry Potter Boxed Set  Books 1-5 (Harry Potter  #1-5),J.K. Rowling/Mary GrandPré,4.78,0439682584,9780439682589,eng,2690,41428,164,9/13/2004,Scholastic\r\n"
                + "9,Unauthorized Harry Potter Book Seven News: \\\"Half-Blood Prince\\\" Analysis and Speculation,W. Frederick Zimmerman,3.74,0976540606,9780976540601,en-US,152,19,1,4/26/2005,Nimble Books\r\n"
                + "10,Harry Potter Collection (Harry Potter  #1-6),J.K. Rowling,4.73,0439827604,9780439827607,eng,3342,28242,808,9/12/2005,Scholastic\r\n"
                + "12224,Streetcar Suburbs: The Process of Growth in Boston  1870-1900,\"Sam Bass Warner, Jr./Sam B. Warner\",3.58,0674842111,9780674842113,en-US,236,61,6,4/20/2004,Harvard University Press");
        writer.close();
        List<IBook> testBookList = testLoader.loadBooks("testbookcsv.csv");
        if (!testBookList.get(7).getTitle()
            .equals("Streetcar Suburbs: The Process of Growth in Boston  1870-1900")) {
          System.out.println("Problem in loadBooks() method");
          return false;
        }
        if (!testBookList.get(7).getAuthors().equals("Sam Bass Warner, Jr./Sam B. Warner")) {
          System.out.println("Problem in loadBooks() method");
          return false;
        }
        if (!testBookList.get(7).getISBN13().equals("9780674842113")) {
          System.out.println("Problem in loadBooks() method");
          return false;
        }
      } catch (IOException e) {
        // unexpected error
        return false;
      }
    }
    return true;
  }

  /**
   * Checks whether the getter loadBooks() method works as expected when parsing a line with
   * escaped quotes and not escaped quotes.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean test5() {
    // tests whether loadBooks() method works correctly when parsing a csv line with quotes that
    // should be deleted and escaped quotes that should be turned into normal quotes
    {
      try {
        FileWriter writer = new FileWriter("testbookcsv.csv");
        BookLoader testLoader = new BookLoader();
        writer.write(
            "bookID,title,authors,average_rating,isbn,isbn13,language_code,  num_pages,ratings_count,text_reviews_count,publication_date,publisher\r\n"
                + "1,Harry Potter and the Half-Blood Prince (Harry Potter  #6),J.K. Rowling/Mary GrandPré,4.57,0439785960,9780439785969,eng,652,2095690,27591,9/16/2006,Scholastic Inc.\r\n"
                + "2,Harry Potter and the Order of the Phoenix (Harry Potter  #5),J.K. Rowling/Mary GrandPré,4.49,0439358078,9780439358071,eng,870,2153167,29221,9/1/2004,Scholastic Inc.\r\n"
                + "4,Harry Potter and the Chamber of Secrets (Harry Potter  #2),J.K. Rowling,4.42,0439554896,9780439554893,eng,352,6333,244,11/1/2003,Scholastic\r\n"
                + "5,Harry Potter and the Prisoner of Azkaban (Harry Potter  #3),J.K. Rowling/Mary GrandPré,4.56,043965548X,9780439655484,eng,435,2339585,36325,5/1/2004,Scholastic Inc.\r\n"
                + "8,Harry Potter Boxed Set  Books 1-5 (Harry Potter  #1-5),J.K. Rowling/Mary GrandPré,4.78,0439682584,9780439682589,eng,2690,41428,164,9/13/2004,Scholastic\r\n"
                + "9,Unauthorized Harry Potter Book Seven News: \\\"Half-Blood Prince\\\" Analysis and Speculation,W. Frederick Zimmerman,3.74,0976540606,9780976540601,en-US,152,19,1,4/26/2005,Nimble Books\r\n"
                + "10,Harry Potter Collection (Harry Potter  #1-6),J.K. Rowling,4.73,0439827604,9780439827607,eng,3342,28242,808,9/12/2005,Scholastic\r\n"
                + "12224,Streetcar Suburbs: The Process of Growth in Boston  1870-1900,\"Sam Bass Warner, Jr./Sam B. Warner\",3.58,0674842111,9780674842113,en-US,236,61,6,4/20/2004,Harvard University Press\r\n"
                + "12225,Unauthorized Harry Potter Book Seven News: \\\"Half-Blood Prince\\\" Analysis and Speculation \"Sam Bass Warner, Jr./Sam B. Warner\",\"Sam Bass Warner, Jr./Sam B. Warner\",3.58,0674842111,9780674842113,en-US,236,61,6,4/20/2004,Harvard University Press");
        writer.close();
        List<IBook> testBookList = testLoader.loadBooks("testbookcsv.csv");
        if (!testBookList.get(8).getTitle().equals(
            "Unauthorized Harry Potter Book Seven News: \"Half-Blood Prince\" Analysis and Speculation Sam Bass Warner, Jr./Sam B. Warner")) {
          System.out.println("Problem in loadBooks() method");
          return false;
        }
        if (!testBookList.get(8).getAuthors().equals("Sam Bass Warner, Jr./Sam B. Warner")) {
          System.out.println("Problem in loadBooks() method");
          return false;
        }
        if (!testBookList.get(8).getISBN13().equals("9780674842113")) {
          System.out.println("Problem in loadBooks() method");
          return false;
        }
      } catch (IOException e) {
        // unexpected error
        return false;
      }
    }
    return true;
  }

  /**
   * Checks whether the getter methods for Book() work as expected when working together with a backend method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean test6() { 
    // tests whether getter methods for Book() works when working with the getByISBN method from backend
    {
      BookMapperBackend testBackend = new BookMapperBackend();
      Book testBook = new Book("Kavan Autobiography", "Kavan Gandhi", "9780767903820");
      testBackend.addBook(testBook);

      IBook resultBook = testBackend.getByISBN("9780767903820");

      if (!resultBook.getTitle().equals("Kavan Autobiography")) {
        System.out.println("Problem in Book getter method");
        return false;
      }
      if (!resultBook.getAuthors().equals("Kavan Gandhi")) {
        System.out.println("Problem in Book getter method");
        return false;
      }
      if (!resultBook.getISBN13().equals("9780767903820")) {
        System.out.println("Problem in Book getter method");
        return false;
      }
    }
    return true;
  }

  /**
   * Checks whether the getter methods for Book() work as expected when working together with a algorithm engineer method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean test7() { 
    // tests whether getter methods for Book() works when working with the getByISBN method from algorithm engineer
    {
      Book testBook = new Book("Kavan Autobiography", "Kavan Gandhi", "9780767903820");

      ISBNValidator testValidator = new ISBNValidator();
      boolean validation = testValidator.validate(testBook.getISBN13());
      if (!validation) {
        System.out.println("Problem in Book getter method");
        return false;
      }
    }
    return true;
  }

  /**
   * Checks whether the addBook() and getNumberOfBooks() methods work as expected.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean test8() { 
    {
      BookMapperBackend testBackend = new BookMapperBackend();
      Book testBook = new Book("Kavan Autobiography", "Kavan Gandhi", "9780767903820");

      testBackend.addBook(testBook);
    
      if (testBackend.getNumberOfBooks() != 1) {
        return false;
      }
    }
    return true;
  }

  /**
   * Checks whether the setAuthorFilter() and getAuthorFilter() methods work as expected.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean test9() { 
    {
      BookMapperBackend testBackend = new BookMapperBackend();
      testBackend.setAuthorFilter("Kavan Gandhi");
    
      if (!testBackend.getAuthorFilter().equals("Kavan Gandhi")) {
      return false;
      }
    }
    return true;
  }
}
