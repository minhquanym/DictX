import java.io.*;
import java.util.*;

public class DictionaryManagement {
    private Dictionary myDictionary;

    /**
     * Call dictionary constructor
     */
    public DictionaryManagement() {
        myDictionary = new Dictionary();
    }
    /**
     * Insert words by typing in command line
     */
    public void insertFromCommandline() throws IOException {
        BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Dictionary length: ");
        int dictionarySize = -1;
        while (dictionarySize <= 0) {
            try {
                dictionarySize = Integer.parseInt(inp.readLine());
            } catch (NumberFormatException e) {
                // do nothing
            }
            if (dictionarySize <= 0) {
                System.out.print("You must provide a positive integer number!\nTry Again: ");
            }
        }

        for (int i = 0; i < dictionarySize; i++) {
            System.out.print("English: ");
            String englishWord = inp.readLine();
            System.out.print("Tieng Viet: ");
            String vietnameseWord = inp.readLine();
            Word newWord = new Word(englishWord, vietnameseWord);
            this.myDictionary.addWord(newWord);
        }
    }

    /**
     * Create dictionary from file dictionaries.txt
     */
    public void insertFromFile() throws IOException {
        try {
            FileReader fr = new FileReader("resources/dictionaries.txt");
            BufferedReader br = new BufferedReader(fr);
            while (br.ready()) {
                String[] words = br.readLine().split("\\t");
                if (words.length == 2) {
                    Word w = new Word(words[0], words[1]);
                    this.myDictionary.addWord(w);
                }
            }
            fr.close();
        } catch (FileNotFoundException e) {
            System.out.println("File dictionaries.txt does not exist in this folder");
        }
    }


    /**
     * Show all words of the dictionary
     */
    public void showWords() {
        System.out.printf("%-10s |%-15s |%-15s \n", "No", "English", "Tiếng Việt");
        for (int i = 0; i < this.myDictionary.getSize(); i++) {
            Word w = this.myDictionary.getWordAt(i);
            System.out.printf("%-10s |%-15s |%-15s \n", i, w.getWord_target(), w.getWord_explain());
        }
    }

    public static void main(String[] args) throws IOException {
        DictionaryManagement dict1 = new DictionaryManagement();
        dict1.insertFromFile();
        dict1.showWords();
    }
}
