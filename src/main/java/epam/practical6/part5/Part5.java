package epam.practical6.part5;

public class Part5 {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();
        String tilda = "~~~~~~~";
        System.out.println(tree.add(3));
        System.out.println(tree.add(3));

        System.out.println(tilda);
        tree.add(new Integer[] { 3, 7, 1, 8, 6, 2, 5, 4 });
        tree.print();
        System.out.println(tilda);
        System.out.println(tree.remove(1));
        System.out.println(tree.remove(1));
        System.out.println(tilda);
        tree.print();
    }
}
