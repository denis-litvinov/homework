package epam.practical3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part5 {
    public static void main(String[] args) {
        System.out.println(roman2Decimal("C"));
        System.out.println(decimal2Roman(2021));
    }

    public static String decimal2Roman(int dec) {
        StringBuilder sb = new StringBuilder();
        int times = 0;
        String[] romans = new String[] { "I", "IV", "V", "IX", "X", "XL", "L",
                "XC", "C", "CD", "D", "CM", "M" };
        int[] ints = new int[] { 1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500,
                900, 1000 };
        for (int i = ints.length - 1; i >= 0; i--) {
            times = dec / ints[i];
            dec %= ints[i];
            while (times > 0) {
                sb.append(romans[i]);
                times--;
            }
        }
        return sb.toString();
    }

    public static int roman2Decimal(String roman) {
        if (roman == null || roman.isEmpty() || !roman.matches("^(M{0,3})(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$"))
            return -1;

        final Matcher matcher = Pattern.compile("M|CM|D|CD|C|XC|L|XL|X|IX|V|IV|I").matcher(roman);
        final int[] decimalValues = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        final String[] romanNumerals = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        int result = 0;

        while (matcher.find())
            for (int i = 0; i < romanNumerals.length; i++)
                if (romanNumerals[i].equals(matcher.group(0)))
                    result += decimalValues[i];

        return result;
    }
}
