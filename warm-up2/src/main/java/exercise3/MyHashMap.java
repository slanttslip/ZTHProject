package exercise3;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Exercise 3. Implement a HashMap from scratch. In order to pass all the tests
 * you need to implement all the methods defined below. The key-value pair will
 * be encapsulated in the MyHashMap.MyEntry object defined below.
 *
 * The buckets list in which each MyEntry object will be stored is stored in "buckets" object.
 */
public class MyHashMap {

    private ArrayList<LinkedList<MyEntry>> buckets;

    private int capacity;

    public MyHashMap(int capacity) {
        this.capacity = capacity;

        // Initialize buckets list
        buckets = new ArrayList<LinkedList<MyEntry>>();
        for(Integer i = 0; i < capacity; i++) {
            buckets.add(new LinkedList<MyEntry>());
        }
    }

    public String get(String key) {
        // TODO
        //null
        if (key == null)
            return null;
        //existent
        int hashCode;
        if (key.hashCode() <0) {
            hashCode = -key.hashCode() % capacity;
        } else {
            hashCode = key.hashCode() % capacity;
        }
        LinkedList<MyEntry> myBucket = buckets.get(hashCode);
        for (MyEntry entry : myBucket) {
            if (entry.getKey().equals(key))
                return entry.getValue();
        }
        // non existent
        return null;
    }

    public void put(String key, String value) {
        // TODO
        if (key == null) {
            buckets.get(0).add(new MyEntry(key, value));
        } else {
            int hashCode;
            if (key.hashCode() < 0) {
                hashCode = -key.hashCode() % capacity;
            } else {
                hashCode = key.hashCode() % capacity;
            }
            if (!this.values().contains(value))
                buckets.get(hashCode).add(new MyEntry(key, value));
        }
    }

    public Set<String> keySet() {
        HashSet<String> keySet = new HashSet<String>();
        for (LinkedList<MyEntry> bucket : buckets) {
            for (MyEntry entry : bucket) {
                keySet.add(entry.getKey());
            }
        }
        return keySet;
    }

    public Collection<String> values() {
        // TODO
        LinkedList<String> valuesList = new LinkedList<String>();
        for (LinkedList<MyEntry> bucket : buckets) {
            for (MyEntry entry : bucket) {
                valuesList.add(entry.getValue());
            }
        }
        return valuesList;
    }

    public String remove(String key) {
        // TODO Returns the value associated with the key removed from the map or null if the key wasn't found
        int hashCode;
        if (key.hashCode() <0) {
            hashCode = -key.hashCode() % capacity;
        } else {
            hashCode = key.hashCode() % capacity;
        }

        LinkedList<MyEntry> bucket = buckets.get(hashCode);
        for (MyEntry entry : bucket) {
            if (entry.getKey().equals(key)) {
                MyEntry returnEntry = new MyEntry(entry.getKey(), entry.getValue());
                bucket.remove(entry);
                return returnEntry.getValue();
            }

        }
        return null;
    }

    public boolean containsKey(String key) {
        // TODO
        int hashCode;
        if (key.hashCode() <0) {
            hashCode = -key.hashCode() % capacity;
        } else {
            hashCode = key.hashCode() % capacity;
        }

        LinkedList<MyEntry> bucket = buckets.get(hashCode);
        for (MyEntry entry : bucket) {
            if (entry.getKey().equals(key))
                return true;
        }
        return false;
    }

    public boolean containsValue(String value) {
        // TODO
        Collection<String> valuesList = this.values();
        if (valuesList.contains(value))
            return true;
        else
            return false;
    }

    public int size() {
        // TODO Return the number of the Entry objects stored in all the buckets
        int size = 0;
        for (LinkedList<MyEntry> bucket : buckets) {
            for (MyEntry entry : bucket) {
                size++;
            }
        }
        return size;
    }

    public void clear() {
        // TODO Remove all the Entry objects from the bucket list
        for (LinkedList<MyEntry> bucket : buckets) {
            bucket.clear();
        }
    }

    public Set<MyEntry> entrySet() {
        // TODO Return a Set containing all the Entry objects
        HashSet<MyEntry> entrySet = new HashSet<MyEntry>();


        for(LinkedList<MyEntry> bucket : buckets){
            for(MyEntry entry : bucket ){
                entrySet.add(entry);
            }
        }
        return entrySet;
    }

    public boolean isEmpty() {

        if(this.size() == 0){
            return true;
        } else {
            return false;
        }
    }

    public static class MyEntry {
        private String key;
        private String value;

        public MyEntry(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String toString() { return "Element: " + key + " " + value; }
    }
}
