package epam.practical3;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class HashExample {
    public static void main(String[] args) throws NoSuchAlgorithmException {

        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        digest.update("asdf".getBytes());

        byte[] hash = digest.digest();

        System.out.println(Arrays.toString(hash));

        String myHash = DatatypeConverter.printHexBinary(hash);
        System.out.println(myHash);



        // output: [56, 55, 83, 50, 113, -114, -54, 115, -125, 86, 79, -109, 17, -65, 107, 84]
       //  MD5 hashing algorithm (other algorithms - SHA-256; SHA-512, etc.).
    }
}

