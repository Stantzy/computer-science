package data_structures.linked_list;

public class SinglyLinkedList<E> {
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    public static class Node<E> {
        public E element;
        public Node<E> next;

        Node(E value) {
            this(value, null);
        }

        Node(E value, Node<E> next) {
            this.element = value;
            this.next = next;
        }
    }

    public SinglyLinkedList(E value) {
        head = new Node<E>(value, null);
        tail = head;
        size++;
    }

    public SinglyLinkedList() {
        head = null;
        tail = null;
    }

    public E getFirst() {
        return head != null ? head.element : null;
    }

    public E getLast() {
        return tail != null ? tail.element : null;
    }

    public int getSize() {
        return size;
    }

    public E addFirst(E element) {
        head = new Node<E>(element, head);

        if(tail == null)
            tail = head;
        size++;

        return head.element;
    }

    public E addLast(E element) {
        E added = null;

        if(tail != null) {
            tail.next = new Node<E>(element);
            tail = tail.next;
            added = tail.element;
            size++;
        }

        return added == null ? addFirst(element) : added;
    }

    public E removeFirst() {
        Node<E> tmp = head;

        if(isEmpty())
            return null;

        if(head == tail)
            tail = null;

        E removed = head.element;
        head = head.next;
        tmp.next = null;
        size--;

        return removed;
    }

    public E removeLast() {
        Node<E> tailPrev;
        Node<E> tmp = head;

        if(isEmpty())
            return null;

        while(tmp.next != tail && tmp.next != null) {
            tmp = tmp.next;
        }

        tailPrev = tmp;
        tmp = tail;
        E removed = tail.element;
        if(tailPrev == tmp) {
            tmp.next = null;
            tail = null;
            head = null;
        } else {
            tail = tailPrev;
            tail.next = null;
        }

        size--;

        return removed;
    }

    public E insertAt(E element, int position) {
        Node<E> tmp = head;

        if(position < 1 || head == null)
            return null;

        for(int i = 1; i < position && i <= size; i++) {
            if(tmp.next != null)
                tmp = tmp.next;
        }

        Node<E> next = position == 1 ? tmp : tmp.next;
        Node<E> nodeToInsert = new Node<>(element, next);
        if(position == 1) {
            nodeToInsert.next = head;
            head = nodeToInsert;
        } else if(position >= getSize()) {
            tail = nodeToInsert;
            tmp.next = nodeToInsert;
        } else {
            tmp.next = nodeToInsert;
        }
        size++;

        return tmp.next.element;
    }

    public E removeAt(int position) {
        int count = 1;
        Node<E> tmp = head;

        if(position < 1)
            return null;

        for(int i = 1; i < position && i <= size; i++) {
            if(tmp.next != null)
                tmp = tmp.next;
        }

        Node<E> removed = tmp.next;
        tmp.next = removed != null ? removed.next : null;
        size--;

        return removed != null ? removed.element : null;
    }

    public void clear() {
        Node<E> tmp = head;

        while(tmp != null) {
            tmp = tmp.next;
            head.next = null;
            head = tmp;
        }

        tail = null;
        size = 0;
    }

    public E find(E elementToFind) {
        Node<E> tmp = head;

        while(tmp != null) {
            if(tmp.element.equals(elementToFind))
                return tmp.element;
            tmp = tmp.next;
        }

        return null;
    }

    public boolean contains(E elementToCheck) {
        return find(elementToCheck) != null;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
