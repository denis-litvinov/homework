package epam.practical5;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part4 {

    public static void main(String[] args) {
        String input = "src\\main\\resources\\practical5\\part4.txt";

        String text = getInput(input);

        String[] stroki = text.split("\n");

        long beforeTime2 = System.currentTimeMillis();
        for (int q = 0; q < stroki.length; q++) {
            new MyThread(stroki[q]);
        }
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.getMessage();
            Thread.currentThread().interrupt();
        }

        long afterTime2 = System.currentTimeMillis();
        log.info(maxV + "");
        log.info((afterTime2 - beforeTime2) + "");


        List<Integer> list = getInputList(input);

        long beforeTime = System.currentTimeMillis();
        try {
            Thread.sleep(40);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }

        int max = Collections.max(list);
        long afterTime = System.currentTimeMillis();
        log.info(max + "");
        log.info((afterTime - beforeTime) + "");


    }

    private static int maxV;
    private static Logger log = Logger.getLogger(Part4.class.getName());


    public static String getInput(String fileName) {

        StringBuilder stringBuilder = new StringBuilder();
        try {
            Scanner scanner = new Scanner(new File(fileName), "cp1251");
            while (scanner.hasNextLine()) {
                stringBuilder.append(scanner.nextLine()).append(System.lineSeparator());
            }
            scanner.close();
            return stringBuilder.toString().trim();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return stringBuilder.toString();
    }


    public static List<Integer> getInputList(String fileName) {
        ArrayList<Integer> list = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(fileName), "cp1251");
            while (scanner.hasNextInt()) {
                list.add(scanner.nextInt());

            }
            scanner.close();
            return list;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public static void maxValue(String s) {
        Pattern p = Pattern.compile("(\\d+)");
        Matcher m = p.matcher(s);
        int max = Integer.MIN_VALUE;


        while (m.find()) {
            int poisk = Integer.parseInt(m.group());
            if (poisk > max) {
                max = poisk;
            }

        }
        if (max > maxV) {
            maxV = max;
        }

    }


    static class MyThread extends Thread {
        private String threads;

        public MyThread(String s) {
            try {
                threads = s;
                start();
                this.join();
            } catch (InterruptedException e) {
                e.getMessage();
                Thread.currentThread().interrupt();
            }
        }

        @Override
        public void run() {
            synchronized (this) {
                maxValue(threads);
            }
        }
    }
}


