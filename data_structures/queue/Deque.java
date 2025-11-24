package data_structures.queue;

public class Deque<T> {
    private int size;
    private Node<T> front;
    private Node<T> back;

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

    public Deque() {
        init();
    }

    public Deque(T element) {
        init(element);
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

    public void pushFront(T element) {
        if(front == null && size == 0) {
            init(element);
        } else {
            Node<T> tmp = front;
            front = new Node<>(element, front, null);
            tmp.prev = front;
            size++;
        }
    }

    public T popFront() {
        if(front == null)
            return null;

        if(size == 1 && front == back) {
            T element = front.element;
            init();
            return element;
        }

        Node<T> tmp = front;
        front = front.next;
        if(front != null)
            front.prev = null;
        tmp.next = null;
        tmp.prev = null;
        size--;

        return tmp.element;
    }

    public void pushBack(T element) {
        if(front == null && size == 0) {
            init(element);
        } else {
            Node<T> tmp = back;
            back = new Node<>(element, null, back);
            tmp.next = back;
            size++;
        }
    }

    public T popBack() {
        if(back == null)
            return null;

        if(size == 1 && front == back) {
            T element = front.element;
            init();
            return element;
        }

        Node<T> tmp = back;
        back = back.prev;
        if(back != null)
            back.next = null;
        tmp.prev = null;
        tmp.next = null;
        size--;

        return tmp.element;
    }

    public T peekFront() {
        return front == null ? null : front.element;
    }

    public T peekBack() {
        return back == null ? null : back.element;
    }

    public boolean isEmpty() {
        return size == 0 && front == null;
    }

    public int size() {
        return size;
    }

    public void clear() {
        Node<T> tmp = front;

        while(tmp != null) {
            front = front.next;
            tmp.next = null;
            tmp = front;
        }

        init();
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

    public Object[] toArray() {
        Object[] arr = new Object[size];
        Node<T> tmp = front;

        for(int i = 0; i < size; i++) {
            arr[i] = tmp.element;
            tmp = tmp.next;
        }

        return arr;
    }
}
