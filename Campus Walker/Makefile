run: CampusWalker.class
	java CampusWalker

CampusWalker.class: CampusWalker.java
	javac CampusWalker.java

runTests: runFrontendDeveloperTests runBackendDeveloperTests runAlgorithmEngineerTests runDataWranglerTests

runDataWranglerTests: DataWranglerTests.class
		java -jar junit5.jar --class-path=. --include-classname=.* --select-class=DataWranglerTests

DataWranglerTests.class: DataWranglerTests.java dwGraph.class dwGraphLoader.class campusNetwork.dot
		javac -cp .:junit5.jar DataWranglerTests.java

dwGraph.class: dwGraph.java
		javac dwGraph.java

dwGraphLoader.class: dwGraphLoader.java
		javac dwGraphLoader.java

runAlgorithmEngineerTests: AlgorithmEngineerTests.class
	java -jar junit5.jar --class-path=. --include-classname=.* --select-class=AlgorithmEngineerTests

AlgorithmEngineerTests.class: AlgorithmEngineerTests.java
	javac -cp .:junit5.jar AlgorithmEngineerTests.java

runFrontendDeveloperTests: FrontendDeveloperTests.class
	java -jar junit5.jar --class-path=. --include-classname=.* --select-class=FrontendDeveloperTests

FrontendDeveloperTests.class: FrontendDeveloperTests.java
	javac -cp .:junit5.jar FrontendDeveloperTests.java

runBackendDeveloperTests: BackendDeveloperTests.class
	java -jar junit5.jar --class-path=. --include-classname=.* --select-class=BackendDeveloperTests

BackendDeveloperTests.class: BackendDeveloperTests.java Backend.class BDGraph.class
	javac -cp .:junit5.jar BackendDeveloperTests.java

Backend.class: Backend.java
	javac Backend.java

BDGraph.class: BDGraph.java
	javac BDGraph.java

clean:
	rm *.class
