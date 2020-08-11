//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Course:          COMP SCI 400 SPRING 2020
// Author:          Yucheng Qiu
// Email:           yqiu56@wisc.edu
// Lecture Number:  001
// Description:     My hash table.
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.util.ArrayList;

// TODO: comment and complete your HashTableADT implementation
// DO ADD UNIMPLEMENTED PUBLIC METHODS FROM HashTableADT and DataStructureADT TO YOUR CLASS
// DO IMPLEMENT THE PUBLIC CONSTRUCTORS STARTED
// DO NOT ADD OTHER PUBLIC MEMBERS (fields or methods) TO YOUR CLASS
//
// TODO: implement all required methods
//
// TODO: describe the collision resolution scheme you have chosen
// identify your scheme as open addressing or bucket
// Open address by double hashing.
//
// TODO: explain your hashing algorithm here
// The first hash function is f(x) that follow these steps:
//					1. Divide x into its last 3 digits A and other digits B
//						 s.t. A + 1000*B = x
//					2. f(x) = B*31 + A*29 + 41
//
// The second hash function is g(x) = x xor x^2.
//
// NOTE: you are not required to design your own algorithm for hashing,
//       since you do not know the type for K,
//       you must use the hashCode provided by the <K key> object
//       and one of the techniques presented in lecture
//
/**
 * A hash table that implements ds.
 * @author YC 
 * @param <K> a comparable Class
 * @param <V> Any class
 */
public class HashTable<K extends Comparable<K>, V> implements HashTableADT<K, V> {
	
	// TODO: ADD and comment DATA FIELD MEMBERS needed for your implementation
		
	// TODO: comment and complete a default no-arg constructor
	/**
	 * Default no-arg constructor with capacity 23
	 * and threshold 0.75 .
	 */
	public HashTable(){
		this(23,0.75);
	}
	
	/**
	 * An inner class to store each key value pair.
	 * @author YC
	 */
	private class Node{
		K key;
		V value;
		boolean EMP = true;
		// Constructor
		Node(K key,V value){
			this.key = key;
			this.value = value;
			if(key!=null) EMP = false;
		}
		
		boolean isEMP() {return EMP;}
	}
	
	private ArrayList<Node> MyTable; 
	
	private double loadFactorThreshold;
	
	private int capacity, size;
	
	// TODO: comment and complete a constructor that accepts 
	// initial capacity and load factor threshold
        // threshold is the load factor that causes a resize and rehash
	/**
	 * Initialize a hashtable with given capacity and threshold.
	 * @param initialCapacity - initial capacity
	 * @param loadFactorThreshold - a factor less than 1 to tell when to resize
	 */
	public HashTable(int initialCapacity, double loadFactorThreshold) {
		MyTable = new ArrayList<Node>(initialCapacity);
		for(int i=0;i<initialCapacity;i++) {
			MyTable.add(new Node(null,null));
		}
		this.loadFactorThreshold =  loadFactorThreshold;
		capacity = initialCapacity;
		size = 0;
	}

	/**
	 * @see DataStructureADT#insert(java.lang.Comparable, java.lang.Object)
	 */
	public void insert(K key, V value) throws IllegalNullKeyException {
		if(key == null)
			throw new IllegalNullKeyException();
		// if table is full
		if((double)(size+1)/capacity  >= loadFactorThreshold) {
			//////////////////////     Resizing    /////////////////////////
			ResizeAndRehash();
		}
		
		int index = FirstHash(key);
		Node newNode = new Node(key, value);
		int SearchingTime = 0;
		while(SearchingTime <= 2*capacity) {
			index += SearchingTime * SecondHash(key);
			index = Math.abs(index) % capacity;
			if(MyTable.get(index).isEMP()) {
				MyTable.set(index, newNode);
				size++;
				return;
			}else if(MyTable.get(index).key.equals(key)) {
				MyTable.get(index).value = value;
				return;
			}
			else SearchingTime++;
		}
		System.out.println("Insert Timeout.");
		//System.out.println("insert "+key+" to the index "+index);
		
	}
	
	/**
	 * Function that helps get(). Get the index of given key.
	 * @param key - key to get
	 * @return the index of the pair in the table
	 */
	private int getHelper(K key) {
		int index = FirstHash(key);
		int SearchingTime = 0;
		while(MyTable.get(index).key==null ||
				!MyTable.get(index).key.equals(key)) {
			if(SearchingTime > 10*capacity || MyTable.get(index).key == null) {
				return -1;
			}
			index += SearchingTime * SecondHash(key);
			index = Math.abs(index) % capacity;
			SearchingTime++;
		}
		return index;
	}
	/**
	 * @see DataStructureADT#remove(java.lang.Comparable)
	 */
	public boolean remove(K key) throws IllegalNullKeyException {
		if(key==null) throw new IllegalNullKeyException();
		int index = getHelper(key);
		if(index == -1) return false;
		Node nullNode = new Node(null, null);
		MyTable.set(index, nullNode);
		size--;
		return true;
	}

	/**
	 * @see DataStructureADT#get(java.lang.Comparable)
	 */
	public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
		if(key==null) throw new IllegalNullKeyException();
		int index = getHelper(key);
		if(index == -1) throw new KeyNotFoundException();
		return MyTable.get(index).value;
	}

	/**
	 * @see DataStructureADT#numKeys()
	 */
	public int numKeys() {
		return size;
	}

	/**
	 * @see HashTableADT#getLoadFactorThreshold()
	 */
	public double getLoadFactorThreshold() {
		return loadFactorThreshold;
	}

	/**
	 * @see HashTableADT#getLoadFactor()
	 */
	public double getLoadFactor() {
		return ((double)size / capacity);
	}

	/**
	 * @see HashTableADT#getCapacity()
	 */
	public int getCapacity() {
		return capacity;
	}
	
	/**
	 * A function that helps table resize and rehash.
	 * @throws IllegalNullKeyException - if key is null
	 */
	private void ResizeAndRehash() throws IllegalNullKeyException{
		//System.out.println("need to be resized,current loadfactor = "+getLoadFactor());
		capacity = 2 * capacity + 1;
		ArrayList<Node> NewTable = new ArrayList<Node>(capacity);
		for(int i=0;i<capacity;i++) {
			NewTable.add(new Node(null,null));
		}
		ArrayList<Node> OldTable = MyTable;
		MyTable = NewTable;
		size = 0;
		for(Node n : OldTable) {
			if(n.isEMP()) continue;
			insert(n.key, n.value);
		}
		//System.out.println("Resize to "+capacity);
	}
	
	/**
	 * @see HashTableADT#getCollisionResolution()
	 */
	public int getCollisionResolution() {
		return 3;
	}
	
	/**
	 * First hash function. Need to be smaller than capacity.
	 * @param key key to hash
	 * @return the result of hashing
	 */
	private int FirstHash(K key) {
		int hashNumber = key.hashCode();
		int Lowerpart = hashNumber%1000;
		int Upperpart = (hashNumber - Lowerpart) / 1000;
		return Math.abs(Upperpart*31 + Lowerpart*29 + 41) % capacity;
	}
	
	/**
	 * Second hash function for open address. No need to be smaller than capacity.
	 * @param key key to hash
	 * @return the result of hashing
	 */
	private int SecondHash(K key) {
		int hashNumber = key.hashCode();
		int Number2 = hashNumber * hashNumber;
		int result = hashNumber ^ Number2;
		return Math.abs(result);
	}
	
	// TODO: implement all unimplemented methods so that the class can compile

		
}
