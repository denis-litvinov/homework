package epam.practical6.part6Right;

public class Part62 {
    private static final int HOW_MANY_WORDS_SHOW = 3;

    public static void main(String[] args) {
        final String fPath = "src\\main\\resources\\practical6\\part6.txt";
        execute(fPath);
    }

    public static void execute(String fPath) {
        new Extractor(Util.getInput(fPath).split("\\s+")).extract()
                .stream()
                .sorted(WordComparator.LENGTH_REVERS_SORT)
                .limit(HOW_MANY_WORDS_SHOW)
                .forEach(w -> System.out.println(w.getContent() + " ==> " + w.getContent().length()));
    }
}
