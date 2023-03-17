import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class AlgorithmEngineerTest {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Algorithm Engineer Individual Test 1: "+result(test1()));
        System.out.println("Algorithm Engineer Individual Test 2: "+result(test2()));
        System.out.println("Algorithm Engineer Individual Test 3: "+result(test3()));
        System.out.println("Algorithm Engineer Individual Test 4: "+result(test4()));
        System.out.println("Algorithm Engineer Individual Test 5: "+result(test5()));
        System.out.println("Algorithm Engineer Integration Test 1: "+result(integrationTest1()));
        System.out.println("Algorithm Engineer Integration Test 2: "+result(integrationTest2()));
        System.out.println("Algorithm Engineer Partner (Frontend Developer) Test 1: "+result(partnerTest1()));
        System.out.println("Algorithm Engineer Partner (Frontend Developer) Test 2: "+result(partnerTest2()));
    }
    public static String result(boolean testResult){
        if (testResult){
            return "passed";
        } else {
            return "failed";
        }
    }
    public static boolean integrationTest1() throws FileNotFoundException {
        //checks if searching for a book by ISBN works
        TextUITester tester = new TextUITester("1\n9788445074879\n4\n");
        IBookLoader bookLoader = new BookLoader(); // new BookLoader();

        // load the books from the data file
        List<IBook> bookList = bookLoader.loadBooks("books.csv"); // bookLoader.loadBooks("books.csv");

        // instantiate the backend
        IBookMapperBackend backend = new BookMapperBackend(); // new BookMapperBackend();

        // add all the books to the backend
        for (IBook book : bookList){
            backend.addBook(book);
        }

        // instantiate the isbn validator
        IISBNValidator isbnValidator = new ISBNValidator(); // new ISBNValidator();

        // instantiate the scanner for user input
        Scanner userInputScanner = new Scanner(System.in);

        // instantiate the front end and pass references to the scanner, backend, and isbn validator to it
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
                "Enter ISBN to look up: 1. \"Fahrenheit 451\" by Ray Bradbury, ISBN: 9788445074879\n" +
                "\n" +
                "You are in the Main Menu:\n" +
                "\t1) Lookup ISBN\n" +
                "\t2) Search by Title Word\n" +
                "\t3) Set Author Name Filter\n" +
                "\t4) Exit Application\n" +
                "Goodbye!\n");
    }

    public static boolean integrationTest2() throws FileNotFoundException {
        //checks if searching for an invalid ISBN says that the ISBN is invalid.
        TextUITester tester = new TextUITester("1\n9788445074878\n4\n");
        IBookLoader bookLoader = new BookLoader(); // new BookLoader();

        // load the books from the data file
        List<IBook> bookList = bookLoader.loadBooks("books.csv"); // bookLoader.loadBooks("books.csv");

        // instantiate the backend
        IBookMapperBackend backend = new BookMapperBackend(); // new BookMapperBackend();

        // add all the books to the backend
        for (IBook book : bookList){
            backend.addBook(book);
        }

        // instantiate the isbn validator
        IISBNValidator isbnValidator = new ISBNValidator(); // new ISBNValidator();

        // instantiate the scanner for user input
        Scanner userInputScanner = new Scanner(System.in);

        // instantiate the front end and pass references to the scanner, backend, and isbn validator to it
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
                "Enter ISBN to look up: Invalid ISBN.\n" +
                "You are in the Main Menu:\n" +
                "\t1) Lookup ISBN\n" +
                "\t2) Search by Title Word\n" +
                "\t3) Set Author Name Filter\n" +
                "\t4) Exit Application\n" +
                "Goodbye!\n");
    }

    public static boolean partnerTest1() throws FileNotFoundException {
        //checks if all 4 parts of main menu work
        TextUITester tester = new TextUITester("1\n9788445074879\n2\nFahrenheit 451\n3\nray bradbury\n2\n\n4\n");
        IBookLoader bookLoader = new BookLoader(); // new BookLoader();

        // load the books from the data file
        List<IBook> bookList = bookLoader.loadBooks("books.csv"); // bookLoader.loadBooks("books.csv");

        // instantiate the backend
        IBookMapperBackend backend = new BookMapperBackend(); // new BookMapperBackend();

        // add all the books to the backend
        for (IBook book : bookList){
            backend.addBook(book);
        }

        // instantiate the isbn validator
        IISBNValidator isbnValidator = new ISBNValidator(); // new ISBNValidator();

        // instantiate the scanner for user input
        Scanner userInputScanner = new Scanner(System.in);

        // instantiate the front end and pass references to the scanner, backend, and isbn validator to it
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
                "Enter ISBN to look up: 1. \"Fahrenheit 451\" by Ray Bradbury, ISBN: 9788445074879\n" +
                "\n" +
                "You are in the Main Menu:\n" +
                "\t1) Lookup ISBN\n" +
                "\t2) Search by Title Word\n" +
                "\t3) Set Author Name Filter\n" +
                "\t4) Exit Application\n" +
                "You are in the Search for Title Word Menu:\n" +
                "Enter a word to search for in book titles (empty for all books): Matches (author filter: null) 4 of 11124\n" +
                "1. \"Fahrenheit 451\" by Ray Bradbury, ISBN: 9788445074879\n" +
                "\n" +
                "2. \"Ray Bradbury's Fahrenheit 451\" by Harold Bloom, ISBN: 9780791059296\n" +
                "\n" +
                "3. \"Fahrenheit 451\" by Ray Bradbury/Christopher Hurt, ISBN: 9780786176274\n" +
                "\n" +
                "4. \"Fahrenheit 451\" by Ray Bradbury/Alfredo Crespo, ISBN: 9788497930055\n" +
                "\n" +
                "Matches (author filter: null) 4 of 11124\n" +
                "You are in the Main Menu:\n" +
                "\t1) Lookup ISBN\n" +
                "\t2) Search by Title Word\n" +
                "\t3) Set Author Name Filter\n" +
                "\t4) Exit Application\n" +
                "You are in the Set Author Filter Menu:\n" +
                "\tAuthor name must currently contain: none\n" +
                "\tEnter a new string for author names to contain (empty for any): You are in the Main Menu:\n" +
                "\t1) Lookup ISBN\n" +
                "\t2) Search by Title Word\n" +
                "\t3) Set Author Name Filter\n" +
                "\t4) Exit Application\n" +
                "You are in the Search for Title Word Menu:\n" +
                "Enter a word to search for in book titles (empty for all books): Matches (author filter: ray bradbury) 21 of 11124\n" +
                "1. \"The Best of Ray Bradbury\" by Ray Bradbury/Dave Gibbons/Richard Corben/Mike Mignola, ISBN: 9780743474764\n" +
                "\n" +
                "2. \"One More For The Road\" by Ray Bradbury, ISBN: 9780743440745\n" +
                "\n" +
                "3. \"Fahrenheit 451\" by Ray Bradbury, ISBN: 9788445074879\n" +
                "\n" +
                "4. \"Bradbury Stories: 100 of His Most Celebrated Tales\" by Ray Bradbury, ISBN: 9780060544881\n" +
                "\n" +
                "5. \"The Illustrated Man\" by Ray Bradbury, ISBN: 9780881031904\n" +
                "\n" +
                "6. \"Dandelion Wine\" by Ray Bradbury, ISBN: 9780380977260\n" +
                "\n" +
                "7. \"Masterpieces: The Best Science Fiction of the Twentieth Century\" by Orson Scott Card/Isaac Asimov/William Gibson/Michael Swanwick/Theodore Sturgeon/Larry Niven/Robert Silverberg/Harry Turtledove/James Blish/George R.R. Martin/James Patrick Kelly/Karen Joy Fowler/Arthur C. Clarke/Lloyd Biggle Jr./Terry Bisson/Poul Anderson/John Kessel/R.A. Lafferty/C.J. Cherryh/Lisa Goldstein/Edmond Hamilton/Robert A. Heinlein/Ursula K. Le Guin/Ray Bradbury/Frederik Pohl/Harlan Ellison/George Alec Effinger/Brian W. Aldiss, ISBN: 9780441011339\n" +
                "\n" +
                "8. \"Fahrenheit 451\" by Ray Bradbury/Christopher Hurt, ISBN: 9780786176274\n" +
                "\n" +
                "9. \"The Illustrated Man\" by Ray Bradbury, ISBN: 9780553105575\n" +
                "\n" +
                "10. \"Farewell Summer (Green Town  #3)\" by Ray Bradbury, ISBN: 9780061131547\n" +
                "\n" +
                "11. \"Zen in the Art of Writing: Essays on Creativity\" by Ray Bradbury, ISBN: 9781877741098\n" +
                "\n" +
                "12. \"Fahrenheit 451\" by Ray Bradbury/Alfredo Crespo, ISBN: 9788497930055\n" +
                "\n" +
                "13. \"Something Wicked This Way Comes\" by Ray Bradbury, ISBN: 9780965020459\n" +
                "\n" +
                "14. \"Switch on the Night\" by Ray Bradbury/Leo Dillon/Diane Dillon, ISBN: 9780553112443\n" +
                "\n" +
                "15. \"Duel\" by Richard Matheson/Ray Bradbury, ISBN: 9780312878269\n" +
                "\n" +
                "16. \"The Martian Chronicles\" by Ray Bradbury/Ian  Miller, ISBN: 9780553011807\n" +
                "\n" +
                "17. \"The Dark Descent\" by David G. Hartwell/Clive Barker/Ray Bradbury/John Collier/Shirley Jackson/Stephen King/Joyce Carol Oates, ISBN: 9780312862176\n" +
                "\n" +
                "18. \"Classic Stories 1: The Golden Apples of the Sun/R is for Rocket\" by Ray Bradbury, ISBN: 9780553286373\n" +
                "\n" +
                "19. \"The Homecoming\" by Ray Bradbury/Dave McKean, ISBN: 9780060859626\n" +
                "\n" +
                "20. \"A Magic-Lover's Treasury of the Fantastic\" by Margaret Weis/Ursula K. Le Guin/Roger Zelazny/Christopher Stasheff/Mercedes Lackey/Ray Bradbury/Andre Norton/Jack Vance/Raymond E. Feist/C.J. Cherryh/Fritz Leiber/Melanie Rawn/Marion Zimmer Bradley/Larry Niven/Zenna Henderson/Greg Bear/Orson Scott Card/Katherine Kurtz/Joe Haldeman/Robert Silverberg/F. Paul Wilson/Keith Birdsong, ISBN: 9780446672849\n" +
                "\n" +
                "21. \"The Cat's Pajamas\" by Ray Bradbury, ISBN: 9780060777333\n" +
                "\n" +
                "Matches (author filter: ray bradbury) 21 of 11124\n" +
                "You are in the Main Menu:\n" +
                "\t1) Lookup ISBN\n" +
                "\t2) Search by Title Word\n" +
                "\t3) Set Author Name Filter\n" +
                "\t4) Exit Application\n" +
                "Goodbye!\n");
    }

    public static boolean partnerTest2() throws FileNotFoundException {
        //checks if the demonstration video requirements for Algorithm Engineer work

        TextUITester tester = new TextUITester("3\ndouglas adams\n2\nhitchhiker\n4\n");
        IBookLoader bookLoader = new BookLoader(); // new BookLoader();

        // load the books from the data file
        List<IBook> bookList = bookLoader.loadBooks("books.csv"); // bookLoader.loadBooks("books.csv");

        // instantiate the backend
        IBookMapperBackend backend = new BookMapperBackend(); // new BookMapperBackend();

        // add all the books to the backend
        for (IBook book : bookList) {
            backend.addBook(book);
        }
        // instantiate the isbn validator
        IISBNValidator isbnValidator = new ISBNValidator(); // new ISBNValidator();

        // instantiate the scanner for user input
        Scanner userInputScanner = new Scanner(System.in);

        // instantiate the front end and pass references to the scanner, backend, and isbn validator to it
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
                "\tEnter a new string for author names to contain (empty for any): You are in the Main Menu:\n" +
                "\t1) Lookup ISBN\n" +
                "\t2) Search by Title Word\n" +
                "\t3) Set Author Name Filter\n" +
                "\t4) Exit Application\n" +
                "You are in the Search for Title Word Menu:\n" +
                "Enter a word to search for in book titles (empty for all books): Matches (author filter: douglas adams) 14 of 11124\n" +
                "1. \"The Restaurant at the End of the Universe (The Hitchhiker's Guide to the Galaxy  #2)\" by Douglas Adams/Martin  Freeman, ISBN: 9780739332078\n" +
                "\n" +
                "2. \"The Hitchhiker's Guide to the Galaxy (Hitchhiker's Guide to the Galaxy  #1)\" by Douglas Adams/Stephen Fry, ISBN: 9780739322208\n" +
                "\n" +
                "3. \"The Hitchhiker's Guide to the Galaxy (Hitchhiker's Guide  #1)\" by Douglas Adams, ISBN: 9781400052936\n" +
                "\n" +
                "4. \"The Hitchhiker's Guide to the Galaxy (Hitchhiker's Guide to the Galaxy  #1)\" by Douglas Adams, ISBN: 9781400052929\n" +
                "\n" +
                "5. \"Life  the Universe and Everything (Hitchhiker's Guide to the Galaxy  #3)\" by Douglas Adams, ISBN: 9780345418906\n" +
                "\n" +
                "6. \"The More Than Complete Hitchhiker's Guide (Hitchhiker's Guide  #1-4 + short story)\" by Douglas Adams, ISBN: 9780681403222\n" +
                "\n" +
                "7. \"The Ultimate Hitchhiker's Guide to the Galaxy (Hitchhiker's Guide to the Galaxy  #1-5)\" by Douglas Adams, ISBN: 9780345453747\n" +
                "\n" +
                "8. \"The Ultimate Hitchhiker's Guide (Hitchhiker's Guide to the Galaxy  #1-5)\" by Douglas Adams, ISBN: 9780517149256\n" +
                "\n" +
                "9. \"The Illustrated Hitchhiker's Guide To The Galaxy\" by Douglas Adams, ISBN: 9780517599242\n" +
                "\n" +
                "10. \"The Ultimate Hitchhiker's Guide: Five Complete Novels and One Story (Hitchhiker's Guide to the Galaxy  #1-5)\" by Douglas Adams, ISBN: 9780517226957\n" +
                "\n" +
                "11. \"The Hitchhiker's Guide to the Galaxy (Hitchhiker's Guide to the Galaxy  #1)\" by Douglas Adams, ISBN: 9780671746063\n" +
                "\n" +
                "12. \"So Long  and Thanks for All the Fish (Hitchhiker's Guide to the Galaxy  #4)\" by Douglas Adams, ISBN: 9780330491235\n" +
                "\n" +
                "13. \"The Hitchhiker's Guide to the Galaxy (Hitchhiker's Guide to the Galaxy  #1)\" by Douglas Adams, ISBN: 9780330491198\n" +
                "\n" +
                "14. \"The Hitchhiker's Guide to the Galaxy: The Quintessential Phase (Hitchhiker's Guide: Radio Play  #5)\" by Douglas Adams/Dirk Maggs/Simon  Jones/Geoffrey McGivern/Mark Wing-Davey/Susan  Sheridan/Sandra Dickinson/Stephen  Moore/William Franklyn/Rula Lenska/Sam  Burke, ISBN: 9780563504078\n" +
                "\n" +
                "Matches (author filter: douglas adams) 14 of 11124\n" +
                "You are in the Main Menu:\n" +
                "\t1) Lookup ISBN\n" +
                "\t2) Search by Title Word\n" +
                "\t3) Set Author Name Filter\n" +
                "\t4) Exit Application\n" +
                "Goodbye!\n");
    }


    public static boolean test1() {
        HashtableMap test = new HashtableMap(8);
        test.put(1, "one");
        test.put(2, "two");
        test.put(3, "three");
        // checks if it returns correct value
        if (test.get(1).equals("one")){
            //checks if it returns the value that should be removed
            if (test.remove(3).equals("three")){
                //checks if the key was actually removed
                if (!test.containsKey(3)){
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean test2() {
        ISBNValidator validator = new ISBNValidator();
        //checks validator to see if it correctly validates ISBNs
        return validator.validate("9781306492935")&&!validator.validate("8781306492935");
    }
    public static boolean test3() {
        HashtableMap test = new HashtableMap(8);
        test.put(1, 1);
        test.put(2, "two");
        test.put(3, false);
        String output="";
        //checks if it iterates over every pair
        for (Object o: test){
            output+=o;
        }
        return output.equals("Key: 1, Value: 1Key: 2, Value: twoKey: 3, Value: false");
    }
    public static boolean test4() {
        HashtableMap test = new HashtableMap(8);
        test.put(1, "a");
        test.put(2, "b");
        test.put(33, "c");
        test.put(47, "d");
        test.put(5224, "e");
        test.put(60112, "f");
        String output="";
        //checks if it iterates over every pair and only returns the values for the keys that are odd.
        for (Object o: test){
            HashtableMap.KeyValuePair pair = (HashtableMap.KeyValuePair) o;
            if ((int) pair.getKey()%2==1) {
                output += pair.getValue();
            }
        }
        return output.equals("acd");
    }
    public static boolean test5() {
        HashtableMap test = new HashtableMap(8);
        test.put(1, "8781306492935");
        test.put(2, "9781306492935");
        test.put(3, "9781306493925");
        test.put(4, "9741102492935");
        test.put(5, "9771306092912");
        test.put(6, "9781307492125");
        ISBNValidator validator = new ISBNValidator();
        String output="";
        //checks if it iterates over every pair and checks if the isbn value of the key is valid.
        for (Object o: test){
            HashtableMap.KeyValuePair pair = (HashtableMap.KeyValuePair) o;
            output += validator.validate((String)pair.getValue());
        }
        return output.equals("falsetruetruetruefalsetrue");
    }
}
