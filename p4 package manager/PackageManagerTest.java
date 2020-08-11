//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Course:          COMP SCI 400 SPRING 2020
// Author:          Yucheng Qiu
// Email:           yqiu56@wisc.edu
// Lecture Number:  001
// Description:     Tests for package manager class.
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;



/** TODO: add class header comments here*/
public class PackageManagerTest{

    // TODO: add other fields that will be used by multiple tests
		PackageManager MyM;
    // TODO: add code that runs before each test method
    @Before
    public void setUp() throws Exception {
    	 MyM = new PackageManager();
    }

    // TODO: add code that runs after each test method
    @After
    public void tearDown() throws Exception {

    }
    
    @Test
    public void test1_testCycle() {
    	try {
    		MyM.constructGraph("cyclic.json");
    		MyM.getInstallationOrderForAllPackages();
    		fail("No Cyclic Exception");
    	}catch (FileNotFoundException e) {
				fail("FileNotFound.");
			}catch (ParseException e) {
				fail("the given json cannot be parsed ");
			}catch(IOException e) {
				fail("the give file cannot be read");
			}catch(CycleException e) {
			}
    	
    }
    @Test
    public void test2_testgetInstallationOrderForAllPackages() {
    	try {
				MyM.constructGraph("shared_dependencies.json");
				
				System.out.println(MyM.getInstallationOrderForAllPackages().toString());
			}catch (FileNotFoundException e) {
				fail("FileNotFound.");
			}catch (ParseException e) {
				fail("the given json cannot be parsed ");
			}catch(IOException e) {
				fail("the give file cannot be read");
			}catch(CycleException e) {
				fail("Should Not be Cyclic");
			} 
    }
    
    @Test
    public void test3_testGetInstallOfOnePKG() {
    	try {
				MyM.constructGraph("shared_dependencies.json");
				System.out.println(MyM.getInstallationOrder("A").toString());
			}catch (FileNotFoundException e) {
				fail("FileNotFound.");
			}catch (ParseException e) {
				fail("the given json cannot be parsed ");
			}catch(IOException e) {
				fail("the give file cannot be read");
			}catch(CycleException e) {
				fail("Should Not be Cyclic");
			}catch(PackageNotFoundException e) {
				fail("PKG not found");
			}
    }
    
    @Test
    public void test4_testToInstall() {
    	try {
    		MyM.constructGraph("valid.json");
    		System.out.println(MyM.toInstall("E", "C"));
    	}catch (FileNotFoundException e) {
				fail("FileNotFound.");
			}catch (ParseException e) {
				fail("the given json cannot be parsed ");
			}catch(IOException e) {
				fail("the give file cannot be read");
			}catch(CycleException e) {
				fail("Should Not be Cyclic");
			}catch(PackageNotFoundException e) {
				fail("PKG not found");
			}
    }
 }
    	
