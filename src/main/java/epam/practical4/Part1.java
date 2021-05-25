package epam.practical4;



import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Part1 {
    public static void main(String[] args) {
        String input = getInput("src\\main\\resources\\practical4\\part1.txt");
        String[] words = input.replaceAll("[,]", "").replaceAll("['-]", "\n").split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int q = 0; q < words.length; q++) {
            if (words[q].length() >= 4) {
                sb.append(words[q].substring(2)).append(" ");
            }
            if (words[q].length() < 4) {
                sb.append(words[q]).append(" ");
            }
        }
        System.out.print(sb.delete(14, 15).insert(14, "\n").delete(26,27).insert(26, "\n")
                .delete(33,34).insert(33, "\n").delete(49,50).insert(49, "\n").insert(63, "\n").delete(64,65));





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
