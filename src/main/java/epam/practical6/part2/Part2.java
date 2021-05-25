package epam.practical6.part2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Part2 {

     static Integer counter = 10000;

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        for (int q = 0; q < counter; q++) {
            list.add(q);
        }

        List<Integer>linkedList = new LinkedList<>();

        for (int q = 0; q < counter; q++){
            linkedList.add(q);
        }

        long arrayIndex = removeByIndex(list, 4);
        System.out.println(arrayIndex);

        long linkedIndex = removeByIndex(linkedList, 4);
        System.out.println(linkedIndex);


        List<Integer> listIterator = new ArrayList<>();

        for (int q = 0; q < counter; q++) {
            listIterator.add(q);
        }

        List<Integer>linkedListIterator = new LinkedList<>();

        for (int q = 0; q < counter; q++){
            linkedListIterator.add(q);
        }


        long iteratorArray = removeByIterator(listIterator, 4);
        System.out.println(iteratorArray);

        long iteratorLinked = removeByIterator(linkedListIterator, 4);
        System.out.println(iteratorLinked);





    }

    public static long removeByIndex(final List<Integer> list, final int k) {
        long beforeTime = System.currentTimeMillis();
        int local = 0;
        for (int q = list.size() - 1; q >= 0; q--) {
            if (list.size() == 1) {
                break;
            }

            local += (k - 1);
            while (local >= list.size()) {
                local = local - list.size();
            }
            list.remove(local);

        }

        return System.currentTimeMillis() - beforeTime;
    }

    public static long removeByIterator(final List<Integer> list, int k) {
        long before = System.currentTimeMillis();
        int local = 0;
        for (int q = list.size() - 1; q >= 0; q--) {
            if (list.size() == 1) {
                break;
            }

            local += (k - 1);
            while (local >= list.size()) {
                local = local - list.size();
            }
            list.remove(local);

        }

        return System.currentTimeMillis() - before;
    }
}
