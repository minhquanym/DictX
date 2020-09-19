import java.util.ArrayList;

public class Dictionary {
    private ArrayList<Word> words;

    public Dictionary() {
        words = new ArrayList<Word>();
    }
    public void addWord(Word w) {
        words.add(w);
    }

    public void removeWord(Word w) {
        words.remove(w);
    }
    public Word getWordAt(int pos) {
        return words.get(pos);
    }

    public static void main(String[] args) {
        Dictionary dict1 = new Dictionary();
        Word bird = new Word("bird", "chim");
        dict1.addWord(bird);
        Word w = dict1.getWordAt(0);
        System.out.println(w.getWord_target() + "\t\t " + w.getWord_explain());
    }
}
