//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Course:          COMP SCI 400 SPRING 2020
// Author:          Yucheng Qiu
// Email:           yqiu56@wisc.edu
// Lecture Number:  001
// Description:     A super class for other test class.
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A super class for other test class.
 */
abstract class DataStructureADTTest<T extends DataStructureADT<String,String>> {
    
    private T ds;
    
    protected abstract T createInstance();

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
        ds = createInstance();
    }

    @AfterEach
    void tearDown() throws Exception {
        ds = null;
    }

    
    /**
     *  fail if the origin size is not zero.
     */
    @Test
    void test00_empty_ds_size() {
        if (ds.size() != 0)
        fail("data structure should be empty, with size=0, but size="+ds.size());
    }
    
    // TODO: review tests 01 - 04

    /**
     * insert one item and assert the size is one.
     */
    @Test
    void test01_insert_one() {
        String key = "1";
        String value = "one";
        ds.insert(key, value);
        assert (ds.size()==1);
    }
    
    /**
     * insert one key,value pair and remove it, then confirm size is 0.
     */
    @Test
    void test02_insert_remove_one_size_0() {
        String key = "1";
        String value = "one";
        ds.insert(key, value);
        assert (ds.remove(key)); // remove the key
        if (ds.size() != 0)
            fail("data structure should be empty, with size=0, but size="+
            ds.size());
    }
    
    /**
     * insert a few key,value pairs such that one of them has the same key as
     * an earlier one.  Confirm that a RuntimeException is thrown.
     */
    @Test
    void test03_duplicate_exception_thrown() {
        String key = "1";
        String value = "one";
        ds.insert("1", "one");
        ds.insert("2", "two");
        try { 
            ds.insert(key, value); 
            fail("duplicate exception not thrown");
        }
        catch (RuntimeException re) { }
        assert (ds.size()==2);
    }
            
    
    /**
     * insert some key,value pairs, then try removing a key that was not
     * inserted.  Confirm that the return value is false.
     */
    @Test
    void test04_remove_returns_false_when_key_not_present() {
        String key = "1";
        String value = "one";
        ds.insert(key, value);
        assert (!ds.remove("2")); // remove non-existent key is false
        assert (ds.remove(key));  // remove existing key is true
        if (ds.get(key)!=null)
            fail("get("+key+ ") returned " + ds.get(key) + " which should have been removed");
    }

    /**
     * Inserts one item and fails if unable to remove it.
     */
    @Test
    void test05_insert_remove_one() {
    	String key = "1";
    	ds.insert(key, "one");
    	try {
    		boolean KEY_CAN_REMOVE = ds.remove(key);
    		// If key can't be removed
    		if(!KEY_CAN_REMOVE) fail("Key can't be removed.");
			} catch (IllegalArgumentException e) {
				// If IllegalArgumentException, key is null
				fail("IllegalArgumentException Happened, null key detected");
			} catch(Exception e){
				// If other kind of exceptions
				fail("Unexpected exception: "+e.getMessage());
			};
    }
    
    /**
     * Inserts many items and fails if size is not correct
     */
    @Test
    void test06_insert_many_size() {
    	String key1 = "1", key2 = "2";
    	// Insert two different items
    	ds.insert(key1, "one");
    	ds.insert(key2, "two");
    	if(ds.size() != 2) fail("Size Wrong");
    }
    
    /**
     * Inserts two pairs with different keys, but same values; fails if
     * second doesn't insert
     */
    @Test
    void test07_duplicate_values() {
    	String key1 = "1", key2 = "2";
    	ds.insert(key1, "one_two");
    	try {
    		ds.insert(key2, "one_two");
			}catch(IllegalArgumentException e) {
				// If IllegalArgumentException, key is null
				fail("IllegalArgumentException Happened, null key detected");
			}catch (Exception e) {
				// If other kind of exceptions
				fail("Unexpected exception: "+e.getMessage());
			}
    }
    // TODO: add tests 05 - 07 as described in assignment
    
    // TODO: add more tests of your own design to ensure that you can detect implementation that fail
    
    // Tip: consider different numbers of inserts and removes and how different combinations of insert and removes
    
    /**
     * Insert a million items and fail when exception (test max size of DS)
     */
    @Test
    void test08_insert_a_million() {
    	for(int i=0; i<10000; i++) {
    		Integer num = new Integer(i);
    		String key = num.toString();
    		String value = "value: "+key;
    		try {
    			ds.insert(key, value);
    		}catch (Exception e) {
					// if exception is catched
    			fail("Exception found when insert the "+i+"th item");
				}
    	}
    }
    
    /**
     * insert null key and fail if IllegalArgumentException is not found
     */
    @Test
    void test09_insert_null_key() {
    	boolean IllegalArgumentExceptionFound = false;
    	try{
    		ds.insert(null, "1");
    	}catch (IllegalArgumentException e) {
				// Pass
    		IllegalArgumentExceptionFound = true;
			}catch (Exception e) {
				// Other exception
				fail("Other Exception found");
			}finally {
				if(!IllegalArgumentExceptionFound)
					fail("no IllegalArgumentException found");
			}
    }
    
    /**
     * remove a null key and fail if no IllegalArgumentException is thrown.
     */
    @Test
    void test10_remove_a_null_key() {
    	try {
    		ds.insert("1", "one");
    		ds.insert("2", "two");
    		ds.remove(null);
    		fail("no IllegalArgumentException found");
    	}catch (IllegalArgumentException e) {
    	}catch (Exception e) {
    		fail("other exception found");
			}
    }
    
    /**
     * remove a non-exist key and fail if not return false
     */
    @Test
    void test11_remove_a_non_exist_key() {
    	ds.insert("1", "one");
  		ds.insert("2", "two");
  		try {
  			if(ds.remove("3")) fail("return true");
  		}catch (Exception e) {
  			fail("Exception found");
			}
    }
    
    /**
     * get a null key and fail if no IllegalArgumentException found.
     */
    @Test
    void test12_get_null_key() {
    	ds.insert("1", "one");
  		ds.insert("2", "two");
  		try {
  			ds.get(null);
  			fail("not null");
  		}catch(IllegalArgumentException e) {
  		}catch(Exception e){
  			fail("other exception found");
  		}
    }
    
    /**
     * get a null key and fail if not return null
     */
    @Test
    void test13_get_non_exist_key() {
    	ds.insert("1", "one");
  		ds.insert("2", "two");
  		try {
  			if(ds.get("3")!=null) fail("not null");
  		}catch(Exception e) {
  			fail("Exception found");
  		}
    }
    
    /**
     * check the size of the DS after remove some items
     */
    @Test
    void test14_check_size_after_removal() {
    	for(int i=0; i<100; i++) {
    		Integer num = new Integer(i);
    		String key = num.toString();
    		String value = "value: "+key;
    		ds.insert(key, value);
    	}
    	if(ds.size()!=100) fail("wrong size");
    	for(int i=0;i<20;i++) {
    		Integer num = new Integer(i+39);
    		String key = num.toString();
    		ds.remove(key);
    	}
    	if(ds.size()!=80) fail("wrong size");
    }
    
    /**
     * test contain() by a non exist or null key and fail if return true
     */
    @Test
    void test15_contain_a_non_exist_key() {
    	ds.insert("1", "one");
  		ds.insert("2", "two");
  		try {
  			if(ds.contains("3")) fail("return true");
  			if(ds.contains(null)) fail("return true");
  		}catch(Exception e) {
  			fail("Exception found");
  		}
    }
    
    /**
     * test contain() by an exist key and fail if return false
     */
    @Test
    void test16_contain_an_exist_key() {
    	ds.insert("1", "one");
  		ds.insert("2", "two");
  		try {
  			if(!ds.contains("2")) fail("return false");
  		}catch(Exception e) {
  			fail("Exception found");
  		}
    }
    
    /**
     * gety an item and fail if the item's value is equal to given key's
     */
    @Test
    void test17_get_item() {
    	ds.insert("1", "one");
  		ds.insert("2", "two");
  		if(!ds.get("1").equals("one")) fail("value not equal");
    }
}
