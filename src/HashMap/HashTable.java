package HashMap;

import singlyLL.SLinkedList;
import singlyLL.SNode;

public class HashTable {
    private static final int DEF_MAX_HASH_SIZE = 10; // Default maximum hash table size
    private SLinkedList<HashTableData>[] hashArray; // Array containing the lists of keys
    private int size;
    private int curr_size;

    // Constructors and helper method setup
    public HashTable() {
        hashArray = new SLinkedList[DEF_MAX_HASH_SIZE];
        size = DEF_MAX_HASH_SIZE;
        curr_size = 0;
    }// Constructor: default size

    public HashTable(int maxNumber) { // Constructor: specific size
        curr_size = 0;
        hashArray = new SLinkedList[maxNumber];
        size = maxNumber;
    }

    // HashFunctions
    int HashFunction(int key) {
        return key % size;
    }

    ;

    int HashFunction(String key) {
        int sum = 0;
        for (int i = 0; i < key.length(); i++) {
            sum += key.charAt(i);
        }
        return sum % size;
    }

    ;

    // Hash manipulation methods
    public boolean retrieve(HashTableData searchElem) {
        if (isEmpty())  //case of empty table
            return false;
        String key = searchElem.getStudent().getSurname();
        int index = HashFunction(key);
        if(hashArray[index] == null || hashArray[index].isEmpty()) //case of empty slot in array, or empty list
            return false;                                   //empty list appears when its only element was removed.
                                                            //if a slot in array never held data, it contains null.
        SNode<HashTableData> temp = hashArray[index].getHead();
        do {
            if (temp.getElement().getStudent() == searchElem.getStudent())
                return true;
        } while ((temp = temp.getNext()) != null);
        return false;
    }


    /*Calculates an index in array, using hash function and invokes LinkedList's insertion method for a list at an index*/
    public boolean insert(HashTableData newElem) {
        int index = HashFunction(newElem.getKey());
        if (retrieve(newElem))
            return false;
        if (hashArray[index] == null)
            hashArray[index] = new SLinkedList<>();
        hashArray[index].insert(newElem);
        curr_size++;
        return true;
    }

    /*Iterates over non-empty lists in array and invokes LinkedList's removal method*/
    public boolean remove(HashTableData remElem) {
        if (!retrieve(remElem) || isEmpty()) //case when searched element is not in the structure or the table is empty
            return false;
        int index = HashFunction(remElem.getKey());
        SNode<HashTableData> temp = hashArray[index].getHead();
        hashArray[index].gotoBeginning();
        do {
            if (temp.getElement().getStudent().equals(remElem.getStudent())) { //using field-wise comparison
                hashArray[index].remove();
                curr_size--;
                return true;
            }
        } while ((temp = temp.getNext()) != null);

        return false;
    }

    public void clear() {
        curr_size = 0;
    }

    public boolean isEmpty() {
        return curr_size == 0;
    }

    public boolean isFull() {
        return curr_size == size;
    }

    // Output the hash structure
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if(hashArray[i] != null)
            {
                sb.append(hashArray[i].toString());
                sb.append('\n');
            }


        }
        return sb.toString();
    }
}// class HashTable

