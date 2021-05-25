package epam.practical4;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {


        String input = getInput("D://hhdajmxb-task4-master/part6.txt");

        String text = "Is there anybody going to listen to my story \n" +
            "Лягає день. Він віддає свої надії ночі. \n" +
            "Робітники \n" +
            "Заморились працювати. \n" +
            "С барабаном ходит ёжик. Бум-бум-бум.";


        StringBuilder result = new StringBuilder();
        Pattern p = Pattern.compile("(?i)(\\b[a-z]+?\\b)");
        Matcher m = p.matcher(input);

        while (m.find()) {
            result.append(m.group(1));
        }


        System.out.println(result);
    }


    public static String getData(String text, String reg) {
        String s = text;
        StringBuilder sb = new StringBuilder();
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(s);
        while (m.find()) {
            sb.append(m.group(1) + " ");
        }
        return sb.toString();
    }


    public static String getInput(String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            Scanner scanner = new Scanner(new File(fileName), "cp1251");
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine()).append(System.lineSeparator());
            }
            scanner.close();
            return sb.toString().trim();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return sb.toString();
    }
}
