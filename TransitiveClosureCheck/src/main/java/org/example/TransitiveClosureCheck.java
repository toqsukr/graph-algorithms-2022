package org.example;

import java.util.*;

import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.graph.repository.GraphType;
import com.mathsystem.domain.plugin.plugintype.GraphCharacteristic;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

public class TransitiveClosureCheck implements GraphProperty {
    @Override
    public boolean execute(Graph abstractGraph) {
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
        DFS(true, abstractGraph, start, vertexMark, oldGraphVertices, adjacencyMatrixSource);
        searchEdges(abstractGraph, adjacencyMatrixSource, oldGraphVertices, oldGraphEdges);


        Graph oldGraph = new Graph(abstractGraph.getDirectType(), oldGraphVertices.size(), oldGraphEdges.size(), oldGraphVertices, oldGraphEdges);
        Map<UUID, Map<UUID, Integer>> adjacencyMatrixOld = new HashMap<>();
        createAdjacencyMatrix(oldGraph, adjacencyMatrixOld);
        UUID otherStart = null;

        for(UUID vertex: abstractGraph.getVertices().keySet()) {
            if(oldGraphVertices.get(vertex) == null && abstractGraph.getVertices().get(vertex).getLabel().equals(abstractGraph.getVertices().get(start).getLabel())) {
                otherStart = vertex;
                break;
            }
        }
        DFS(false, abstractGraph, otherStart, vertexMark, lastGraphVertices, adjacencyMatrixSource);
        searchEdges(abstractGraph, adjacencyMatrixSource, lastGraphVertices, lastGraphEdges);

        Graph lastGraph = new Graph(abstractGraph.getDirectType(), lastGraphVertices.size(), lastGraphEdges.size(), lastGraphVertices, lastGraphEdges);
        Map<UUID, Map<UUID, Integer>> adjacencyMatrixLast = new HashMap<>();
        createAdjacencyMatrix(lastGraph, adjacencyMatrixLast);

        algorithmWarshall(oldGraphEdges.size() > lastGraphEdges.size() ? adjacencyMatrixLast : adjacencyMatrixOld);

        if(adjacencyMatrixOld.size() != adjacencyMatrixLast.size())     isTransitiveClosure = false;
        else {
            for(UUID vertix1 : adjacencyMatrixOld.keySet()) {
                for(UUID vertix2: adjacencyMatrixOld.keySet()) {
                    for(UUID vertix3: adjacencyMatrixLast.keySet()) {
                        for(UUID vertix4: adjacencyMatrixLast.keySet()) {
                            if(abstractGraph.getVertices().get(vertix3).getLabel().equals(abstractGraph.getVertices().get(vertix1).getLabel()) && abstractGraph.getVertices().get(vertix4).getLabel().equals(abstractGraph.getVertices().get(vertix2).getLabel())) {
                                if(!adjacencyMatrixOld.get(vertix1).get(vertix2).equals(adjacencyMatrixLast.get(vertix3).get(vertix4))) {
                                    isTransitiveClosure = false;
                                    break;
                                }
                            }
                        }
                        if(!isTransitiveClosure)    break;
                    }
                    if(!isTransitiveClosure)    break;
                }
                if(!isTransitiveClosure)    break;
            }
        }
        return isTransitiveClosure;
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
        int[][] bitArray = new int[adjacencyMatrix.size()][adjacencyMatrix.size()];
        int countVertix1 = 0, countVertix2 = 0;
        for(UUID vertix1: adjacencyMatrix.keySet()) {
            countVertix2 = 0;
            for(UUID vertix2: adjacencyMatrix.keySet()) {
                bitArray[countVertix1][countVertix2 % adjacencyMatrix.size()] = adjacencyMatrix.get(vertix1).get(vertix2) == 1 ? 1 : 0;
                countVertix2++;
            }
            countVertix1++;
        }
            for(int i = 0;i < adjacencyMatrix.size();i++) {
                for (int j = 0; j < adjacencyMatrix.size(); j++) {
                    if(bitArray[i][j] == 1) {
                        for(int k = 0;k < adjacencyMatrix.size();k++) {
                            bitArray[i][k] = bitArray[i][k] | bitArray[j][k];
                        }
                    }
                }
            }
        for(int i = 0;i < adjacencyMatrix.size();i++) {
            for (int j = 0; j < adjacencyMatrix.size(); j++) {
                if(bitArray[i][j] == 1) {
                    for(int k = 0;k < adjacencyMatrix.size();k++) {
                        bitArray[i][k] = bitArray[i][k] | bitArray[j][k];
                    }
                }
            }
        }

        countVertix1 = 0;
        countVertix2 = 0;
        for(UUID vertix1: adjacencyMatrix.keySet()) {
            for(UUID vertix2: adjacencyMatrix.keySet()) {
                adjacencyMatrix.get(vertix1).put(vertix2, bitArray[countVertix1][countVertix2 % adjacencyMatrix.size()]);
                if(vertix1.equals(vertix2)) adjacencyMatrix.get(vertix1).put(vertix2, 0);
                countVertix2++;
            }
            countVertix1++;
        }
    }

    private void DFS(boolean first, Graph graph, UUID vertex1ID, Map<UUID, Integer> vertexMark, Map<UUID, Vertex> oldGraphVertices, Map<UUID, Map<UUID, Integer>> adjacencyMatrixSource) {
            oldGraphVertices.put(vertex1ID, graph.getVertices().get(vertex1ID));
            vertexMark.put(vertex1ID, 1);
            for(UUID vertex2ID : graph.getVertices().keySet()) {
                if(vertexMark.get(vertex2ID) != 1 && (adjacencyMatrixSource.get(vertex1ID).get(vertex2ID) == 1 || adjacencyMatrixSource.get(vertex2ID).get(vertex1ID) == 1)) {
                    DFS(first, graph, vertex2ID, vertexMark, oldGraphVertices, adjacencyMatrixSource);
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