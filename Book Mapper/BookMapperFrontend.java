// --== CS400 Project One File Header ==--
// Name: Sarthak Agrawal
// CSL Username: sarthak
// Email: skagrawal@wisc.edu
// Lecture #: 003 @2:25pm
// Notes to Grader: none

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BookMapperFrontend implements IBookMapperFrontend {

    private Scanner scn;
    private IBookMapperBackend backend;
    private IISBNValidator validator;

    public BookMapperFrontend(Scanner scn, IBookMapperBackend backend, IISBNValidator validator) {
        //Initializes the class vars
        this.scn = scn;
        this.backend = backend;
        this.validator = validator;

    }

    /**
     * This method runs the command loop for the frontend. Will run forever unless user inputs 4.
     */
    @Override
    public void runCommandLoop() {
        String input = "";
        while (!input.equals("4")) {
            //Runs loop until the input is equal to 4, IE, the user stopped it.
            displayMainMenu();
            input = scn.nextLine();
            if (input.equals("1")) {
                isbnLookup();
            } else if (input.equals("2")) {
                titleSearch();
            } else if (input.equals("3")) {
                System.out.println("You are in the Set Author Filter Menu:\n\tAuthor name must currently contain: none");
                System.out.print("\tEnter a new string for author names to contain (empty for any): ");
                String filter = scn.nextLine();
                backend.setAuthorFilter(filter);
            } else if (!input.equals("4")){
                System.out.println("Invalid Input.");
            }
        }

        System.out.println("Goodbye!");
    }

    /**
     * Prints out the mian menu that includes information about what each command does.
     */
    @Override
    public void displayMainMenu() {
        System.out.println(
                "You are in the Main Menu:\n" +
                        "\t1) Lookup ISBN\n" +
                        "\t2) Search by Title Word\n" +
                        "\t3) Set Author Name Filter\n" +
                        "\t4) Exit Application");

    }

    /**
     * Prints out all the books passed into the function
     * @param books
     */
    @Override
    public void displayBooks(List<IBook> books) {
        for (int i = 0; i < books.size(); i++) {
            //Prints out each book using the appropriate format.
            System.out.println((i+1) + ". \"" + books.get(i).getTitle() + "\" by " +
                    books.get(i).getAuthors() + ", ISBN: " + books.get(i).getISBN13() + "\n");
        }
    }

    /**
     * Looks up the ISBN, and prints out the book with the corresponding ISBN.
     * Prints Invalid ISBN if the ISBN is invalid.
     */
    @Override
    public void isbnLookup() {
        System.out.print("You are in the Lookup ISBN Menu:\nEnter ISBN to look up: ");
        String isbn = scn.nextLine();
        //Scans for ISBN
        if (!validator.validate(isbn)) {
            //Checks if the ISBN is valid
            System.out.println("Invalid ISBN.");
            return;
        }
        displayBooks(Arrays.asList(backend.getByISBN(isbn)));
    }

    /**
     * Uses user input to print all the books containing the title. Uses the displaybooks function.
     */
    @Override
    public void titleSearch() {
        System.out.print("You are in the Search for Title Word Menu:" +
                "\nEnter a word to search for in book titles (empty for all books): ");
        String word = scn.nextLine();

        List<IBook> books = backend.searchByTitleWord(word);
        //Searches for the title

        System.out.println("Matches (author filter: " + backend.getAuthorFilter() + ") " + books.size() + " of " + backend.getNumberOfBooks());

        //Prints the books
        displayBooks(books);

        System.out.println("Matches (author filter: " + backend.getAuthorFilter() + ") " + books.size() + " of " + backend.getNumberOfBooks());
    }

}
