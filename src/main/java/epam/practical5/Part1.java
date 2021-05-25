package epam.practical5;


public class Part1 {


    public static void main(String[] args)  {
        MyThread thread = new MyThread();
        thread.start();

        try{
            thread.join();
        } catch (InterruptedException e){
            e.getMessage();
            Thread.currentThread().interrupt();
        }



        Thread thread1 = new Thread(new Runner());
        thread1.start();
        try{
            thread1.join();
        } catch (InterruptedException e){
            e.getMessage();
            Thread.currentThread().interrupt();
        }
    }
}

class MyThread extends Thread {
    @Override
    public void run() {

        for (int q = 0; q < 4; q++) {

            System.out.println(getClass().getName());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }


    }
}

class Runner implements Runnable {
    @Override
    public void run() {

        for (int q = 0; q < 4; q++) {
            System.out.println(getClass().getName());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }

    }
}
