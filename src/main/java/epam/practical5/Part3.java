package epam.practical5;

public class Part3 {
    private int counter;
    private int counter2;

    int numberOfThreads;
    int numberOfIterations;
    Thread[] threads;


    public Part3(int numberOfThreads, int numberOfIterations) {
        this.numberOfThreads = numberOfThreads;
        this.numberOfIterations = numberOfIterations;
    }

    public void compare() {
        threads = new Thread[numberOfThreads];
        counter = 0;
        counter2 = 0;
        for (int q = 0; q < threads.length; q++){
            threads[q] = new Comparator();
        }
        for (int q = 0; q < threads.length; q++){
            threads[q].start();
        }
        for (int q = 0; q < threads.length; q++){
            try{
                threads[q].join();
            } catch (InterruptedException e){
                e.getMessage();
                Thread.currentThread().interrupt();
            }
        }
    }


    public void compareSync() {
        threads = new Thread[numberOfThreads];
        counter = 0;
        counter2 = 0;
        for (int q = 0; q < threads.length; q++){
            threads[q] = new ComparatorSync();
        }
        for (int q = 0; q < threads.length; q++){
            threads[q].start();
        }

        for (int q = 0; q < threads.length; q++){
            try{
                threads[q].join();
            } catch (InterruptedException e){
                e.getMessage();
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        Part3 part3 = new Part3(5, 5);
        part3.compare();
        part3.compareSync();
    }

    class Comparator extends Thread {
        @Override
        public void run() {
            for (int q = 0; q < numberOfIterations; q++) {
                System.out.println(counter + " " + counter2);
                counter++;

                try {
                    Thread.sleep(90);
                } catch (InterruptedException e) {
                    e.getMessage();
                    Thread.currentThread().interrupt();
                }
                counter2++;
            }
        }
    }


    class ComparatorSync extends Thread {
        public void sleep(){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
                interrupt();
            }
        }
        @Override
        public void run() {
            for (int q = 0; q < numberOfIterations; q++) {
                synchronized (threads) {
                    System.out.println(counter + " " + counter2);
                    counter++;
                    sleep();
                    counter2++;
                }
            }
        }
    }
}

