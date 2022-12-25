package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import bicon.GraphBicon.GraphAdjMatrix;

public class GraphDvusvaz implements GraphProperty {
	private int vertexCount;
	private int edgeCount;
	private int timer;
	private boolean[] used;
	private boolean dv;
	private int[] tin;
	private int[] fup;

	public boolean execute(Graph graph) {
		GraphAdjMatrix graphMatrix = new GraphAdjMatrix(graph);
		vertexCount = graphMatrix.matrix.length;
		edgeCount = graph.getEdgeCount();
		used = new boolean[vertexCount];
		tin = new int[vertexCount];
		fup = new int[vertexCount];
		timer = 0;
		Arrays.fill(used, false);

		DFS(graphMatrix, 0, -1); //проверка графа на связность
		if (!isAllVisited(used))
			return false;

		return dv;

	}

	private boolean isAllVisited(boolean[] used) { // все ли вершины посещены
		for (boolean use : used) {
			if (!use)
				return false; // Несвязный граф
		}
		return true;
	}

	private void DFS(GraphAdjMatrix matrix, int v, int p) { //обход в глубину
		used[v] = true;
		tin[v] = fup[v] = timer++;
		int children = 0;
		for (int j = 0; j < edgeCount; j++) {
			if (matrix.matrix[v][j] != -1) { //если путь существует
				int to = matrix.matrix[v][j];
				if (to == p) continue;
				if (used[to])
					fup[v] = Math.min(fup[v], tin[to]);
				else {
					DFS(matrix, to, v);
					fup[v] = Math.min(fup[v], fup[to]);
					if (fup[to] >= tin[v] && p!=-1)
						dv= false;
					++children;
				}
			}
		}
		if (p==-1 && children > 1)
			dv = false;
		
	}

	public static class GraphAdjMatrix {
		private final Integer[][] matrix;

		public GraphAdjMatrix(Graph graph) {
			int vertexCount = graph.getVertexCount();
			int edgeCount = graph.getEdgeCount();
			List<Edge> edges = graph.getEdges();
			Map<UUID, Vertex> vertexMap = graph.getVertices();
			List<UUID> vertexes = vertexMap.keySet().stream().toList();// список вершин
			matrix = new Integer[vertexCount][edgeCount];
			for (int row = 0; row < vertexCount; row++) {
				for (int col = 0; col < edgeCount; col++) {
					matrix[row][col] = -1;
				}
			}
			for (int edgeIndex = 0; edgeIndex < edgeCount; ++edgeIndex) {
				Edge e = edges.get(edgeIndex);
				UUID from = e.getFromV();
				UUID to = e.getToV();
				int fromIndex = vertexes.indexOf(from);
				int toIndex = vertexes.indexOf(to);
				matrix[fromIndex][edgeIndex] = toIndex;
				matrix[toIndex][edgeIndex] = fromIndex;

			}
		}

	}

}