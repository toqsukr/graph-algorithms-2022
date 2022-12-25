package org.example;
import java.util.ArrayList;

import com.mathsystem.api.graph.GraphFactory;
import com.mathsystem.api.graph.model.Graph;

import java.io.File;
import java.io.FileNotFoundException;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        //ArrayList V;
        int [][] G;
        File s = new File("src/main/resources/graph10.txt");
        Graph graph = GraphFactory.loadGraphFromFile(s);
        DualConnectedGraph graphLeaves = new DualConnectedGraph();
        System.out.print (graphLeaves.execute(graph));
    }
}

