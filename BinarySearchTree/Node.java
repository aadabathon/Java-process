
public class Node {
	private int data;
	private Node leftChild;
	private Node rightChild;
	
	public Node() {
		data = -1;
		leftChild = null;
		rightChild = null;
	}
	
	public Node (int input) {
		data = input;
		leftChild = null;
		rightChild = null;
	}
	
	public void setData(int input) {
		data = input;
	}
	public int getData() {
		return data;
	}
	
	public void setLeftChild(Node input) {
		leftChild  = input;
	}
	public Node getLeftChild() {
		return leftChild;
	}
	
	public void setRightChild(Node input) {
		rightChild  = input;
	}
	public Node getRightChild() {
		return rightChild;
	}
	
	
}
