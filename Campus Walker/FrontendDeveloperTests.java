import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FrontendDeveloperTests {

    protected Scanner scanner;
    protected BackendInterface<String, Integer> backend;
    protected Frontend frontend;

    /**
     * Tests the integration of the Frontend with all other roles by testing the closest path menu.
     * @throws FileNotFoundException
     */
    @Test
    public void integrationTest1() throws FileNotFoundException{
        TextUITester tester = new TextUITester("3\ncole hall\n6\n");
        dwGraphLoader loader = new dwGraphLoader();

        GraphInterface<String, Integer> graph = loader.load("campusNetwork.dot");

        Backend<String, Integer> backend = new Backend<>(graph);

        Scanner scanner = new Scanner(System.in);

        Frontend frontend = new Frontend(scanner, backend);

        frontend.runCommandLoop();

        String output = tester.checkOutput();
        Assertions.assertEquals(output,"Welcome to Campus Walk Time!\n" +
                "\n" +
                "You are in the main menu:\n" +
                "        1) Distance From 1 Building to All Others\n" +
                "        2) Distance Between 2 Buildings\n" +
                "        3) Closest Building\n" +
                "        4) Farthest Building\n" +
                "        5) Buildings List\n" +
                "        6) Exit Application\n" +
                "You are in the Closest Building Menu:\n" +
                "        Enter a Building for the starting point: " +
                "Results (Cole Hall): \n" +
                "Engineering Hall is the closest building to Cole Hall, and it is 1.53 minutes away.\n" +
                "You are in the main menu:\n" +
                "        1) Distance From 1 Building to All Others\n" +
                "        2) Distance Between 2 Buildings\n" +
                "        3) Closest Building\n" +
                "        4) Farthest Building\n" +
                "        5) Buildings List\n" +
                "        6) Exit Application\n" +
                "Goodbye!\n");
    }

    /**
     * Tests the integration of the Frontend with all other roles by
     * testing the distance from one building to all others menu.
     * @throws FileNotFoundException
     */
    @Test
    public void integrationTest2() throws FileNotFoundException{
        TextUITester tester = new TextUITester("1\nsellery\n6\n");
        dwGraphLoader loader = new dwGraphLoader();

        GraphInterface<String, Integer> graph = loader.load("campusNetwork.dot");

        Backend<String, Integer> backend = new Backend<>(graph);

        Scanner scanner = new Scanner(System.in);

        Frontend frontend = new Frontend(scanner, backend);

        frontend.runCommandLoop();

        String output = tester.checkOutput();
        Assertions.assertEquals(output,"Welcome to Campus Walk Time!\n" +
                "\n" +
                "You are in the main menu:\n" +
                "        1) Distance From 1 Building to All Others\n" +
                "        2) Distance Between 2 Buildings\n" +
                "        3) Closest Building\n" +
                "        4) Farthest Building\n" +
                "        5) Buildings List\n" +
                "        6) Exit Application\n" +
                "You are in the Distance From 1 Building to All Others menu:\n" +
                "        Enter a Building for the starting point: " +
                "Results (Sellery): \n" +
                "Sellery to Grainger Hall will take 0.75 minutes.\n" +
                "Sellery to Kohl Center will take 1.13 minutes.\n" +
                "Sellery to Camp Randall will take 2.38 minutes.\n" +
                "Sellery to Witte will take 2.63 minutes.\n" +
                "Sellery to Smith will take 3.73 minutes.\n" +
                "Sellery to Mosse Humanities will take 3.78 minutes.\n" +
                "Sellery to Chadbourne will take 4.5 minutes.\n" +
                "Sellery to Van Vleck will take 4.86 minutes.\n" +
                "Sellery to State Capital will take 5.23 minutes.\n" +
                "Sellery to Gordon's will take 5.85 minutes.\n" +
                "Sellery to Bascom Hall will take 6.11 minutes.\n" +
                "Sellery to Memorial Union will take 6.68 minutes.\n" +
                "Sellery to Van Hise will take 7.0 minutes.\n" +
                "Sellery to QQ's will take 7.16 minutes.\n" +
                "Sellery to Bradley will take 8.11 minutes.\n" +
                "Sellery to Science Hall will take 8.41 minutes.\n" +
                "Sellery to Union South will take 10.1 minutes.\n" +
                "Sellery to Cole Hall will take 11.83 minutes.\n" +
                "Sellery to Comp Sci Building will take 11.95 minutes.\n" +
                "Sellery to Dejope will take 12.16 minutes.\n" +
                "Sellery to UW Hospital will take 13.13 minutes.\n" +
                "Sellery to Engineering Hall will take 13.36 minutes.\n" +
                "Sellery to Chemistry Building will take 14.41 minutes.\n" +
                "You are in the main menu:\n" +
                "        1) Distance From 1 Building to All Others\n" +
                "        2) Distance Between 2 Buildings\n" +
                "        3) Closest Building\n" +
                "        4) Farthest Building\n" +
                "        5) Buildings List\n" +
                "        6) Exit Application\n" +
                "Goodbye!\n");
    }

    /**
     * Tests the dwGraphLoader class by checking to see if it loads the correct amount of edges.
     * @throws FileNotFoundException
     */
    @Test
    public void CodeReviewOfDataWranglerTest1() throws FileNotFoundException{
        dwGraphLoader loader=new dwGraphLoader();
        GraphInterface<String, Integer> graph=loader.load("campusNetwork.dot");
        Assertions.assertEquals(graph.getEdgeCount(),62);
    }

    /**
     * Tests the dwGraphLoader class by checking to see if it loads the correct amount of vertices.
     * @throws FileNotFoundException
     */
    @Test
    public void CodeReviewOfDataWranglerTest2() throws FileNotFoundException {
        dwGraphLoader loader=new dwGraphLoader();
        GraphInterface<String, Integer> graph=loader.load("campusNetwork.dot");
        Assertions.assertEquals(graph.getVertexCount(),24);
    }

    /**
     * Tests the Buildings List option in the main menu.
     */
    @Test
    public void test1(){
        TextUITester tester = new TextUITester("5\n6\n");
        scanner = new Scanner(System.in);
        backend=new FDBackend<>();
        frontend=new Frontend(scanner, backend);
        frontend.runCommandLoop();

        String output = tester.checkOutput();
        Assertions.assertEquals(output, "Welcome to Campus Walk Time!\n" +
                "\n" +
                "You are in the main menu:\n" +
                "        1) Distance From 1 Building to All Others\n" +
                "        2) Distance Between 2 Buildings\n" +
                "        3) Closest Building\n" +
                "        4) Farthest Building\n" +
                "        5) Buildings List\n" +
                "        6) Exit Application\n" +
                "You are in the Building List Menu:\n" +
                "Science Hall\n" +
                "QQ's\n" +
                "Union South\n" +
                "Memorial Union\n" +
                "Mosse Humanities\n" +
                "Comp Sci Building\n" +
                "Van Vleck\n" +
                "Chemistry Building\n" +
                "Grainger Hall\n" +
                "Engineering Hall\n" +
                "Van Hise\n" +
                "UW Hospital\n" +
                "Dejope\n" +
                "Camp Randall\n" +
                "Kohl Center\n" +
                "Gordon's\n" +
                "Chadbourne\n" +
                "Sellery\n" +
                "Smith\n" +
                "Witte\n" +
                "Bascom Hall\n" +
                "Cole Hall\n" +
                "State Capital\n" +
                "Bradley\n" +
                "You are in the main menu:\n" +
                "        1) Distance From 1 Building to All Others\n" +
                "        2) Distance Between 2 Buildings\n" +
                "        3) Closest Building\n" +
                "        4) Farthest Building\n" +
                "        5) Buildings List\n" +
                "        6) Exit Application\n" +
                "Goodbye!\n");
    }

    /**
     * Tests the Distance from 1 Building to All Others option in the main menu.
     */
    @Test
    public void test2(){
        TextUITester tester = new TextUITester("1\ncamp randall\n6\n");
        scanner = new Scanner(System.in);
        backend=new FDBackend<>();
        frontend=new Frontend(scanner, backend);
        frontend.runCommandLoop();

        String output = tester.checkOutput();
        Assertions.assertEquals(output, "Welcome to Campus Walk Time!\n" +
                "\n" +
                "You are in the main menu:\n" +
                "        1) Distance From 1 Building to All Others\n" +
                "        2) Distance Between 2 Buildings\n" +
                "        3) Closest Building\n" +
                "        4) Farthest Building\n" +
                "        5) Buildings List\n" +
                "        6) Exit Application\n" +
                "You are in the Distance From 1 Building to All Others menu:\n" +
                "        Enter a Building for the starting point: " +
                "Results (Camp Randall): \n" +
                "Camp Randall to Engineering Hall will take 0.16 minutes.\n" +
                "Camp Randall to Union South will take 0.16 minutes.\n" +
                "You are in the main menu:\n" +
                "        1) Distance From 1 Building to All Others\n" +
                "        2) Distance Between 2 Buildings\n" +
                "        3) Closest Building\n" +
                "        4) Farthest Building\n" +
                "        5) Buildings List\n" +
                "        6) Exit Application\n" +
                "Goodbye!\n");
    }

    /**
     * Tests the Distance Between 2 Buildings option in the main menu.
     */
    @Test
    public void test3(){
        TextUITester tester = new TextUITester("2\ncamp randall\nuw hospital\n6\n");
        scanner = new Scanner(System.in);
        backend=new FDBackend<>();
        frontend=new Frontend(scanner, backend);
        frontend.runCommandLoop();

        String output = tester.checkOutput();
        Assertions.assertEquals(output, "Welcome to Campus Walk Time!\n" +
                "\n" +
                "You are in the main menu:\n" +
                "        1) Distance From 1 Building to All Others\n" +
                "        2) Distance Between 2 Buildings\n" +
                "        3) Closest Building\n" +
                "        4) Farthest Building\n" +
                "        5) Buildings List\n" +
                "        6) Exit Application\n" +
                "You are in the Distance Between 2 Buildings menu:\n" +
                "        Enter a Building for the starting point: " +
                "        Enter a Building for the ending point: " +
                "Results (Camp Randall to UW Hospital): \n" +
                "Camp Randall to UW Hospital will take 0.16 minutes.\n" +
                "You are in the main menu:\n" +
                "        1) Distance From 1 Building to All Others\n" +
                "        2) Distance Between 2 Buildings\n" +
                "        3) Closest Building\n" +
                "        4) Farthest Building\n" +
                "        5) Buildings List\n" +
                "        6) Exit Application\n" +
                "Goodbye!\n");
    }

    /**
     * Tests the Closest Building option in the main menu.
     */
    @Test
    public void test4(){
        TextUITester tester = new TextUITester("3\ncamp randall\n6\n");
        scanner = new Scanner(System.in);
        backend=new FDBackend<>();
        frontend=new Frontend(scanner, backend);
        frontend.runCommandLoop();

        String output = tester.checkOutput();
        Assertions.assertEquals(output,"Welcome to Campus Walk Time!\n" +
                "\n" +
                "You are in the main menu:\n" +
                "        1) Distance From 1 Building to All Others\n" +
                "        2) Distance Between 2 Buildings\n" +
                "        3) Closest Building\n" +
                "        4) Farthest Building\n" +
                "        5) Buildings List\n" +
                "        6) Exit Application\n" +
                "You are in the Closest Building Menu:\n" +
                "        Enter a Building for the starting point: " +
                "Results (Camp Randall): \n" +
                "Engineering Hall is the closest building to Camp Randall, and it is 0.16 minutes away.\n" +
                "You are in the main menu:\n" +
                "        1) Distance From 1 Building to All Others\n" +
                "        2) Distance Between 2 Buildings\n" +
                "        3) Closest Building\n" +
                "        4) Farthest Building\n" +
                "        5) Buildings List\n" +
                "        6) Exit Application\n" +
                "Goodbye!\n");
    }

    /**
     * Tests the Farthest Building option in the main menu.
     */
    @Test
    public void test5(){
        TextUITester tester = new TextUITester("4\ncamp randall\n6\n");
        scanner = new Scanner(System.in);
        backend=new FDBackend<>();
        frontend=new Frontend(scanner, backend);
        frontend.runCommandLoop();

        String output = tester.checkOutput();
        Assertions.assertEquals(output,"Welcome to Campus Walk Time!\n" +
                "\n" +
                "You are in the main menu:\n" +
                "        1) Distance From 1 Building to All Others\n" +
                "        2) Distance Between 2 Buildings\n" +
                "        3) Closest Building\n" +
                "        4) Farthest Building\n" +
                "        5) Buildings List\n" +
                "        6) Exit Application\n" +
                "You are in the Farthest Building Menu:\n" +
                "        Enter a Building for the starting point: " +
                "Results (Camp Randall): \n" +
                "Union South is the farthest building to Camp Randall, and it is 0.5 minutes away.\n" +
                "You are in the main menu:\n" +
                "        1) Distance From 1 Building to All Others\n" +
                "        2) Distance Between 2 Buildings\n" +
                "        3) Closest Building\n" +
                "        4) Farthest Building\n" +
                "        5) Buildings List\n" +
                "        6) Exit Application\n" +
                "Goodbye!\n");
    }
}
