package tests.data_structures.linked_list;

import data_structures.stack.Stack;

public class StackTest {
    public static final int STACK_SIZE = 1000;

    public static void main(String[] args) {
        testPushAndPop();
        testPeekAndSize();
        testIsEmpty();
        testClear();
        testPopFromEmptyStack();
        testPeekFromEmptyStack();
    }

    private static void testPushAndPop() {
        Stack<Integer> stack = new Stack<>();
        stack.push(10);
        stack.push(20);
        stack.push(30);

        int element;
        element = stack.pop();
        assert element == 30 : "testPushAndPop: expected 30";

        element = stack.pop();
        assert element == 20 : "testPushAndPop: expected 20";

        element = stack.pop();
        assert element == 10 : "testPushAndPop: expected 10";
    }

    private static void testPeekAndSize() {
        Stack<Integer> stack = new Stack<>();
        int element;
        int size;

        stack.push(5);
        stack.push(15);

        element = stack.peek();
        assert element == 15 : "testPeek: expected 15, got " + element;
        size = stack.size();
        assert size == 2 : "testSize: expected 2, got " + size;

        stack.pop();

        element = stack.peek();
        assert element == 5 : "testPeek: expected 5, got " + element;
        size = stack.size();
        assert size == 1 : "testSize: expected 1, got " + size;
    }

    private static void testIsEmpty() {
        Stack<Integer> stack = new Stack<>();

        assert stack.isEmpty() : "testIsEmpty: new stack must be empty";

        stack.push(1);
        assert !stack.isEmpty() :
                "testIsEmpty: stack must not be empty after pushing an element";

        stack.pop();
        assert stack.isEmpty() : "testIsEmpty: stack must be empty here";
    }

    private static void testClear() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        stack.clear();
        assert stack.isEmpty() : "testClear: stack must be clear";
        assert stack.size() == 0 : "testClear: size must be zero";
    }

    private static void testPopFromEmptyStack() {
        Stack<Integer> stack = new Stack<>();
        assert stack.isEmpty() : "testPopFromEmptyStack: stack must be clear";
        assert stack.pop() == null :
                "testPopFromEmptyStack: must be null from empty stack";
    }

    private static void testPeekFromEmptyStack() {
        Stack<Integer> stack = new Stack<>();
        assert stack.isEmpty() : "testPeekFromEmptyStack: stack must be clear";
        assert stack.peek() == null :
                "testPeekFromEmptyStack: must be null from empty stack";
    }
}
