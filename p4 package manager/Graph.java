//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Course:          COMP SCI 400 SPRING 2020
// Author:          Yucheng Qiu
// Email:           yqiu56@wisc.edu
// Lecture Number:  001
// Description:     My Graph class.
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;



/**
 * Filename:   Graph.java
 * Project:    p4
 * Authors:    
 * 
 * Directed and unweighted graph implementation
 */

public class Graph implements GraphADT {
	
	private class Graphnode<T>{
		private T data;
		private List<Graphnode<T>> neighbors;
		public Graphnode(T data) {
			this.data = data;
			neighbors = new ArrayList<Graphnode<T>>();
		}
		
	}
	
	private ArrayList<Graphnode<String>> VertexList;
	private Hashtable<String,Integer> NodeTable;
	//boolean[][] edgeMatrix;
	
	private int NumOfNodes;
	private int NumOfEdges;

	public Graph() {
		NodeTable = new Hashtable<String, Integer>();
		VertexList = new ArrayList<Graphnode<String>>();
		NumOfEdges = 0;
		NumOfNodes = 0;
	}

	/**
	 * @see GraphADT#addVertex(java.lang.String)
	 */
	public void addVertex(String vertex) {
		if(vertex == null) return;
		// If vertex already exists
		if(containVertex(vertex)) return;
		Graphnode<String> newVertex = new Graphnode<String>(vertex);
		VertexList.add(newVertex);
		NodeTable.put(vertex, NumOfNodes);
		NumOfNodes++;
	}

	/**
	 * @see GraphADT#removeVertex(java.lang.String)
	 */
	public void removeVertex(String vertex) {
		if(vertex == null) return;
		if(!containVertex(vertex)) return;
		
		int index = NodeTable.get(vertex);
		Graphnode<String> node = VertexList.get(index);
		NumOfEdges -= node.neighbors.size();
		node.neighbors.clear();
		NumOfNodes--;
		
		for(Graphnode<String> x : VertexList) {
			for(Graphnode<String> xNbr :x.neighbors) {
				if(xNbr.data.equals(vertex)) {
					x.neighbors.remove(xNbr);
					NumOfEdges--;
				}
			}
		}
		
	}

	/**
	 * @see GraphADT#addEdge(java.lang.String, java.lang.String)
	 */
	public void addEdge(String vertex1, String vertex2) {
		if(vertex1==null || vertex2 ==null) return;
		if(!containVertex(vertex1)) addVertex(vertex1);
		if(!containVertex(vertex2)) addVertex(vertex2);
		Graphnode<String> Vstart = VertexList.get(NodeTable.get(vertex1));
		Graphnode<String> Vend = VertexList.get(NodeTable.get(vertex2));
		Vstart.neighbors.add(Vend);
		NumOfEdges++;
	}

	/**
	 * @see GraphADT#removeEdge(java.lang.String, java.lang.String)
	 */
	public void removeEdge(String vertex1, String vertex2) {
		if(vertex1==null || vertex2 ==null) return;
		if(!containVertex(vertex1)||!containVertex(vertex2)) return;
		int index1 = NodeTable.get(vertex1);
		Graphnode<String> node1 = VertexList.get(index1);
		int index2 = NodeTable.get(vertex2);
		Graphnode<String> node2 = VertexList.get(index2);
		if(node1.neighbors.contains(node2)) {
			node1.neighbors.remove(node2);
			NumOfEdges--;
		}
		return;
	}

	/**
	 * @see GraphADT#getAllVertices()
	 */
	public Set<String> getAllVertices() {
		Set<String> AllVertices = new HashSet<String>();
		for(Graphnode<String> x :VertexList) {
			AllVertices.add(x.data);
		}
		return AllVertices;
	}

	/**
	 * @see GraphADT#getAdjacentVerticesOf(java.lang.String)
	 */
	public List<String> getAdjacentVerticesOf(String vertex) {
		int index = NodeTable.get(vertex);
		Graphnode<String> node = VertexList.get(index);
		ArrayList<String> Adj = new ArrayList<String>();
		for(Graphnode<String> x : node.neighbors) Adj.add(x.data);
		return Adj;
	}

	/**
	 * @see GraphADT#size()
	 */
	public int size() {
		
		return NumOfEdges;
	}

	/**
	 * @see GraphADT#order()
	 */
	public int order() {
		
		return NumOfNodes;
	}
	
	/**
	 * Search the graph for specific vertex.
	 * @param vertex vertex to search
	 * @return true if the vertex exists
	 */
	private boolean containVertex(String vertex) {
		return NodeTable.containsKey(vertex);
	}

}
