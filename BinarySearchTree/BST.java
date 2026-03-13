public class BST implements BSTInterface<Node> {
    private Node root;

    public BST() {
        root = null;
    }

    private Node insertRec(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }
        if (value < current.getData()) {
            current.setLeftChild(insertRec(current.getLeftChild(), value));
        } else if (value > current.getData()) {
            current.setRightChild(insertRec(current.getRightChild(), value));
        }
        return current;
    }

    private Node removeRec(Node current, int value, boolean[] found) {
        if (current == null) return null;
        
        if (value < current.getData()) {
            current.setLeftChild(removeRec(current.getLeftChild(), value, found));
        } else if (value > current.getData()) {
            current.setRightChild(removeRec(current.getRightChild(), value, found));
        } else {
            found[0] = true;
            if (current.getLeftChild() == null) {
                return current.getRightChild();
            } else if (current.getRightChild() == null) {
                return current.getLeftChild();
            }
            int minValue = findMin(current.getRightChild());
            current.setData(minValue);
            current.setRightChild(removeRec(current.getRightChild(), minValue, new boolean[1]));
        }
        return current;
    }

    private int findMin(Node current) {
        int min = current.getData();
        while (current.getLeftChild() != null) {
            current = current.getLeftChild();
            min = current.getData();
        }
        return min;
    }

    private boolean searchRec(Node current, int value) {
        if (current == null) return false;
        if (value == current.getData()) {
            return true;
        } else if (value < current.getData()) {
            return searchRec(current.getLeftChild(), value);
        } else {
            return searchRec(current.getRightChild(), value);
        }
    }

    private void printInOrderRec(Node current) {
        if (current == null) return;
        printInOrderRec(current.getLeftChild());
        System.out.println(current.getData());
        printInOrderRec(current.getRightChild());
    }

    @Override
    public int getRoot() {
        if (root == null) {
            throw new IllegalStateException("BST is empty.");
        }
        return root.getData();
    }

    @Override
    public void insert(Node n) {
        root = insertRec(root, n.getData());
    }

    @Override
    public void printBSTInOrder() {
        printInOrderRec(root);
    }

    @Override
    public void remove(Node n) {
        boolean[] found = new boolean[1];
        root = removeRec(root, n.getData(), found);
        if (found[0]) {
            System.out.println("Node found and removed.");
        } else {
            System.out.println("Node not found.");
        }
    }

    @Override
    public void find(Node n) {
        boolean exists = searchRec(root, n.getData());
        if (exists) {
            System.out.println("Node found.");
        } else {
            System.out.println("Node not found.");
        }
    }
}
