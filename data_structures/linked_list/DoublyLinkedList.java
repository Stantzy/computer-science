package data_structures.linked_list;

public class DoublyLinkedList<T> {
    private Node<T> first = null;
    private Node<T> last = null;
    private int size = 0;

    private static class Node<T> {
        T element;
        Node<T> next;
        Node<T> prev;

        Node(T element) {
            this(element, null, null);
        }

        Node(T element, Node<T> next, Node<T> prev) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }

    public DoublyLinkedList(T element) {
        first = new Node<>(element);
        last = first;
        size = 1;
    }

    public DoublyLinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    public T addFirst(T element) {
        if(first == null || last == null) {
            first = new Node<>(element);
            last = first;
        } else {
            first.prev = new Node<>(element, first, null);
            first = first.prev;
        }
        size++;
        return first.element;
    }

    public T addLast(T element) {
        if(first == null || last == null) {
            first = new Node<>(element);
            last = first;
        } else {
            last.next = new Node<>(element, null, last);
            last = last.next;
        }
        size++;
        return last.element;
    }

    public T removeFirst() {
        if(first == null)
            return null;
        if(size == 1) {
            T element = first.element;
            clear();
            return element;
        }

        Node<T> tmp = first;
        first = first.next;
        tmp.next = null;
        size--;

        return tmp.element;
    }

    public T removeLast() {
        if(first == null)
            return null;
        if(size == 1) {
            T element = first.element;
            clear();
            return element;
        }

        Node<T> tmp = last;
        last = last.prev;
        tmp.prev = null;
        size--;

        return tmp.element;
    }

    public T insertAt(T element, int position) {
        Node<T> tmp = first;
        int count = 1;

        if(position < 1)
            return null;
        if(first == null || position == 1)
            return addFirst(element);
        if(position >= size)
            return addLast(element);

        while(count < position) {
            count++;
            tmp = tmp.next;
        }

        Node<T> newNodePrev = tmp.prev;
        Node<T> newNodeNext = tmp;
        Node<T> newNode = new Node<>(element, newNodeNext, newNodePrev);
        tmp.prev = newNode;
        size++;

        return newNode.element;
    }

    public T removeAt(int position) {
        Node<T> tmp = first;
        int count = 1;

        if(first == null)
            return null;
        if(position == 1)
            return removeFirst();
        if(position >= size)
            return removeLast();

        while(count < position) {
            count++;
            tmp = tmp.next;
        }

        tmp.prev.next = tmp.next;
        tmp.next.prev = tmp.prev;
        T removed = tmp.element;
        tmp.prev = null;
        tmp.next = null;

        return removed;
    }

    public T getFirst() {
        return first == null ? null : first.element;
    }

    public T getLast() {
        return last == null ? null : last.element;
    }

    public T getAt(int position) {
        Node<T> tmp = first;
        int count = 1;

        if(first == null)
            return null;
        if(position == 1)
            return getFirst();
        if(position >= size)
            return getLast();

        while(count < position) {
            count++;
            tmp = tmp.next;
        }

        return tmp.element;
    }

    public T setAt(T element, int position) {
        Node<T> tmp = first;
        int count = 1;

        if(first == null)
            return null;
        if(position == 1) {
            first.element = element;
            return first.element;
        }
        if(position >= size) {
            last.element = element;
            return last.element;
        }

        while(count < position) {
            count++;
            tmp = tmp.next;
        }

        tmp.element = element;

        return tmp.element;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        Node<T> tmp = first;
        while(tmp != null) {
            first = tmp.next;
            tmp.prev = null;
            tmp.next = null;
            tmp = first;
        }
        last = null;
        size = 0;
    }

    public boolean contains(T element) {
        Node<T> tmp = first;
        while(tmp != null) {
            if(tmp.element.equals(element))
                return true;
            tmp = tmp.next;
        }
        return false;
    }

    public int indexOf(T element) {
        Node<T> tmp = first;
        int count = 1;
        while(tmp != null) {
            if(tmp.element.equals(element))
                return count;
            tmp = tmp.next;
            count++;
        }
        return -1;
    }

    public Object[] toArray() {
        Object[] arr = new Object[size];
        Node<T> tmp = first;

        for(int i = 0; i < size; i++) {
            arr[i] = tmp.element;
            tmp = tmp.next;
        }

        return arr;
    }
}
