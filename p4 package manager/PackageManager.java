//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Course:          COMP SCI 400 SPRING 2020
// Author:          Yucheng Qiu
// Email:           yqiu56@wisc.edu
// Lecture Number:  001
// Description:     My Package manager class.
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Filename:   PackageManager.java
 * Project:    p4
 * Authors:    
 * 
 * PackageManager is used to process json package dependency files
 * and provide function that make that information available to other users.
 * 
 * Each package that depends upon other packages has its own
 * entry in the json file.  
 * 
 * Package dependencies are important when building software, 
 * as you must install packages in an order such that each package 
 * is installed after all of the packages that it depends on 
 * have been installed.
 * 
 * For example: package A depends upon package B,
 * then package B must be installed before package A.
 * 
 * This program will read package information and 
 * provide information about the packages that must be 
 * installed before any given package can be installed.
 * all of the packages in
 * 
 * You may add a main method, but we will test all methods with
 * our own Test classes.
 */

public class PackageManager {
    
    private Graph graph;
    /*
     * Package Manager default no-argument constructor.
     */
    public PackageManager() {
      graph = new Graph();
    }
    
    /**
     * Takes in a file path for a json file and builds the
     * package dependency graph from it. 
     * 
     * @param jsonFilepath the name of json data file with package dependency information
     * @throws FileNotFoundException if file path is incorrect
     * @throws IOException if the give file cannot be read
     * @throws ParseException if the given json cannot be parsed 
     */
    public void constructGraph(String jsonFilepath) throws FileNotFoundException, IOException, ParseException {
    	FileReader reader = new FileReader(jsonFilepath);
    	Object obj = new JSONParser().parse(reader);
    	JSONObject jo = (JSONObject) obj;
    	JSONArray packages = (JSONArray) jo.get("packages");
    	
    	for(int i = 0; i < packages.size(); i++) {
    		JSONObject jsonPkg = (JSONObject) packages.get(i);
    		String name = (String) jsonPkg.get("name");
    		JSONArray dependencies = (JSONArray) jsonPkg.get("dependencies");
    		if(dependencies.isEmpty()) {
    			graph.addVertex(name);
    		}
    		for(int j = 0; j < dependencies.size(); j++) {
    			String Vstart = (String) dependencies.get(j);
    			graph.addEdge(name, Vstart);
    		}
    	}
    }
    
    /**
     * Helper method to get all packages in the graph.
     * 
     * @return Set<String> of all the packages
     */
    public Set<String> getAllPackages() {
        return graph.getAllVertices();
    }
    
    /**
     * Given a package name, returns a list of packages in a
     * valid installation order.  
     * 
     * Valid installation order means that each package is listed 
     * before any packages that depend upon that package.
     * 
     * @return List<String>, order in which the packages have to be installed
     * 
     * @throws CycleException if you encounter a cycle in the graph while finding
     * the installation order for a particular package. Tip: Cycles in some other
     * part of the graph that do not affect the installation order for the 
     * specified package, should not throw this exception.
     * 
     * @throws PackageNotFoundException if the package passed does not exist in the 
     * dependency graph.
     */
    public List<String> getInstallationOrder(String pkg) throws CycleException, PackageNotFoundException {
      ////////////////////////////////////// if pkg not exists
    	boolean PKGExist = false;
    	for(String x : graph.getAllVertices()) {
      	if(x.equals(pkg)) PKGExist = true;
      }
    	if(PKGExist == false) throw new PackageNotFoundException();
    	////////////////////////////////////// if cycle is detected
    	Hashtable<String, Boolean> visited = new Hashtable<String, Boolean>();
    	Hashtable<String, Boolean> Instack = new Hashtable<String, Boolean>();
    	for(String v : graph.getAllVertices()) {
    		visited.put(v, false);
    		Instack.put(v, false);
    	}
    	if(IsCycled(pkg, Instack, visited)) throw new CycleException();
    	////////////////////////////////////// else
    	List<String> OrderList = new ArrayList<String>();
    	InstallOrderOf(pkg, OrderList);
    	return OrderList;
    }
    
    /**
     * A recursive method to detect cycles.
     * @param current current node
     * @param Instack a boolean hashtable for checking nodes if in stack
     * @param visited a boolean hashtable for checking nodes if visited
     * @return true if a cycle is detected
     */
    private boolean IsCycled(String current, Hashtable<String, Boolean> Instack,
    	Hashtable<String, Boolean> visited) {
    	
    	if(Instack.get(current)) return true;
    	if(visited.get(current)) return false;
    	visited.replace(current, false, true);
    	Instack.replace(current, false, true);
    	for(String x : graph.getAdjacentVerticesOf(current)) {
    		if(IsCycled(x, Instack, visited)) return true;
    	}
    	Instack.replace(current, true, false);
    	return false;
    }
    
    /**
     * A recursive method to get the install order.
     * @param pkg package to get
     * @param OrderList a List contains the order
     */
    private void InstallOrderOf(String pkg,List<String> OrderList) {
    	
    	for(String x: graph.getAdjacentVerticesOf(pkg)) {
    		InstallOrderOf(x, OrderList);
    	}
    	if(!OrderList.contains(pkg))OrderList.add(pkg);
    	
    	
    }
    /**
     * Given two packages - one to be installed and the other installed, 
     * return a List of the packages that need to be newly installed. 
     * 
     * For example, refer to shared_dependecies.json - toInstall("A","B") 
     * If package A needs to be installed and packageB is already installed, 
     * return the list ["A", "C"] since D will have been installed when 
     * B was previously installed.
     * 
     * @return List<String>, packages that need to be newly installed.
     * 
     * @throws CycleException if you encounter a cycle in the graph while finding
     * the dependencies of the given packages. If there is a cycle in some other
     * part of the graph that doesn't affect the parsing of these dependencies, 
     * cycle exception should not be thrown.
     * 
     * @throws PackageNotFoundException if any of the packages passed 
     * do not exist in the dependency graph.
     */
    public List<String> toInstall(String newPkg, String installedPkg) throws CycleException, PackageNotFoundException {
    	//////////////////////////////////////if pkg not exists
    	boolean PKGExist1 = false,PKGExist2 = false;
    	for(String x : graph.getAllVertices()) {
    		if(x.equals(newPkg)) PKGExist1 = true;
    		if(x.equals(installedPkg)) PKGExist2 = true;
    	}
    	if(!PKGExist1 || !PKGExist2) throw new PackageNotFoundException();  
    	//////////////////////////////////////if cycle is detected
    	Hashtable<String, Boolean> visited = new Hashtable<String, Boolean>();
    	Hashtable<String, Boolean> Instack = new Hashtable<String, Boolean>();
    	for(String v:graph.getAllVertices()) {
    		visited.put(v, false);
    		Instack.put(v, false);
    	}
    	if(IsCycled(newPkg, Instack, visited)) throw new CycleException();
    	/////////////////////////////////////else
    	List<String> order = getInstallationOrder(newPkg);
    	List<String> installed = getInstallationOrder(installedPkg);
    	for(String str1 : installed) {
    		if(order.contains(str1)) order.remove(str1);
    	}
    	return order;
    }
    
    /**
     * Return a valid global installation order of all the packages in the 
     * dependency graph.
     * 
     * assumes: no package has been installed and you are required to install 
     * all the packages
     * 
     * returns a valid installation order that will not violate any dependencies
     * 
     * @return List<String>, order in which all the packages have to be installed
     * @throws CycleException if you encounter a cycle in the graph
     */
    public List<String> getInstallationOrderForAllPackages() throws CycleException {
    	//////////////////////////////////////if cycle is detected
    	
    	for(String x: graph.getAllVertices()) {
    		Hashtable<String, Boolean> visited = new Hashtable<String, Boolean>();
      	Hashtable<String, Boolean> Instack = new Hashtable<String, Boolean>();
      	for(String v : graph.getAllVertices()) {
      		visited.put(v, false);
      		Instack.put(v, false);
      	}
    		if(IsCycled(x, Instack, visited)) throw new CycleException();
    	}
    	/////////////////////////////////////// topo sort
    	return topoSort();
    }
    
    /**
     * Give a plausible solution order of installation by topological sort.
     * @return an arraylist contains all vertices
     */
    private List<String> topoSort() {
    	
    	Queue<String> q = new LinkedList<String>();
    	List<String> order = new ArrayList<String>();
    	Stack<String> s = new Stack<String>();
    	Hashtable<String, Boolean> AtTop = new Hashtable<String, Boolean>();
    	Hashtable<String, Boolean> Visited = new Hashtable<String, Boolean>();
    	for(String x: graph.getAllVertices()) AtTop.put(x, true);
    	for(String x: graph.getAllVertices()) {
    		for(String y:graph.getAdjacentVerticesOf(x)) {
    			if(AtTop.get(y)) AtTop.put(y, false);
    		}
    		Visited.put(x, false);
    	}
    	
    	for(String x: graph.getAllVertices()) {
    		if(AtTop.get(x)) {
    			q.add(x);
    			Visited.replace(x, true);
    		}
    	}
    	
    	while (!q.isEmpty()) {
				String current = q.remove();
				
				for(String str:graph.getAdjacentVerticesOf(current)) {
					if(!Visited.get(str)) q.add(str);
					Visited.put(str,true);
				}
				s.push(current);
				
			}
    	while(!s.isEmpty()) {
    		order.add(s.pop());
    	}
    	return order;
    	
    }
    
    /**
     * Find and return the name of the package with the maximum number of dependencies.
     * 
     * Tip: it's not just the number of dependencies given in the json file.  
     * The number of dependencies includes the dependencies of its dependencies.  
     * But, if a package is listed in multiple places, it is only counted once.
     * 
     * Example: if A depends on B and C, and B depends on C, and C depends on D.  
     * Then,  A has 3 dependencies - B,C and D.
     * 
     * @return String, name of the package with most dependencies.
     * @throws CycleException if you encounter a cycle in the graph
     */
    public String getPackageWithMaxDependencies() throws CycleException {
			/////////////////////////////////////if cycle is detected
    	for(String x: graph.getAllVertices()) {
    		Hashtable<String, Boolean> visited = new Hashtable<String, Boolean>();
      	Hashtable<String, Boolean> Instack = new Hashtable<String, Boolean>();
      	for(String v : graph.getAllVertices()) {
      		visited.put(v, false);
      		Instack.put(v, false);
      	}
    		if(IsCycled(x, Instack, visited)) throw new CycleException();
    	}
    	/////////////////////////////////// else
    	int maxD = -1;
    	String maxPKG = "";
    	for(String x: graph.getAllVertices()) {
    		int temp = getDependencies(x);
    		if(temp > maxD) {
    			maxD = temp;
    			maxPKG = x;
    		}
    	}
    	
    	return maxPKG;
    }
    
    /**
     * A recursive method to get the dependencies of a vertex.
     * @param current current node
     * @return the number of dependencies of current
     */
    private int getDependencies(String current) {
    	
    	if(graph.getAdjacentVerticesOf(current).isEmpty()) return 0;
    	int max = 0;
    	for(String x: graph.getAdjacentVerticesOf(current)) {
    		int temp = getDependencies(x);
    		if( temp > max) max = temp;
    	}
    	return max+1;
    }

    public static void main (String [] args) {
        System.out.println("PackageManager.main()");
        List<String> test1 = new ArrayList<String>();
        test1.add("1");
        String a = "1";
        System.out.println(test1.contains(a));
    }
    
}
