package org.example;

import com.mathsystem.api.graph.GraphFactory;
import com.mathsystem.api.graph.model.Graph;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;

public class GraphTestTest {

    @Test
    void TwoLinked() throws FileNotFoundException {
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/two_linked.txt"));

        GraphDvusvaz GraphTest = new GraphDvusvaz();
        assertThat(GraphTest.execute(testGraph)).isEqualTo(true);
    }

    @Test
    void TwoAngle() throws FileNotFoundException {
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph_two_angle.txt"));

        GraphDvusvaz GraphTest = new GraphDvusvaz();
        assertThat(GraphTest.execute(testGraph)).isEqualTo(false);
    }

    @Test
    void TwoVertices() throws FileNotFoundException {
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph_on_two_vertices.txt"));

        GraphDvusvaz GraphTest = new GraphDvusvaz();
        assertThat(GraphTest.execute(testGraph)).isEqualTo(false);
    }

    @Test
    void NotTwoLinked() throws FileNotFoundException {
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/not_two_linked.txt"));

        GraphDvusvaz GraphTest = new GraphDvusvaz();
        assertThat(GraphTest.execute(testGraph)).isEqualTo(false);
    }

    @Test
    void NotConnected() throws FileNotFoundException {
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/not_connected.txt"));

        GraphDvusvaz GraphTest = new GraphDvusvaz();
        assertThat(GraphTest.execute(testGraph)).isEqualTo(false);
    }

    @Test
    void GraphAngles() throws FileNotFoundException {
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph_angle.txt"));

        GraphDvusvaz GraphTest = new GraphDvusvaz();
        assertThat(GraphTest.execute(testGraph)).isEqualTo(false);
    }
}
