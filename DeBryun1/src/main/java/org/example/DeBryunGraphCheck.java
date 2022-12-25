package org.example;
import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.*;

import static java.nio.file.Files.size;

public class DeBryunGraphCheck implements GraphProperty {
    @Override
    public boolean execute(Graph graph){
        /*
        Получаем информацию из графа
         */
        Map<UUID, Vertex> vertexMap = graph.getVertices();
        Collection<UUID> keys = vertexMap.keySet();
        List<Edge> edgeList = graph.getEdges();
        Vertex iVertex;
        /*
        Проходим по всем верщинам графа и создаем алфавит
        */
        String alphabet = "";
        for(UUID k:keys){
            iVertex = vertexMap.getOrDefault(k,null);
            String vLabel = iVertex.getLabel();
            for(int i=0;i<vLabel.length();++i){
                if(alphabet.indexOf(vLabel.charAt(i)) == -1){
                    alphabet = alphabet.concat(String.valueOf(vLabel.charAt(i)));
                }
            }
        }

        /*
        Проверяем, чтобы пометки в вершинах были одинаковой длины
         */
        int vLabelLen = 0;
        for(UUID k:keys){
            iVertex = vertexMap.getOrDefault(k,null);
            String vLabel = iVertex.getLabel();
            int sLen = vLabel.length();
            if(vLabelLen != 0){
                if(sLen != vLabelLen){
                    return false;
                }
            }else {
                vLabelLen = sLen;
            }
        }
        /*
        Проверяем, чтобы длина пометок не равнялась нулю
        */
        if(vLabelLen == 0){
            return false;
        }
        /*
        Проверка количества ребер
        */
        if(edgeList.size() != Math.pow(alphabet.length(),vLabelLen+1)){
            return false;
        }
        /*
        Проверка количества вершин
        */
        if(vertexMap.size() != Math.pow(alphabet.length(),vLabelLen)){
            return false;
        }
        /*
        Проверка длин ребер
        */
        for(Edge e:edgeList){
            if(e.getLabel().length() !=vLabelLen+1){
                return false;
            }
        }
        /*
        Проверяем, что граф является графом Де Брюина
         */
        ArrayList<String> labelList = new ArrayList<String>();
        for(Edge e:edgeList){
            Vertex sVertex = vertexMap.getOrDefault(e.getFromV(),null);
            Vertex fVertex = vertexMap.getOrDefault(e.getToV(),null);
            String prefix = sVertex.getLabel();
            String postfix = fVertex.getLabel();
            String word = e.getLabel();
            /*
            Проверяем, что слова в стартовой и конечной вершинах являются соответственно префиксом и суффиксом
            для слова в ребре
             */
            if(!(prefix.equals(word.substring(0,word.length()-1)))){
                return false;
            }
            if(!(postfix.equals(word.substring(1,word.length())))){
                return false;
            }
            /*
            Проверяем, что слова в ребрах не повторяются
            */
            for(String elem:labelList){
                if(elem.equals(word)){
                    return false;
                }
            }
            labelList.add(word);
        }


        return true;
    }
}
