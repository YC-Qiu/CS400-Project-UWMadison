//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Course:          COMP SCI 400 SPRING 2020
// Author:          Yucheng Qiu
// Email:           yqiu56@wisc.edu
// Lecture Number:  001
// Description:     My Red-Black Tree.
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;



/**
 * Implements a generic Red-Black tree.
 *
 * DO NOT CHANGE THE METHOD SIGNATURES OF PUBLIC METHODS
 * DO NOT ADD ANY PACKAGE LEVEL OR PUBLIC ACCESS METHODS OR FIELDS.
 * 
 * You are not required to override remove.
 * If you do not override this operation,
 * you may still have the method in your type, 
 * and have the method throw new UnsupportedOperationException.
 * See https://docs.oracle.com/javase/7/docs/api/java/lang/UnsupportedOperationException.html
 * 
 * @param <K> is the generic type of key, must be a Comparable tyle
 * @param <V> is the generic type of value
 */
public class RBT<K extends Comparable<K>, V> implements STADT<K,V>{

    // USE AND DO NOT EDIT THESE CONSTANTS
    public static final int RED = 0;
    public static final int BLACK = 1;

    private class Node {
			K key;
			V value;
			Node parent;
			int color;
			Node leftNode;
			Node rightNode;
			
			Node(Node parent, K key, V value,int color) {
				this.key = key;
				this.value = value;
				this.parent = parent;
				this.color = color;
			}
		}
    
		private Node root;
		
		private int size;
		
    // TODO: define a default no-arg constructor
    public RBT() {
    	root = null;
    	size = 0;
    }

    /**
     * Returns the color of the node that contains the specified key.
     * Returns RBT.RED if the node is red, and RBT.BLACK if the node is black.
     * Returns -1 if the node is not found.
     * @param 
     * @return
     */
    public int colorOf(K key) {
        // TODO: implement private helper method as you see fit
        // From Deb's solution
        //Node found = getNodeWith(root,key);
        //return found==null ? -1 : found.color;
    	Node found = getHelper(root, key);
    	
      return found == null ? -1 : found.color;
    }

    /**
     * Returns true if root is null or the color of the root is black.
     * If root is null, returns true.
     * @return true if root is black, else returns false (for red)
     */
    public boolean rootIsBlack() {
      // TODO implement this method for your RBT 
    	if(root == null || root.color == BLACK) return true;
    	else return false;
    }

    /**
     * Returns true if the node that contains this key is RED.
     * If key is null, throws IllegalNullKeyException.
     * If key is not found, throws KeyNotFoundException.
     * @return true if the key is found and the node's color is RED,
     * else return false if key is found and the node's color is BLACK.
     */
    public boolean isRed(K key) 
            throws IllegalNullKeyException, KeyNotFoundException {
    	if(key==null) throw new IllegalNullKeyException();
    	Node found = getHelper(root, key);
      if(found==null) throw new KeyNotFoundException();
      return found.color == RED;
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
     * Returns true if the node that contains this key is BLACK.
     * If key is null, throws IllegalNullKeyException.
     * If key is not found, throws KeyNotFoundException.
     * @return true if the key is found and the node's color is BLACK,
     * else return false if key is found and the node's color is RED.
     */
    public boolean isBlack(K key) 
            throws IllegalNullKeyException, KeyNotFoundException {
    	
    	if(key==null) throw new IllegalNullKeyException();
    	Node found = getHelper(root, key);
      if(found==null) throw new KeyNotFoundException();
      return found.color == BLACK;
    }

    @Override
    public K getKeyAtRoot() {
        // TODO Auto-generated method stub
        return root.key;
    }

    @Override
    public K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
        // TODO Auto-generated method stub
    	if(key==null) throw new IllegalNullKeyException();
    	Node found = getHelper(root, key);
    	if(found==null) throw new KeyNotFoundException();
      return found.leftNode.key;
    }

    @Override
    public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
        // TODO Auto-generated method stub
    	if(key==null) throw new IllegalNullKeyException();
    	Node found = getHelper(root, key);
    	if(found==null) throw new KeyNotFoundException();
      return found.rightNode.key;
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
    
    @Override
    public int getHeight() {
        return getHeightHelper(root);
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
    
    @Override
    public List<K> getInOrderTraversal() {
      // TODO Auto-generated method stub
    	List<K> PrintList = new ArrayList<K>();
    	traverseHelp(PrintList, root, "INORDER");
    	return PrintList;
    }

    @Override
    public List<K> getPreOrderTraversal() {
    	List<K> PrintList = new ArrayList<K>();
    	traverseHelp(PrintList, root, "PREORDER");
    	return PrintList;
    }

    @Override
    public List<K> getPostOrderTraversal() {
    	List<K> PrintList = new ArrayList<K>();
    	traverseHelp(PrintList, root, "POSTORDER");
    	return PrintList;
    }

    @Override
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
    
    private void rightRotate(Node Parent) {
    	Node leftC = Parent.leftNode;
    	Parent.leftNode = leftC.rightNode;
    	if(leftC.rightNode != null) leftC.rightNode.parent = Parent;
    	leftC.parent = Parent.parent;
    	////////////// if Parent is root ////////////////
    	if(leftC.parent == null) root = leftC;
    	else {
    		if(Parent == Parent.parent.rightNode) Parent.parent.rightNode = leftC;
    		else Parent.parent.leftNode = leftC;
    	}
    	leftC.rightNode = Parent;
    	Parent.parent = leftC;
    }
    
    private void leftRotate(Node Parent) {
    	Node rightC = Parent.rightNode;
    	Parent.rightNode = rightC.leftNode;
    	if(rightC.leftNode != null) rightC.leftNode.parent = Parent;
    	rightC.parent = Parent.parent;
    	////////////// if Parent is root ////////////////
    	if(rightC.parent == null) root = rightC;
    	else {
    		if(Parent == Parent.parent.rightNode) Parent.parent.rightNode = rightC;
    		else Parent.parent.leftNode = rightC;
    	}
    	rightC.leftNode = Parent;
    	Parent.parent = rightC;
    }
    
    @Override
    public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
    	// TODO Auto-generated method stub
    	if(key == null) throw new IllegalNullKeyException();
      
      if(root == null) { // Empty Tree
      	root = new Node(null,key, value,BLACK);
      	size++;
      	return;
      }
      
    	Node current = root;
    	Node newnode;
      while(true) {
      	
    		if(key.compareTo(current.key) == 0) throw new DuplicateKeyException();
    		else if(key.compareTo(current.key) > 0) {
    			if(current.rightNode == null) {
    				newnode = new Node(current,key,value,RED);
    				current.rightNode = newnode;
    				break;
    			}
    			current = current.rightNode;
    		}
    		else {
    			if(current.leftNode == null) {
    				newnode = new Node(current,key,value,RED);
    				current.leftNode = newnode;
    				break;
    			}
    			current = current.leftNode;
    		}
      }
      size++;
      
      rebalance(newnode);
      
      return;
    }
        
    private void rebalance(Node node) {
    	Node parent,gparent;
      
      while ((parent = node.parent) != null && parent.color == RED) {
      	gparent = parent.parent;
      	
      	if(parent == gparent.leftNode) { ///////////// parent is leftchild of gparent //////////
      		Node uncle = gparent.rightNode;
      		///////////// Case 1 Uncle is Red /////////////////
      		if(uncle != null && uncle.color == RED) {
      			uncle.color = BLACK;
      			parent.color = BLACK;
      			gparent.color = RED;
      			node = gparent;
      			continue;
      		}
      	
      	
      	////////////// Case 2 Uncle is null, node is rightchild of parent //////////////
      	if(parent.rightNode == node) {
      		leftRotate(parent);
      		Node temp = parent;
      		parent = node;
      		node = temp;
      	}
      	//////////////Case 3 Uncle is null, node is leftchild of parent //////////////
    		parent.color = BLACK;
    		gparent.color = RED;
    		rightRotate(gparent);
      }else { ///////////// parent is rightchild of gparent //////////
      	Node uncle = gparent.leftNode;
    		///////////// Case 1 Uncle is Red /////////////////
    		if(uncle != null && uncle.color == RED) {
    			uncle.color = BLACK;
    			parent.color = BLACK;
    			gparent.color = RED;
    			node = gparent;
    			continue;
    		}
    	
    	////////////// Case 2 Uncle is null, node is leftchild of parent //////////////
    	if(parent.leftNode == node) {
    		rightRotate(parent);
    		Node temp = parent;
    		parent = node;
    		node = temp;
    	}
    	//////////////Case 3 Uncle is null, node is rightchild of parent //////////////
  		parent.color = BLACK;
  		gparent.color = RED;
  		leftRotate(gparent);
      }
     }
      
      root.color = BLACK;
		}
    @Override
    public boolean remove(K key) throws IllegalNullKeyException {
    	if(key==null)throw new IllegalNullKeyException();
    	return false;
    }

    @Override
    public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
    	if(key==null) throw new IllegalNullKeyException();
    	Node found = getHelper(root, key);
    	if(found==null) throw new KeyNotFoundException();
    	return found.value;
    }

    @Override
    public boolean contains(K key) throws IllegalNullKeyException {
    	if(key==null) throw new IllegalNullKeyException();
    	if(root==null)  return false;
      Node node_found = getHelper(root, key);
      if(node_found==null) return false;
      return true;
    }

    @Override
    public int numKeys() {
        return size;
    }

    @Override
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


    // TODO: override the insert method so that it rebalances 
    //       according to red-black tree insert algorithm.


    // TODO [OPTIONAL] you may override print() to include
    //      color R-red or B-black.
    
}
