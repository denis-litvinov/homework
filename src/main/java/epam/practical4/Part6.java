package epam.practical4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part6 {
    private static final String ENCODING = "cp1251";
    private static final String LATN = "(?i)(\\b[a-z]+?\\b)";
    private static final String CYRL = "(?U)(?i)(\\b[а-яґєіїё]+?\\b)";


    public static void main(String[] args)  {

        String input = "";
        input = Part6.getInput("src\\main\\resources\\practical4\\part6.txt");
        Scanner sc = new Scanner(System.in);
        String typeOfData;
        while (sc.hasNext()) {
            typeOfData = sc.nextLine();
            if ("Latn".equals(typeOfData) || "latn".equals(typeOfData)) {
                System.out.println("Latn: " + getData(input, LATN));
            } else if ("Cyrl".equals(typeOfData) || "cyrl".equals(typeOfData)){
                System.out.println("Cyrl: " + getData(input, CYRL));
            } else if ("stop".equals(typeOfData)){
                return;
            } else{
                System.out.println("smth: Incorrect input");
            }
        }
        sc.close();

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

    public static String getInput(String fileName)  {

        StringBuilder sb = new StringBuilder();
        try {
            Scanner scanner = new Scanner(new File(fileName), ENCODING);
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine()).append(System.lineSeparator());
            }
            scanner.close();
            return sb.toString().trim();
        } catch (FileNotFoundException e){
            e.getMessage();
        }
        return sb.toString().trim();


    }


}
