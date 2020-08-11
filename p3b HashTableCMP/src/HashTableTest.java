//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Course:          COMP SCI 400 SPRING 2020
// Author:          Yucheng Qiu
// Email:           yqiu56@wisc.edu
// Lecture Number:  001
// Description:     Tests for hash table.
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
// TODO: add imports as needed

// org.junit.Assert.*; 
import static org.junit.jupiter.api.Assertions.fail;

import javax.swing.text.html.HTML;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



/** TODO: add class header comments here*/
public class HashTableTest{

    // TODO: add other fields that will be used by multiple tests
	HashTable<Integer, String> ht;
    // TODO: add code that runs before each test method
    @Before
    public void setUp() throws Exception {
    	 ht = new HashTable<Integer, String>();
    }

    // TODO: add code that runs after each test method
    @After
    public void tearDown() throws Exception {

    }
    
    /** 
     * Tests that a HashTable returns an integer code
     * indicating which collision resolution strategy 
     * is used.
     * REFER TO HashTableADT for valid collision scheme codes.
     */
    @Test
    public void test000_collision_scheme() {
        HashTableADT htIntegerKey = new HashTable<Integer,String>();
        int scheme = htIntegerKey.getCollisionResolution();
        if (scheme < 1 || scheme > 9) 
            fail("collision resolution must be indicated with 1-9");
    }
        
    /** IMPLEMENTED AS EXAMPLE FOR YOU
     * Tests that insert(null,null) throws IllegalNullKeyException
     */
    @Test
    public void test001_IllegalNullKey() {
       try {
            HashTableADT htIntegerKey = new HashTable<Integer,String>(); 
            
            htIntegerKey.insert(null, null);
            fail("should not be able to insert null key");
        } 
        catch (IllegalNullKeyException e) { /* expected */ } 
        catch (Exception e) {
            fail("insert null key should not throw exception "+e.getClass().getName());
        }
    }
    
    @Test
    public void test002_insert3num() {
    	try {
    		ht.insert(18, "18");
    		ht.insert(10, "10");
    		ht.insert(2, "2");
    	}catch (Exception e) {
    		fail("Unable to insert 3 pairs");
			}
    }
    
    @Test
    public void test003_testCapacityAfterResize() {
    	try{
    		for(int i=0;i<20;i++) ht.insert(i, i+"");
    		assert(ht.getCapacity()==47);
    	}catch (Exception e) {
    		fail("Unexpected exception");
			}
    	
    }
    
    @Test
    public void test004_testSizeAfterResize() {
    	try{
    		for(int i=0;i<20;i++) ht.insert(i, i+"");
    		assert(ht.numKeys()==20);
    	}catch (Exception e) {
    		fail("Unexpected exception");
			}
    }
    
    @Test
    public void test005_testGetNonExistKey() {
    	try {
    		for(int i=0;i<13;i++)ht.insert(i, i+"");
    		ht.get(88);
    		fail("Should throw KeyNotFoundException");
    	}catch (KeyNotFoundException e) {
    	}catch (Exception e) {
    		fail("Unexpected exception");
    		e.printStackTrace();
			}
    }
    
    @Test
    public void test006_removeExistKey() {
    	try {
    		for(int i=0;i<13;i++)ht.insert(i, i+"");
    		ht.remove(12);
    	}catch (Exception e) {
    		fail("Should not throw exception here");
			}
    }
    
    @Test
    public void test007_testSizeAfterRemovingExistKey() {
    	try {
    		for(int i=0;i<13;i++)ht.insert(i, i+"");
    		ht.remove(12);
    		assert(ht.numKeys()==12);
    	}catch (Exception e) {
    		fail("Should not throw exception here");
			}
    }
    // TODO add your own tests of your implementation
    
    @Test
    public void test008_testGetMoreThan128Elements() {
    	try {
    		for(int i=0;i<200;i++)ht.insert(i, i+"");
    		ht.get(128);
    	}catch (KeyNotFoundException e) {
    		fail("KeyNotFoundException at get(128)");
			}catch(Exception e) {
				fail("Should not throw exception here");
			}
    }
}
