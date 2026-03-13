public abstract class AbstractSinglyLinkedNode <T, S extends AbstractSinglyLinkedNode<T, S>> {
    public T data;
    public S next;

    public abstract T getData();

    public abstract void setData(T data);

    public abstract S getNext();

    public abstract void setNext(S next);

    @Override
    public final String toString() {
        return String.format("Node(%d)", this.data);
    }
}