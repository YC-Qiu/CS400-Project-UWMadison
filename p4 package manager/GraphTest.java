//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Course:          COMP SCI 400 SPRING 2020
// Author:          Yucheng Qiu
// Email:           yqiu56@wisc.edu
// Lecture Number:  001
// Description:     Tests for Graph class.
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;



/** TODO: add class header comments here*/
public class GraphTest{

    // TODO: add other fields that will be used by multiple tests
		Graph g;
    // TODO: add code that runs before each test method
    @Before
    public void setUp() throws Exception {
    	 g = new Graph();
    }

    // TODO: add code that runs after each test method
    @After
    public void tearDown() throws Exception {

    }
    
    @Test
    public void test1_add30VerticeAndGetNumber() {
    	for(int i = 0; i<30;i++) {
    		g.addVertex(""+i);
    	}
    	assert(g.order()==30);
    	assert(g.size()==0);
    }
    
    @Test 
    public void test2_addthenremoveEdge() {
    	g.addEdge("1", "2");
    	assert(g.size()==1);
    	g.removeEdge("1", "2");
    	assert(g.size()==0);
    }
    
    @Test
    public void test3_addnullvertex() {
    	g.addVertex("1");
    	g.addVertex(null);
    	assert(g.order()==1);
    }
}