package epam.practical6.part3;


public class Part3 {

    public static void main(String[] args) {
        Parking parking = new Parking(4);


        parking.arrive(2);
        parking.arrive(3);
        parking.arrive(2);
        parking.arrive(2);
        parking.arrive(2);
        parking.depart(1);
        parking.depart(1);


        parking.print();


    }
}
