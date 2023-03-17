import java.io.FileNotFoundException;
public interface IGraphLoader {

    /**
     *
     * This method loads all of the data from the dot file into the graph ADT
     *
     *
     *@param filepathToDot Path to the dot file
     *@returns a GraphADT of the dot file
     *@throws FileNotFoundException
     */
    public GraphADT <String, Integer> load (String filepathToDot) throws FileNotFoundException;
}


