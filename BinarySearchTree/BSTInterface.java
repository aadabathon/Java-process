
/*
 * This interface is to be implemented to make a simple binary search tree.
 */

interface BSTInterface<T> {
	
	int getRoot();  // Leaves the root intact, but returns the root node's value.
	
	void insert(T n);  // Inserts the node according to BST rules in zyBooks.
	
	/* Prints the BST node data from smallest to largest value.
	 * The format of the output will be one value per line of output.
	 */
	void printBSTInOrder();  
	
	/* Removes the first occurrence by in-order traversal of the node by data value. 
	 * Prints either "Node found and removed." or "Node not found." based on the results.
	 */	
	void remove(T n);  
	
	/* Prints the message "Node found." if the first occurrence of the node by data value is found.
	 * The node is left in place.  Prints the message "Node not found." if the node is not found.
	 */
	void find(T n);  
}