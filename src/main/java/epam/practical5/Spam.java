package epam.practical5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Spam {
    String[] messages;
    int[] delays;
    Thread[] threads;

    public Spam(String[] messages, int[] delays) {
        this.messages = messages;
        this.delays = delays;
    }


    public static void main(final String[] args) {
        String[] messages = new String[]{"Java", ".NET"};
        int[] times = new int[]{250, 500};
        Spam spam = new Spam(messages, times);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        spam.start();

        String s;
        try {
            s = reader.readLine();
            if ("".equals(s)){
                spam.stop();
            }
        } catch (IOException e) {
            e.getMessage();
        }

        spam.join();


    }

    public void start() {
        threads = new Thread[messages.length];

        for (int q = 0; q < threads.length; q++) {
            threads[q] = new Worker(messages[q], delays[q]);
        }
        for (int q = 0; q < threads.length; q++) {
            threads[q].start();
        }
    }

    public void join() {
        for (int q = 0; q < threads.length; q++) {
            try {
                threads[q].join();
            } catch (InterruptedException e) {
                e.getMessage();
                Thread.currentThread().interrupt();
            }
        }
    }

    public void stop() {
        for (int q = 0; q < threads.length; q++) {
            threads[q].interrupt();
        }
    }


    private static class Worker extends Thread {
        private String message;
        private int time;

        public Worker(String message, int time) {
            this.message = message;
            this.time = time;
        }
        @Override
        public void run() {
            try{
                while (!isInterrupted()){
                    System.out.println(message);
                    Thread.sleep(time);
                }
            } catch (InterruptedException e){
                e.getMessage();
                Thread.currentThread().interrupt();
            }

        }
    }
}
