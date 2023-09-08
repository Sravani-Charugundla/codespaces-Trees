class AVLTree {
    public class Node {
        private int value;
        private Node left;
        private Node right;
        private int height;

        public Node(int value) {
            this.value = value;
            this.height = 1;
        }

        public int getValue() {
            return value;
        }
    }

    private Node root;

    public AVLTree() {
        root = null;
    }

    public int height(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    public int balanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    public void insert(int value) {
        root = insert(value, root);
    }

    private Node insert(int value, Node node) {
        if (node == null) {
            return new Node(value);
        }

        if (value < node.value) {
            node.left = insert(value, node.left);
        } else if (value > node.value) {
            node.right = insert(value, node.right);
        } else {
            // Duplicate values are not allowed (you can decide how to handle them).
            return node;
        }

        node.height = Math.max(height(node.left), height(node.right)) + 1;

        int balance = balanceFactor(node);

        // Left-Left case
        if (balance > 1 && value < node.left.value) {
            return rotateRight(node);
        }

        // Right-Right case
        if (balance < -1 && value > node.right.value) {
            return rotateLeft(node);
        }

        // Left-Right case
        if (balance > 1 && value > node.left.value) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // Right-Left case
        if (balance < -1 && value < node.right.value) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    public void display() {
        display(this.root, "Root Node:");
    }

    private void display(Node node, String details) {
        if (node == null) {
            return;
        }
        System.out.println(details + node.getValue());
        display(node.left, "Left child of " + node.getValue() + ":");
        display(node.right, "Right child of " + node.getValue() + ":");
    }

    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();
        avlTree.insert(1);
        avlTree.insert(2);
        avlTree.insert(3);
        avlTree.insert(4);
        avlTree.insert(5);
        avlTree.insert(6);
        avlTree.insert(7);

        System.out.println("Tree is balanced: " + avlTree.isBalanced(avlTree.root));
        avlTree.display();
    }

    public boolean isBalanced(Node root) {
        if (root == null) {
            return true;
        }

        int balance = Math.abs(height(root.left) - height(root.right));

        return balance <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }
}
