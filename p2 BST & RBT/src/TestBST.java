//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Course:          COMP SCI 400 SPRING 2020
// Author:          Yucheng Qiu
// Email:           yqiu56@wisc.edu
// Lecture Number:  001
// Description:     A JUnit class tests my BST.
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//@SuppressWarnings("rawtypes")
public class TestBST {

    protected STADT<Integer,String> bst;

    /**
     * @throws java.lang.Exception
     */
    @BeforeEach
    void setUp() throws Exception {
         bst = new BST<Integer,String>();
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterEach
    void tearDown() throws Exception {
    }

    /** 
     * CASE 123 Insert three values in sorted order and then check 
     * the root, left, and right keys to see if insert worked 
     * correctly.
     */
    @Test
    void testBST_001_insert_sorted_order_simple() {
        try {
            bst.insert(10, "10");
            if (!bst.getKeyAtRoot().equals(10))
                fail("insert at root does not work");
            
            bst.insert(20, "20");
            if (!bst.getKeyOfRightChildOf(10).equals(20)) 
                fail("insert to right child of root does not work");
            
            bst.insert(30, "30");
            if (!bst.getKeyAtRoot().equals(10)) 
                fail("inserting 30 changed root");

            if (!bst.getKeyOfRightChildOf(20).equals(30)) 
                fail("inserting 30 as right child of 20");

            // IF rebalancing is working,
            // the tree should have 20 at the root
            // and 10 as its left child and 30 as its right child

            Assert.assertEquals(bst.getKeyAtRoot(), Integer.valueOf(10));
            Assert.assertEquals(bst.getKeyOfRightChildOf(10), Integer.valueOf(20));
            Assert.assertEquals(bst.getKeyOfRightChildOf(20), Integer.valueOf(30));

            bst.print();
            
        } catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception: "+e.getMessage() );
        }
    }

    /** 
     * CASE 321 Insert three values in reverse sorted order and then check 
     * the root, left, and right keys to see if insert 
     * worked in the other direction.
     */
    @Test
    void testBST_002_insert_reversed_sorted_order_simple() {
        try {
            bst.insert(30, "30");
            if (!bst.getKeyAtRoot().equals(30))
                fail("insert at root does not work");
            
            bst.insert(20, "20");
            if (!bst.getKeyOfLeftChildOf(30).equals(20)) 
                fail("insert to left child of root does not work");
            
            bst.insert(10, "10");
            if (!bst.getKeyAtRoot().equals(30)) 
                fail("inserting 10 changed root");

            if (!bst.getKeyOfLeftChildOf(20).equals(10)) 
                fail("inserting 10 as left child of 20");

            // IF rebalancing is working,
            // the tree should have 20 at the root
            // and 10 as its left child and 30 as its right child

            Assert.assertEquals(bst.getKeyAtRoot(), Integer.valueOf(30));
            Assert.assertEquals(bst.getKeyOfLeftChildOf(30), Integer.valueOf(20));
            Assert.assertEquals(bst.getKeyOfLeftChildOf(20), Integer.valueOf(10));

            bst.print();
            
        } catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception: "+e.getMessage() );
        }
    }

    /** 
     * CASE 132 Insert three values so that rebalancing requires new key 
     * to be the "new" root of the rebalanced tree.
     * 
     * Then check the root, left, and right keys to see if insert 
     * occurred correctly.
     */
    @Test
    void testBST_003_insert_smallest_largest_middle_order_simple() {
        try {
            bst.insert(10, "10");
            if (!bst.getKeyAtRoot().equals(10))
                fail("insert at root does not work");
            
            bst.insert(30, "30");
            if (!bst.getKeyOfRightChildOf(10).equals(30)) 
                fail("insert to right child of root does not work");
            Assert.assertEquals(bst.getKeyOfRightChildOf(10),Integer.valueOf(30));
            
            bst.insert(20, "20");
            if (!bst.getKeyAtRoot().equals(10)) 
                fail("inserting 20 changed root");

            if (!bst.getKeyOfLeftChildOf(30).equals(20)) 
                fail("inserting 20 as left child of 30");

            // IF rebalancing is working,
            // the tree should have 20 at the root
            // and 10 as its left child and 30 as its right child

            Assert.assertEquals(bst.getKeyAtRoot(), Integer.valueOf(10));
            Assert.assertEquals(bst.getKeyOfRightChildOf(10), Integer.valueOf(30));
            Assert.assertEquals(bst.getKeyOfLeftChildOf(30), Integer.valueOf(20));

            bst.print();
            
        } catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception: "+e.getMessage() );
        }
    }

    /** 
     * CASE 312 Insert three values so that rebalancing requires new key 
     * to be the "new" root of the rebalanced tree.
     * 
     * Then check the root, left, and right keys to see if insert 
     * occurred correctly.
     */
    @Test
    void testBST_004_insert_largest_smallest_middle_order_simple() {
        try {
            bst.insert(30, "30");
            if (!bst.getKeyAtRoot().equals(30))
                fail("insert at root does not work");
            
            bst.insert(10, "10");
            if (!bst.getKeyOfLeftChildOf(30).equals(10)) 
                fail("insert to left child of root does not work");
            
            bst.insert(20, "20");
            if (!bst.getKeyAtRoot().equals(30)) 
                fail("inserting 10 changed root");

            if (!bst.getKeyOfRightChildOf(10).equals(20)) 
                fail("inserting 20 as right child of 10");

            // the tree should have 30 at the root
            // and 10 as its left child and 20 as 10's right child

            Assert.assertEquals(bst.getKeyAtRoot(), Integer.valueOf(30));
            Assert.assertEquals(bst.getKeyOfLeftChildOf(30), Integer.valueOf(10));
            Assert.assertEquals(bst.getKeyOfRightChildOf(10), Integer.valueOf(20));

            bst.print();
            
        } catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception: "+e.getMessage() );
        }
    }
    
    @Test
    void testBST_005_levelorder_traverse() {
    	
    	try {
    		bst.insert(7, "7");
    		bst.insert(3, "3");
    		bst.insert(10, "10");
    		bst.insert(1, "1");
    		bst.insert(4, "4");
    		bst.insert(9, "9");
    		bst.insert(40, "40");
			} catch (Exception e) {
				e.printStackTrace();
        fail( "Unexpected exception: "+e.getMessage() );
			}
    	if(!bst.getLevelOrderTraversal().toString().equals("[7, 3, 10, 1, 4, 9, 40]"))
    		fail("Wrong levelorder traverse");
    	
    }
    
    @Test
    void testBST_006_add20_remove20_contain20() {
    	try {
				for(int i=1;i<=10;i++) {
					bst.insert(i, i+"");
					bst.insert(-i, -i+"");
				}
				for(int i=1;i<=10;i++) {
					bst.remove(i);
					bst.remove(-i);
				}
				bst.print();
				if(bst.contains(1)) fail("Test 006 contains failed!");
				if(bst.contains(-1)) fail("Test 006 contains failed!");
				
			} catch (Exception e) {
				e.printStackTrace();
        fail( "Unexpected exception: "+e.getMessage() );
			}
    }
    
    
    // TODO: Add your own tests
    
    // Add tests to make sure that bst grows as expected.
    // Does it maintain it's balance?
    // Does the height of the tree reflect it's actual height
    // Use the traversal orders to check.
    
    // Can you insert many and still "get" them back out?
    
    // Does delete work? 
    // If delete is not implemented, does calling it throw an UnsupportedOperationException

}
