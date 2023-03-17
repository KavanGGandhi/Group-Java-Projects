public interface IterableRBTADT <KeyType extends Comparable<KeyType>> extends SortedCollectionInterface<KeyType>, Iterable<KeyType>{

    //This interface combines SortedCollectionInterface with Iterable and defines no additional methods
    //if addition to the ones it inherits form both these interfaces

    public KeyType search(KeyType key);

    public KeyType remove(KeyType key);

}

