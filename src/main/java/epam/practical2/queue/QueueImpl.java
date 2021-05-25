package epam.practical2.queue;





import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueImpl implements Queue {

    private int sizE;
    private Node last;
    private Node first;
    private int modCount = 0;

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
        sizE = 0;
    }

    @Override
    public int size() {
        return sizE;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl(0);
    }

    private class IteratorImpl implements Iterator<Object> {


        private Node next;
        private int nextInde;


        IteratorImpl(int index) {
            next = (index == sizE) ? null : node(index);
            nextInde = index;
        }

        public boolean hasNext() {
            return nextInde < sizE;
        }

        public Object next() {
            Node lastReturned;
            if (!hasNext())
                throw new NoSuchElementException();

            lastReturned = next;
            next = next.next;
            nextInde++;
            return lastReturned.item;
        }

    }
    Node node(int index) {

        if (index < (sizE >> 1)) {
            Node x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node x = last;
            for (int i = sizE - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }

    @Override
    public void enqueue(Object element) {
        Node lastt = last;
        Node newNode = new Node(element, lastt, null);
        last = newNode;
        if (lastt == null)
            first = newNode;
        else
            lastt.next = newNode;
        sizE++;
        modCount++;
    }


    @Override
    public Object dequeue() {
        Node f = first;
        if (f == null)
            return null;

        Object element = f.item;
        Node next = f.next;
        f.item = null;
        f.next = null;
        first = next;
        if (next == null)
            last = null;
        else
            next.prev = null;
        sizE--;
        modCount++;
        return element;
    }



    @Override
    public Object top() {
        Node f = first;
        if (f == null)
            return null;
        return f.item;
    }

    @Override
    public String toString() {
        Iterator<Object> it = iterator();
        if (!it.hasNext())
            return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (; ; ) {
            Object e = it.next();
            sb.append(e == this ? "(this Collection)" : e);
            if (!it.hasNext())
                return sb.append(']').toString();
            sb.append(',').append(' ');
        }
    }

    public static void main(String[] args) {
//Something1


    }

}
