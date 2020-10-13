package DictionaryRoot;

import java.io.IOException;

public class DictionaryCommandline {
    private DictionaryManagement dictionaryManagement;

    /**
     * constructor for dictionary commandline version.
     */
    public DictionaryCommandline() {
        dictionaryManagement = new DictionaryManagement();
    }

    /**
     * show all word to terminal.
     */
    public void showAllWords() {
        this.dictionaryManagement.showWords();
    }

    /**
     * return suggest word.
     * @throws IOException no file match "dictionary.txt".
     */
    public void dictionarySearcher() throws IOException {
        this.dictionaryManagement.suggestWord();
    }

    /**
     * basic dictionary.
     * @throws IOException no file match.
     */
    public void dictionaryBasic() throws IOException {
        this.dictionaryManagement.insertFromCommandline();
        this.showAllWords();
    }

    /**
     * dictionary advanced.
     * @throws IOException no file match.
     */
    public void dictionaryAdvanced() throws IOException {
        this.dictionaryManagement.insertFromFile();
        this.showAllWords();
        this.dictionaryManagement.dictionaryLookup();
    }

    /**
     * main function to test dictionary command line version.
     * @param args args.
     * @throws IOException no file match.
     */
    public static void main(String[] args) throws IOException {
        DictionaryCommandline dict1 = new DictionaryCommandline();
        dict1.dictionaryAdvanced();
        dict1.dictionarySearcher();
//        dict1.dictionaryManagement.editWord();

        dict1.dictionaryManagement.addWord();
//        dict1.dictionaryManagement.deleteWord();

//        dict1.dictionarySearcher();
        dict1.dictionaryManagement.dictionaryExportToFile();
    }
}
