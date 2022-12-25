package org.example;

import com.mathsystem.api.graph.GraphFactory;
import com.mathsystem.api.graph.model.Graph;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;

public class GraphTestTest {

    @Test
    void MyTest1() throws FileNotFoundException {
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph (1).txt"));

        GraphTest GraphTest = new GraphTest();
        assertThat(GraphTest.execute(testGraph)).isEqualTo(0);
    }

    @Test
    void MyTest2() throws FileNotFoundException {
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph (2).txt"));

        GraphTest GraphTest = new GraphTest();
        assertThat(GraphTest.execute(testGraph)).isEqualTo(2);
    }

    @Test
    void MyTest3() throws FileNotFoundException {
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph (3).txt"));

        GraphTest GraphTest = new GraphTest();
        assertThat(GraphTest.execute(testGraph)).isEqualTo(3);
    }

    @Test
    void MyTest4() throws FileNotFoundException {
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph (4).txt"));

        GraphTest GraphTest = new GraphTest();
        assertThat(GraphTest.execute(testGraph)).isEqualTo(0);
    }
}
