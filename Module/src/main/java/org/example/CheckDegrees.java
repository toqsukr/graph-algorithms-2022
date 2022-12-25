package org.example;

import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.oldmodel.Vertex;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CheckDegrees implements GraphProperty {
    @Override
    public boolean execute(Graph abstractGraph)
    {

        Map<UUID, Integer> NeighboursCount = new HashMap<>();
        SearchNeighbours(abstractGraph, NeighboursCount);


        boolean flag = true;
        for (UUID vertex : abstractGraph.getVertices().keySet())
        {
            try
            {
                if (abstractGraph.getVertices().get(vertex).getWeight() != NeighboursCount.get(vertex) && Integer.valueOf(abstractGraph.getVertices().get(vertex).getLabel()) != NeighboursCount.get(vertex))
                {
                    flag = false;
                }
            } catch (NumberFormatException e)
            {
                flag = false;
                break;
            }

        }


        return flag;
    }

    private void SearchNeighbours(Graph graph, Map<UUID, Integer> NeighboursCount)
    {
        for (UUID vertex : graph.getVertices().keySet())
        {
            NeighboursCount.put(vertex, 0);
        }
        for (Edge edge : graph.getEdges())
        {
            UUID vertex1 = edge.getFromV();
            UUID vertex2 = edge.getToV();
            int value = NeighboursCount.get(vertex1);
            NeighboursCount.put(vertex1, value + 1);
            value = NeighboursCount.get(vertex2);
            NeighboursCount.put(vertex2, value + 1);
        }
    }

}