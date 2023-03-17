// --== CS400 Project One File Header ==--
// Name: Rishi Natraj
// CSL Username: natraj
// Email: rnatraj@wisc.edu
// Lecture #: 003 @2:25pm
// Notes to Grader: none

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.LinkedList;

public class HashtableMap<KeyType, ValueType> implements IterableMapADT<KeyType, ValueType>{
    int cap;
    int size;
    LinkedList<KeyValuePair>[] hashtable;
    public HashtableMap(int capacity){
        //initializes capacity to provided capacity and size to 0, and initializes a new hashtable array of LinkedLists
        cap=capacity;
        size=0;
        hashtable = (LinkedList<KeyValuePair>[]) new LinkedList<?>[cap];
        for (int i=0;i<cap;i++){
            hashtable[i] = new LinkedList<KeyValuePair>();
        }
    }
    public HashtableMap() {
        //initializes capacity to 15 and size to 0, and initializes a new hashtable array of LinkedLists
        cap=15;
        size=0;
        hashtable = (LinkedList<KeyValuePair>[]) new LinkedList<?>[cap];
        for (int i=0;i<cap;i++){
            hashtable[i] = new LinkedList<KeyValuePair>();
        }
    }

    @Override
    public boolean put(KeyType key, ValueType value) {
        //checks is the key is null, and if it is, returns false
        if (key==null){
            return false;
        }
        //determines the index of the hashtable that the pair will be inserted in to
        int index = Math.abs(key.hashCode()) % cap;
        //checks if the key already exists in the table, and if it does, returns false
        for (int i = 0; i< hashtable[index].size(); i++){
            if (hashtable[index].get(i).getKey().equals(key)){
                return false;
            }
        }
        //increases the size by one
        size++;
        //checks if the load factor is over 0.7, and if it is,
        // it rehashes the hashtable and determines the new index the pair will be inserted into
        if (loadFactor()>=0.7) {
            rehash();
            index = Math.abs(key.hashCode()) % cap;
        }
        //adds the pair to the LinkedList at its mapped index.
        hashtable[index].add(new KeyValuePair(key, value));
        return true;
    }

    @Override
    public ValueType get(Object key) throws NoSuchElementException {
        //determines the index the key will be at
        int index = Math.abs(key.hashCode()) % cap;
        //searches for the key in the LinkedList at the index, if it is not there throws exception
        for (int i = 0; i < hashtable[index].size(); i++) {
            if (hashtable[index].get(i).getKey().equals(key)) {
                return hashtable[index].get(i).getValue();
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public Object remove(Object key) {
        //checks if the key is in the array
        if (containsKey(key)){
            //determines the index the key will be at
            int index = Math.abs(key.hashCode()) % cap;
            //searches for the key in the LinkedList at the index, and then removes the pair once it is found
            for (int i = 0; i < hashtable[index].size(); i++) {
                if (hashtable[index].get(i).getKey().equals(key)) {
                    return hashtable[index].remove().getValue();
                }
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(Object key) {
        //determines the index the key will be at
        int index = Math.abs(key.hashCode()) % cap;
        //searches for the key in the LinkedList at the index, and returns true if it is found
        for (int i = 0; i<hashtable[index].size(); i++){
            if (hashtable[index].get(i).getKey().equals(key)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        //returns size of the array
        return size;
    }

    @Override
    public void clear() {
        //sets the hashtable to a new array of LinkedLists
        hashtable = (LinkedList<KeyValuePair>[]) new LinkedList<?>[cap];
        for (int i=0;i<cap;i++){
            hashtable[i] = new LinkedList<KeyValuePair>();
        }
        size=0;
    }

    private double loadFactor(){
        //returns the load factor of the hashtable
        return (double)size/(double)cap;
    }

    private void rehash(){
        //sets the current hashtable to a different variable
        LinkedList<KeyValuePair>[] oldHashtable = hashtable;
        //doubles the capacity of the hashtable and initalizes a new array with the new capacity
        cap=cap*2;
        hashtable = (LinkedList<KeyValuePair>[]) new LinkedList<?>[cap];
        for (int i=0;i<cap;i++){
            hashtable[i] = new LinkedList<KeyValuePair>();
        }
        //for each pair in the old array, finds its index in the new array and adds it.
        for(int i=0;i<oldHashtable.length; i++){
            for (int j=0; j<oldHashtable[i].size(); j++){
                int index = Math.abs(oldHashtable[i].get(j).getKey().hashCode()) % cap;
                hashtable[index].add(new KeyValuePair(oldHashtable[i].get(j).getKey(), oldHashtable[i].get(j).getValue()));
            }
        }
    }



    public class KeyValuePair {
        KeyType k;
        ValueType v;
        public KeyValuePair(KeyType key, ValueType value){
            k = key;
            v = value;
//            System.out.println(k.hashCode());
        }

        public String toString(){
            return "Key: "+k+", Value: "+v;
        }

        public KeyType getKey(){
            return k;
        }

        public ValueType getValue(){
            return v;
        }
    }

    /**
     * @return
     */
    @Override
    public Iterator<ValueType> iterator() {
        return new Iterator<ValueType>() {
            int currentLinkedList=0;
            int currentIndexInList=0;
            int numReturned=0;
            @Override
            public boolean hasNext() {
                if ((currentLinkedList>=hashtable.length-1)&&(hashtable[currentLinkedList].size()>=currentIndexInList)){
                    return false;
                } else if (numReturned>=size()) {
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public ValueType next() {
                while (currentIndexInList>=hashtable[currentLinkedList].size()){
                    currentLinkedList++;
                    currentIndexInList=0;
                }
                ValueType current=(ValueType) hashtable[currentLinkedList].get(currentIndexInList);
                currentIndexInList++;
                numReturned++;
                return current;
            }
        };
    }

}
