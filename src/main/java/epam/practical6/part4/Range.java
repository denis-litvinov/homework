package epam.practical6.part4;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Range implements Iterable<Integer> {

    private int n;
    private int m;
    private boolean reversedOrder;
    private int[] arr;


    public Range(int n, int m) {
        this(n, m, false);
    }

    public Range(int firstBound, int secBound, boolean reversedOrder) {
        n = firstBound;
        m = secBound;
        this.reversedOrder = reversedOrder;
        arr = createArr();
    }

    private int[] createArr() {
        arr = new int[m - n + 1];
        int index = 0;
        for (int q = n; q <= m; q++) {
            arr[index] = q;
            index++;
        }
        return arr;
    }


    @Override
    public Iterator<Integer> iterator() {
        return new IteratorImpl();
    }

    private final class IteratorImpl implements Iterator<Integer> {
        private int currentIndex;
        private int backIndex = arr.length-1;


        @Override
        public boolean hasNext() {
            if (reversedOrder) {
                return backIndex >= 0;
            }
            return currentIndex != arr.length;
        }

        @Override
        public Integer next() {
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            if (reversedOrder) {
                return arr[backIndex--];
            }
            return arr[currentIndex++];
        }

    }
}
