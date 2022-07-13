package comp261.assig3;

import java.io.File;
import java.util.ArrayList;

/*
Graph class for nodes and edges 

*/

public class Graph {

    // all the nodes
    private ArrayList<Node> nodeList;
    // all the edges
    private ArrayList<Edge> edgeList;

    private double[][] adjacencyMatrix;
    private double[][] busAdjacencyMatrix;
    private double[][] carAdjacencyMatrix;
    private double[][] mopedAdjacencyMatrix;

    // A graph of nodes and edges
    public Graph(ArrayList<Node> nodeList, ArrayList<Edge> edgeList) {
        this.nodeList = nodeList;
        this.edgeList = edgeList;
        // construct the adjacency list for each not
        buildAdjacencyMatrix();
        buildEdgeLists();
    }

    // constructor for the graph from files
    public Graph(File nodeFile, File edgeFile) {
        this.nodeList = Parser.parseNodes(nodeFile);
        this.edgeList = Parser.parseEdges(edgeFile, nodeList); // this allows the linking of edges to nodes
        buildAdjacencyMatrix();
        buildEdgeLists();
    }

    // Given an ID return the corresponding node
    public Node findNode(int id) {
        for (Node n : this.getNodeList()) {
            if (n.getId() == id)
                return n;
        }
        return null;
    }

    // build the three adjacency matrix
    public void buildAdjacencyMatrix() {
        adjacencyMatrix = new double[nodeList.size()][nodeList.size()];
        for (Edge e : edgeList) {
            adjacencyMatrix[e.getFromId()][e.getToId()] = e.getWeight();

        }

        busAdjacencyMatrix = new double[nodeList.size()][nodeList.size()];
        for (Edge e : edgeList) {
            busAdjacencyMatrix[e.getFromId()][e.getToId()] = e.getVehicleWeight(0);

        }

        carAdjacencyMatrix = new double[nodeList.size()][nodeList.size()];
        for (Edge e : edgeList) {
            carAdjacencyMatrix[e.getFromId()][e.getToId()] = e.getVehicleWeight(1);

        }

        mopedAdjacencyMatrix = new double[nodeList.size()][nodeList.size()];
        for (Edge e : edgeList) {
            mopedAdjacencyMatrix[e.getFromId()][e.getToId()] = e.getVehicleWeight(2);

        }
    }

    public void buildEdgeLists(){
        for(Edge e: edgeList){
            Node from = e.getFrom();
            Node to = e.getTo();
            to.addEdgeIncoming(e);
            from.addEdgeOutgoing(e);
        }   

    }

    public double[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public double[][] getBusAdjacencyMatrix() {
        return busAdjacencyMatrix;
    }

    public double[][] getCarAdjacencyMatrix() {
        return carAdjacencyMatrix;
    }

    public double[][] getMopedAdjacencyMatrix() {
        return mopedAdjacencyMatrix;
    }

    public ArrayList<Node> getNodeList() {
        return nodeList;
    }

    public ArrayList<Edge> getEdgeList() {
        return edgeList;
    }

}
