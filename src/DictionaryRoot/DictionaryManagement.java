package DictionaryRoot;

import java.io.*;
import java.util.*;

public class DictionaryManagement {
    private Dictionary myDictionary;

    /**
     * Call dictionary constructor.
     */
    public DictionaryManagement() {
        myDictionary = new Dictionary();
    }

    /**
     * Insert words by typing in command line.
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
     * Create dictionary from file dictionaries.txt.
     * @throws IOException no file match.
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
     * Show all words of the dictionary.
     */
    public void showWords() {
        System.out.printf("%-10s |%-15s |%-15s \n", "No", "English", "Tiếng Việt");
        for (int i = 0; i < this.myDictionary.getSize(); i++) {
            Word w = this.myDictionary.getWordAt(i);
            System.out.printf("%-10s |%-15s |%-15s \n", i+1, w.getWord_target(), w.getWord_explain());
        }
    }

    /**
     * Search word from command line.
     * @throws IOException no file match.
     */
    public void dictionaryLookup() throws IOException {
        System.out.print("Enter the word you want to lookup: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String English = br.readLine();

        Word results = this.myDictionary.searchWord(English);
        if (results == null) {
            System.out.println("Sorry, we did not find that word");
        } else {
            System.out.printf("%-10s |%-15s |%-15s \n", 1, results.getWord_target(), results.getWord_explain());
        }
    }

    /**
     * Add new word from command line.
     * @throws IOException no file match.
     */
    public void addWord() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the word (English) you want to add: ");
        String English = br.readLine();

        System.out.print("Enter the meaning of word (Vietnamese) you want to add: ");
        String Vietnamese = br.readLine();

        Word results = this.myDictionary.searchWord(English);
        if (results == null) {
            this.myDictionary.addWord(new Word(English, Vietnamese));
        } else {
            System.out.println("Sorry, this word existed.");
        }
    }
    /**
     * Delete word from command line.
     * @throws IOException no file match.
     */
    public void deleteWord() throws IOException {
        System.out.print("Enter the word (English) you want to delete: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String English = br.readLine();

        Word results = this.myDictionary.searchWord(English);
        if (results == null) {
            System.out.println("Sorry, we did not find that word");
        } else {
            this.myDictionary.removeWord(results);
        }
    }

    /**
     * Edit word from command line.
     * @throws IOException no file match.
     */
    public void editWord() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the word (English) you want to edit: ");
        String English = br.readLine();
        System.out.println("Enter new Vietnamese meaning: ");
        String Vietnamese = br.readLine();

        Word currentWord = this.myDictionary.searchWord(English);
        Word newWord = new Word(English, Vietnamese);
        if (currentWord == null) {
            System.out.println("Word not exist. Will be added instead");
        } else {
            this.myDictionary.removeWord(currentWord);
        }
        this.myDictionary.addWord(newWord);
    }

    /**
     * Suggest word with prefix from command line.
     * @throws IOException no file match.
     */
    public void suggestWord() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the prefix (English) you want to find: ");
        String prefix = br.readLine();

        ArrayList<Word> result = this.myDictionary.suggestWord(prefix, -1);

        System.out.println("Result found");
        System.out.printf("%-10s |%-15s |%-15s \n", "No", "English", "Tiếng Việt");
        for (int i = 0; i < result.size(); i++) {
            Word w = result.get(i);
            System.out.printf("%-10s |%-15s |%-15s \n", i+1, w.getWord_target(), w.getWord_explain());
        }
    }

    /**
     * export dictionary to "new_dictionaries.txt".
     * @throws IOException no file match.
     */
    public void dictionaryExportToFile() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("new_dictionaries.txt"));
        ArrayList<Word> allWords = myDictionary.getAllWords();

        for (int i = 0; i < allWords.size(); i++) {
            if (allWords.get(i) == null) {
                continue; // skip word be deleted
            }
            String English = allWords.get(i).getWord_target();
            String Vietnamese = allWords.get(i).getWord_explain();
            bw.write(English + "\t" + Vietnamese);
            bw.newLine();
        }
        bw.close();
    }

    /**
     * main function to test dictionary management.
     * @param args args.
     * @throws IOException no file match.
     */
    public static void main(String[] args) throws IOException {
        DictionaryManagement dict1 = new DictionaryManagement();
        dict1.insertFromFile();
        dict1.showWords();
    }
}