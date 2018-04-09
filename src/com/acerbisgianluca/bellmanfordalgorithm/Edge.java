/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.acerbisgianluca.bellmanfordalgorithm;

/**
 *
 * @author Acerbis Gianluca
 */
public class Edge {

    private String start;
    private String end;
    private int weight;

    public Edge(String start, String end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean equals(Edge e) {
        return this.start.equals(e.start) && this.end.equals(e.end);
    }    

    @Override
    public String toString() {
        return start + "\t" + end + "\t" + weight;
    }
    
    
}
