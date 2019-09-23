

public class BigNum {
    private class Node {
        int value;
        Node next;

        public Node(int v, Node n) {
            value = v;
            next = n;
        }
    }

    Node head;

    // Construct an empty BigNum.
    public BigNum() {
        this.head = null;
    }

    // Construct a BigNum from an int, which must be >= 0. Throw an
    // IllegalArgumentException if n < 0.
    public BigNum(int n) {
        if (n < 0)
            throw new IllegalArgumentException("your number must be bigger or equal to 0");

        int nLength = (int) (Math.log10(n) + 1); // find number of digits n has
        int counter = 0;
        while (counter < nLength) {
            counter++;
            int value = n % 10; // value of the next node
            Node minh = new Node(value, null); // next node
            if (head == null) // if our BigNum is empty
                head = minh; // then next node is head
            else { // if not
                Node last = head; // create a node last at head
                while (last.next != null) { // loop through until the end of the linkedlist
                    last = last.next;
                }
                // once we reach the end of the linkedlist
                minh.next = last.next; // make the next node's null
                last.next = minh; // put the next node into our linkedlist
            }

            n = n / 10; // update n
        }
    }

    // Construct a BigNum from a String, which must contain only
    // characters between 0 and 9. Throw an IllegalArgumentException
    // for any other character.
    public BigNum(String s) {
        if (!s.matches("[0-9]+"))
            throw new IllegalArgumentException("your string must only contain number");

        // go through each digit from the end of the string
        for (int i = s.length() - 1; i >= 0; i--) {
            // create a new node with that value
            Node minh = new Node(Character.getNumericValue(s.charAt(i)), null);
            // then put that node in our BigNum
            if (head == null)
                head = minh;
            else {
                Node last = head;
                while (last.next != null) {
                    last = last.next;
                }
                // put the lastest node to the end of BigNum
                minh.next = last.next;
                last.next = minh;
            }
        }
    }

    // helper method to find the length of a BigNum
    public int length() {
        Node last = this.head;
        int counter = 0;
        while (last != null) {
            counter++;
            last = last.next;
        }
        return counter;
    }

    // Convert this BigNum to a String
    public String toString() {
        String result = "";
        Node last = this.head;
        while (last != null) { // go through each node till the end of BigNum
            String valueInString = Integer.toString(last.value); // turn last.value into String type
            result = result.concat(valueInString); // concat that string to our result
            last = last.next; // update last
        }
        // however, now our string result is backward
        // so we have to reverse the order of all digits
        String realResult = "";
        for (int i = result.length() - 1; i >= 0; i--) { // go from the end of string result
            // put each digit into realResult string
            realResult = realResult.concat(Character.toString(result.charAt(i)));
        }
        return realResult;
    }

    // Compare this BigNum to another BigNum, returning 0 if they are equal,
    // a value > 0 if this > other, or a value < 0 if this < other.
    public int compareTo(BigNum other) {
        // the length of 2 BigNum is different, whichever BigNum has more
        // digits is bigger
        if (this.length() > other.length())
            return 1;
        else if (this.length() < other.length())
            return -1;
        else {
            // 2 arrays of int to store digits of 2 BigNum
            int[] BigNumThis = new int[this.length()];
            int[] BigNumOther = new int[other.length()];

            // store all digits of "this" BigNum into first array
            Node thisLast = this.head;
            // go through each node
            for (int i = BigNumThis.length - 1; i >= 0; i--) {
                BigNumThis[i] = thisLast.value;
                thisLast = thisLast.next;
            }

            // store all digits of "other" BigNum into second array
            Node otherLast = other.head;
            // go through each node
            for (int i = BigNumOther.length - 1; i >= 0; i--) {
                BigNumOther[i] = otherLast.value;
                otherLast = otherLast.next;
            }

            // since the 2 array store digit in the right order
            // compare each digit
            for (int i = 0; i < BigNumOther.length; i++) {
                if (BigNumThis[i] > BigNumOther[i])
                    return 1;
                else if (BigNumThis[i] < BigNumOther[i])
                    return -1;
            }

            // if the 2 array contain the exact same digits
            return 0;
        }
    }

    // Add this BigNum to another BigNum, returning a new BigNum which contains the sum of the two
    public BigNum add(BigNum other) {
        BigNum result = new BigNum(); // create BigNum result

        // create 2 node to keep track of 2 BigNum
        Node lastThis = this.head;
        Node lastThat = other.head;
        // create a node to keep track of BigNum result
        Node resultLast = null;

        int value;
        int memory = 0;
        // add 2 values of 2 node a the same index
        // go down both BigNum until both of them reach the end
        while (lastThis != null || lastThat != null) {
            if (lastThat != null && lastThis != null) // if both node still exist
                value = lastThis.value + lastThat.value + memory;
                // one of the two keeping track node has reached the end
            else if (lastThis == null)
                value = lastThat.value + memory;
            else
                value = lastThis.value + memory;

            memory = value / 10; // if we add and get something > 10
            // make sure to add 1 more to the next value

            // create a new node
            Node minh = new Node(value % 10, null);

            // put node minh in the end of our BigNum result
            if (result.head == null)
                result.head = minh;
            else {
                // Node resultLast;
                resultLast = result.head;
                while (resultLast.next != null) {
                    resultLast = resultLast.next;
                }
                minh.next = resultLast.next;
                resultLast.next = minh;
            }

            // we only update the two last nodes
            // if they have not reached to the end yet
            if (lastThis != null)
                lastThis = lastThis.next;
            if (lastThat != null)
                lastThat = lastThat.next;
        }

        // we have to take care of the very last case when we may
        // have to write out both of the digits
        if (memory == 1) {
            Node add = new Node(1, null);
            resultLast.next.next = add;
        }

        return result;
    }

    // I have not finished this extra credit part so you can just ignore this method
    public BigNum multiply(int other) {
        if (0 > other || other > 9)
            throw new IllegalArgumentException("only digits from 0 to 9");

        BigNum result = new BigNum();
        int memory = 0;
        // if multiply by 0
        if (other == 0) {
            result.head.value = 0;
            return result;
        }
        else if (other == 1) { // multiphy by 1
            BigNum otherItself = new BigNum(other);
            return otherItself;
        }
        else { // multiply by any other number
            for (int i = 0; i < this.length(); i++) {
                int valueForAnswer = this.head.value * other + memory;
                this.head = this.head.next;
                memory = valueForAnswer / 10;
                if (this.head != null) {
                    Node answer = new Node((valueForAnswer % 10), null);
                    if (result.head == null)
                        result.head = answer;
                    else {
                        Node resultLast;
                        resultLast = result.head;
                        while (resultLast.next != null) {
                            resultLast = resultLast.next;
                        }
                        answer.next = resultLast.next;
                        resultLast.next = answer;
                    }
                }
                else {
                    Node answer2 = new Node(valueForAnswer / 10, null);
                    Node answer1 = new Node(valueForAnswer % 10, answer2);
                    Node resultLast;
                    resultLast = result.head;
                    while (resultLast.next != null) {
                        resultLast = resultLast.next;
                    }
                    resultLast.next = answer1;
                }
            }
        }
        return result;
    }


    // Use this to test your other BigNum methods
    public static void main(String[] args) {
        BigNum test1 = new BigNum("9999");
        BigNum test2 = new BigNum(8888);
        StdOut.println("Compare: " + test1.compareTo(test2));
        StdOut.println(test1);
        StdOut.println(test2);
        BigNum sum = test1.add(test2);
        StdOut.println(sum);
        StdOut.println("test1's length: " + test1.length());
        StdOut.println(test1.toString() + ": is string");
    }
}
