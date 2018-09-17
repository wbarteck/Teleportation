import java.util.ArrayList;

/*
 * Node serves as each destination in the graph
 */
public class Node {
	public String label;
	public ArrayList<Node> adjacencyList;
	
	public Node(String _label) {
		label = _label;
		adjacencyList = new ArrayList<Node>();
	}
	
	public boolean addAdj(Node node) {
		if (adjacencyList.contains(node))
			return false;
		adjacencyList.add(node);
		return true;
	}
}
