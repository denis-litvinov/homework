package epam.practical6;


import epam.practical6.part2.Part2;
import epam.practical6.part3.Part3;
import epam.practical6.part4.Part4;
import epam.practical6.part5.Part5;
import epam.practical6.part6Right.Part6;

public class Demo {


    public static void main(String[] args) {
        String path = "src\\main\\resources\\practical6\\part6.txt";

        /*
        try {
            System.setIn(new ByteArrayInputStream(
                    "asd asdf asd asdf asdf 43 asdsf 43 43 434".getBytes(System
                            .getProperty("file.encoding"))));
        } catch (UnsupportedEncodingException e) {
            System.err.println("Unsupported encoding");
        }
        System.setIn(System.in);
        System.out.println("~~~~~~~~~~~~Part1");
        Part1.main(args);
*/
        System.out.println("~~~~~~~~~~~~Part2");
        Part2.main(args);
        System.out.println("~~~~~~~~~~~~Part3");
        Part3.main(args);
        System.out.println();

        System.out.println("~~~~~~~~~~~~Part4");
        Part4.main(args);
        System.out.println();
        System.out.println("~~~~~~~~~~~~Part5");
        Part5.main(args);


        System.out.println("~~~~~~~~~~~~Part6");
        System.out.println("Frequency demonstration: ");
        Part6.main(new String[] {"--input", path, "--task",

                "frequency"});
        System.out.println("Length demonstration: ");
        Part6.main(new String[] {"--input", path, "--task", "length"});
        System.out.println("Duplicates demonstration: ");
        Part6.main(new String[] {"--input", path, "--task",

                "duplicates"});
    }
}
