### Core: Ford-fulkerson [0-45]
1. [20] Using DFS/BFS find a flow between source and sink nodes.
    - [10] Find and display the flow value.
    - [10] Find and display the flow path.

2. [25] Implement the Edmond-karp algorithm.
	- [15] Find and display the maximum flow through a given flow network.
	- [10] Find and display all the Augmentation paths identified in the flow network. 

To achieve the two above points, I added code to the FordFulkerson.java file and the GraphController.java file. 

In the FordFulkerson.java file:
- In calcMaxFlows(), I grabbed the nodes list and set the residualGraph to null. Because the sink is the last value in the 
  nodes list, I added a do-while loop, which will call the bfs until the parent of the sink is not null. This will return 
  the max flow of the graph.
- In the bfs(), the code iterates through the nodes with a queue, polling the node on top, then adding its neighbouring nodes. 
  Durring this, the 'parent' of each node is set. I then iterate backwards from the sink, using the parents, to create the paths. 
  Next I use the Edmond-karp algorithm to find out "how much flow we can send." Add the path to the augmentations paths list. 

In the GraphController.java file:
- In the handleNetworkFlow(), I edited the file to print out the max flow
- In the handleAugmentationPaths(), I edited the file to print out all of the augmentation paths

### Completion: s-t cut and Vehicle-type sensitivity[45-80]
1. [25]: Identify the s-t cut exhibited by your implementation of the Ford-fulkerson method.
	- [15] Find and display the s-t cut.
	- [10] Find and display the capacity of the s-t cut

To code the s-t cut, I edited the minCut_s_t() method in FordFulkerson:
- First I removed the sink and the source from the node list, to ensure that the source is always in set A and the sink is always
  in set B. I used a for loop to create all unique combinations of the nodes for set A. For each of these sets, I calculated the 
  'outgoing' flow to set B. If this flow is smaller than the current smallest flow, then it will calculate the B set. 
- In GraphController.java - handleMinCut(), I printed out the min cut value and the two sets. 
        
2. [10]: Vehicle-type sensitivity [10].
    - [10] Allow your program to take "type" of the vehicle as input- Update your code to include "type" of vehicle as input. 
Based on the "type" update the capacity of **all** the edges of the network graph using the following formula : Bus - x, Cars - 2x, Moped - 4x.

For this I edited, MapView.fxml, Edge.java and GraphControler.java:
- In Edge.java, I added an int vehicle variable, which will edit the weight based on what type of vehicle it is. 
- In MapView.fxml, I added a combo box, so the user can choose between Car, Bus and Moped. 
- This combo box is conected to the handleVehicle() in GraphControler.java. This will change the weights of the edges 
  based on the vehicle and then update the graph. 

### Challenge[80-100]: Extend to allow different edge "types"
Create another version of your algorithm - "Extended-FF" which allows different edges to support different vehicle types to cater to scenarios where some roads are only suitable for a specific type of a vehicle. 
Your implementation should provide the maximum flow in terms of _number of vehicles._

For challenge, I edited the files: Edge.java, FordFulkerson.java, GraphControler.java, Graph.java and Node.java. 

- For Edge.java, when the weight was loaded in, it is randomly split between bus, cars and mopeds. 
- In Graph.java, it creates 3 adjacency matrixes, one for each of the transportation types. 
- In Node.java, I changed the outgoingNeighbours(), to support the new adjacency martixes. 
- For FordFulkerson.java, I had to edit both the min cut function and the calcMaxFlow function. 
  I now call calcMaxFlow three times, one for each of the vehicle types, to calculate the max flow for each of them.
  The same is done in min cut, the method is run three times, once for each of the vehicle types. 
  These values are then plused together to create the total max flow or minimum cut and printed in the GraphController.java.
