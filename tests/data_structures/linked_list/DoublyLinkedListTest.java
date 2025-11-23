package tests.data_structures.linked_list;

import data_structures.linked_list.DoublyLinkedList;
import data_structures.linked_list.SinglyLinkedList;

public class DoublyLinkedListTest {
    private static final int LIST_SIZE = 100;

    public static void main(String[] args) {
        System.out.println(testEmptyList());
        System.out.println(testAdding());
        System.out.println(testDeleting());
        System.out.println(testInsertion());
        System.out.println(testSearching());
        System.out.println(testStability());
    }

    private static boolean testEmptyList() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        return list.isEmpty() && list.getSize() == 0;
    }

    private static boolean testAdding() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        fillList(list);

        boolean condition1 = list.getFirst() == 100 && list.getLast() == 10000;
        boolean condition2 = list.getSize() == LIST_SIZE * 2;

        return condition1 && condition2;
    }

    private static boolean testDeleting() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>(1);

        list.removeFirst();
        boolean condition1 = list.isEmpty();

        list.addFirst(2);
        list.removeLast();
        boolean condition2 = list.isEmpty();

        fillList(list);
        int removed = list.removeFirst();
        int removed2 = list.removeLast();
        boolean condition3 = removed == 100 && removed2 == 10000;

        int headValue = list.getFirst();
        int tailValue = list.getLast();
        boolean condition4 = headValue == 99 && tailValue == 9801;

        list.clear();
        boolean condition5 = list.isEmpty();

        return condition1 && condition2 &&
                condition3 && condition4 && condition5;
    }

    private static boolean testInsertion() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        fillList(list);
        list.insertAt(-1, 1);
        boolean condition1 = list.getFirst() == -1;

        list.insertAt(-2, 202);
        boolean condition2 = list.getLast() == -2;

        Integer result = list.insertAt(1, -1);
        boolean condition3 = result == null;

        return condition1 && condition2 && condition3;
    }

    private static boolean testSearching() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        fillList(list);

        boolean condition1 = list.indexOf(9801) != -1;
        boolean condition2 = list.indexOf(-1) == -1;
        boolean condition3 = list.indexOf(100) != -1;
        boolean condition4 = list.indexOf(10000) != -1;

        return condition1 && condition2 && condition3 && condition4;
    }

    private static boolean testStability() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        list.addFirst(10);
        list.addLast(20);
        list.addFirst(5);
        list.removeLast();
        list.addLast(30);
        list.removeFirst();
        list.addLast(40);

        boolean condition1 = list.getSize() == 3 &&
                list.getFirst() == 10 &&
                list.getLast() == 40;

        list.clear();

        list.addLast(1);
        list.removeLast();
        list.addFirst(2);
        list.addLast(3);
        list.removeFirst();
        list.addFirst(4);
        list.addLast(5);
        list.removeLast();
        list.removeFirst();
        list.removeFirst();

        boolean condition2 = list.getSize() == 0 &&
                list.getFirst() == null &&
                list.getLast() == null;

        list.clear();

        for(int i = 0; i < 20; i++)
            list.addFirst(i);

        for(int i = 0; i < 10; i++)
            list.removeFirst();

        list.addFirst(100);
        list.addLast(200);

        for(int i = 0; i < 5; i++)
            list.removeLast();

        list.addLast(300);
        list.removeFirst();
        list.addFirst(100);

        boolean condition3 = list.getSize() == 8 &&
                list.getLast() == 300 &&
                list.getFirst() == 100;

        return condition1 && condition2 && condition3;
    }

    private static void fillList(DoublyLinkedList<Integer> list) {
        for(int i = 1; i <= LIST_SIZE; i++) {
            list.addFirst(i);
            list.addLast(i * i);
        }
    }
}
