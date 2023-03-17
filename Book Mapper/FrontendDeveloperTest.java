import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class FrontendDeveloperTest {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("FrontendDeveloper Individual Test 1: " + passed(test1()));
        System.out.println("FrontendDeveloper Individual Test 2: " + passed(test2()));
        System.out.println("FrontendDeveloper Individual Test 3: " + passed(test3()));
        System.out.println("FrontendDeveloper Individual Test 4: " + passed(test4()));
        System.out.println("FrontendDeveloper Individual Test 5: " + passed(test5()));
        System.out.println("FrontendDeveloper Integration Test 1: " + passed(integrationTest1()));
        System.out.println("FrontendDeveloper Integration Test 2: " + passed(integrationTest2()));
        System.out.println("FrontendDeveloper Partner (AlgorithmEngineer) Test 1: " + passed(partnerTest1()));
        System.out.println("FrontendDeveloper Partner (AlgorithmEngineer) Test 2: " + passed(partnerTest2()));

    }

    public static String passed(boolean passed) {
        if (passed) {
            return "passed";
        } else {
            return "failed";
        }
    }

    /**
     * Runs the frontend UI and does Search for a title and then exits the UI.
     * @return true if the main menu was displayed twice, false otherwise
     */
    public static boolean test1() {
        TextUITester tester = new TextUITester("2\n\n4\n");

        IBookMapperFrontend frontend = new BookMapperFrontend(new Scanner(System.in), new FDBookMapperBackend(), new FDISBNValidator());

        frontend.runCommandLoop();

        String output = tester.checkOutput();

        return output.split("You are in the Main Menu").length == 3;
    }

    /**
     * Uses the frontend UI to set the Author Filter to John and then exits the UI
     * @return true if the author filter was successfully set to John, else false
     */
    public static boolean test2() {
        TextUITester tester = new TextUITester("3\nJohn\n4\n");

        FDBookMapperBackend backend = new FDBookMapperBackend();

        IBookMapperFrontend frontend = new BookMapperFrontend(new Scanner(System.in), backend, new FDISBNValidator());

        System.out.println(backend.getAuthorFilter());
        frontend.runCommandLoop();

        String output = tester.checkOutput();

        return backend.getAuthorFilter().equals("John");

    }

    /**
     * Runs the frontend calls the lookup ISBN function
     * @return true if the frontend prints out a book with the title ISBN1, false otherwise
     */
    public static boolean test3() {
        TextUITester tester = new TextUITester("1\nISBN 1\n4\n");

        FDBookMapperBackend backend = new FDBookMapperBackend();

        IBookMapperFrontend frontend = new BookMapperFrontend(new Scanner(System.in), backend, new FDISBNValidator());

        System.out.println(backend.getAuthorFilter());
        frontend.runCommandLoop();

        String output = tester.checkOutput();

        return output.split("\"")[1].equals("Title 1");

        }

    /**
     * Runs the frontend and calls the "Search By Title Word option" in the frontend
     * @return true if the frontend printed out two books with Title 1 and Title 2, false otherwise
     */
    public static boolean test4() {
        TextUITester tester = new TextUITester("2\n\n4\n");

        FDBookMapperBackend backend = new FDBookMapperBackend();

        IBookMapperFrontend frontend = new BookMapperFrontend(new Scanner(System.in), backend, new FDISBNValidator());

        System.out.println(backend.getAuthorFilter());
        frontend.runCommandLoop();

        String output = tester.checkOutput();

        return output.split("\"")[1].equals("Title 1") && output.split("\"")[3].equals("Title 2");

    }

    /**
     * Runs the frontend, sets the author name filter and then lists all books using search.
     * @return true if when listing the books it outputs the new author filter, false otherwise
     */
    public static boolean test5() {
        TextUITester tester = new TextUITester("3\nJohn\n2\n\n4\n");

        FDBookMapperBackend backend = new FDBookMapperBackend();

        IBookMapperFrontend frontend = new BookMapperFrontend(new Scanner(System.in), backend, new FDISBNValidator());

        System.out.println(backend.getAuthorFilter());
        frontend.runCommandLoop();

        String output = tester.checkOutput();

        return output.split("author filter: ")[1].contains("John");

    }

    /**
     * Initializes the full application, set's the author filter, and then searches by title word
     * @return true if the correct books are displayed, otherwise false
     * @throws FileNotFoundException if the data file is not found
     */
    public static boolean integrationTest1() throws FileNotFoundException {
        TextUITester tester = new TextUITester("3\nKlein\n2\nHarry\n4\n");
        IBookLoader bookLoader = new BookLoader(); // new BookLoader();

        //initialization steps, taken from BookMapper.java
        List<IBook> bookList = bookLoader.loadBooks("books.csv"); // bookLoader.loadBooks("books.csv");
        IBookMapperBackend backend = new BookMapperBackend(); // new BookMapperBackend();
        for (IBook book : bookList) {
            backend.addBook(book);
        }
        IISBNValidator isbnValidator = new ISBNValidator(); // new ISBNValidator();
        Scanner userInputScanner = new Scanner(System.in);
        IBookMapperFrontend frontend = new BookMapperFrontend(userInputScanner, backend, isbnValidator); // new BookMapperFrontend(userInputScanner, backend, isbnValidator);
        // start the input loop of the front end
        frontend.runCommandLoop();

        String output = tester.checkOutput();
        return output.equals("You are in the Main Menu:\n" +
                "\t1) Lookup ISBN\n" +
                "\t2) Search by Title Word\n" +
                "\t3) Set Author Name Filter\n" +
                "\t4) Exit Application\n" +
                "You are in the Set Author Filter Menu:\n" +
                "\tAuthor name must currently contain: none\n" +
                "\tEnter a new string for author names to contain (empty for any): " +
                "You are in the Main Menu:\n" +
                "\t1) Lookup ISBN\n" +
                "\t2) Search by Title Word\n" +
                "\t3) Set Author Name Filter\n" +
                "\t4) Exit Application\n" +
                "You are in the Search for Title Word Menu:\n" +
                "Enter a word to search for in book titles (empty for all books): " +
                "Matches (author filter: Klein) 1 of 11124\n" +
                "1. \"Harry Potter and Philosophy: If Aristotle Ran Hogwarts\" by David Baggett/Shawn E. Klein, ISBN: 9780812694550\n\n" +
                "Matches (author filter: Klein) 1 of 11124\n" +
                "You are in the Main Menu:\n" +
                "\t1) Lookup ISBN\n" +
                "\t2) Search by Title Word\n" +
                "\t3) Set Author Name Filter\n" +
                "\t4) Exit Application\n" +
                "Goodbye!\n");
    }

    /**
     * Initializes the full application and looks up a book's ISBN to find the book
     * @return true if the correct book and output is displayed, otherwise false
     * @throws FileNotFoundException if the data file is not found
     */
    public static boolean integrationTest2() throws FileNotFoundException {
        TextUITester tester = new TextUITester("1\n9780767915069\n4\n");
        IBookLoader bookLoader = new BookLoader(); // new BookLoader();

        //initialization steps, taken from BookMapper.java
        List<IBook> bookList = bookLoader.loadBooks("books.csv"); // bookLoader.loadBooks("books.csv");
        IBookMapperBackend backend = new BookMapperBackend(); // new BookMapperBackend();
        for (IBook book : bookList) {
            backend.addBook(book);
        }
        IISBNValidator isbnValidator = new ISBNValidator(); // new ISBNValidator();
        Scanner userInputScanner = new Scanner(System.in);
        IBookMapperFrontend frontend = new BookMapperFrontend(userInputScanner, backend, isbnValidator); // new BookMapperFrontend(userInputScanner, backend, isbnValidator);
        // start the input loop of the front end
        frontend.runCommandLoop();

        String output = tester.checkOutput();
        return output.equals("You are in the Main Menu:\n" +
                "\t1) Lookup ISBN\n" +
                "\t2) Search by Title Word\n" +
                "\t3) Set Author Name Filter\n" +
                "\t4) Exit Application\n" +
                "You are in the Lookup ISBN Menu:\n" +
                "Enter ISBN to look up: 1. \"Bill Bryson's African Diary\" by Bill Bryson, ISBN: 9780767915069\n\n" +
                "You are in the Main Menu:\n" +
                "\t1) Lookup ISBN\n" +
                "\t2) Search by Title Word\n" +
                "\t3) Set Author Name Filter\n" +
                "\t4) Exit Application\n" +
                "Goodbye!\n");
    }

    /**
     * Creates an ISBNValidator and checks whether the validator identifies the valid ISBN and invalid ISBN
     * @return true if it determines the valid ISBN valid and invalid ISBN invalid, false otherwise
     */
    public static boolean partnerTest1() {
        ISBNValidator validator = new ISBNValidator();
        return validator.validate("9780439655484")&&!validator.validate("5847083921084");
    }

    /**
     * Creates a new HashtableMap with a few key value
     * @return
     */
    public static boolean partnerTest2() {
        HashtableMap table = new HashtableMap();
        table.put("one", 1);
        table.put("two", 2);

        String res = "";

        for (Object pair: table) {
            res+=pair.toString();
        }

        return res.equals("Key: two, Value: 2Key: one, Value: 1");
    }

}
