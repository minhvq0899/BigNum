

public class LinkedList {
    private class Node {
        int value;
        Node next;

        public Node(int v, Node n) {
            value = v;
            next = n;
        }

    }

    Node head;

    public LinkedList() {
        head = null;
    }

    public void insert(int n) {
        Node temp = new Node(n, null);
        if (head == null)
            head = temp;
        else {
            Node last = head;
            while (last.next != null)
                last = last.next;

            last.next = temp;
        }
    }

    public void insertTop(int n) {
        Node temp = new Node(n, head);
        head = temp;
    }

    public void sortedInsert(int n) {
        Node temp = new Node(n, null);
        if (head == null)
            head = temp;
        else if (head.value >= n) {
            temp.next = head;
            head = temp;

        }
        else {
            Node last = head;
            while (last.next != null && last.next.value < n) {
                last = last.next;
            }
            temp.next = last.next;
            last.next = temp;

        }
    }

    public boolean exists(int t) {
        Node temp = head;
        while (temp != null) {
            if (temp.value == t)
                return true;
            temp = temp.next;
        }
        return false;
    }

    public void remove(int t) {
        if (head.value == t) {
            head = head.next;
            return;
        }
        else {
            Node temp = head;
            while (temp.next != null) { // how do we check the last linkedlist then?
                if (temp.next.value == t) {
                    temp.next = temp.next.next;
                    return;
                }
                temp = temp.next;
            }

            throw new RuntimeException("not found");
        }
    }

    public void printAll() {
        Node temp = head;
        while (temp != null) {
            StdOut.println(temp.value);
            temp = temp.next;
        }
    }

    public int lastPos(int value, Node start) {
        int index = 0;
        int found = -1;
        Node current = start;
        while (current != null) {
            if (current.value == value) {
                found = index;
            }
            index++;
            current = current.next;
        }
        return found;
    }

    public static void main(String[] args) {
        LinkedList l = new LinkedList();
        l.sortedInsert(1);
        l.sortedInsert(4);
        l.sortedInsert(20);
        l.sortedInsert(8);
        l.sortedInsert(10);
        l.sortedInsert(9);


        l.printAll();
    }
}
