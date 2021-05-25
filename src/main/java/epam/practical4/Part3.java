package epam.practical4;


import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {
    private static final String ENCODING = "cp1251";

    public static final String PINT = "(?m)(\\s\\b\\d+\\s)";
    public static final String PDOUB = "(?m)(\\.\\d\\d|\\d+\\.\\d+|\\d+\\.)";
    public static final String PCH = "(?m)(?U)(\\b\\w\\s)";
    public static final String PSTR = "(?U)(?m)([a-zA-zа-яА-Я]\\w{1,})";

    public static String getInput(String fileName) throws IOException {
        StringBuilder sb = new StringBuilder();
        Scanner scanner = new Scanner(new File(fileName), ENCODING);
        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine()).append(System.lineSeparator());
        }
        scanner.close();

        return sb.toString().trim();
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

    public static void main(String[] args)  {
        String input="";
        try{
            input = Part3.getInput("src\\main\\resources\\practical4\\part3.txt");
        } catch (IOException e){
            System.out.println("Incorrect input");
        }
        Scanner sc = new Scanner(System.in);
        String typeOfData;
        while (sc.hasNext()) {
            typeOfData = sc.nextLine();
            if ("char".equals(typeOfData)) {
                System.out.println(Part3.getData(input, PCH).replaceAll("\\s\\s", " "));
            } else if ("String".equals(typeOfData)) {
                System.out.println(Part3.getData(input, PSTR));
            } else if ("double".equals(typeOfData)) {
                System.out.println(Part3.getData(input, PDOUB));
            } else if ("int".equals(typeOfData)) {
                System.out.println(
                    Part3.getData(input, PINT).replaceAll("^\\s", "").replaceAll("\\D\\D\\D", " ").replaceAll("\\s$", ""));
            } else if ("stop".equals(typeOfData)) {
                return;
            } else {
                System.out.println("Incorrect input");
            }
        }
        sc.close();
    }
}
