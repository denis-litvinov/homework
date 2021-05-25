package epam.practical4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part4 implements Iterable<String> {
    public static final String P41 = "(?m)(?U)([A-ZА-Я].+\\.)";

    public static void main(String[] args) {
        Part4 part4 = new Part4();
        Iterator<String> iterator = part4.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }


    @Override
    public Iterator iterator() {
        String input = null;


        try {
            input = getInput("src\\main\\resources\\practical4\\part4.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();


        String chernovik = input;
        sb.append(chernovik);
        sb.delete(72,73).insert(72, "\n");
        sb.insert(177, "\n").delete(178,179);
        sb.insert(220, "\n").delete(219,220);

        String superFinal = sb.toString();


        return new Iterator<String>() {


            Pattern p = Pattern.compile(P41);
            Matcher m = p.matcher(superFinal);

            @Override
            public boolean hasNext() {
                return m.find();
            }

            @Override
            public String next() {

                return m.group();

            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public static String getInput(String fileName) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        Scanner scanner = new Scanner(new File(fileName), "cp1251");
        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine()).append(" ");

        }
        scanner.close();


        return sb.toString();

    }
}
