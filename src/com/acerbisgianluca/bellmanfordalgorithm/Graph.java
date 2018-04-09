/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.acerbisgianluca.bellmanfordalgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Acerbis Gianluca
 */
public class Graph {

    private final int nNodes;
    private final Map<String,List<Edge>> nodes;

    public Graph(int nNodes) {
        this.nNodes = nNodes;
        this.nodes = new HashMap();
    }
    
    public void addEdge(String start, String end, int weight){
        nodes.get(start).add(new Edge(start, end, weight));            
    }
    
    public List<Edge> neighbours(String start){
        return nodes.get(start);
    }

    public int getnNodes() {
        return nNodes;
    }

    public Map<String, List<Edge>> getNodes() {
        return nodes;
    }

    public void putEmptyEdge(String name) {
        nodes.put(name, new ArrayList());
    }
    
}
