// --== CS400 Project Three File Header ==--
// Name: Kavan Gandhi
// CSL Username: kavan
// Email: kgandhi4@wisc.edu
// Lecture #: <003 @2:25pm>

/*
 * A simple class that groups a given KeyType and ValueType together in one object
 */
public class Pair <KeyType, ValueType> {
    private KeyType key;
    private ValueType value;
    
    public Pair(KeyType key, ValueType value) {
      this.key = key;
      this.value = value;
    }
  
    /**
     * @return the key
     */
    public KeyType getKey() {
      return key;
    }
  
    /**
     * @return the value
     */
    public ValueType getValue() {
      return value;
    }
}
