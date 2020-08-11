//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Course:          COMP SCI 400 SPRING 2020
// Author:          Yucheng Qiu
// Email:           yqiu56@wisc.edu
// Lecture Number:  001
// Description:     My Binary Search Tree.
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;


// DO IMPLEMENT A BINARY SEARCH TREE IN THIS CLASS

/**
 * Defines the operations required of student's BST class.
 *
 * NOTE: There are many methods in this interface 
 * that are required solely to support gray-box testing 
 * of the internal tree structure.  They must be implemented
 * as described to pass all grading tests.
 * 
 * @author Deb Deppeler (deppeler@cs.wisc.edu)
 * @param <K> A Comparable type to be used as a key to an associated value.  
 * @param <V> A value associated with the given key.
 */
public class BST<K extends Comparable<K>, V> implements STADT<K,V> {
	
		private class Node {
			K key;
			V value;
			Node leftNode;
			Node rightNode;
			
			Node(K key, V value) {
				this.key = key;
				this.value = value;
			}
		}
		
		private Node root;
		
		private int size;
		
		public BST() {
			this.root = null;
	    this.size = 0;
		}
		
    /**
     * Returns the key that is in the root node of this ST.
     * If root is null, returns null.
     * @return key found at root node, or null
     */
    public K getKeyAtRoot() {
        return root.key;
    }
    
    /**
     * Tries to find a node with a key that matches the specified key.
     * If a matching node is found, it returns the returns the key that is in the left child.
     * If the left child of the found node is null, returns null.
     * 
     * @param key A key to search for
     * @return The key that is in the left child of the found key
     * 
     * @throws IllegalNullKeyException if key argument is null
     * @throws KeyNotFoundException if key is not found in this BST
     */
    public K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
    	if(key == null) throw new IllegalNullKeyException();
    	Node node_found = getHelper(root, key);
    	if(node_found==null) throw new KeyNotFoundException();
    	return node_found.leftNode.key;
    }
    
    /**
     * Tries to find a node with a key that matches the specified key.
     * If a matching node is found, it returns the returns the key that is in the right child.
     * If the right child of the found node is null, returns null.
     * 
     * @param key A key to search for
     * @return The key that is in the right child of the found key
     * 
     * @throws IllegalNullKeyException if key is null
     * @throws KeyNotFoundException if key is not found in this BST
     */
    public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
    	if(key == null) throw new IllegalNullKeyException();
    	Node node_found = getHelper(root, key);
    	if(node_found==null) throw new KeyNotFoundException();
    	return node_found.rightNode.key;
    }
    

    /**
     * Returns the height of this BST.
     * H is defined as the number of levels in the tree.
     * 
     * If root is null, return 0
     * If root is a leaf, return 1
     * Else return 1 + max( height(root.left), height(root.right) )
     * 
     * Examples:
     * A BST with no keys, has a height of zero (0).
     * A BST with one key, has a height of one (1).
     * A BST with two keys, has a height of two (2).
     * A BST with three keys, can be balanced with a height of two(2)
     *                        or it may be linear with a height of three (3)
     * ... and so on for tree with other heights
     * 
     * @return the number of levels that contain keys in this BINARY SEARCH TREE
     */
    public int getHeight() {
    	return getHeightHelper(root);
    }
    
    /**
     * A recursive method to help to get the height.
     * @param current - the root of current subtree
     * @return the height of this subtree
     */
    private int getHeightHelper(Node current) {
    	if(current == null ) return 0;
    	int left_height = getHeightHelper(current.leftNode);
    	int right_height = getHeightHelper(current.rightNode);
    	if(left_height >= right_height) return left_height + 1;
    	else return right_height + 1;
    }
    
    
    /**
     * Returns the keys of the data structure in sorted order.
     * In the case of binary search trees, the visit order is: L V R
     * 
     * If the SearchTree is empty, an empty list is returned.
     * 
     * @return List of Keys in-order
     */
    public List<K> getInOrderTraversal() {
    	List<K> PrintList = new ArrayList<K>();
    	traverseHelp(PrintList, root, "INORDER");
    	return PrintList;
    }
    
    /**
     * Recursive helper method to traverse. Will take the current key and add it to
     * list based on the given order. Then continue to recurse on the correct subtree.
     * 
     * @param list - list for storing the keys
     * @param current - the root of the current subtree we are traversing
     * @param order - the type of traversal to perform
     */
    private void traverseHelp(List<K> list, Node current, String order) {
      if (current == null)
        return;

      switch (order) {
        case "PREORDER":
          list.add(current.key);
          traverseHelp(list,current.leftNode, order);
          traverseHelp(list,current.rightNode, order);
          break;
        case "POSTORDER":
          traverseHelp(list,current.leftNode, order);
          traverseHelp(list,current.rightNode, order);
          list.add(current.key);
          break;
        case "INORDER":
          traverseHelp(list,current.leftNode, order);
          list.add(current.key);
          traverseHelp(list,current.rightNode, order);
          break;
      }
    }
    
    /**
     * Returns the keys of the data structure in pre-order traversal order.
     * In the case of binary search trees, the order is: V L R
     * 
     * If the SearchTree is empty, an empty list is returned.
     * 
     * @return List of Keys in pre-order
     */
    public List<K> getPreOrderTraversal() {
    	List<K> PrintList = new ArrayList<K>();
    	traverseHelp(PrintList, root, "PREORDER");
    	return PrintList;
    }

    /**
     * Returns the keys of the data structure in post-order traversal order.
     * In the case of binary search trees, the order is: L R V 
     * 
     * If the SearchTree is empty, an empty list is returned.
     * 
     * @return List of Keys in post-order
     */
    public List<K> getPostOrderTraversal() {
    	List<K> PrintList = new ArrayList<K>();
    	traverseHelp(PrintList, root, "POSTORDER");
    	return PrintList;
    }

    /**
     * Returns the keys of the data structure in level-order traversal order.
     * 
     * The root is first in the list, then the keys found in the next level down,
     * and so on. 
     * 
     * If the SearchTree is empty, an empty list is returned.
     * 
     * @return List of Keys in level-order
     */
    public List<K> getLevelOrderTraversal() {
    	List<K> PrintList = new ArrayList<K>();
    	Queue<Node> q  =new LinkedList<Node>(); // Use queue to get a correct level order of sequence
    	if(root==null) return PrintList;
    	q.add(root);
    	while(!q.isEmpty()) {
    		Node popnode = q.remove();
    		PrintList.add(popnode.key);
    		if(popnode.leftNode != null) q.add(popnode.leftNode);
    		if(popnode.rightNode != null) q.add(popnode.rightNode);
    	}
      return PrintList;
    }
    
    
    /** 
     * Add the key,value pair to the data structure and increase the number of keys.
     * If key is null, throw IllegalNullKeyException;
     * If key is already in data structure, throw DuplicateKeyException(); 
     * Do not increase the num of keys in the structure, if key,value pair is not added.
     */
    public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
      if(key == null) throw new IllegalNullKeyException();
      if(root == null) { // Empty Tree
      	root = new Node(key, value);
      	size++;
      	return;
      }
      
    	Node current = root;
    	
      while(true) {
      	
    		if(key.compareTo(current.key) == 0) throw new DuplicateKeyException();
    		else if(key.compareTo(current.key) > 0) {
    			if(current.rightNode == null) {
    				current.rightNode = new Node(key,value);
    				break;
    			}
    			current = current.rightNode;
    		}
    		else {
    			if(current.leftNode == null) {
    				current.leftNode = new Node(key,value);
    				break;
    			}
    			current = current.leftNode;
    		}
      }
      size++;
      return;
    }
    
    /**
     * Recursive helper method to delete.
     * 
     * @param current - The "root" of the subtree we are deleting from, ie the
     * node we are currently at.
     * @param key - the key to be deleted from the tree
     * @return the root of the modified subtree we deleted from
     * @throws NoSuchElementException if the camper is not in the tree
     */
    private Node deleteHelp(Node current, K key) {
      if (current == null)
        throw new NoSuchElementException();

      K Ckey = current.key;

      if (key.compareTo(Ckey) == 0) {
      	// If a node whose leftnode and rightnode are both null, after removed
      	// the structure of tree remains the same.
        if (current.leftNode == null && current.rightNode == null)
          return null;
        if (current.leftNode != null && current.rightNode == null)
        	return current.leftNode;
        if (current.leftNode == null && current.rightNode != null)
          return current.rightNode;

        // If both left and right node is there
        Node successor = current.rightNode;
        while (successor.leftNode != null)
          successor = successor.leftNode;

        current.key = successor.key;
        current.value = successor.value;
        current.rightNode = deleteHelp(current.rightNode, successor.key);
      }
      if (key.compareTo(Ckey) < 0)
        current.leftNode = deleteHelp(current.leftNode, key);
      if (key.compareTo(Ckey) > 0)
        current.rightNode = deleteHelp(current.rightNode, key);

      return current;
    }
    

    /** 
     * If key is found, remove the key,value pair from the data structure 
     * and decrease num keys, and return true.
     * If key is not found, do not decrease the number of keys in the data structure, return false.
     * If key is null, throw IllegalNullKeyException
     */
    public boolean remove(K key) throws IllegalNullKeyException {
    	if(key == null) throw new IllegalNullKeyException();
    	try {
    		Node newroot = deleteHelp(this.root, key);
    		root = newroot;
    		size--;
    		return true;
			} catch (NoSuchElementException e) {
				return false;
			}
    }
    
    /**
     * Search for a key's Node in the whole tree, started with root. If key is not found,
     * null value is returned.
     * 
     * @param startNode - the subtree's root to search
     * @param key - given key
     * @return the Node whose key matches given key
     */
    private Node getHelper(Node startNode, K key) {
    	Node current = startNode;
    	while(key.compareTo(current.key) != 0) {

    		if(key.compareTo(current.key) > 0) {
    			current = current.rightNode;
    		}
    		else {
    			current = current.leftNode;
    		}
    		if(current == null) break;
    	}
    	return current;
		}
   
    /**
     * Returns the value associated with the specified key.
     *
     * Does not remove key or decrease number of keys
     * If key is null, throw IllegalNullKeyException 
     * If key is not found, throw KeyNotFoundException().
     */
    public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
      if(key==null) throw new IllegalNullKeyException();
      Node node_found = getHelper(root, key);
      if(node_found==null) throw new KeyNotFoundException();
    	return node_found.value;
    }

    /** 
     * Returns true if the key is in the data structure
     * If key is null, throw IllegalNullKeyException 
     * Returns false if key is not null and is not present 
     */
    public boolean contains(K key) throws IllegalNullKeyException { 
    	if(key==null) throw new IllegalNullKeyException();
    	if(root==null)  return false;
      Node node_found = getHelper(root, key);
      if(node_found==null) return false;
      return true;
    }

    /**
     *  Returns the number of key,value pairs in the data structure
     */
    public int numKeys() {
        return size;
    }
    
    
    /**
     * Print the tree. 
     *
     * For our testing purposes of your print method: 
     * all keys that we insert in the tree will have 
     * a string length of exactly 2 characters.
     * example: numbers 10-99, or strings aa - zz, or AA to ZZ
     *
     * This makes it easier for you to not worry about spacing issues.
     *
     * You can display a binary search in any of a variety of ways, 
     * but we must see a tree that we can identify left and right children 
     * of each node
     *
     * For example: 
     
           30
           /\
          /  \
         20  40
         /   /\
        /   /  \
       10  35  50 

       Look from bottom to top. Inorder traversal of above tree (10,20,30,35,40,50)
       
       Or, you can display a tree of this kind.

       |       |-------50
       |-------40
       |       |-------35
       30
       |-------20
       |       |-------10
       
       Or, you can come up with your own orientation pattern, like this.

       10                 
               20
                       30
       35                
               40
       50                  

       The connecting lines are not required if we can interpret your tree.

     */
    public void print() {
    	Queue<Node> q  =new LinkedList<Node>(); // Use queue to get a correct level order of sequence
    	q.add(root);
    	// Max number of nodes in tree
    	int h = getHeight();
    	int NodeNum = 0;
    	int LevelCount = 1, WhiteSpaceStart = (int)(Math.pow(2,h)-2);
    	while(!q.isEmpty()) {
    		Node popnode = q.remove();
    		NodeNum++;
    		/////////////////////////// Newline, print WS first/////////////////////////////
    		if(NodeNum == Math.pow(2,LevelCount-1)) {
    			// Start in a new line
    			WSprint(WhiteSpaceStart);
    		}
    		/////////////////////////// Print Keys ////////////////////////////////////
    		if(popnode == null) {
    			System.out.print("**");
    			q.add(null); q.add(null); // Empty nodes
    		}
    		else {
    			q.add(popnode.leftNode); q.add(popnode.rightNode);
      		System.out.print(popnode.key);
    		}
    		///////////////////////// Check if the line is end ////////////////////////////
    		if(NodeNum >= Math.pow(2, LevelCount)-1) {
    			LevelCount++;
    			WhiteSpaceStart = (WhiteSpaceStart - 2 )/2;
    			System.out.println("\n");// newline
    			if(LevelCount > h) break;
    		}
    		///////////////////////// if not to the next line, print WS ////////////////////
    		else WSprint((WhiteSpaceStart+1)*2);
    		
    	}
    }
    
    /**
     * A helper method to print white spaces.
     * 
     * @param num - number of whitespaces
     */
    private void WSprint(int num) {
    	for(int i = 0; i < num; i++) System.out.print(" ");
    }
    	

} // copyrighted material, students do not have permission to post on public sites




//  deppeler@cs.wisc.edu
