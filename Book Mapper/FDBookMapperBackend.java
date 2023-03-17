import java.util.Arrays;
import java.util.List;

public class FDBookMapperBackend implements IBookMapperBackend {

    private String filterString;

    public class Book1 implements IBook {

        @Override
        public String getTitle() {
            return "Title 1";
        }

        @Override
        public String getAuthors() {
            return "Author 1";
        }

        @Override
        public String getISBN13() {
            return "ISBN 1";
        }
        
    }

    public class Book2 implements IBook {

        @Override
        public String getTitle() {
            return "Title 2";
        }

        @Override
        public String getAuthors() {
            return "Author 2";
        }

        @Override
        public String getISBN13() {
            return "ISBN 2";
        }
        
    }


    @Override
    public void addBook(IBook book) {
    }

    @Override
    public int getNumberOfBooks() {
        return 2;
    }

    @Override
    public void setAuthorFilter(String filterBy) {
        filterString = filterBy;
        
    }

    @Override
    public String getAuthorFilter() {
        return filterString;
    }

    @Override
    public void resetAuthorFilter() {
        filterString = "";
    }

    @Override
    public List<IBook> searchByTitleWord(String word) {
        return Arrays.asList(new Book1(), new Book2());
    }

    @Override
    public IBook getByISBN(String ISBN) {
        return new Book1();
    }
    
}