package comp261.assig3;

import java.util.ArrayList;

/*
Simple Edge class for the graph

*/
// store the edges from the loaded file
public class Edge {
    // links directly to the nodes
    private Node from;
    private Node to;
    // also saves ids in case edges were loaded before nodes, or nodes are reloaded
    // but you want to keep the same edge connections
    private int fromId;
    private int toId;

    private double weight;
    private double vehicle; 

    private int vehicles[] = new int[3]; 

    // Node constructor
    public Edge(Node from, Node to, double weight) {
        this.from = from;
        this.fromId = from.getId();
        this.to = to;
        this.toId = to.getId();
        this.weight = weight;
        this.vehicle = 1; 

        setVehicleNums(weight); 
    }

    // Nodes without the weight assume a weight of 1
    public Edge(Node from, Node to) {
        this(from, to, 1.0);
        this.vehicle = 1; 

        setVehicleNums(weight); 
    }

    // int constructor
    public Edge(int from, int to, double weight) {
        this.fromId = from;
        this.from = null; // the nodes might not exist yet
        this.toId = to;
        this.to = null; // the nodes might not exist yet
        this.weight = weight;
        this.vehicle = 1; 

        setVehicleNums(weight); 
    }

    // randomly create the amount of cars, buses and mopeds that can be on the edge
    public void setVehicleNums(double weight){
         int busNum = (int) Math.round(Math.random() * weight); 
         int carNum = (int) Math.round(Math.random() * (weight - busNum));
         int mopedNum = (int) (weight - carNum - busNum); 
 
         carNum = carNum * 2;
         mopedNum = mopedNum * 4;

         vehicles[0] = busNum;
         vehicles[1] = carNum; 
         vehicles[2] = mopedNum; 
    }

    // getters
    public Node getFrom() {
        return from;
    }

    public int getFromId() {
        return fromId;
    }

    public Node getTo() {
        return to;
    }

    public int getToId() {
        return toId;
    }

    public double getWeight() {
        return weight * vehicle;
    }

    public void setVehicle(double d){
        this.vehicle = d;
    }

    public double getVehicle(){
        return this.vehicle;
    }

    public int getVehicleWeight(int index){
        return this.vehicles[index];
    }

}
