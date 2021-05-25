package epam.practical6.part4;


public class Part4 {
    public static void main(String[] args) {

        Range range = new Range(3, 10);


        for (Integer el : range) {
            System.out.print(el + " ");
        }

        System.out.println();


        Range range1 = new Range(3, 10, true);

        for (Integer el : range1) {
            System.out.print(el + " ");
        }


    }
}
