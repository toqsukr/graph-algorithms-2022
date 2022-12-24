package org.example;

import com.mathsystem.api.graph.GraphFactory;
import com.mathsystem.api.graph.model.Graph;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File s = new File("src/main/resources/graph.txt");
        Graph graph = GraphFactory.loadGraphFromFile(s);
        CheckDegrees graphproperty = new CheckDegrees();
        System.out.println(graphproperty.execute(graph));
    }
}