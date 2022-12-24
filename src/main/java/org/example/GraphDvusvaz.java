package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

public class GraphDvusvaz implements GraphProperty {
	private int vertexCount;
	private int edgeCount;
	private boolean[] used;

	public boolean execute(Graph graph) {
		GraphAdjMatrix graphMatrix = new GraphAdjMatrix(graph);
		vertexCount = graphMatrix.matrix.length;
		edgeCount = graph.getEdgeCount();
		used = new boolean[vertexCount];
		Arrays.fill(used, false);

		DFS(graphMatrix, 0, -1, used); //проверка графа на связность
		if (!isAllVisited(used))
			return false;

		for (int i = 0; i < vertexCount; i++) { //"удаляем" по одной точке и проверяем, остался ли граф связным 
			Arrays.fill(used, false);
			used[i] = true; //отмечаем "удаленную" точку, чтобы она не влияла на оценку связности
			DFS(graphMatrix, (i != 0) ? i - 1 : i + 1, i, used);
			if (!isAllVisited(used))
				return false;
		}
		return true;

	}

	private boolean isAllVisited(boolean[] used) { // все ли вершины посещены
		for (boolean use : used) {
			if (!use)
				return false; // Несвязный граф
		}
		return true;
	}

	private void DFS(GraphAdjMatrix matrix, int v, int exc, boolean[] used) { //обход в глубину
		used[v] = true;
		
		if (v != exc) { //является ли точка "удаленной"
			for (int j = 0; j < edgeCount; j++) {
				if (matrix.matrix[v][j] != -1 
						&& matrix.matrix[v][j] != exc 
						&& !used[matrix.matrix[v][j]]) { //если путь существует, точка не "удалена" и не использована
					DFS(matrix, matrix.matrix[v][j], exc, used);
				}
			}
		}
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