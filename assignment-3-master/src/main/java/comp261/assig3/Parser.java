package comp261.assig3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;



/**
 * This utility class parses the files, and return the relevant data structure.
 * Internally it uses BufferedReaders instead of Scanners to read in the files,
 * as Scanners are slow.
 * 
 * @author Simon
 */
public class Parser {

    /**  Parsing the nodes file. 
     * node_id,node_name,x,y
    */
	public static ArrayList<Node> parseNodes(File nodeFile){
    	ArrayList<Node> nodes = new ArrayList<Node>();
    
        // read the node file
		try {
			// make a reader
			BufferedReader br = new BufferedReader(new FileReader(nodeFile));
			br.readLine(); // throw away the top line of the file.
			String line;
			// read in each line of the file
			while ((line = br.readLine()) != null) {
				// tokenise the line by splitting it at ",".
				String[] tokens = line.split("[,]");
                if (tokens.length >= 3) {
                    // process the tokens
                    int nodeId = Integer.parseInt(tokens[0]);
                    String nodeName = tokens[1];
                    double x = Double.valueOf(tokens[2]);
                    double y = Double.valueOf(tokens[3]);
                    nodes.add(new Node(x, y, nodeName, nodeId));
                }
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException("file reading failed.");
        }
        return nodes;
    }
    
    public static ArrayList<Edge> parseEdges(File edgeFile, ArrayList<Node> nodes) {

        ArrayList<Edge> edgeList = new ArrayList<Edge>();
		try {
			// make a reader
			BufferedReader br = new BufferedReader(new FileReader(edgeFile));
			br.readLine(); // throw away the top line of the file.
			String line;
			// read in each line of the file
			while ((line = br.readLine()) != null) {
				// tokenise the line by splitting it at ",".
				String[] tokens = line.split("[,]");
                if (tokens.length >= 4) {
                    // process the tokens
                    String edgeId = tokens[0];
                    int from = Integer.valueOf(tokens[1]);
                    int to = Integer.valueOf(tokens[2]);
                    double weight = Double.valueOf(tokens[3]);
                    if (nodes!=null && nodes.size() > from && nodes.size() > to) {
                        edgeList.add(new Edge(nodes.get(from), nodes.get(to), weight));
                        //edgeList.add(new Edge(nodes.get(to), nodes.get(from), weight));
                    }else{ // if the nodes are not in the list, add both directions
                       // edgeList.add(new Edge(to, from, weight));
                        edgeList.add(new Edge(from, to, weight));
                    }
                    
                }
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException("file reading failed.");
        }
        return edgeList;
    }  
}

// code for COMP261 assignments