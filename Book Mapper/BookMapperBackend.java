import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;


public class BookMapperBackend implements IBookMapperBackend {
  
  HashtableMap map = new HashtableMap();
  Iterator mapIterator = map.iterator();
  
  String authorFilter;
  
  /**
   * This method adds books
   * 
   * @param IBook to be added to hashmap
   */
  @Override
  public void addBook(IBook book) {
    
    map.put(book.getISBN13(), book);
    
  }
  
  /**
   * This method returns amount of books in the hashmap
   * 
   * @return bookmap size
   */
  @Override
  public int getNumberOfBooks() {
    return map.size();
  }

  /**
   * This method sets the author filter
   * 
   * @param filterBy string to filter by
   */
  @Override
  public void setAuthorFilter(String filterBy) {
    authorFilter = filterBy;
    
  }

  /**
   * This method gets the author filter
   * 
   * @return authorFilter
   */
  @Override
  public String getAuthorFilter() {
    return authorFilter;
  }

  /**
   * This method resets the author filter
   * 
   * 
   */
  @Override
  public void resetAuthorFilter() {
    authorFilter = null;
    
  }

  /**
   * This method searches for books by title
   * 
   * @param word to look for in title
   * @return List of books with title
   */
  @Override
  public List<IBook> searchByTitleWord(String word) {
    
    List<IBook> bookList = new ArrayList<IBook>();
    
    String author = getAuthorFilter();
    
    Iterator bookIterator = map.iterator();

    while (bookIterator.hasNext()){

      HashtableMap.KeyValuePair bookPair = (HashtableMap.KeyValuePair) bookIterator.next();
                
      // verifies right title
      if (((IBook) bookPair.getValue()).getTitle().toLowerCase().contains(word.toLowerCase())) {
        
        // check if there is an author filter
        if (author != "" && author != null) {
          
          // checks if author filter and current book KeyValuePair correlate
          if (((IBook) bookPair.getValue()).getAuthors().toLowerCase().contains(author.toLowerCase())) {
                        
            // if correct title and author, add
            bookList.add((Book) bookPair.getValue());
            
          }
        }
        
        else {
          bookList.add((Book) bookPair.getValue());
        }
      
      }
        
    }
    
    return bookList;
  }

  /**
   * This method gets the book with matching ISBN value
   * 
   * @param ISBN value 
   * @return specific book with said value
   */
  @Override
  public IBook getByISBN(String ISBN) {
    
    String author = getAuthorFilter();
    
    Iterator bookIterator = map.iterator();

    while (bookIterator.hasNext()) {

      HashtableMap.KeyValuePair bookPair = (HashtableMap.KeyValuePair) bookIterator.next();

      // verifies ISBN is same
      if (bookPair.getKey().equals(ISBN)) {
        
        // check if there is an author filter
        // if author filter, check that author is correct
        if (author != "" && author != null) {
          if (((IBook) bookPair.getValue()).getAuthors().contains(author)) {
            return (IBook) bookPair.getValue();
          }
          
          return null;
        }
        // else no author filter, return book
        return (IBook) bookPair.getValue();
      }
      
    }
    
    return null;
    
  }

  


}

