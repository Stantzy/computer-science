package data_structures.stack;

public class Stack<T> {
    private int size;
    private Node<T> top;

    private static class Node<T> {
        T element;
        Node<T> prev;

        Node(T element) {
            this(element, null);
        }

        Node(T element, Node<T> prev) {
            this.element = element;
            this.prev = prev;
        }
    }

    public Stack() {
        top = null;
        size = 0;
    }

    public Stack(T element) {
        top = new Node<>(element);
        size = 1;
    }

    public T push(T element) {
        if(top == null)
            top = new Node<>(element);
        else
            top = new Node<>(element, top);
        size++;
        return top.element;
    }

    public T pop() {
        if(top == null)
            return null;
        Node<T> tmp = top;
        T element = top.element;
        top = top.prev;
        tmp.prev = null;
        size--;
        return element;
    }

    public T peek() {
        return top == null ? null : top.element;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void clear() {
        Node<T> tmp = top;
        while(tmp != null) {
            top = top.prev;
            tmp.prev = null;
            tmp = top;
        }
        size = 0;
    }
}
