package epam.practical6.part6Right;

public class Part6 {
    public static void main(String[] args) {
        if (args.length < 4) {
            throw new IllegalArgumentException("Must be 4 arguments. Now: " + args.length);
        }

        ArgParser parser = new ArgParser(args);
        parser.parse();
        parser.execute();
    }
}
