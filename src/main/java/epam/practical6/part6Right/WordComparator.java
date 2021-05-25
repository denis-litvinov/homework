package epam.practical6.part6Right;



import java.util.Comparator;

public enum WordComparator implements Comparator<Word> {
    FREQUENCY_SORT {
        public int compare(Word o1, Word o2) {
            return o2.getFrequency() - o1.getFrequency();
        }
    },
    LEXICOGRAPHICALLY_SORT {
        public int compare(Word o1, Word o2) {
            return o1.getContent().compareTo(o2.getContent());
        }
    },
    LEXICOGRAPHICALLY_REVERS_SORT {
        public int compare(Word o1, Word o2) {
            return -LEXICOGRAPHICALLY_SORT.compare(o1, o2);
        }
    },
    LENGTH_SORT {
        public int compare(Word o1, Word o2) {
            return o1.getContent().length() - o2.getContent().length();
        }
    },
    LENGTH_REVERS_SORT {
        public int compare(Word o1, Word o2) {
            return -LENGTH_SORT.compare(o1, o2);
        }
    }}
