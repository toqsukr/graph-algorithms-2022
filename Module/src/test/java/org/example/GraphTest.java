package org.example;

import com.mathsystem.api.graph.GraphFactory;
import com.mathsystem.api.graph.model.Graph;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;

public class GraphTest {

    @Test
    void emptyGraph() throws FileNotFoundException {
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph (1).txt"));

        CheckDegrees GraphTest = new CheckDegrees();
        assertThat(GraphTest.execute(testGraph)).isEqualTo(true);
    }

    @Test
    void nonConnectedGraphF() throws FileNotFoundException {
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph (2).txt"));

        CheckDegrees GraphTest = new CheckDegrees();
        assertThat(GraphTest.execute(testGraph)).isEqualTo(false);
    }

    @Test
    void nonConnectedGraphT() throws FileNotFoundException {
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph (3).txt"));

        CheckDegrees GraphTest = new CheckDegrees();
        assertThat(GraphTest.execute(testGraph)).isEqualTo(true);
    }

    @Test
    void completeGraph2() throws FileNotFoundException {
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph (4).txt"));

        CheckDegrees GraphTest = new CheckDegrees();
        assertThat(GraphTest.execute(testGraph)).isEqualTo(true);
    }
    @Test
    void orientedGraph() throws FileNotFoundException {
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph (5).txt"));

        CheckDegrees GraphTest = new CheckDegrees();
        assertThat(GraphTest.execute(testGraph)).isEqualTo(false);
    }
    @Test
    void completeGraph4Equal() throws FileNotFoundException {
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph (6).txt"));

        CheckDegrees GraphTest = new CheckDegrees();
        assertThat(GraphTest.execute(testGraph)).isEqualTo(true);
    }
    @Test
    void undirectedPainted() throws FileNotFoundException {
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph (7).txt"));

        CheckDegrees GraphTest = new CheckDegrees();
        assertThat(GraphTest.execute(testGraph)).isEqualTo(true);
    }
    @Test
    void paintedCycle() throws FileNotFoundException {
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph (8).txt"));

        CheckDegrees GraphTest = new CheckDegrees();
        assertThat(GraphTest.execute(testGraph)).isEqualTo(false);
    }
    @Test
    void connectivityComp4Different() throws FileNotFoundException {
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph (9).txt"));

        CheckDegrees GraphTest = new CheckDegrees();
        assertThat(GraphTest.execute(testGraph)).isEqualTo(false);
    }
    @Test
    void fullGraph3Color() throws FileNotFoundException {
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph (10).txt"));

        CheckDegrees GraphTest = new CheckDegrees();
        assertThat(GraphTest.execute(testGraph)).isEqualTo(true);
    }
}
