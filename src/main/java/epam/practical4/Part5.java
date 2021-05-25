package epam.practical4;


import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Part5 {
    public static void main(String[] args){
        String resources = "resources";
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()){
            String [] mas = scan.nextLine().split(" ");
            if (mas[0].equals("stop")) {
                break;
            }
            Locale locale = new Locale(mas[1]);
            ResourceBundle defRb = ResourceBundle.getBundle(resources, locale);
            System.out.println(defRb.getString(mas[0]));

        }
        scan.close();


    }
}
