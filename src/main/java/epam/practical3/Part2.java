package epam.practical3;


import java.util.Arrays;

public class Part2 {
    public static void main(String[] args) {
        String text = "src\\main\\resources\\practical3\\part2.txt";
        System.out.println(convert(Util.readFile(text)));
    }

    public static String convert(String input) {
        String[] words = input.replaceAll("[,]", "").replaceAll("['-]", "\n").split("\\s+");

        int max = words[0].length();
        int min = words[0].length();

        StringBuilder sbMax = new StringBuilder();
        StringBuilder sbMin = new StringBuilder();
        for (int q = 0; q < words.length; q++) {
            // Clear the StringBuilder
            if (words[q].length() > max) {
                sbMax.setLength(0);
            }
            else if (words[q].length() < min) {
                sbMin.setLength(0);
            }
            if (words[q].length() >= max) {
                max = words[q].length();
                sbMax.append(words[q]).append(" ");
            }
            else if (words[q].length() <= min) {
                min = words[q].length();
                sbMin.append(words[q]).append(" ");
            }
        }

        String[] minArr = sbMin.toString().split(" ");
        String[] maxArr = sbMax.toString().split(" ");

        String[] newMinArr = Arrays.stream(minArr).distinct().toArray(String[]::new);
        String[] newMaxArr = Arrays.stream(maxArr).distinct().toArray(String[]::new);

        StringBuilder oneSb = new StringBuilder();
        oneSb.append("Min: ");
        for (int q = 0; q < newMinArr.length; q++) {
            oneSb.append(newMinArr[q]).append(", ");
        }

        StringBuilder twoSb = new StringBuilder();
        twoSb.append("Max: ");
        for (int q = 0; q < newMaxArr.length; q++) {
            twoSb.append(newMaxArr[q]).append(", ");
        }

        return oneSb.delete(oneSb.length() - 2, oneSb.length()).toString() + "\n" + twoSb.delete(twoSb.length() - 2, twoSb.length()).toString();
    }


}

