package data_structures.tree;

import java.util.NoSuchElementException;

public class BinarySearchTree<T extends Comparable<T>> {
    private Node<T> root;
    private int size;

    private static class Node<T extends Comparable<T>> {
        T element;
        Node<T> left;
        Node<T> right;

        Node(T element) {
            this(element, null, null, null);
        }

        Node(T element, Node<T> left, Node<T> right, Node<T> parent) {
            this.element = element;
            this.left = left;
            this.right = right;
        }
    }

    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    public void put(T element) {
        root = put(element, root);
        size++;
    }

    private Node<T> put(T element, Node<T> node) {
        if(node == null)
            return new Node<>(element);

        int compareResult = element.compareTo(node.element);

        if (compareResult < 0) {
            node.left = put(element, node.left);
        } else if(compareResult > 0) {
            node.right = put(element, node.right);
        } else {
            throw new IllegalArgumentException(
                    "Element already present in the tree"
            );
        }

        return node;
    }

    public boolean remove(T element) {
        int oldSize = size;
        root = remove(root, element);
        return size < oldSize;
    }

    private Node<T> remove(Node<T> node, T element) {
        if(node == null)
            return null;

        if(element.compareTo(node.element) < 0) {
            node.left = remove(node.left, element);
        } else if(element.compareTo(node.element) > 0) {
            node.right = remove(node.right, element);
        } else {
            if(node.left == null || node.right == null) {
                node = (node.left == null) ? node.right : node.left;
                size--;
            } else {
                Node<T> maxInLeft = getMax(node.left);
                node.element = maxInLeft.element;
                node.left = remove(node.left, maxInLeft.element);
            }
        }

        return node;
    }

    public T search(T element) {
        return search(root, element).element;
    }

    private Node<T> search(Node<T> node, T element) {
        if(node == null) {
            throw new NoSuchElementException(
                    "The element = " + element + " does not present in the tree"
            );
        }

        int compareResult = element.compareTo(node.element);

        if(compareResult == 0)
            return node;

        if(compareResult < 0) {
            return search(node.left, element);
        }

        return search(node.right, element);
    }

    public boolean contains(T element) {
        boolean exceptionThrown = false;

        try {
            search(element);
        } catch (RuntimeException e) {
            exceptionThrown = true;
        }

        return !exceptionThrown;
    }

    public T getMin() {
        return getMin(root).element;
    }

    private Node<T> getMin(Node<T> root) {
        Node<T> tmp = root;

        if(tmp == null)
            throw new NoSuchElementException("The tree is empty");

        while(tmp.left != null)
            tmp = tmp.left;

        return tmp;
    }

    public T getMax() {
        return getMax(root).element;
    }

    private Node<T> getMax(Node<T> root) {
        Node<T> tmp = root;

        if(tmp == null)
            throw new NoSuchElementException("The tree is empty");

        while(tmp.right != null)
            tmp = tmp.right;

        return tmp;
    }

    public void clear() {
        clearSubtree(root);
        root = null;
        size = 0;
    }

    private void clearSubtree(Node<T> node) {
        if(node == null)
            return;

        clearSubtree(node.left);
        clearSubtree(node.right);

        node.left = null;
        node.right = null;
        node.element = null;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0 && root == null;
    }

    public void printInOrderTraversal() {
        printInOrderTraversal(root);
    }

    private void printInOrderTraversal(Node<T> node) {
        if(node == null)
            return;
        printInOrderTraversal(node.left);
        System.out.println(node.element);
        printInOrderTraversal(node.right);
    }

    public void printPreOrderTraversal() {
        printPreOrderTraversal(root);
    }

    private void printPreOrderTraversal(Node<T> node) {
        if(node == null)
            return;
        System.out.println(node.element);
        printPreOrderTraversal(node.left);
        printPreOrderTraversal(node.right);
    }

    public void printPostOrderTraversal() {
        printPostOrderTraversal(root);
    }

    private void printPostOrderTraversal(Node<T> node) {
        if(node == null)
            return;
        printPostOrderTraversal(node.left);
        printPostOrderTraversal(node.right);
        System.out.println(node.element);
    }
}
