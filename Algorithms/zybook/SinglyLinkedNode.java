public class SinglyLinkedNode<T> extends AbstractSinglyLinkedNode<T, SinglyLinkedNode<T>> {
    public SinglyLinkedNode(T data) { 
        this.data = data;
        this.next = null;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public void setData(T data) {
        this.data = data;
    }

    @Override
    public SinglyLinkedNode<T> getNext() {
        return next;
    }

    @Override
    public void setNext(SinglyLinkedNode<T> next) {
        this.next = next;
    }

}