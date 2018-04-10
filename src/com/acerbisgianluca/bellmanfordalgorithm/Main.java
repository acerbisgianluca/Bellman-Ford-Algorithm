/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.acerbisgianluca.bellmanfordalgorithm;

import com.acerbisgianluca.filemanager.FileManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Acerbis Gianluca
 */
public class Main {
    
    private static Graph g;
    private static int nNodes;
    private static String startVertex;
    private static List<String> nodes;
    
    public static void main(String[] args){
        try {
            getNodesFromFile();
            g = new Graph(nNodes);
            inizializeNodes();
            getEdgesFromFile();
        } catch (IOException ex) {
            System.out.println("Error reading from file, retry!");
            System.exit(0);
        }
        
        bellmanFord(g, startVertex);
    }
    
    public static void getNodesFromFile() throws IOException{
        nodes = new ArrayList();
        for(String s : FileManager.fileToString("./input/nodes.txt"))
            if(!s.equals("\n"))
                nodes.add(s.replace("\n", ""));

        nNodes = nodes.size();
        startVertex = nodes.get(0);
    }
    
    public static void getEdgesFromFile() throws IOException{
        for(String s : FileManager.fileToString("./input/edges.txt")){
            String[] split = s.split(",");
            
            g.addEdge(split[0], split[1], Integer.parseInt(split[2]));
            /*
             * if last value is "*" the weight is the same
             * if last value is not "-" the weight is the last value
             * if last value is "-" the edge is not bilateral
             */
            if(split[3].contains("*"))
                g.addEdge(split[1], split[0], Integer.parseInt(split[2]));
            else if(!split[3].contains("-"))
                g.addEdge(split[1], split[0], Integer.parseInt(split[3]));
        }
    }

    private static void bellmanFord(Graph g, String startVertex) {
        Map<String, Float> distances = new HashMap<>();
        Map<String, String> predecessors = new HashMap<>();
        
        //initialize arrays
        for(String node : nodes){
            distances.put(node, Float.MAX_VALUE);
            predecessors.put(node, "me");
        }
        distances.replace(startVertex, 0f);
        
        //relax all edges v - 1 times repeatedly
        for(int i = 1; i < g.getnNodes() - 1; i++){
            for(int j = 0; j < g.getnNodes(); j++){
                Iterator<Edge> it = g.neighbours(nodes.get(j)).iterator();
                while(it.hasNext()){
                    Edge e = it.next();
                    if(distances.get(e.getStart()) + e.getWeight() < distances.get(e.getEnd())){
                        distances.replace(e.getEnd(), distances.get(e.getStart()) + e.getWeight());
                        predecessors.replace(e.getEnd(), e.getStart());
                    }
                }
            }
        }
        
        //check for negative-weight circles
        for (int i = 0; i < g.getnNodes(); i++) {
            Iterator<Edge> it = g.neighbours(nodes.get(i)).iterator();
            while (it.hasNext()) {
                Edge e = it.next();
                if(distances.get(e.getStart()) + e.getWeight() < distances.get(e.getEnd())){
                    System.out.println("Graph contains negative-weight circle!");
                    return;
                }
            }
        }
        
        //write output to file and console
        String out = "Node\tDistance\tPredecessor\n";
        
        System.out.print(out);
        for (int i = 0; i < g.getnNodes(); i++){
            out += (nodes.get(i) + "\t" + distances.get(nodes.get(i)) + "\t\t" + predecessors.get(nodes.get(i)) + "\n");
            System.out.print(nodes.get(i) + "\t" + distances.get(nodes.get(i)) + "\t\t" + predecessors.get(nodes.get(i)) + "\n");
        }
        
        try {
            FileManager.stringToFile(out, "./output/result.txt");
        } catch (IOException ex) {
            System.out.println("Error writing to file, create a directory called \"output\" if it doesn't exist!");
            System.exit(0);
        }
    }

    private static void inizializeNodes() {
        for(String s : nodes){
            g.putEmptyEdge(s.replace("\n", ""));
        }
    }
}
