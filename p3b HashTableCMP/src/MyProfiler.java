
// Used as the data structure to test our hash table against Tree Map
import java.util.*;

public class MyProfiler<K extends Comparable<K>, V> {

	HashTableADT<K, V> hashtable;
	TreeMap<K, V> treemap;

	public MyProfiler() {
		// TODO: complete the Profile constructor
		// Instantiate your HashTable and Java's TreeMap
		hashtable = new HashTable<K, V>();
		treemap = new TreeMap<K, V>();
	}

	public void insert(K key, V value) {
		// TODO: complete insert method
		// Insert K, V into both data structures
		try {
			hashtable.insert(key, value);
			treemap.put(key, value);
		} catch (IllegalNullKeyException e) {
			e.printStackTrace();
		}
	}

	public void retrieve(K key) {
		// TODO: complete the retrieve method
		// get value V for key K from data structures
		try {
			hashtable.get(key);
		} catch (IllegalNullKeyException e) {
			e.printStackTrace();
		} catch (KeyNotFoundException e) {
			System.out.println(key+" cant find in hashtable.");
		}
		treemap.get(key);
	}

	public static void main(String[] args) {
		try {
			int numElements = Integer.parseInt(args[0]);
			MyProfiler<Integer, Integer> profiler = new MyProfiler<Integer, Integer>();
			// TODO: complete the main method.
			// Create a profile object.
			// For example, Profile<Integer, Integer> profile = new Profile<Integer,
			// Integer>();
			
			// execute the insert method of profile as many times as numElements
			// execute the retrieve method of profile as many times as numElements
			// See, ProfileSample.java for example.
			for(int i = 0; i < numElements; i++) {
			  profiler.insert(i, i);
			}
			
			for(int i = 0; i < numElements; i++) {
			  profiler.retrieve(i);
			}
			
			String msg = String.format("Inserted and retreived %d (key,value) pairs", numElements);
			System.out.println(msg);
		} catch (Exception e) {
			System.out.println("Usage: java MyProfiler <number_of_elements>");
			System.exit(1);
		}
	}
}
