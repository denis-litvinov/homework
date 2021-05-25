package epam.practical5;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Part2 {
    static InputStream std = System.in;
    public static void main(String[] args) {

        System.setIn(new MyInputstream());

        Thread t = new Thread() {
            @Override
            public void run() {
                Spam.main(null);
            }
        };

        t.start();



        try{
            t.join();
        } catch (InterruptedException e){
            e.getMessage();
            Thread.currentThread().interrupt();
        }

        System.setIn(std);



    }

    public static class MyInputstream extends InputStream {
        int index;
        int indexN;
        @Override
        public int read() {

            byte [] separatorArr = System.lineSeparator().getBytes();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                Logger.getLogger("").log(Level.INFO, e.getLocalizedMessage());
            }
            if (index != 0) {
                return -1;
            }
            index++;
            return separatorArr[0] & 0xFF;
        }
        @Override
        public int read(byte[] b,int in,int itr) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                Logger.getLogger("").log(Level.INFO, e.getLocalizedMessage());
            }
            byte [] separatorArr = System.lineSeparator().getBytes();
            if (indexN != 0) {
                return -1;
            }
            indexN++;
            b[0] = (byte) (separatorArr[0] & 0xFF);
            return 1;
        }
    }
}
