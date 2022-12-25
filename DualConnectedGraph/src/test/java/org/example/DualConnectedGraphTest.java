package org.example;

import com.mathsystem.api.graph.GraphFactory;
import com.mathsystem.api.graph.model.Graph;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;

public class DualConnectedGraphTest {

    @Test
    void Home() throws FileNotFoundException {
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph1.txt"));

        DualConnectedGraph GraphTest = new DualConnectedGraph();
        assertThat(GraphTest.execute(testGraph)).isEqualTo(false);
    }

    @Test
    void DualHome() throws FileNotFoundException {
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph2.txt"));

        DualConnectedGraph GraphTest = new DualConnectedGraph();
        assertThat(GraphTest.execute(testGraph)).isEqualTo(true);
    }

    @Test
    void Pine() throws FileNotFoundException {
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph3.txt"));

        DualConnectedGraph GraphTest = new DualConnectedGraph();
        assertThat(GraphTest.execute(testGraph)).isEqualTo(false);
    }

    @Test
    void DualPine() throws FileNotFoundException {
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph4.txt"));

        DualConnectedGraph GraphTest = new DualConnectedGraph();
        assertThat(GraphTest.execute(testGraph)).isEqualTo(true);
    }
    @Test
    void Bridge() throws FileNotFoundException {
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph5.txt"));

        DualConnectedGraph GraphTest = new DualConnectedGraph();
        assertThat(GraphTest.execute(testGraph)).isEqualTo(false);
    }
    @Test
    void DualBridge() throws FileNotFoundException {
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph6.txt"));

        DualConnectedGraph GraphTest = new DualConnectedGraph();
        assertThat(GraphTest.execute(testGraph)).isEqualTo(true);
    }
    @Test
    void SmallPine() throws FileNotFoundException {
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph7.txt"));

        DualConnectedGraph GraphTest = new DualConnectedGraph();
        assertThat(GraphTest.execute(testGraph)).isEqualTo(false);
    }
    @Test
    void VertiCe() throws FileNotFoundException {
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph8.txt"));

        DualConnectedGraph GraphTest = new DualConnectedGraph();
        assertThat(GraphTest.execute(testGraph)).isEqualTo(true);
    }
    @Test
    void Poor() throws FileNotFoundException {
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph9.txt"));

        DualConnectedGraph GraphTest = new DualConnectedGraph();
        assertThat(GraphTest.execute(testGraph)).isEqualTo(false);
    }
    @Test
    void Components() throws FileNotFoundException {
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph10.txt"));

        DualConnectedGraph GraphTest = new DualConnectedGraph();
        assertThat(GraphTest.execute(testGraph)).isEqualTo(false);
    }
}
