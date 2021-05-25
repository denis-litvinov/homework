package epam.practical2.list;


import java.util.Iterator;
import java.util.NoSuchElementException;


public class ListImpl implements List {

    private int size;
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
        size = 0;

    }

    @Override
    public int size() {
        return size;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl(0);
    }

    private class IteratorImpl implements Iterator<Object> {

        private Node next;
        private int nextIndex;


        IteratorImpl(int index) {
            next = (index == size) ? null : node(index);
            nextIndex = index;
        }

        public boolean hasNext() {
            return nextIndex < size;
        }

        public Object next() {
            Node lastReturned;
            if (!hasNext())
                throw new NoSuchElementException();

            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.item;
        }


    }

    Node node(int in) {
        if (in < (size >> 1)) {
            Node x = first;
            for (int i = 0; i < in; i++)
                x = x.next;
            return x;
        } else {
            Node x = last;
            for (int i = size - 1; i > in; i--)
                x = x.prev;
            return x;
        }
    }

    @Override
    public void addFirst(Object element) {
        linkFirst(element);

    }

    private void linkFirst(Object element) {
        Node f = first;
        Node newNode = new Node(element, null, f);
        first = newNode;
        if (f == null)
            last = newNode;
        else
            f.prev = newNode;
        size++;

    }

    @Override
    public void addLast(Object element) {
        linkLast(element);
    }

    void linkLast(Object element) {
        Node las = last;
        Node newNode = new Node(element, last, null);
        last = newNode;
        if (las == null)
            first = newNode;
        else
            las.next = newNode;
        size++;

    }

    @Override
    public void removeFirst() {
        Node f = first;
        if (f == null)
            throw new NoSuchElementException();
        unlinkFirst(f);
    }

    private Object unlinkFirst(Node f) {
        Object element = f.item;
        Node next = f.next;
        f.item = null;
        f.next = null; // help GC
        first = next;
        if (next == null)
            last = null;
        else
            next.prev = null;
        size--;
        return element;
    }

    @Override
    public void removeLast() {
        Node l = last;
        if (l == null)
            throw new NoSuchElementException();
        unlinkLast(l);
    }

    private Object unlinkLast(Node las) {
        Object element = las.item;
        Node prev = las.prev;
        las.item = null;
        las.prev = null; // help GC
        last = prev;
        if (prev == null)
            first = null;
        else
            prev.next = null;
        size--;
        return element;
    }

    @Override
    public Object getFirst() {
        Node fir = first;
           if (fir == null)
              return null;
        return fir.item;
    }

    @Override
    public Object getLast() {
        Node las = last;
          if (las == null)
             return null;
        return las.item;
    }

    @Override
    public Object search(Object element) {

            for (Node x = first; x != null; x = x.next) {
                if (element.equals(x.item))
                    return element;

            }

        return null;
    }

    @Override
    public boolean remove(Object element) {
        if (element == null) {
            for (Node x = first; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node x = first; x != null; x = x.next) {
                if (element.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    Object unlink(Node x) {

        Object element = x.item;
        Node next = x.next;
        Node prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
        return element;
    }


    @Override
    public String toString() {
        Iterator<Object> iter = iterator();
        if (!iter.hasNext())
            return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (; ; ) {
            Object e = iter.next();
            sb.append(e == this ? "(this Collection)" : e);
            if (!iter.hasNext())
                return sb.append(']').toString();
            sb.append(',').append(' ');
        }
    }


    public static void main(String[] args) {
        //Something




    }
}
