import java.util.NoSuchElementException;

public class ArrayBasedStack<E> extends ArrayBasedAbstractStack<E> {

    public ArrayBasedStack() {
        super();
    }

    public ArrayBasedStack(int maximumSize) {
        super(maximumSize); 
    }

    @Override
    public boolean push(E element) {
        if (isBounded && length >= maximumSize) {
            return false; 
        }
        contents.add(element);
        length++;
        return true;
    }

    @Override
    public E peek() {
        if (length == 0) {
            throw new NoSuchElementException();
        }
        return contents.get(length - 1); 
    }

    @Override
    public E pop() {
        if (length == 0) {
            throw new NoSuchElementException();
        }
        E removed = contents.remove(length - 1); 
        length--;
        return removed;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            sb.append(contents.get(i)).append(" ");
        }

        return sb.toString();
    }
}
