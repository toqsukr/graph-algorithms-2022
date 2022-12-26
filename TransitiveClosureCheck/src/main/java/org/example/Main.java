package org.example;

import com.mathsystem.api.graph.GraphFactory;
import com.mathsystem.api.graph.model.Graph;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File s = new File("src/main/resources/eightVerticesFalseTransitiveClosureCheck/eightVerticesFalseTransitiveClosureCheck.txt");
        Graph graph = GraphFactory.loadGraphFromFile(s);
        TransitiveClosureCheck graphLeaves = new TransitiveClosureCheck();
        System.out.println(graphLeaves.execute(graph));
    }
}