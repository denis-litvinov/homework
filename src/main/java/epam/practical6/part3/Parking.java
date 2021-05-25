package epam.practical6.part3;


public class Parking {

    private int[] arr;

    public Parking(int capacity) {
        arr = new int[capacity];
    }


    public boolean arrive(int k) {
        if (k > arr.length){
            throw new IllegalArgumentException();
        }

        int start = k;
        while (arr[k] != 0) {
            k = (k + 1) % arr.length;
            if (k == start) {
                return false;
            }
        }
        arr[k] = 1;
        return true;
    }

    public boolean depart(int k) {
        if (k > arr.length){
            throw new IllegalArgumentException();
        }

       if (arr[k] == 1){
           arr[k] = 0;
           return true;
       }
       return false;
    }


    public void print() {
        for (int q = 0; q < arr.length; q++){
            System.out.print(arr[q]);
        }
    }
}
