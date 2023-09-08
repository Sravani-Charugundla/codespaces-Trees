import java.util.*;

public class Traversals {
    public class Node {
        private int value;
        private Node left;
        private Node right;
       

        public Node(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    private Node root;

    public Traversals() {
        root = null;
    }

    public void insert(int value) {
        root = insert(value, root);
    }

    public Node insert(int value, Node node) {
        if (node == null) {
            return new Node(value);
        } else if (value < node.value) {
            node.left = insert(value, node.left);
        } else if (value > node.value) {
            node.right = insert(value, node.right);
        }
        // Add a default return statement to handle cases where value == node.value.
        return node;
    }

    public void display() {
        display(root, "Root Node");

    }

    public void display(Node node, String details) {
        if (node == null) {
            return;
        }
        System.out.println(details + node.getValue());
        display(node.left, "Left of Node " + node.getValue() + ":");
        display(node.right, "Right of Node " + node.getValue() + ":");
    }

    public void postorder()
    {
        postorder(root,"Root Node");
    }
    public void postorder(Node node,String details)
    {
        if(node==null)
        {
            return;
        }
        display(node.left,"Left of Node"+node.getValue()+":");
        display(node.right,"Right of Node"+node.getValue()+":");
        System.out.println(details + node.getValue());
    }

    public static void main(String[] args) {
        String dec;
        Scanner sc = new Scanner(System.in);
        Traversals trav = new Traversals();
        do {
            System.out.println("Do you want to insert a value (Y/N)?");
            dec = sc.next();
            if (dec.equals("Y")) {
                System.out.println("Enter value to be inserted:");
                int val = sc.nextInt();
                trav.insert(val);
            }
        } while (dec.equals("Y"));
        trav.display();
        System.out.println("PostOrder");
        trav.postorder();
    }
}
