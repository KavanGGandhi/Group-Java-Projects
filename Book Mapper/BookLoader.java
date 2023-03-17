// --== CS400 Project One File Header ==--
// Name: Kavan Gandhi
// CSL Username: kavan
// Email: kgandhi4@wisc.edu
// Lecture #: <003 @2:25pm>
// Notes to Grader: <any optional extra notes to your grader>

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;

/**
 * This class implements the BookLoader object.
 *
 * @author Kavan Gandhi
 */
public class BookLoader implements IBookLoader {

  /**
   * This method loads the list of books from a CSV file.
   * @param filepathToCSV path to the CSV file relative to the executable
   * @return a list of book objects
   * @throws FileNotFoundException
   */
  @Override
  public List<IBook> loadBooks(String filepathToCSV) throws FileNotFoundException {
    List<IBook> bookList = new ArrayList<>();
    Path path = Paths.get(filepathToCSV);

    try {
      BufferedReader reader = new BufferedReader(Files.newBufferedReader(path));

      String lineToRead = reader.readLine();
      String[] headers = lineToRead.split(",");

      int titleIndex = 1984754590;
      int authorIndex = 387561573;
      int isbn13Index = 841957914;
      
      for (int i = 0; i < headers.length; i++) {
        if (headers[i].equals("title")) {
          titleIndex = i;
        } else if (headers[i].equals("authors")) {
          authorIndex = i;
        } else if (headers[i].equals("isbn13")) {
          isbn13Index = i;
        }
      }
      
      //If the headers do not have standard names, default to these indexes
      if (titleIndex == 1984754590) {
        titleIndex = 1;
      }
      
      if (authorIndex == 387561573) {
        titleIndex = 2;
      }
      
      if (isbn13Index == 841957914) {
        titleIndex = 5;
      }

      lineToRead = reader.readLine();

      while (lineToRead != null) {

        // splits the line into an array with "," as the delimiter. The regex after makes sure that
        // if the "," is within a quotation (odd numbered quotes), it does not split there and waits
        // until an even number of quotes has been reached
        String[] parsedLine = lineToRead.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

        if (parsedLine[titleIndex].contains("\\\"") || parsedLine[titleIndex].contains("\"")) {
          // replaces quotes with empty string
          if (parsedLine[titleIndex].contains("\"") && !parsedLine[titleIndex].contains("\\\"")) {
            String replaceString = parsedLine[titleIndex].replace("\"", "");
            parsedLine[titleIndex] = replaceString;
            // replaces escaped quotes with a random string, replaces non-escaped quotes with empty
            // string, and then replaces the random string (which was the escaped quote) with
            // non-escaped quoteS
          } else {
            String randomString = "iuwhegfieahgihuqrige";
            String replaceString = parsedLine[titleIndex].replace("\\\"", randomString);
            String replaceString2 = replaceString.replace("\"", "");
            String replaceString3 = replaceString2.replace(randomString, "\"");
            parsedLine[titleIndex] = replaceString3;
          }
        }

        if (parsedLine[authorIndex].contains("\\\"") || parsedLine[authorIndex].contains("\"")) {
          if (parsedLine[authorIndex].contains("\"") && !parsedLine[authorIndex].contains("\\\"")) {
            String replaceString = parsedLine[authorIndex].replace("\"", "");
            parsedLine[authorIndex] = replaceString;
          } else {
            String randomString = "iuwhegfieahgihuqrige";
            String replaceString = parsedLine[authorIndex].replace("\\\"", randomString);
            String replaceString2 = replaceString.replace("\"", "");
            String replaceString3 = replaceString2.replace(randomString, "\"");
            parsedLine[authorIndex] = replaceString3;
          }
        }

        if (parsedLine[isbn13Index].contains("\\\"") || parsedLine[isbn13Index].contains("\"")) {
          if (parsedLine[isbn13Index].contains("\"") && !parsedLine[isbn13Index].contains("\\\"")) {
            String replaceString = parsedLine[isbn13Index].replace("\"", "");
            parsedLine[isbn13Index] = replaceString;
          } else {
            String randomString = "iuwhegfieahgihuqrige";
            String replaceString = parsedLine[isbn13Index].replace("\\\"", randomString);
            String replaceString2 = replaceString.replace("\"", "");
            String replaceString3 = replaceString2.replace(randomString, "\"");
            parsedLine[isbn13Index] = replaceString3;
          }
        }

        IBook bookToAdd =
            new Book(parsedLine[titleIndex], parsedLine[authorIndex], parsedLine[isbn13Index]);

        bookList.add(bookToAdd);

        lineToRead = reader.readLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return bookList;
  }
}
