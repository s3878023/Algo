package W3;

public class P1 {
    public static void main(String[] args) {
        Node n1, n2, n3, n4, n5;

        n5 = new Node(5, null);
        n4 = new Node(4, n5);
        n3 = new Node(3, n4);
        n2 = new Node(2, n3);
        n1 = new Node(1, n2);
        n5.next = n2;

        Node node = n1;
        int count = 0;
        System.out.println("Before Loop Is Remove");
        while (count < 10 && node != null) {
            System.out.println(node.data);
            node = node.next;
            count ++;
        }

        Node fast, slow;
        fast = slow = n1;

        while (true) {
            if (fast.next == null || fast.next.next == null) {
                return;
            }
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        int nodes = 0;
        do {
            slow = slow.next;
            nodes++;
        }while (slow!=fast);

        fast = slow = n1;
        for (int i = 0; i < nodes; i++) {
            fast = fast.next;
        }

        while (slow.next != fast.next) {
            slow = slow.next;
            fast= fast.next;
        }
        fast.next = null;

        node = n1;
        count = 0;
        System.out.println("After loop removal");
        while (count < 10 && node != null) {
            System.out.println(node.data);
            node = node.next;
            count++;
        }
    }

    static class Node {
        int data;
        Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
