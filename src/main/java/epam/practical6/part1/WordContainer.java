package epam.practical6.part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class WordContainer {
    static Set<Word> list = new HashSet<>();

    public static void main(String[] args)  {
        for (int q = 0; q < list.size(); q++){
            list.clear();
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = null;

        Pattern p = Pattern.compile("\\S+");

        while (true) {
            try {
                line = reader.readLine();
                Matcher m = p.matcher(line);

                while (m.find()) {
                    if (m.group().equals("stop")){
                        break;
                    }
                    addon(new Word(m.group()));
                }
                if (line.contains("stop")) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

       List <Word> hz = list.stream().sorted(Comparator.comparing(Word::getFrequency).reversed().thenComparing(Word::getContent)).collect(Collectors.toList());

        for (Word word : hz) {
            System.out.println(word);
        }

    }

    public static void addon(Word word) {
        for (Word w : list) {
            if (w.getContent().equals(word.getContent())) {
                w.setFrequency(w.getFrequency() + 1);
            }
        }
        list.add(word);
    }

}
