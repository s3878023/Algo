package W4;

import W3.LinkedListQueue;

import java.util.Random;


public class P1 {
    public static void main(String[] args) {
        TreeTraversal<Integer> tree = new TreeTraversal<>();
        Random rnd = new Random(System.currentTimeMillis());
        for (int i = 0; i < 10; i++) {
            int value = rnd.nextInt(100);
            System.out.println("Add: " + value);
            tree.add(value);
        }
        // Display results
        System.out.println("Pre Order Traversal");
        tree.preOrder();
        System.out.println("Post Order Traversal");
        tree.postOrder();
        System.out.println("In Order Traversal");
        tree.inOrder();
        System.out.println("Breadth First Traversal");
        tree.breadthFirst();
    }
}

    class TreeTraversal<T extends Comparable<T>> extends BST<T> {
        public void preOrder() {
            preOrderRecursive(root);
        }

        private void preOrderRecursive(BinaryTreeNode<T> node) {
            if (node != null) {
                System.out.println(node.data);
                preOrderRecursive(node.left);
                preOrderRecursive(node.right);
            }
        }

        public void postOrder() {
            postOrderRecursive(root);
        }

        private void postOrderRecursive(BinaryTreeNode<T> node) {
            if (node != null) {
                postOrderRecursive(node.left);
                postOrderRecursive(node.right);
                System.out.println(node.data);
            }
        }

        public void inOrder() {
            inOrderRecursive(root);
        }

        private void inOrderRecursive(BinaryTreeNode<T> node) {
            if (node != null) {
                inOrderRecursive(node.left);
                System.out.println(node.data);
                inOrderRecursive(node.right);
            }
        }

        public void breadthFirst() {
            LinkedListQueue<BinaryTreeNode<T>> queue = new LinkedListQueue<>();
            queue.enQueue(root);
            while (!queue.isEmpty()){
                BinaryTreeNode<T> node = queue.peekFront();
                queue.deQqueue();
                if (node != null) {
                    System.out.println(node.data);
                    queue.enQueue(node.left);
                    queue.enQueue(node.right);
                }
            }

        }


    }