package epam.practical5;


import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Part5 {
    static String file = "src\\main\\resources\\practical5\\part5.txt";
    static RandomAccessFile raf;

    public static void main(String[] args) {
        try {
            raf = new RandomAccessFile(file, "rw");
        } catch (IOException e) {
            e.getMessage();
        }

        Thread[] threads = new Thread[10];
        for (int q = 0; q < threads.length; q++) {
            threads[q] = new MainWriter(q, raf);
        }

        for (int q = 0; q < threads.length; q++) {
            threads[q].start();
            try {
                threads[q].join();
            } catch (InterruptedException e) {
                e.getMessage();
                Thread.currentThread().interrupt();
            }
        }

        System.out.println(getInput(file));

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

    static class MainWriter extends Thread {
        RandomAccessFile raf;
        long pointer;
        int number;

        public MainWriter(int number, RandomAccessFile raf) {
            this.number = number;
            this.pointer = (long)number * 21;
            this.raf = raf;
        }

        @Override
        public void run() {
            for (int q = 0; q < 20; q++) {
                synchronized (raf) {
                    try {
                        raf.seek(pointer + q);
                        raf.writeBytes(Integer.toString(number));
                        raf.write(System.lineSeparator().getBytes());
                    } catch (IOException e) {
                        e.getMessage();
                    }

                }
            }

            try {
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }


}

