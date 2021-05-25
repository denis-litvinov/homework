package epam.practical3;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
    public static void main(String[] args) {
        String text = "src\\main\\resources\\practical3\\part1.txt";
        System.out.println(convert1(Util.readFile(text)));
        System.out.println(convert2(Util.readFile(text)));
        System.out.println(convert3(Util.readFile(text)));
        System.out.println(convert4(Util.readFile(text)));
    }

    public static String convert1(String input) {
        String[] str = input.split("\n");
        String[] words;
        StringBuilder sb = new StringBuilder();
        for (int q = 1; q < str.length; q++) {
            words = str[q].split(";");
            sb.append(words[0]).append(": ").append(words[2]).append("\n");
        }
        return sb.toString();
    }

    public static String convert2(String input) {
        String[] str = input.split("\n");
        String[] words;
        StringBuilder sb = new StringBuilder();

        for (int q = 1; q < str.length; q++) {
            words = str[q].split(";");
            sb.append(words[1]).append(" (email: ").append(words[2]).append(") ");
        }
        String[] arr = sb.toString().split(" ");

        String str1 = arr[1] + " " + arr[0] + " " + arr[2] + " " + arr[3] + "\n";
        String str2 = arr[5] + " " + arr[4] + " " + arr[6] + " " + arr[7] + "\n";
        String str3 = arr[9] + " " + arr[8] + " " + arr[10] + " " + arr[11] + "\n";
        String str4 = arr[13] + " " + arr[12] + " " + arr[14] + " " + arr[15] + "\n";

        return str1 + str2 + str3 + str4;
    }

    public static String convert3(String input) {
        Pattern p = Pattern.compile("^?(.+;)(.+;)(.+)(@.+)");
        Matcher m = p.matcher(input);
        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        while (m.find()) {
            Pattern p2 = Pattern.compile(m.group(4).substring(1));
            Matcher m2 = p2.matcher(sb);
            if (!m2.find()) {
                sb.append(System.lineSeparator() + m.group(4).substring(1)
                        + " ==> "
                        + m.group(1).substring(0, m.group(1).length() - 1));
            } else {
                Pattern p3 = Pattern.compile(m.group(4).substring(1) + ".+");
                Matcher m3 = p3.matcher(sb);
                m3.find();
                sb.insert(m3.end(),
                        ", " + m.group(1).substring(0, m.group(1).length() - 1));
            }
        }
        return sb.append("\n").toString().substring(2);
    }

    public static String convert4(String input) {
        String[] s = input.split("\n");
        String total = "";
        total += s[0] + ";Password" + "\n" + s[1] + ";5647" + "\n" + s[2] + ";7895" + "\n" + s[3] + ";2354" + "\n" + s[4] + ";6235" + "\n";
        return total;
    }
}
