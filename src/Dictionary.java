import java.lang.reflect.Array;
import java.util.ArrayList;

public class Dictionary {
    private ArrayList<Word> words;
    private Trie englishTrie;

    public Dictionary() {
        words = new ArrayList<Word>();
        englishTrie = new Trie();
    }
    public ArrayList<Word> getAllWords() {
        return this.words;
    }
    public void addWord(Word w) {
        // Check if the word existed
        int idSearched = englishTrie.search(w.getWord_target());
        if (idSearched == -1) {
            words.add(w);
            englishTrie.add(w.getWord_target(), words.size() - 1);
        } else {
            System.out.println("Sorry, Word " + w.getWord_target() + " is existed.");
        }
    }

    public void removeWord(Word w) {
        int idSearched = englishTrie.search(w.getWord_target());
        if (idSearched == -1) {
            System.out.println("Cant delete. Word not existed");
        } else {
            words.set(idSearched, null);
            englishTrie.delete(w.getWord_target());
        }
    }
    public Word getWordAt(int pos) {
        return words.get(pos);
    }
    public int getSize() {
        return words.size();
    }

    public Word searchWord(String w) {
        int idSearched = englishTrie.search(w);
        if (idSearched == -1) {
            return null;
        } else {
            return words.get(idSearched);
        }
    }

    public ArrayList<Word> suggestWord(String prefix) {
        ArrayList<Integer> ids = this.englishTrie.suggest(prefix, this.words.size() - 1);

        ArrayList<Word> result = new ArrayList<Word>();
        for (int i = 0; i < ids.size(); i++) {
            result.add(this.words.get(ids.get(i)));
        }
        return result;
    }
    public static void main(String[] args) {
        Dictionary dict1 = new Dictionary();
        Word bird = new Word("bird", "chim");
        dict1.addWord(bird);
        Word w = dict1.getWordAt(0);
        System.out.println(w.getWord_target() + "\t\t " + w.getWord_explain());
    }
}
