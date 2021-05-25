package epam.practical3;

public class Part3 {
    public static void main(String[] args) {
        String text = "src\\main\\resources\\practical3\\part3.txt";
        System.out.println(convert(Util.readFile(text)));
    }

    public static String convert(String input) {
        String[] words = input.replaceAll("[,]", "").replaceAll("['-]", "\n").split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int q = 0; q < words.length; q++) {
            String c = words[q].substring(0, 1);
            char k = c.charAt(0);
            if (words[q].length() >= 3 && Character.isUpperCase(k)) {
                sb.append(words[q].substring(0, 1).toLowerCase() + words[q].substring(1)).append(" ");
            }
            if (words[q].length() >= 3 && Character.isLowerCase(k)) {
                sb.append(words[q].substring(0, 1).toUpperCase() + words[q].substring(1)).append(" ");
            }
            if (words[q].length() < 3) {
                sb.append(words[q]).append(" ");
            }
        }

        String[] hz = sb.toString().split(" ");
        StringBuilder sbNew = new StringBuilder();

        for (int q = 0; q < hz.length; q++) {
            sbNew.append(hz[q]).append(" ");
            if (hz[q].equals("Younger")) {
                sbNew.deleteCharAt(18);
                sbNew.append("\n");
            }
        }
        return sbNew.toString().trim();
    }
}