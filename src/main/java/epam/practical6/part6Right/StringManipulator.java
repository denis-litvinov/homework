package epam.practical6.part6Right;

public class StringManipulator {
    private StringManipulator() {
    }

    public static final String EOL = System.lineSeparator();

    public static String convertArrayToString(int[] array, String separator) {
        if (array == null) {
            return "null";
        }

        if (array.length == 0) {
            return "";
        }

        if (separator == null) {
            separator = "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(array[0]);
        for (int i = 1; i < array.length; i++) {
            sb.append(separator).append(array[i]);
        }
        return sb.toString();
    }

    public static String convertStringArrayToSingleString(String[] strings, String separator) {
        if (strings == null) {
            return "null";
        }
        if (strings.length == 0) {
            return "";
        }
        if (separator == null) {
            separator = EOL;
        }

        StringBuilder sbResult = new StringBuilder(strings[0]);

        for (int i = 1; i < strings.length; i++) {
            sbResult.append(separator).append(strings[i]);
        }
        return sbResult.toString();
    }

}

