//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Course:          COMP SCI 400 SPRING 2020
// Author:          Yucheng Qiu
// Email:           yqiu56@wisc.edu
// Lecture Number:  001
// Description:     A Data structure class that uses linkedlist.
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

// TODO: Add class header here
/**
 * A class implements DataStructureADT<String,String> that uses linkedlist.
 * @author YC Qiu
 * @version 1.0
 */
public class DS_My implements DataStructureADT< String, String > {
		
    // TODO may wish to define an inner class 
    // for storing key and value as a pair
    // such a class and its members should be "private"
		private Node firstNode;
		private Node lastNode;
		private int size = 0;
		
		/**
		 * This class constructs nodes for the linkedlist in DS_My.
		 * @author pc
		 * @version 1.0
		 */
		private class Node{
			private String value; // value of the Node
			private String key; // A special key for each Node
			private Node nextNode;// Connect to its next Node
			
			Node(String key,String value){
				this.value = value;
				this.key = key;
				nextNode = null;
			}
		}
    // Private Fields of the class
    // TODO create field(s) here to store data pairs
    
    /**
     * Constructor.
     */
    public DS_My() {
        // TODO Auto-generated method stub
        firstNode = null;
        lastNode = null;
    }


		/**
		 * Add the key,value pair to the data structure and increases size. Can
		 * accept and insert null values.
		 * @throws IllegalArgumentException("null key") if key is null
		 * @throws RuntimeException("duplicate key") If key is already in data
		 * structure
		 * @param key a unique key for searching
		 * @param value stored value
		 * @see DataStructureADT#insert(java.lang.Comparable, java.lang.Object)
		 */
		public void insert(String key, String value){
			// If key is null
			if(key == null) throw new IllegalArgumentException("null key");
			// If key is already in data structure
			if(contains(key)) throw new RuntimeException("duplicate key");
			
			Node newNode = new Node(key,value);
			if(size == 0) {
				// If the data structure is empty
				firstNode = newNode;
				lastNode = newNode;
			}
			else {
				lastNode.nextNode = newNode;
				lastNode = newNode;
			}
			size++;
			return;
		}

		/**
		 * If key is found, Removes the key from the data structure and decreases
		 * size.
		 * @param key key for searching
		 * @see DataStructureADT#remove(java.lang.Comparable)
		 * @throws IllegalArgumentException("null key") if key is null. Not
		 * decreasing its size.
		 * @return false if key is not found, otherwise true. 
		 */
		public boolean remove(String key) {
			// If key is null
			if(key==null) throw new IllegalArgumentException("null key");
		  // Iterate the data structure
			int count = 0; // Count for iteration
			Node previous = null; // Previous node
			
			for(Node i = firstNode ; i!=null ;count++, i = i.nextNode) {
				if(i.key.equals(key)) {removeHelper(previous,i);return true;}
				if(count !=0) previous = i;
			}
			// If not found
			return false;
		}
		
		/**
		 * This method helps remove() to delete a node from the list.
		 * @param PreNode node before Currnode
		 * @param Currnode node with the specified key
		 */
		private void removeHelper(Node PreNode, Node Currnode) {
			if(PreNode == null) { // If the specified key is at firstnode
				Node NewHead = Currnode.nextNode;
				if(NewHead==null) lastNode=null;
				else Currnode.nextNode = null;
				firstNode = NewHead;
			}
			else {
				PreNode.nextNode = Currnode.nextNode;
				// If the specified key is at lastnode
				if(Currnode.nextNode==null) lastNode = PreNode;
				else Currnode.nextNode = null;
			}
			size--;
		}
		
		/**
		 * Returns the value associated with the specified key. Notice: get - does
		 * not remove key or decrease size.
		 * @param key key for searching
		 * @throws IllegalArgumentException("null key") if key is null
		 * @see DataStructureADT#get(java.lang.Comparable)
		 * @return null if key is not null and is not found in data structure
		 */
		public String get(String key) {
			// If key is null
			if(key==null) throw new IllegalArgumentException("null key");
			// Iterate the data structure
			for(Node i = firstNode; i!=null ;i = i.nextNode) {
				if(i.key.equals(key)) return i.value;
			}
			// If not found
			return null;
		}

		/**
		 * Detect whether the data structure contains the key
		 * @param key key to detect
		 * @see DataStructureADT#contains(java.lang.Comparable)
		 * @return true if the key is in the data structure, false if key is null
		 * or not present
		 */
		public boolean contains(String key) {
			// if key is null
			if(key == null) return false;
			// Iterate the data structure
			for(Node i = firstNode; i!=null ;i = i.nextNode) {
				if(i.key.equals(key)) return true;
			}
			// If not found
			return false;
		}

		/**
		 * Return the size of current data structure
		 * @see DataStructureADT#size()
		 * @return size
		 */
		public int size() {
			return size;
		}

    // TODO: add unimplemented methods
    // ProTip: Eclipse can do this for you

                                                          

}                            
    
