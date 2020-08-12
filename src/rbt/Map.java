package rbt;

public class Map<K extends Comparable<K>, V> implements MapInterface<K, V> {

    Node root = null;

    public class Node {

        public static final boolean BLACK = true;
        public static final boolean RED = false;

        K key;
        V value;

        Node rightChild;
        Node leftChild;

        boolean color;

        Node(K key, V value) {

            this.key = key;
            this.value = value;
            color = RED;
            leftChild = null;
            rightChild = null;
        }
    }

    @Override
    public void setValue(K key, V value) {

        if (key == null) {

            throw new NullKeyException();
        }

        Node n = new Node(key, value);

        root = insertNode(root, n);
        root.color = Node.BLACK;
    }

    private Node insertNode(Node p, Node n) {

        if (p == null) {
            return n;
        }

        if (n.key.compareTo(p.key) < 0) {
            p.leftChild = insertNode(p.leftChild, n);
        } else if (n.key.compareTo(p.key) > 0) {
            p.rightChild = insertNode(p.rightChild, n);
        } else {
            return n;
        }

        p = leftRotateIfNeeded(p);
        p = rightRotateIfNeeded(p);
        recolorIfNeeded(p);

        return p;
    }

    private Node leftRotateIfNeeded(Node n) {

        if (isNodeRed(n.rightChild) && !isNodeRed(n.leftChild)) {

            n = rotateLeft(n);

            if (isNodeRed(n) && !isNodeRed(n.leftChild)) {

                n.color = Node.BLACK;
                n.leftChild.color = Node.RED;

            }
        }

        return n;
    }

    private Node rightRotateIfNeeded(Node n) {

        if (isNodeRed(n.leftChild) && isNodeRed(n.leftChild.leftChild)) {

            n = rotateRight(n);
            n.color = Node.BLACK;
            n.rightChild.color = Node.RED;
        }

        return n;
    }

    private void recolorIfNeeded(Node n) {

        if (isNodeRed(n.leftChild) && isNodeRed(n.rightChild)) {

            n.color = Node.RED;
            n.rightChild.color = Node.BLACK;
            n.leftChild.color = Node.BLACK;

            if (n == root) {
                n.color = Node.BLACK;
            }
        }
    }

    private Node rotateLeft(Node g) {

        Node p = g.rightChild;
        Node c = p.leftChild;

        p.leftChild = g;
        g.rightChild = c;

        return p;
    }

    private Node rotateRight(Node g) {

        Node p = g.leftChild;
        Node c = p.rightChild;

        p.rightChild = g;
        g.leftChild = c;

        return p;
    }

    private boolean isNodeRed(Node n) {

        if (n == null) {
            return false;
        }

        return (n.color == Node.RED);
    }

    @Override
    public V getValue(K key) {

        if (key == null) {

            throw new NullKeyException();
        }

        Node p = root;

        while (p != null && !p.key.equals(key)) {

            p = p.key.compareTo(key) > 0 ? p.leftChild : p.rightChild;
        }

        return p == null ? null : p.value;
    }
}
