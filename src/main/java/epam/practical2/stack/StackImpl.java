package epam.practical2.stack;



import java.util.Iterator;
import java.util.NoSuchElementException;


public class StackImpl implements Stack {
    private int siZe;
    private Node last;
    private Node first;


    private static class Node {
        Object item;
        Node prev;
        Node next;

        Node(Object item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;

        }
    }

    @Override
    public void push(Object element) {
        Node l = last;
        Node newNode = new Node(element, last, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        siZe++;

    }

    @Override
    public Object pop() {
        Node l = last;
        if (l == null)
            return null;

        Object element = l.item;
        Node prev = l.prev;
        l.item = null;
        l.prev = null;
        last = prev;
        if (prev == null)
            first = null;
        else
            prev.next = null;
        siZe--;
        return element;
    }

    @Override
    public Object top() {
        Node l = last;
        if (l == null)
            return null;
        return l.item;
    }

    @Override
    public void clear() {
        Node x = first;
        while (x != null){
            Node next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = last = null;
        siZe = 0;
    }

    @Override
    public int size() {
        return siZe;
    }

    @Override
    public Iterator<Object> iterator() {
        return new IteratorImpl(size());
    }

    private class IteratorImpl implements Iterator<Object> {

        private Node next;
        private int nextIndex;

        IteratorImpl(int index) {
            next = (index == siZe) ? null : node(index);
            nextIndex = index;
        }

        @Override
        public boolean hasNext() {
            return nextIndex > 0;
        }

        @Override
        public Object next() {
            Node lastReturned;

            if (!hasNext())
                throw new NoSuchElementException();

            lastReturned = next = (next == null) ? last : next.prev;
            nextIndex--;
            return lastReturned.item;
        }

        private Node node(int indexx) {

            if (indexx < (siZe >> 1)) {
                Node x = first;
                for (int i = 0; i < indexx; i++)

                    x = x.next;
                return x;
            } else {
                Node x = last;
                for (int i = siZe - 1; i > indexx; i--)
                    x = x.prev;
                return x;
            }
        }

    }




    public String toString() {
        String result;
        StringBuilder sb = new StringBuilder();
        Object[] results = new Object[siZe];
        int i = 0;
        if (siZe == 0) {
            sb.append("[");
            sb.append("]");
            result = sb.toString();
            return result;
        }
        for (Node x = first; x != null; x = x.next) {
            results[i++] = x.item;
        }
        sb.append("[");
        int j = 0;
        for (; ; ) {
            if (j > results.length - 1) {
                break;
            }

            if (j == results.length - 1) {
                sb.append(results[j]);
                sb.append("]");

            } else {
                sb.append(results[j]);
                sb.append(", ");
            }
            j++;
        }
        result = sb.toString();
        return result;
    }

    public static void main(String[] args) {
        //Something2

    }
}
