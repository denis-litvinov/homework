package epam.practical4;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Part2 {
    public static void main(String[] args) {
        String fileOne = "src\\main\\resources\\practical4\\part2.txt";
        String fileTwo = "src\\main\\resources\\practical4\\part2_sorted.txt";

        SecureRandom random = new SecureRandom();
        int[] arr = new int[10];
        for (int q = 0; q < arr.length; q++) {
            arr[q] = random.nextInt(50);
        }
        ArrayList<String> list = new ArrayList<>();
        try (final FileWriter writer = new FileWriter(fileOne, false)) {
            for (int i = 0; i < arr.length; ++i) {
                String output = Integer.toString(arr[i]);
                writer.write(output);
                writer.write(" ");
                output = output + " ";
                list.add(output);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        StringBuilder sb = new StringBuilder();
        sb.append("input ==> ");
        for (String x : list) {
            sb.append(x);
        }
        System.out.print(sb.toString().trim());

        ArrayList<String> list2 = new ArrayList<>();


        try(FileReader reader = new FileReader(fileOne) ; Scanner scan = new Scanner(reader) ){
            while (scan.hasNextLine()) {
                list2.add(scan.nextLine());
            }
        } catch (Exception e){
            System.out.print("Exception");
        }

        StringBuilder builder = new StringBuilder();
        System.out.println();
        for (String x : list2) {
            builder.append(x);
        }

        String[] strokiRead = builder.toString().split(" ");
        ArrayList<Integer> listIntov = new ArrayList<>();

        for (String x : strokiRead) {
            listIntov.add(Integer.parseInt(x));
        }
        Collections.sort(listIntov);

        ArrayList<String> strokiFinal = new ArrayList<>();
        try (final FileWriter writerVtoroi = new FileWriter(fileTwo, false)) {
            for (int i = 0; i < listIntov.size(); ++i) {
                String output = Integer.toString(listIntov.get(i));
                writerVtoroi.write(output);
                writerVtoroi.write(" ");
                output = output + " ";
                strokiFinal.add(output);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        StringBuilder sbFinal = new StringBuilder();
        sbFinal.append("output ==> ");
        for (String x:strokiFinal){
            sbFinal.append(x);
        }

        System.out.print(sbFinal.toString().trim() + "\n");


    }
}
