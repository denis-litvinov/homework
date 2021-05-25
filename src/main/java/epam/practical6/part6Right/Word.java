package epam.practical6.part6Right;

public class Word implements Comparable<Word> {

    private String content;

    private int frequency;

    public Word(String content) {
        this.content = content;
        frequency = 1;
    }

    @Override
    public int compareTo(Word o) {
        if (o.frequency == this.frequency) {
            return WordComparator.LEXICOGRAPHICALLY_SORT.compare(this, o);
        } else {
            return WordComparator.FREQUENCY_SORT.compare(this, o);
        }
    }

    public String getContent() {
        return content;
    }

    public int getFrequency() {
        return frequency;
    }

    public void incFrequency() {
        frequency++;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Word)) {
            return false;
        }

        Word word = (Word) o;

        return content.equals(word.content);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + content.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return getContent() + " - " + frequency;
    }

}
