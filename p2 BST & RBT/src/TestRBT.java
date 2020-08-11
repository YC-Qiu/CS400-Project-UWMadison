//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Course:          COMP SCI 400 SPRING 2020
// Author:          Yucheng Qiu
// Email:           yqiu56@wisc.edu
// Lecture Number:  001
// Description:     A JUnit class tests my RBT.
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;

// TODO: Add tests to test if a Red-Black tree 
// extension of BST is correct.  Mostly check node color and position

//@SuppressWarnings("rawtypes")
public class TestRBT  {

    protected RBT<Integer,String> rbt;

    /**
     * @throws java.lang.Exception
     */
    @BeforeEach
    void setUp() throws Exception {
         rbt = new RBT<Integer,String>();
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterEach
    void tearDown() throws Exception {
    }

    /** 
     * CASE 123 Insert three values in sorted order and then check 
     * the root, left, and right keys to see if RBT rebalancing 
     * occurred.
     * 
     */
    @Test
    void testRBT_001_insert_sorted_order_simple() {
        try {
            rbt.insert(10, "10");
            Assert.assertTrue(rbt.rootIsBlack());
            
            rbt.insert(20, "20");
            Assert.assertTrue(rbt.getKeyOfRightChildOf(10).equals(20)) ;
            Assert.assertEquals(rbt.colorOf(20),RBT.RED);
            
            rbt.insert(30, "30");  // SHOULD CAUSE REBALANCING
            Assert.assertTrue(rbt.getKeyOfRightChildOf(20).equals(30));
            
            // IF rebalancing is working,
            // the tree should have 20 at the root
            // and 10 as its left child and 30 as its right child
            Assert.assertEquals(rbt.getKeyAtRoot(), Integer.valueOf(20));
            Assert.assertEquals(rbt.getKeyOfLeftChildOf(20), Integer.valueOf(10));
            Assert.assertEquals(rbt.getKeyOfRightChildOf(20), Integer.valueOf(30));

            rbt.print();
            
        } catch (Exception e) {
            //e.printStackTrace();
            fail( "Unexpected exception: "+e.getMessage() );
        }
    }

    /** 
     * CASE 321 Insert three values in reverse sorted order and then check 
     * the root, left, and right keys to see if rebalancing 
     * occurred in the other direction.
     */
    @Test
    void testRBT_002_insert_reversed_sorted_order_simple() {
        
    	try {
        rbt.insert(30, "30");
        Assert.assertTrue(rbt.rootIsBlack());
        
        rbt.insert(20, "20");
        Assert.assertTrue(rbt.getKeyOfLeftChildOf(30).equals(20)) ;
        Assert.assertEquals(rbt.colorOf(20),RBT.RED);
        
        rbt.insert(10, "10");  // SHOULD CAUSE REBALANCING
        Assert.assertTrue(rbt.getKeyOfLeftChildOf(20).equals(10));
        
        // IF rebalancing is working,
        // the tree should have 20 at the root
        // and 10 as its left child and 30 as its right child
        Assert.assertEquals(rbt.getKeyAtRoot(), Integer.valueOf(20));
        Assert.assertEquals(rbt.getKeyOfLeftChildOf(20), Integer.valueOf(10));
        Assert.assertEquals(rbt.getKeyOfRightChildOf(20), Integer.valueOf(30));

        rbt.print();
        
    } catch (Exception e) {
        e.printStackTrace();
        fail( "Unexpected exception: "+e.getMessage() );
    }
        
    }

    /** 
     * CASE 132 Insert three values so that rebalancing requires new key 
     * to be the "new" root of the rebalanced tree.
     * 
     * Then check the root, left, and right keys to see if rebalancing 
     * occurred correctly.
     */
    @Test
    void testRBT_003_insert_smallest_largest_middle_order_simple() {
        
    	try {
        rbt.insert(10, "10");
        Assert.assertTrue(rbt.rootIsBlack());
        
        rbt.insert(30, "30");
        Assert.assertTrue(rbt.getKeyOfRightChildOf(10).equals(30)) ;
        Assert.assertEquals(rbt.colorOf(30),RBT.RED);
        
        rbt.insert(20, "20");  // SHOULD CAUSE REBALANCING
        Assert.assertTrue(rbt.getKeyOfLeftChildOf(20).equals(10));
        
        // IF rebalancing is working,
        // the tree should have 20 at the root
        // and 10 as its left child and 30 as its right child
        Assert.assertEquals(rbt.getKeyAtRoot(), Integer.valueOf(20));
        Assert.assertEquals(rbt.getKeyOfLeftChildOf(20), Integer.valueOf(10));
        Assert.assertEquals(rbt.getKeyOfRightChildOf(20), Integer.valueOf(30));

        rbt.print();
        
    } catch (Exception e) {
        e.printStackTrace();
        fail( "Unexpected exception: "+e.getMessage() );
    }
        
    }

    /** 
     * CASE 312 Insert three values so that rebalancing requires new key 
     * to be the "new" root of the rebalanced tree.
     * 
     * Then check the root, left, and right keys to see if rebalancing 
     * occurred correctly.
     */
    @Test
    void testRBT_004_insert_largest_smallest_middle_order_simple() {
        
    	try {
        rbt.insert(30, "30");
        Assert.assertTrue(rbt.rootIsBlack());
        
        rbt.insert(10, "10");
        Assert.assertTrue(rbt.getKeyOfLeftChildOf(30).equals(10)) ;
        Assert.assertEquals(rbt.colorOf(10),RBT.RED);
        
        rbt.insert(20, "20");  // SHOULD CAUSE REBALANCING
        Assert.assertTrue(rbt.getKeyOfLeftChildOf(20).equals(10));
        
        // IF rebalancing is working,
        // the tree should have 20 at the root
        // and 10 as its left child and 30 as its right child
        Assert.assertEquals(rbt.getKeyAtRoot(), Integer.valueOf(20));
        Assert.assertEquals(rbt.getKeyOfLeftChildOf(20), Integer.valueOf(10));
        Assert.assertEquals(rbt.getKeyOfRightChildOf(20), Integer.valueOf(30));

        rbt.print();
        
    	} catch (Exception e) {
        e.printStackTrace();
        fail( "Unexpected exception: "+e.getMessage() );
    }
        
    }
    
    @Test
    void testRBT_005_levelorder_traverse() {
    	
    	try {
    		rbt.insert(7, "7");
    		rbt.insert(3, "3");
    		rbt.insert(10, "10");
    		rbt.insert(1, "1");
    		rbt.insert(4, "4");
    		rbt.insert(9, "9");
    		rbt.insert(40, "40");
			} catch (Exception e) {
				e.printStackTrace();
        fail( "Unexpected exception: "+e.getMessage() );
			}
    	if(!rbt.getLevelOrderTraversal().toString().equals("[7, 3, 10, 1, 4, 9, 40]"))
    		fail("Wrong levelorder traverse");
    	
    }
    
    @Test
    void testRBT_006_test_rebalance() {
    	try {
    		rbt.insert(6, "6");
    		rbt.insert(14, "14");
    		rbt.insert(94, "94");
    		rbt.insert(73, "73");
    		rbt.insert(21, "21");
    		rbt.insert(91, "91");
    		rbt.insert(45, "45");
    		if(!rbt.getKeyOfLeftChildOf(73).equals(21))
      		fail("test 006 structure wrong!");
    		if(!rbt.getKeyOfRightChildOf(73).equals(94))
    			fail("test 006 structure wrong!");
			} catch (Exception e) {
				e.printStackTrace();
        fail( "Unexpected exception: "+e.getMessage() );
			}
    	
    	
    }
    // TODO: Add your own tests
    
    // Add tests to make sure that rebalancing occurs even if the 
    // tree is larger.   Does it maintain it's balance?
    // Does the height of the tree reflect it's actual height
    // Use the traversal orders to check.
    
    // Can you insert many and still "get" them back out?
    
    // Does delete work?  Does the tree maintain balance when a key is deleted?
    // If delete is not implemented, does calling it throw an UnsupportedOperationException

} // copyright Deb Deppeler, all rights reserved, DO NOT SHARE
