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

        CheckDegrees GraphTest = new CheckDegrees();
        assertThat(GraphTest.execute(testGraph)).isEqualTo(true);
    }

    @Test
    void MyTest2() throws FileNotFoundException {
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph (2).txt"));

        CheckDegrees GraphTest = new CheckDegrees();
        assertThat(GraphTest.execute(testGraph)).isEqualTo(false);
    }

    @Test
    void MyTest3() throws FileNotFoundException {
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph (3).txt"));

        CheckDegrees GraphTest = new CheckDegrees();
        assertThat(GraphTest.execute(testGraph)).isEqualTo(true);
    }

    @Test
    void MyTest4() throws FileNotFoundException {
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph (4).txt"));

        CheckDegrees GraphTest = new CheckDegrees();
        assertThat(GraphTest.execute(testGraph)).isEqualTo(true);
    }
    @Test
    void MyTest5() throws FileNotFoundException {
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph (5).txt"));

        CheckDegrees GraphTest = new CheckDegrees();
        assertThat(GraphTest.execute(testGraph)).isEqualTo(true);
    }
    @Test
    void MyTest6() throws FileNotFoundException {
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph (6).txt"));

        CheckDegrees GraphTest = new CheckDegrees();
        assertThat(GraphTest.execute(testGraph)).isEqualTo(false);
    }
    @Test
    void MyTest7() throws FileNotFoundException {
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph (7).txt"));

        CheckDegrees GraphTest = new CheckDegrees();
        assertThat(GraphTest.execute(testGraph)).isEqualTo(false);
    }
    @Test
    void MyTest8() throws FileNotFoundException {
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph (8).txt"));

        CheckDegrees GraphTest = new CheckDegrees();
        assertThat(GraphTest.execute(testGraph)).isEqualTo(false);
    }
    @Test
    void MyTest9() throws FileNotFoundException {
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph (9).txt"));

        CheckDegrees GraphTest = new CheckDegrees();
        assertThat(GraphTest.execute(testGraph)).isEqualTo(false);
    }
    @Test
    void MyTest10() throws FileNotFoundException {
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph (10).txt"));

        CheckDegrees GraphTest = new CheckDegrees();
        assertThat(GraphTest.execute(testGraph)).isEqualTo(true);
    }
}
