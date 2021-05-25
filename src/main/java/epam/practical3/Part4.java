package epam.practical3;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Part4 {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(hash("asdf", "SHA-256"));
    }

    public static String hash(String input, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        digest.update(input.getBytes());
        byte[] hash = digest.digest();
        return byteArrayToHex(hash).toUpperCase();
    }

    public static String byteArrayToHex(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for(byte b: a)
            sb.append(String.format("%02x", b));
        return sb.toString();
    }
}
