package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Graph_1 {
    private int [][] G;

    public Graph_1(int[][] g) {G = g;
    }

    public void dfs(int [] label, int [] GV, List [] E)
    {
        int v, k, t1, t2, ch;
        Stack steck = new Stack<>();
        boolean [] used = new boolean[this.G.length];
        for (int i=0; i< this.G.length;i++) {
            used[i] = false;
        }
        ch=1;
        v= GV[0];
        steck.add(v);
        used[v]=true;
        label[v]=ch;
        ch+=1;
        while(!steck.empty()){
            //System.out.println(steck);
            v=(int)steck.peek();
            k=0;
            for (int j=0; j<this.G[v].length; j++){
                if(used[this.G[v][j]]==false) {
                    used[this.G[v][j]] = true;
                    steck.add(this.G[v][j]);
                    label[this.G[v][j]]=ch;
                    ch+=1;
                    k = 1;
                    break;
                }
            }
            if (k==0){
                t1 = (int)steck.peek();
                steck.pop();
                if(!steck.empty()) {
                    t2 = (int) steck.peek();
                    for (int j=0; j<this.G[t1].length;j++){
                        if ((this.G[t1][j] != t2)&&(steck.search(G[t1][j])!=-1)) {
                            E[t1].add(label[G[t1][j]]);
                        }
                    }
                }
            }}}

    public boolean dfsm(int [] label, int [] GV, List [] E){
        int v, v1, k, m1;
        boolean ans=false;
        Stack steck = new Stack<>();
        boolean [] used = new boolean[this.G.length];
        boolean us = true;
        for (int i=0; i< this.G.length;i++) {
            used[i] = false;
        }

        v=GV[0];
        steck.add(v);
        used[v]=true;


        while(!steck.empty()){
            //System.out.println(steck);
            v=(int)steck.peek();
            k=0;
            for (int j=0; j<this.G[v].length; j++){
                if(used[this.G[v][j]]==false) {
                    used[this.G[v][j]] = true;
                    steck.add(this.G[v][j]);
                    k = 1;
                    break;
                }
            }
            if (k==0){
                v=(int)steck.peek();
                steck.pop();
                if(!steck.empty()){
                    v1 = (int) steck.peek();
                    if (!E[v].isEmpty()){
                        m1=label[v1];
                        if (m1 > (int) Collections.min(E[v])){
                            E[v1].add((int)Collections.min(E[v]));
                            //System.out.println("perehod");
                        }

                    }
                    else {
                        ans=false;
                        us=false;
                        break;
                    }
                }
            }
        }
        if (us) ans=true;
        return ans;
    }
    public boolean main(Graph_1 test){
        int [] label;
        int [] answer;
        List<Integer>[] E;
        int [] GV;

        E = new List[G.length];
        for (int i =0; i<G.length; i++){
            E[i] = new ArrayList();
        }
        label = new int[G.length];
        answer = new int[G.length];

        for (int i=0; i<G.length;i++){
            label[i]=0;
            answer[i]=0;
        }
        GV = new int[G.length];
        for (int i=0; i< G.length; i++){
            GV[i]=i;
        }
        test.dfs(label,GV,E);
        return test.dfsm(label,GV,E);
    }
}
