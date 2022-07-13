package comp261.assig3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import javafx.util.Pair;

// helper class that does not need local memory

public class FordFulkerson {
    // class members
    // Augmentation paths and the corresponding bottleneck flows
    private static ArrayList<Pair<ArrayList<Node>, Double>> augmentationPaths = new ArrayList<Pair<ArrayList<Node>, Double>>();
    // residual graphs
    private static double[][] residualGraph;
    private static double[][] busResidualGraph;
    private static double[][] carResidualGraph;
    private static double[][] mopedResidualGraph;
    public static Node[] parents;


    // constructor
    public FordFulkerson() {
        augmentationPaths = null;
        busResidualGraph = null;
        carResidualGraph = null;
        mopedResidualGraph = null; 

    }

    // find maximum flow value
    public static double calcMaxflows(Graph graph, Node source, Node sink, int vehicle) {
        ArrayList<Node> nodes = graph.getNodeList();
        residualGraph = null;

        if(vehicle == 0){
            residualGraph = graph.getBusAdjacencyMatrix();
        }else if(vehicle == 1){
            residualGraph = graph.getCarAdjacencyMatrix();
        }else{
            residualGraph = graph.getMopedAdjacencyMatrix();
        }
        parents = new Node[nodes.size()];
        double flow = 0;

        do{
            for(int i = 0; i < nodes.size(); i++){
                parents[i] = null;
            }
            flow += bfs(source, sink, parents);
        }while(parents[sink.getId()] != null);

        return flow;
    }

    // TODO:Use BFS to find an augmentation path from s to t
    // add the augmentation path found to the arraylist of augmentation paths
    // return bottleneck flow
    public static double bfs(Node s, Node t, Node[] parent) {
        Queue<Node> queue = new ArrayDeque<Node>();
        ArrayList<Node> path = new ArrayList<Node>();

        parent[s.getId()] = s; 

        queue.add(s);
        while(!queue.isEmpty()){
            Node cur = queue.poll();
            cur.setVisited(true);

            for(Node nextNode: cur.getNeighbours()){
                if(parent[nextNode.getId()] == null && nextNode != s && residualGraph[cur.getId()][nextNode.getId()] != 0){
                    parent[nextNode.getId()] = cur;
                    queue.offer(nextNode);
                }
            }
        }

        // Create the augmentation paths
        path.add(t);
        Node child = t;
        while(parent[child.getId()] != null && !parent[child.getId()].equals(child)){
            child = parent[child.getId()];
            path.add(child);
        }
        Collections.reverse(path);

        // We have found an augmentation path, see how much flow we can send
        if(parent[t.getId()] != null){ 
            double df = Double.MAX_VALUE;
            for(Node current = t, 
                pOfCurrent = parent[current.getId()];
                current != s;
                current = pOfCurrent,
                pOfCurrent = parent[pOfCurrent.getId()]){
                    df = Math.min(df, residualGraph[pOfCurrent.getId()][current.getId()]);
            }
              
            for(Node current = t, pOfCurrent = parent[current.getId()];
                current != s; current = pOfCurrent, pOfCurrent = parent[pOfCurrent.getId()]){
                    residualGraph[pOfCurrent.getId()][current.getId()] -= df;
                    residualGraph[current.getId()][pOfCurrent.getId()] += df;
                }

            augmentationPaths.add(new Pair<ArrayList<Node>, Double>(path, df));
            return df; 
        }
        
        return 0;
    }

    // TODO: For each flow identified by bfs() build the path from source to sink
    // Add this path to the Array list of augmentation paths: augmentationPaths
    // along with the corresponding flow
    public static void flowPath(Node s, Node t, Node[] parent, double new_flow) {

        ArrayList<Node> augmentationPath = new ArrayList<Node>();

        augmentationPaths.add(new Pair<ArrayList<Node>, Double>(augmentationPath, new_flow));

    }

    // getter for augmentation paths
    public static ArrayList<Pair<ArrayList<Node>, Double>> getAugmentationPaths() {
        return augmentationPaths;
    }


    // TODO: find min-cut - as a set of sets and the corresponding cut-capacity
    public static Pair<Pair<HashSet<Node>, HashSet<Node>>, Double> minCut_s_t(Graph graph, Node source, Node sink, int vehicle) {
        Pair<Pair<HashSet<Node>, HashSet<Node>>, Double> minCutwithSets = null;
        double minValue = Double.MAX_VALUE;  

        ArrayList<HashSet<Node>> aSets = new ArrayList<HashSet<Node>>();
        ArrayList<HashSet<Node>> bSets = new ArrayList<HashSet<Node>>();

        ArrayList<Node> nodesList = graph.getNodeList(); 
        ArrayList<Node> nodes = new ArrayList(nodesList); 

        nodes.remove(sink);
        nodes.remove(source);

        // Create all of the A sets:
        for(int i = 0; i < (1<<nodes.size()); i++){
            HashSet<Node> a = new HashSet<Node>();
            a.add(source);
            for(int j = 0; j < nodes.size(); j++){
                if((i & (1 << j)) > 0){
                    a.add(nodes.get(j));
                }
            }
            aSets.add(a);
        }

        for(int i = 0; i < aSets.size(); i++){
            HashSet<Node> currA = aSets.get(i);

            // Calculate the cut flow for the set
            double cutFlow = 0;
            for(Node n : aSets.get(i)){
                for(Edge e : n.getEdgesOutgoing()){ 
                    if(!aSets.get(i).contains(e.getTo())){
                        if(vehicle == 0){cutFlow = cutFlow + e.getVehicleWeight(0);}
                        else if(vehicle == 1){cutFlow = cutFlow + e.getVehicleWeight(1);}
                        else if(vehicle == 2){cutFlow = cutFlow + e.getVehicleWeight(2);}
                    }
                }

            }  

            if(cutFlow < minValue){
                
                // create the corresponding b set
                HashSet<Node> b = new HashSet<Node>();
                b.add(sink);
                for(Node n : nodes){
                    if(!currA.contains(n)){
                        b.add(n);
                    }
                }
                bSets.add(b);

                Pair<HashSet<Node>, HashSet<Node>> temp = new Pair<HashSet<Node>, HashSet<Node>>(currA, b); 
                minCutwithSets = new Pair<Pair<HashSet<Node>, HashSet<Node>>, Double>(temp, cutFlow);
                minValue = cutFlow;


            }
            
        }  
        
        return minCutwithSets;
    }

    // TODO: Use dfs to find set of nodes connected to s
    private static void dfs(Graph graph, Node s) {
    }
}
