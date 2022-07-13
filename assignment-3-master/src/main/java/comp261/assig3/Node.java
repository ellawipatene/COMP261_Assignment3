package comp261.assig3;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;

// contains all the node information
public class Node {
    // position of the node
    private double x;
    private double y;

    private String name;
    private int id;

    // for path finding and dfs
    private boolean visited;

    // adjacency lists for connected edges
    private List<Edge> outgoingEdges;
    private List<Edge> incomingEdges;

    // for NO_ID allocated to a node
    public static final int NO_ID = -1;

    public Node(double x, double y, String name, int id) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.id = id;
        this.outgoingEdges = new ArrayList<Edge>();
        this.incomingEdges = new ArrayList<Edge>();
    }

    // if there is just a raw x and y with no id
    public Node(double x, double y) {
        this.x = x;
        this.y = y;
        this.name = "";
        this.id = NO_ID;
        this.outgoingEdges = new ArrayList<Edge>();
    }

    // add edge to outgoing edges
    public void addEdgeOutgoing(Edge edge) {
        outgoingEdges.add(edge);
    }

    // get edges
    public List<Edge> getEdgesOutgoing() {
        return outgoingEdges;
    }

    // add edge to incoming edges
    public void addEdgeIncoming(Edge edge) {
        incomingEdges.add(edge);
    }

    // get edges incoming
    public List<Edge> getEdgesIncoming() {
        return incomingEdges;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Point2D getPoint() {
        return new Point2D(x, y);
    }

    public double distance(double x, double y) {
        return Math.sqrt(Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2));
    }

    public String toString() {
        return "Node " + id + ": " + name + " at (" + x + ", " + y + ")";
    }

    public boolean equals(Node node) {
        return this.id == node.id;
    }

    public boolean equals(int id) {
        return this.id == id;
    }

    public boolean equals(String name) {
        return this.name.equals(name);
    }

    public boolean equalLocation(double x, double y) {
        return this.x == x && this.y == y;
    }

    public boolean equalLocation(Node node) {
        return this.x == node.x && this.y == node.y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setLocation(Node node) {
        this.x = node.x;
        this.y = node.y;
    }

    // is visited
    public boolean isVisited() {
        return visited;
    }

    // set visited
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    // method to get neighbours
    public ArrayList<Node> getNeighbours() {
        ArrayList<Node> neighbours = new ArrayList<Node>();
        for (int i = 0; i < Main.graph.getAdjacencyMatrix()[this.id].length; i++) {
            if (Main.graph.getAdjacencyMatrix()[this.id][i] != 0) {
                Node neighbour = Main.graph.findNode(i);
                neighbours.add(neighbour);

            }

        }

        return neighbours;
    }

}
