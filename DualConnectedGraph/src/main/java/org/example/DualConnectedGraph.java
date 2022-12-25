package org.example;

import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.domain.graph.repository.GraphType;
import com.mathsystem.domain.plugin.plugintype.GraphCharacteristic;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.*;

public class DualConnectedGraph implements GraphProperty {
    @Override
    public boolean execute(Graph abstractGraph) {
        int[][] G;
        boolean ans = false;
        if (abstractGraph.getVertexCount() != 0){
            G = createAdjacencyList(abstractGraph);
//            for (int i=0; i<G.length; i++){
//                System.out.print(i);
//                System.out.print(" : ");
//                for (int j=0; j<G[i].length; j++){
//                    System.out.print(G[i][j]);
//                    System.out.print(" ");
//                }
//                System.out.println(" ");
//            }
            Graph_1 test = new Graph_1(G);
            ans = test.main(test);
        }
        else { ans = false;}
        if (abstractGraph.getEdgeCount()==0) {ans=false;}
        if (abstractGraph.getVertexCount()==1) {ans=true;}

        return ans;
    }



    public int [][] createAdjacencyList(Graph graph) {
        List[] G2;
        int [][] G;
        String [] V;
        String [][] E;
        int [][] E1;
        int [] V1;
        int [] sizeArr;
        int nv, k=0, n1=0, ne=0;
        nv = (graph.getVertexCount());
        V = new String[nv];
        k=0;
        for (UUID vertex : graph.getVertices().keySet()){
            V[k] = vertex.toString();
            k++;
        }
        ne=graph.getEdgeCount();
        E = new String[ne][2];

        k=0;
        for (Edge edge : graph.getEdges()) {
            UUID vertex1 = edge.getFromV();
            UUID vertex2 = edge.getToV();
            E[k][0] = vertex1.toString();
            E[k][1] = vertex2.toString();
            k=k+1;
        }

        E1 = new int[ne][2];
        V1 = new int [nv];


        for (int i=0; i<nv; i++){
            V1[i] = i;
            for (int j1 =0; j1<ne; j1++){
                for (int j2 = 0; j2<2; j2++){
                    if (E[j1][j2].equals(V[i])){
                        E1[j1][j2] = i;
                    }
                }
            }
        }

        G2 = new List[nv];
        for (int i =0; i<nv; i++){
            G2[i] = new ArrayList();
        }
        for (int i =0; i< ne; i++){
            G2[E1[i][0]].add(E1[i][1]);
            if (graph.getDirectType()==GraphType.UNDIRECTED){
                G2[E1[i][1]].add(E1[i][0]);
            }
        }
        G = new int[nv][];
        sizeArr = new int[nv];
        for (int i =0; i< nv; i++){
            sizeArr[i] = G2[i].size();
            //System.out.println(G[i]);
        }
        for (int i =0; i<nv; i++){
            G[i] = new int[sizeArr[i]];
        }
        for (int i=0; i<nv; i++){
            for (int j=0; j<sizeArr[i]; j++){
                G[i][j] = (int)(G2[i].get(j));
            }
        }
        return G;
    }
}

