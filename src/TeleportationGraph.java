import java.util.*;

public class TeleportationGraph {
	private HashMap<String, Node> graph;
	
	public TeleportationGraph() {
		graph = new HashMap<String,Node>();
	}
	
	public void linkCities(String src, String dst) {
		// Create or get nodes
		 Node srcNode = addCity(src);
		 Node dstNode = addCity(dst);
		 // Add nodes to each other's adj list
		 srcNode.addAdj(dstNode);
		 dstNode.addAdj(srcNode);
	}
	
	private Node addCity(String label) {
		if (!graph.containsKey(label)) {
			Node node = new Node(label);
			graph.put(label, node);
			return node;
		}
		// Get existing node / city
		return graph.get(label);
	}
	
	public String citiesInJumps(String label, int jumps) {
		HashSet<Node> set = new HashSet<Node>();
		set = citiesInJumpsHelper(label, jumps, set);
		String s = "";
		for (Node n : set) {
			if (!n.label.equals(label))
				s += n.label +", ";
		}
		return (s.length()>2) ? s.substring(0,s.length()-2) : s;
	}
	
	private HashSet<Node> citiesInJumpsHelper(String label, int jumps, HashSet<Node> set) {
		if (jumps == 0)
			return set;
		// Node doesn't exist
		if (!graph.containsKey(label))
			return null;
		Node node = graph.get(label);
		for (Node n : node.adjacencyList) {
			set.add(n);
			if (jumps>1)
				set.addAll(citiesInJumpsHelper(n.label, jumps-1, new HashSet<Node>()));
		}
		return set;
	}
	
	public boolean canReachCity(String a, String b) {
		// Nodes exist?
		if (!graph.containsKey(a) || !graph.containsKey(b))
			return false;
		Node src = graph.get(a);
		Node dst = graph.get(b);
		// dummy check
		if (a.equals(b))
			return true;
		// search
		for (Node n : src.adjacencyList) {
			if (n.label.equals(dst.label))
				return true;
		}
		for (Node n : src.adjacencyList) {
			return canReachCity(n.label, b);
		}
		return false;
	}
	
	public boolean cycleFrom(String _src) {
		if (!graph.containsKey(_src))
			return false;
		Node src = graph.get(_src);
		for (Node n : src.adjacencyList) {
			
			if (cycleFromHelper(_src, n.label, new ArrayList<String>()))
				return true;
			
		}
		return false;
		
	}
	
	private boolean cycleFromHelper(String _src, String _curr, ArrayList<String> visited) {
		Node node = graph.get(_curr);
		visited.add(_curr);
		
		if (visited.size()==1) {
			for (Node n : node.adjacencyList) {
				if (!_src.equals(n.label)) {
					
					if (cycleFromHelper(_src,n.label,visited))
						return true;
				}
					
			}
		} else {
			for (Node n : node.adjacencyList) {
				if (_src.equals(n.label))
					return true;
			}
			for (Node n : node.adjacencyList) {
				if (!visited.contains(n.label) && cycleFromHelper(_src,n.label,visited))
					return true;
			}
		}
		
		return false;
	}
}
