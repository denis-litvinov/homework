package epam.practical2.array;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class ArrayImpl implements Array {

    private Object[] elements;
    private int index;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;
    static String indexStr = "Index: ";
    static String sizeString = ", Size";

    public ArrayImpl() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    public ArrayImpl(int size) {
        elements = new Object[size];
    }



    @Override
    public void add(Object element) {
        if (index == elements.length) {
            growArray();
        }
        elements[index] = element;
        index++;
        size++;
    }


    private void growArray() {
        Object[] newArray = new Object[elements.length * 2];
        System.arraycopy(elements, 0, newArray, 0, index - 1);
        elements = newArray;
    }


    @Override
    public void set(int index, Object element) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException(indexStr + index + sizeString + size);
        }
        if (index < size) {
            elements[index] = element;
        }
    }

    public Object elements(int index) {
        return elements[index];
    }


    @Override
    public Object get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException(indexStr + index + sizeString + size);
        }
        return elements[index];
    }


    @Override
    public int indexOf(Object element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public void remove(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException(indexStr + index + sizeString + size);
        }
        int value = size - index - 1;
        if (value > 0) {
            System.arraycopy(elements, index + 1, elements, index, value);
        }
        elements[--size] = null;

    }


    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }


    @Override
    public int size() {
        return size;
    }


    @Override
    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        private int currentIndex;

        @Override
        public boolean hasNext() {
            if (currentIndex >= elements.length) {
                return false;
            }
            return elements[currentIndex] != null || elements[currentIndex] == null;
        }

        @Override
        public Object next() {

            if (!hasNext()) {
                return null;

            }
            try {
                return elements[currentIndex++];
            } catch (NoSuchElementException e){
                throw new NoSuchElementException();
            }

        }

        @Override
        public void remove() {
            ArrayImpl.this.remove(currentIndex - 1);
            currentIndex = 0;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();


        for (Object o : elements) {
            sb.append(o).append(",").append(" ");

        }
        sb.delete(sb.length() - 2, sb.length());

        return "[" + sb.toString().trim() + "]";
    }



    public static void main(String[] args) {
        //Something


    }
}
