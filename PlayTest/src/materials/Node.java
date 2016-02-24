package materials;

/**
 * A (very) simple class to build off of for a node in a tree.  
 * @author Nicholas Caputo
 *
 */
public class Node {
	// the value placed at this node
	public int data;
	
	// these may be null, they simply point at a node this one is connected to.
	public Node ancestor;  // when would we use this?
	public Node left;
	public Node middle; // may not be a Binary tree
	public Node right;
	
	public Node(int data, Node left, Node right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}
}
