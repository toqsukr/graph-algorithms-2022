package org.example;

import java.util.*;

import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.graph.repository.GraphType;
import com.mathsystem.domain.plugin.plugintype.GraphCharacteristic;

public class TransitiveClosureCheck implements GraphCharacteristic {
    @Override
    public Integer execute(Graph abstractGraph) {
        Map<UUID, Map<UUID, Integer>> adjacencyMatrixSource = new HashMap<>();
        createAdjacencyMatrix(abstractGraph, adjacencyMatrixSource);

        boolean isTransitiveClosure = true;
        Map<UUID, Integer> vertexMark = new HashMap<UUID, Integer>();
        for(UUID vertex: abstractGraph.getVertices().keySet()) {
            vertexMark.put(vertex, 0);
        }
        Map<UUID, Vertex> oldGraphVertices = new HashMap<UUID, Vertex>();
        List<Edge> oldGraphEdges = new ArrayList<Edge>();
        Map<UUID, Vertex> lastGraphVertices = new HashMap<UUID, Vertex>();
        List<Edge> lastGraphEdges = new ArrayList<Edge>();

        UUID start = new ArrayList<>(abstractGraph.getVertices().keySet()).get(0);
        DFS(abstractGraph, start, vertexMark, oldGraphVertices, adjacencyMatrixSource);
        searchEdges(abstractGraph, adjacencyMatrixSource, oldGraphVertices, oldGraphEdges);

        Graph oldGraph = new Graph(abstractGraph.getDirectType(), oldGraphVertices.size(), oldGraphEdges.size(), oldGraphVertices, oldGraphEdges);
        Map<UUID, Map<UUID, Integer>> adjacencyMatrixOld = new HashMap<>();
        createAdjacencyMatrix(oldGraph, adjacencyMatrixOld);
        UUID otherStart = null;

        for(UUID vertex: abstractGraph.getVertices().keySet()) {
            if(oldGraphVertices.get(vertex) == null) {
                otherStart = vertex;
                break;
            }
        }
        DFS(abstractGraph, otherStart, vertexMark, lastGraphVertices, adjacencyMatrixSource);
        searchEdges(abstractGraph, adjacencyMatrixSource, lastGraphVertices, lastGraphEdges);


        Graph lastGraph = new Graph(abstractGraph.getDirectType(), lastGraphVertices.size(), lastGraphEdges.size(), lastGraphVertices, lastGraphEdges);
        Map<UUID, Map<UUID, Integer>> adjacencyMatrixLast = new HashMap<>();
        createAdjacencyMatrix(lastGraph, adjacencyMatrixLast);

        algorithmWarshall(oldGraphEdges.size() > lastGraphEdges.size() ? adjacencyMatrixLast : adjacencyMatrixOld);
//        for(UUID v1: adjacencyMatrixOld.keySet()) {
//            for(UUID v2: adjacencyMatrixOld.keySet()) {
//                System.out.print(adjacencyMatrixOld.get(v1).get(v2));
//            }
//            System.out.println(" ");
//        }
//        System.out.println(" ");
//        for(UUID v1: adjacencyMatrixLast.keySet()) {
//            for(UUID v2: adjacencyMatrixLast.keySet()) {
//                System.out.print(adjacencyMatrixLast.get(v1).get(v2));
//            }
//            System.out.println(" ");
//
//        }
        int[][] matrixOld = new int[adjacencyMatrixOld.size()][adjacencyMatrixOld.size()];
        int[][] matrixLast = new int[adjacencyMatrixLast.size()][adjacencyMatrixLast.size()];
        if(adjacencyMatrixOld.size() != adjacencyMatrixLast.size())     isTransitiveClosure = false;
        else {
            int i = 0;
            int j = 0;
            for(UUID vertixOld1: adjacencyMatrixOld.keySet()) {
                for(UUID vertixOld2: adjacencyMatrixOld.keySet()) {
                    matrixOld[i][j % adjacencyMatrixOld.size()] = adjacencyMatrixOld.get(vertixOld1).get(vertixOld2);
                    j++;
                }
                if(i % adjacencyMatrixOld.size() == 0)  i++;
            }
            i = 0;
            j = 0;
            for(UUID vertixLast1: adjacencyMatrixLast.keySet()) {
                for(UUID vertixLast2: adjacencyMatrixLast.keySet()) {
                    matrixLast[i][j % adjacencyMatrixLast.size()] = adjacencyMatrixLast.get(vertixLast1).get(vertixLast2);
                    j++;
                }
                if(i % adjacencyMatrixOld.size() == 0)  i++;

            }
            for(int v1 = 0;v1 < adjacencyMatrixOld.size();v1++) {
                for(int v2 = 0;v2 < adjacencyMatrixOld.size();v2++) {
                    if(matrixOld[v1][v2] != matrixLast[v1][v2]) {
                        isTransitiveClosure = false;
                        break;
                    }
                }
                if(!isTransitiveClosure)    break;
            }
        }
        return isTransitiveClosure ? 1 : 0;
    }


    private void searchEdges(Graph graph, Map<UUID, Map<UUID, Integer>> adjacencyMatrix, Map<UUID, Vertex> GraphVertices, List<Edge> GraphEdges) {
        try {
            for (UUID vertex1 : GraphVertices.keySet()) {
                for (UUID vertex2 : GraphVertices.keySet()) {
                    if (adjacencyMatrix.get(vertex1).get(vertex2) == 1 && vertex1 != vertex2) {
                        for (Edge edge : graph.getEdges()) {
                            if (edge.getFromV().equals(vertex1) && edge.getToV().equals(vertex2)) {
                                GraphEdges.add(edge);
                                break;
                            }
                        }
                    }
                }
            }
        } catch (NullPointerException e) {
            System.err.println("Неверный формат ввода графа!");
        }
    }

    private void algorithmWarshall(Map <UUID, Map<UUID, Integer>> adjacencyMatrix) {
        String[] bitArray = new String[adjacencyMatrix.size()];
        int countVertix1 = 0, countVertix2 = 0;
        for(UUID vertix1: adjacencyMatrix.keySet()) {
            bitArray[countVertix1] = " ";
            for(UUID vertix2: adjacencyMatrix.keySet()) {
                bitArray[countVertix1] += adjacencyMatrix.get(vertix1).get(vertix2) == 1 ? '1' : '0';
            }
            bitArray[countVertix1] = bitArray[countVertix1].substring(1);
            countVertix1++;
        }

            for(int i = 0;i < adjacencyMatrix.size();i++) {
                for(String bitOtherString: bitArray) {
                    bitArray[i] = Integer.toBinaryString((Integer.parseInt(bitArray[i], 2) | Integer.parseInt(bitOtherString, 2)));
                }
            }


        countVertix1 = 0;
        for(UUID vertix1: adjacencyMatrix.keySet()) {
            for(UUID vertix2: adjacencyMatrix.keySet()) {
                adjacencyMatrix.get(vertix1).put(vertix2, Integer.parseInt(bitArray[countVertix1].charAt(countVertix2 % adjacencyMatrix.size()) + ""));
                if(vertix1.equals(vertix2)) adjacencyMatrix.get(vertix1).put(vertix2, 0);
                countVertix2++;
            }
            if(countVertix1 % adjacencyMatrix.size() == 0)  countVertix1++;
        }


    }

    private void DFS(Graph graph, UUID vertex1ID, Map<UUID, Integer> vertexMark, Map<UUID, Vertex> oldGraphVertices, Map<UUID, Map<UUID, Integer>> adjacencyMatrixSource) {
            oldGraphVertices.put(vertex1ID, graph.getVertices().get(vertex1ID));
            vertexMark.put(vertex1ID, 1);
            for(UUID vertex2ID : graph.getVertices().keySet()) {
                if(vertexMark.get(vertex2ID) != 1 && adjacencyMatrixSource.get(vertex1ID).get(vertex2ID) == 1) {
                    DFS(graph, vertex2ID, vertexMark, oldGraphVertices, adjacencyMatrixSource);
                }
            }
    }

    private void createAdjacencyMatrix(Graph graph, Map<UUID, Map<UUID, Integer>> adjacencyMatrix) {
        for (UUID vertex1 : graph.getVertices().keySet()) {
            Map<UUID, Integer> vertexes = new HashMap<>();

            for (UUID vertex2 : graph.getVertices().keySet()) {
                vertexes.put(vertex2, 0);
            }

            adjacencyMatrix.put(vertex1, vertexes);
        }

        for (Edge edge : graph.getEdges()) {
            UUID vertex1 = edge.getFromV();
            UUID vertex2 = edge.getToV();

            adjacencyMatrix.get(vertex1).put(vertex2, 1);
            if (graph.getDirectType() == GraphType.UNDIRECTED) {
                adjacencyMatrix.get(vertex2).put(vertex1, 1);
            }
        }
    }

}