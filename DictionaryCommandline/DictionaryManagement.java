import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.Scanner;

public class DictionaryManagement {
    private int dictionarySize = 0;
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
    public void insertFromCommandline() {
        Scanner inp = new Scanner(System.in);
        System.out.print("Dictionary length: ");
        dictionarySize = Integer.parseInt(inp.nextLine());

        for (int i = 0; i < dictionarySize; i++) {
            System.out.print("English: ");
            String englishWord = inp.nextLine();
            System.out.print("Tieng Viet: ");
            String vietnameseWord = inp.nextLine();
            Word newWord = new Word(englishWord, vietnameseWord);
            this.myDictionary.addWord(newWord);
        }
    }

    /**
     * Show all words of the dictionary
     */
    public void showWords() {
        System.out.printf("%-10s |%-15s |%-15s \n", "No", "English", "Tiếng Việt");
        for (int i = 0; i < dictionarySize; i++) {
            Word w = this.myDictionary.getWordAt(i);
            System.out.printf("%-10s |%-15s |%-15s \n", i, w.getWord_target(), w.getWord_explain());
        }
    }

    public static void main(String[] args) {
        DictionaryManagement dict1 = new DictionaryManagement();
        dict1.insertFromCommandline();
        dict1.showWords();
    }
}
