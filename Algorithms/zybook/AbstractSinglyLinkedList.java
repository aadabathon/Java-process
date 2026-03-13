public abstract class AbstractSinglyLinkedList <T, S extends AbstractSinglyLinkedNode<T, S>> {
    //these should be protected, but I made them public to test your code implementation
    public S head = null;
    public S tail = null;
    public int length = 0;


    protected AbstractSinglyLinkedList() {}
    /**
     * Get the node at the specified index.
     * @param index List index
     * @return The node at {@code index}
     */
    protected abstract S getNode(int index);

    /**
     * Inserts a node with the data n at the specified position in the list.
     * Prints a message if the requested position does not exist.
     * @param index List index
     * @param n data to insert
     */
    public abstract void add(int index, T n);

    /**
     * Appends a node with the specified data to the end of the list.
     * @param n data to append
     */
    public abstract void add(T n);

    /**
     * Prepends a node with the specified data to the head of the list.
     * @param n SinglyLinkedNode to prepend
     */
    public abstract void addFirst(T n);

    /**
     * Removes all the nodes from the list.
     */
    public abstract void clear();

    /**
     * Returns and removes the head of the list.
     * @return The head node
     */
    public abstract S remove();

    /**
     * Returns and removes the node at the specified position in the list.
     * @param index List index
     * @return The node at {@code index}
     */
    public abstract S removeAt(int index);

    /**
     * Removes the first occurrence of the node with specified data if it is present.
     * @param n data contents of the SinglyLinkedNode to remove
     * @return Whether the operation was successful
     */
    public abstract boolean remove(T n);

    /**
     * Replaces the node at the specified position in the list with a
     * node containing the specified data.
     * Returns the replaced node.
     * Prints a message if the requested position does not exist.
     * @param index List index
     * @param n SinglyLinkedNode to swap in
     * @return SinglyLinkedNode swapped out
     */
    public abstract S set(int index, T n);

    /**
     * Returns the number of nodes in the list.
     * @return Length of the list
     */
    public final int size() {
        return length;
    }

    /**
     * Prints each node's data and position in the list.
     */
    public final void printList() {
        System.out.printf("SLL (size %d)\n", length);
        S n = head;
        for (int i = 0; i < length; i++) {
            System.out.printf("  %d: %s\n", i, n);
            if(n != null) n = n.getNext();
        }
    }
}
