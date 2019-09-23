/* *****************************************************************************
 *  Name:    Alan Turing
 *  NetID:   aturing
 *  Precept: P00
 *
 *  Description:  Prints 'Hello, World' to the terminal window.
 *                By tradition, this is everyone's first program.
 *                Prof. Brian Kernighan initiated this tradition in 1974.
 *
 **************************************************************************** */

public class MyStack<Item> {


    private class Node {
        Item value;
        Node next;

        public Node(Item v) {
            value = v;
        }
    }

    Node head;

    public MyStack() {
        head = null;
    }

    public void push(Item v) {
        Node tmp = new Node(v);
        tmp.next = head;
        head = tmp;
    }

    public Item pop() {
        if (head == null)
            throw new RuntimeException("popping from an empty stack");
        Item tmp = head.value;
        head = head.next;
        return tmp;
    }

    public static void main(String[] args) {
        MyStack<Integer> ms = new MyStack();
        ms.push(12);
        ms.push(2);
        StdOut.println(ms.pop());
    }
}
