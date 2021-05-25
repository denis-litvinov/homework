package epam.practical6.part1;

public class Word implements Comparable<Word> {

    private String content;
    private int frequency;

    public String getContent() {
        return content;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Word(String content) {
        this.content = content;
        this.frequency = 1;
    }

    @Override
    public int compareTo(Word o) {
        return Integer.compare(frequency, o.frequency);

    }

    @Override
    public String toString() {
        return "" + content  + " : " + frequency;
    }

    @Override
    public int hashCode() {
       return content.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this){
            return true;
        }
        if (!(obj instanceof Word)){
            return false;
        }
        Word other = (Word) obj;
        return this.content.equals(other.content);
    }
}
