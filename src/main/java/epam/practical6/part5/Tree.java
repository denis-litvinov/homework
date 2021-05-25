package epam.practical6.part5;

public class Tree<E extends Comparable<E>> {
    public E get() {
        return root.element;
    }


    private Node<E> root;

    private Node<E> parent;
    private Node<E> current;

    public void setParent(Node<E> parent) {
        this.parent = parent;
    }

    public void setCurrent(Node<E> current) {
        this.current = current;
    }

    public Node<E> getParent() {
        return parent;
    }

    public Node<E> getCurrent() {
        return current;
    }

    public boolean remove(E element) {
        final int left = -1;
        final int right = 1;
        final int noStep = 0;

        setParent(null);
        setCurrent(root);
        int comp = 0;
        int lastStep = noStep;

        while (current != null
                && (comp = current.element.compareTo(element)) != 0) {
            parent = current;
            if (comp < 0) {
                lastStep = right;
                current = current.right;
            } else {
                lastStep = left;
                current = current.left;
            }
        }
        if (current == null) {
            return false;
        }
        if (current.left == null) {
            if (lastStep == right) {
                parent.right = current.right;
            } else if (lastStep == left) {
                parent.left = current.right;
            } else {
                root = current.right;
            }
        } else if (current.right == null) {
            if (lastStep == right) {
                parent.right = current.left;
            } else if (lastStep == left) {
                parent.left = current.left;
            } else {
                root = current.left;
            }
        } else {
            Node<E> nodeToReplace = current.right;
            parent = current;
            while (nodeToReplace.left != null) {
                parent = nodeToReplace;
                nodeToReplace = nodeToReplace.left;
            }
            current.element = nodeToReplace.element;
            if (parent.equals(current)) {
                parent.right = nodeToReplace.right;
            } else {
                parent.left = nodeToReplace.right;
            }
        }
        return true;
    }

    public void add(E[] elements) {
        for (int i = 0; i < elements.length; i++) {
            add(elements[i]);
        }
    }

    public boolean add(E e) {
        if (root == null) {
            root = new Node<>(e, null, null);
            return true;
        }
        return add(root, e);
    }

    public boolean add(Node<E> node, E e) {
        if (e.compareTo(node.element) < 0) {
            if (node.left == null) {
                node.left = new Node<>(e, null, null);
                return true;
            }
            return add(node.left, e);
        }
        if (e.compareTo(node.element) > 0) {
            if (node.right == null) {
                node.right = new Node<>(e, null, null);
                return true;
            }
            return add(node.right, e);
        }
        return false;
    }

    public void print(){
        goTree(root, "");
    }
    private void goTree(Node <E> node, String s){
        if (node != null){
            goTree(node.left, s + "  ");
            System.out.println(s + node.element);
            goTree(node.right, s + "  ");
        }
    }



    public static class Node<E> {

        private E element;
        private Node<E> left;
        private Node<E> right;

        Node(E element, Node<E> left, Node<E> right) {
            this.element = element;
            this.left = left;
            this.right = right;

        }

    }

}


