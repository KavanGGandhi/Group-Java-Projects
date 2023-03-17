run:  BookMapper.class
	java BookMapper

BookMapper.class: BookMapper.java
	javac BookMapper.java

runTests: runAlgorithmEngineerTests runFrontendDeveloperTests runBackendDeveloperTests runDataWranglerTests


runBackendDeveloperTests: BackendDeveloperTest.class
	java BackendDeveloperTest

runFrontendDeveloperTests: FrontendDeveloperTest.class
	java FrontendDeveloperTest

FrontendDeveloperTest.class: FrontendDeveloperTest.java
	javac FrontendDeveloperTest.java

runDataWranglerTests: Book.class BookLoader.class DataWranglerTest.class
	java DataWranglerTest

BookLoader.class: BookLoader.java
	javac BookLoader.java

DataWranglerTest.class: DataWranglerTest.java
	javac DataWranglerTest.java


clean:
	rm *.class




BackendDeveloperTest.class: BackendDeveloperTest.java BookMapperBackend.class
	javac BackendDeveloperTest.java

BookMapperBackend.class: BookMapperBackend.java HashtableMap.class Book.class
	javac BookMapperBackend.java

Book.class: Book.java
	javac Book.java

HashtableMap.class: HashtableMap.java KeyValuePair.class
	javac HashtableMap.java

KeyValuePair.class: KeyValuePair.java
	javac KeyValuePair.java

runAlgorithmEngineerTests: AlgorithmEngineerTest.class
	java AlgorithmEngineerTest

AlgorithmEngineerTest.class: AlgorithmEngineerTest.java
	javac AlgorithmEngineerTest.java

