run: Main.class
	java Main

Main.class: Main.java
	javac Main.java

runTests: runFrontendDeveloperTests runDataWranglerTests runBackendDeveloperTests runAlgorithmEngineerTests

runBackendDeveloperTests: BackendDeveloperTests.class
	java -jar junit5.jar --class-path=. --include-classname=.* --select-class=BackendDeveloperTests

BackendDeveloperTests.class: BackendDeveloperTests.java
	javac -cp .:junit5.jar BackendDeveloperTests.java

runDataWranglerTests: DataWranglerTests.class
	java -jar junit5.jar --class-path=. --include-classname=.* --select-class=DataWranglerTests

DataWranglerTests.class: DataWranglerTests.java
	javac -cp .:junit5.jar DataWranglerTests.java

clean:
	rm *.class

runFrontendDeveloperTests: FrontendDeveloperTests.class FDLicensePlateChecker.class Backend.class Frontend.class
	java -jar junit5.jar --class-path=. --include-classname=.* --select-class=FrontendDeveloperTests

FrontendDeveloperTests.class: FrontendDeveloperTests.java
	javac -cp .:junit5.jar FrontendDeveloperTests.java

FDLicensePlateChecker.class: FDLicensePlateChecker.java
	javac FDLicensePlateChecker.java

FDBackend.class: FDBackend.java
	javac FDBackend.java

Frontend.class: Frontend.java
	javac Frontend.java

runAlgorithmEngineerTests: AlgorithmEngineerTests.class
	java -jar junit5.jar --class-path=. --include-classname=.* --select-class=AlgorithmEngineerTests


AlgorithmEngineerTests.class: AlgorithmEngineerTests.java AELicensePlateChecker.class AECar.class AERedBlackTree.class
	javac -cp .:junit5.jar AlgorithmEngineerTests.java

AELicensePlateChecker.class: AELicensePlateChecker.java
	javac AELicensePlateChecker.java

AECar.class: AECar.java ICar.class
	javac AECar.java

AERedBlackTree.class: AERedBlackTree.java
	javac AERedBlackTree.java

ICar.class: ICar.java
	javac ICar.java
