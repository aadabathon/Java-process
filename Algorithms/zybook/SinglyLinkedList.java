public class SinglyLinkedList<T> extends AbstractSinglyLinkedList<T, SinglyLinkedNode<T>> {


        @Override
        protected SinglyLinkedNode<T> getNode(int index) {
           if (index < 0 || index >= length) return null;
            SinglyLinkedNode<T> current = head;
            for (int i = 0; i < index; i++){
                current = current.next;
            }
            return current;
        }

        @Override
        public void addFirst(T n) { 
            SinglyLinkedNode<T> newNode = new SinglyLinkedNode<>(n);
            newNode.setNext(head);
            head = newNode; 
            if (tail == null) tail = newNode;
            length++;
            }
        
        @Override
        public void add(int index, T n){
            if (index < 0  || index > length) {
                System.out.printf("Oh no! Can't append at position %d!\n", index);
                System.out.println();
                return;
            }
            
            
            if (index == 0){
                addFirst(n);
            } else if (index == length){
                add(n);
            } else {
                SinglyLinkedNode<T> newNode = new SinglyLinkedNode<>(n);
                SinglyLinkedNode<T> current = head;
                for (int i = 0; i < index - 1; i++){
                    current = current.next;
                }
                newNode.next = current.next;
                current.next = newNode;
                length++;
            }
            }

            @Override
            public void clear(){
                head = null;
                tail = null;
                length = 0;
            }

            @Override 
            public SinglyLinkedNode<T> remove(){
                if (head == null) return null;
                SinglyLinkedNode<T> removed = head;
                head = head.getNext();
                if (head == null) tail = null;
                length--;
                return removed;
            }

            @Override
            public boolean remove(T n){
                if ( head == null) return false;
                if (head.data.equals(n)){
                    remove();
                    return true;
                } 
                SinglyLinkedNode<T> prev = head;
                SinglyLinkedNode<T> current = head.getNext();
                while ( current != null) { 
                    if (current.data.equals(n)) {
                        prev.setNext(current.getNext());
                        if (current == tail) tail = prev;
                        length--;
                        return true;
                    }
                    prev = current;
                    current = current.getNext();
                }
                return false;     
            }
            @Override
            public void add(T n) {
                SinglyLinkedNode<T> newNode = new SinglyLinkedNode<>(n);
                if (head == null) { 
                    head = newNode;
                    tail = newNode;
                } else {
                    tail.setNext(newNode);
                    tail = newNode;
                }
                length++;
                }
            

            @Override
            public SinglyLinkedNode<T> removeAt(int index) {
                if (index < 0 || index >= length) {
                    System.out.println("Index out of bounds");
                    return null;
                }
               if (index == 0) return remove();
                SinglyLinkedNode<T> prev = getNode(index - 1);
                SinglyLinkedNode<T> removed = prev.getNext();
                prev.setNext(removed.getNext());
                
                if (removed == tail) tail = prev;
                length--;
                return removed;

            }

            @Override
            public SinglyLinkedNode<T> set(int index, T n){
                if ( index < 0 || index >= length) { 
                    System.out.println("Index out of bounds");
                    return null;
                }
                SinglyLinkedNode<T> node = getNode(index);
                SinglyLinkedNode<T> oldNode = new SinglyLinkedNode<>(node.data);
                node.data = n;
                return oldNode;
            }
        }
        









 