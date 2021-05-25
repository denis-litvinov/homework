package epam.practical6.part6Right;

public class Part61 {
    private static final int HOW_MANY_WORDS_SHOW = 3;

    public static void main(String[] args) {
        final String fPath = "src\\main\\resources\\practical6\\part6.txt";
        execute(fPath);
    }

    public static void execute(String fPath) {
        new Extractor(Util.getInput(fPath).split("\\s+")).extract()
                .stream()
                .sorted(WordComparator.FREQUENCY_SORT)
                .limit(HOW_MANY_WORDS_SHOW)
                .sorted(WordComparator.LEXICOGRAPHICALLY_REVERS_SORT)
                .forEach(w -> System.out.println(w.getContent() + " ==> " + w.getFrequency()));
    }
}
