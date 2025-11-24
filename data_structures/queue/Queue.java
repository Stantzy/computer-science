package data_structures.queue;

public class Queue<T> {
    private int size;
    private Node<T> front;
    private Node<T> back;

    private static class Node<T> {
        T element;
        Node<T> next;

        Node(T element) {
            this(element, null);
        }

        Node(T element, Node<T> next) {
            this.element = element;
            this.next = next;
        }
    }

    public Queue(T element) {
        init(element);
    }

    public Queue() {
        init();
    }

    public void enqueue(T element) {
        if(size == 0 && back == null) {
            init(element);
        } else {
            back.next = new Node<>(element);
            back = back.next;
            size++;
        }
    }

    public T dequeue() {
        if(size == 0 && front == null)
            return null;

        Node<T> tmp = front;
        front = front.next;
        tmp.next = null;
        size--;

        return tmp.element;
    }

    public T peek() {
        return front == null ? null : front.element;
    }

    public boolean contains(T element) {
        Node<T> tmp = front;

        while(tmp != null) {
            if(tmp.element.equals(element))
                return true;
            tmp = tmp.next;
        }

        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0 && front == null;
    }

    public void clear() {
        while(front != null) {
            Node<T> tmp = front;
            front = front.next;
            tmp.next = null;
            tmp = front;
        }
        init();
    }

    public Object[] toArray() {
        Object[] arr = new Object[size];
        Node<T> tmp = front;

        for(int i = 0; i < size; i++) {
            arr[i] = tmp.element;
            tmp = tmp.next;
        }

        return arr;
    }

    private void init() {
        front = null;
        back = null;
        size = 0;
    }

    private void init(T element) {
        front = new Node<>(element);
        back = front;
        size = 1;
    }
}
