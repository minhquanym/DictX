package DictionaryRoot;

import java.io.*;
import java.util.ArrayList;

public class Dictionary {
    private ArrayList<Word> words;
    private Trie englishTrie;

    /**
     * construction no param for dictionary.
     */
    public Dictionary() {
        words = new ArrayList<Word>();
        englishTrie = new Trie();
    }

    /**
     * getter to get all words in dictionary.
     * @return list of words.
     */
    public ArrayList<Word> getAllWords() {
        return this.words;
    }

    /**
     * Import dictionary from file "dictionaries.txt".
     * @throws IOException no file "dictionary.txt".
     */
    public void importFromFile() throws IOException {
        try {
            FileReader fr = new FileReader("resources/dictionaries.txt");
            BufferedReader br = new BufferedReader(fr);
            while (br.ready()) {
                String[] words = br.readLine().split("\\t");
                this.addWord(new Word(words[0], words[1]));
            }
            fr.close();
        } catch (FileNotFoundException e) {
            System.out.println("File dictionaries.txt does not exist in this folder");
        }
    }

    /**
     * function to save new dictionary.
     * @throws IOException no file "dictionaries.txt".
     */
    public void saveNewDictionary() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("resources/dictionaries.txt"));
        ArrayList<Word> allWords = this.getAllWords();

        for (int i = 0; i < allWords.size(); i++) {
            if (allWords.get(i) == null) {
                continue; // skip word be deleted
            }
            String English = allWords.get(i).getWord_target();
            String Vietnamese = allWords.get(i).getPlain_explain();
            bw.write(English + "\t" + Vietnamese);
            bw.newLine();
        }
        bw.close();
    }

    /**
     * add new word to dictionary.
     * @param w new word.
     */
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

    /**
     * remove word from dictionary.
     * @param w word which want to remove.
     */
    public void removeWord(Word w) {
        int idSearched = englishTrie.search(w.getWord_target());
        if (idSearched == -1) {
            System.out.println("Cant delete. Word not existed");
        } else {
            words.set(idSearched, null);
            englishTrie.delete(w.getWord_target());
        }
    }

    /**
     * get word at id pos.
     * @param pos position of word.
     * @return word at position pos.
     */
    public Word getWordAt(int pos) {
        return words.get(pos);
    }

    /**
     * get size of list word in dictionary.
     * @return size of list word in dictionary.
     */
    public int getSize() {
        return words.size();
    }

    /**
     * search for word in dictionary.
     * @param w string target which want to search.
     * @return word match with string w.
     */
    public Word searchWord(String w) {
        int idSearched = englishTrie.search(w);
        if (idSearched == -1) {
            return null;
        } else {
            return words.get(idSearched);
        }
    }

    /**
     * Suggest some word start with "prefix". Has limit.
     * If limit = -1 then return all words.
     */
    public ArrayList<Word> suggestWord(String prefix, int limit) {
        if (limit == -1) {
            limit = this.words.size() - 1;
        }
        ArrayList<Integer> ids = this.englishTrie.suggest(prefix, Math.min(this.words.size() - 1, limit));

        ArrayList<Word> result = new ArrayList<Word>();
        for (int i = 0; i < ids.size(); i++) {
            result.add(this.words.get(ids.get(i)));
        }
        return result;
    }

    /**
     * main function to test dictionary.
     */
    public static void main(String[] args) throws IOException {
        Dictionary dict = new Dictionary();
        dict.importFromFile();
        dict.saveNewDictionary();
    }
}