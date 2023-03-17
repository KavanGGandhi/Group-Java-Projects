import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

// Name: Michael Dinh
// CSL Username: mdinh
// Email: mtdinh@wisc.edu
// Lecture #: MWF @2:25pm
// tester class
public class BackendDeveloperTest {

    public static void main(String args[]) {

        if (test1()) {
            System.out.println("BackendDeveloper Individual Test 1: passed");
        } else {
            System.out.println("BackendDeveloper Individual Test 1: failed");
        }

        if (test2()) {
            System.out.println("BackendDeveloper Individual Test 2: passed");
        } else {
            System.out.println("BackendDeveloper Individual Test 2: failed");
        }

        if (test3()) {
            System.out.println("BackendDeveloper Individual Test 3: passed");
        } else {
            System.out.println("BackendDeveloper Individual Test 3: failed");
        }

        if (test4()) {
            System.out.println("BackendDeveloper Individual Test 4: passed");
        } else {
            System.out.println("BackendDeveloper Individual Test 4: failed");
        }

        if (test5()) {
            System.out.println("BackendDeveloper Individual Test 5: passed");
        } else {
            System.out.println("BackendDeveloper Individual Test 5: failed");
        }
        
        if (test6()) {
            System.out.println("BackendDeveloper Integration Test 6: passed");
        } else {
            System.out.println("BackendDeveloper Integration Test 6: failed");
        }
        
        if (test7()) {
            System.out.println("BackendDeveloper Integration Test 7: passed");
        } else {
            System.out.println("BackendDeveloper Integration Test 7: failed");
        }
                
        
        // 2 Partner Tests
        if (test8()) {
            System.out.println("BackendDeveloper Partner (DataWrangler) Test 1: passed");
        }
        else {
            System.out.println("BackendDeveloper Partner (DataWrangler) Test 1: failed");
        }
        
        if (test9()) {
            System.out.println("BackendDeveloper Partner (DataWrangler) Test 2: passed");
        }
        else {
            System.out.println("BackendDeveloper Partner (DataWrangler) Test 2: failed");
        }

    }

    /**
     * This tester method tests if the BookMapperBackend class's getNumberOfBooks
     * and addBook work
     * 
     * @return boolean if all tests pass
     */
    public static boolean test1() {

        BookMapperBackend backend1 = new BookMapperBackend();

        if (backend1.getNumberOfBooks() != 0) {
            return false;
        }

        backend1.addBook(new Book("Blue Book", "Bhumin", "54321"));

        if (backend1.getNumberOfBooks() != 1) {
            return false;
        }

        backend1.addBook(new Book("Red Book", "Mike Evans", "22"));

        if (backend1.getNumberOfBooks() != 2) {
            return false;
        }

        return true;
    }

    /**
     * This tester method tests if setAuthorFilter and getAuthorFilter work
     * 
     * @return boolean if all tests pass
     */
    public static boolean test2() {

        BookMapperBackend backend2 = new BookMapperBackend();

        if (backend2.getAuthorFilter() != null) {
            return false;
        }

        backend2.setAuthorFilter("Breece Hall");

        if (!backend2.getAuthorFilter().equals("Breece Hall")) {
            return false;
        }

        return true;
    }

    /**
     * This tester method tests if the resetAuthorFilter function works
     * 
     * @return boolean if all tests pass
     */
    public static boolean test3() {

        BookMapperBackend backend3 = new BookMapperBackend();
        backend3.setAuthorFilter("Bob");
        if (!backend3.getAuthorFilter().equals("Bob")) {
            return false;
        }

        backend3.resetAuthorFilter();

        if (backend3.getAuthorFilter() != null) {
            return false;
        }

        return true;
    }

    /**
     * This tester method tests if the searchByTitleWord
     * 
     * @return boolean if all tests pass
     */
    public static boolean test4() {

        BookMapperBackend backend4 = new BookMapperBackend();

        backend4.addBook(new Book("BlueBook", "Bhumin", "54321"));
        backend4.addBook(new Book("RedBook", "Catman", "1"));
        backend4.addBook(new Book("GreenBook", "Dogman", "2"));

        // checks All books are detected because all fit ""
        int qty1 = backend4.searchByTitleWord("").size();
        if (qty1 != 3) {
            return false;
        }

        // verify all 3 books added because include "Book" in title
        int qty2 = backend4.searchByTitleWord("Book").size();
        if (qty2 != 3) {
            return false;
        }

        // verify list size is 0 because "Books" is in none of titles
        int qty3 = backend4.searchByTitleWord("Books").size();
        if (qty3 != 0) {
            return false;
        }

        // setting the author filter such that it only matches 1 of 3.
        // Searching by title of empty string should detect all 3 books, but author
        // filter will bring it down to only the first book
        backend4.setAuthorFilter("Bhumin");
        int qty4 = backend4.searchByTitleWord("").size();
        if (qty4 != 1) {
            return false;
        }

        return true;
    }

    /**
     * This tester method tests if the getByISBN works
     * 
     * @return boolean if all tests pass
     * 
     */
    public static boolean test5() {

        BookMapperBackend backend5 = new BookMapperBackend();

        backend5.addBook(new Book("Purple", "Bhumin", "54321"));
        backend5.addBook(new Book("Brown", "Bob", "23"));
        backend5.addBook(new Book("Black", "Dogman", "2"));

        String expectedTitle1 = "Black";
        Book book1 = (Book) backend5.getByISBN("2");

        // verifies filter returns book with inputed isbn
        if (!book1.getTitle().equals(expectedTitle1)) {
            return false;
        }

        // verifies that despite inputting a isbn filter that a book has, author filter
        // filters it out
        backend5.setAuthorFilter("Lebron");
        Book book2 = (Book) backend5.getByISBN("23");
        if (book2 != null) {
            return false;
        }

        String expectedAuthor = "Bhumin";
        backend5.resetAuthorFilter();
        Book book3 = (Book) backend5.getByISBN("54321");

        // verifies that having a book that matches both isbn and author returns the
        // book
        if (!book3.getAuthors().equals(expectedAuthor)) {
            return false;
        }

        return true;
    }

    /**
     * This tester method tests if the BookMapperBackend class's getNumberOfBooks
     * and addBook work with AE's implementations of Book
     * 
     * @return boolean if all tests pass
     */
    public static boolean test6() {

        BookMapperBackend backend1 = new BookMapperBackend();

        if (backend1.getNumberOfBooks() != 0) {
            return false;
        }

        backend1.addBook(new Book("Blue Book", "Bhumin", "54321"));

        if (backend1.getNumberOfBooks() != 1) {
            return false;
        }

        backend1.addBook(new Book("Red Book", "Mike Evans", "22"));

        if (backend1.getNumberOfBooks() != 2) {
            return false;
        }

        return true;
    }

    /**
     * Tests if BookMapperBackend is able to add actual Book classes, (added to AE's
     * HashtableMap class)
     * Also tests if Backend searchByTitleWord function works with the HashtableMap
     * implementation
     * 
     * @return boolean of if the tests pass
     */

    public static boolean test7() {

        BookMapperBackend backend7 = new BookMapperBackend();

        backend7.addBook(new Book("BlueBook", "Bhumin", "54321"));
        backend7.addBook(new Book("RedBook", "Catman", "1"));
        backend7.addBook(new Book("GreenBook", "Dogman", "2"));

        // checks All books are detected because all fit ""
        int qty1 = backend7.searchByTitleWord("").size();
        if (qty1 != 3) {
            return false;
        }

        // verify all 3 books added because include "Book" in title
        int qty2 = backend7.searchByTitleWord("Book").size();
        if (qty2 != 3) {
            return false;
        }

        // verify list size is 0 because "Books" is in none of titles
        int qty3 = backend7.searchByTitleWord("Books").size();
        if (qty3 != 0) {
            return false;
        }

        // setting the author filter such that it only matches 1 of 3.
        // Searching by title of empty string should detect all 3 books, but author
        // filter will bring it down to only the first book
        backend7.setAuthorFilter("Bhumin");
        int qty4 = backend7.searchByTitleWord("").size();
        if (qty4 != 1) {
            return false;
        }

        return true;
    }

    /**
     * This method checks whether the book class functions properly
     * 
     * @return true if the tests verify correct functionality
     */
    public static boolean test8() {
        
        IBook book1 = new Book("Zoology", "Michael Dinh", "2332");
        
        if (!book1.getAuthors().equals("Michael Dinh")) {
            return false;
        }
        
        if (!book1.getTitle().equals("Zoology")) {
            return false;
        }
        
        if (!book1.getISBN13().equals("2332")) {
            return false;
        }
        
        return true;
    }

    /**
     * This method checks whether the bookloader's loadbooks method works properly
     * by loading a book
     * 
     * @return true if the tests verify correct functionality
     */
    public static boolean test9() {

        {
            try {
                FileWriter writer = new FileWriter("testbookcsv.csv");
                BookLoader loader = new BookLoader();

                // writing 1 book to load
                writer.write(
                        "bookID,title,authors,average_rating,isbn,isbn13,language_code,  num_pages,ratings_count,text_reviews_count,publication_date,publisher\r\n"
                                + "1,Harry Potter and the Half-Blood Prince (Harry Potter  #6),J.K. Rowling/Mary GrandPré,4.57,0439785960,9780439785969,eng,652,2095690,27591,9/16/2006,Scholastic Inc.");
                writer.close();

                List<IBook> bookList = loader.loadBooks("testbookcsv.csv");

                // test if the title, author and isbn13 all match
                if (!bookList.get(0).getTitle()
                        .equals("Harry Potter and the Half-Blood Prince (Harry Potter  #6)")) {
                    return false;
                }
                
                if (!bookList.get(0).getAuthors().equals("J.K. Rowling/Mary GrandPré")) {
                    return false;
                }
                
                if (!bookList.get(0).getISBN13().equals("9780439785969")) {
                    return false;
                }
            } catch (IOException e) {
                // no exceptions should be caught
                return false;
            }
        }
        // return true if no problems occur
        return true;
    }

}
