package DictionaryRoot;

import java.io.IOException;

public class DictionaryCommandline {
    private DictionaryManagement dictionaryManagement;

    public DictionaryCommandline() {
        dictionaryManagement = new DictionaryManagement();
    }
    public void showAllWords() {
        this.dictionaryManagement.showWords();
    }
    public void dictionarySearcher() throws IOException {
        this.dictionaryManagement.suggestWord();
    }

    public void dictionaryBasic() throws IOException {
        this.dictionaryManagement.insertFromCommandline();
        this.showAllWords();
    }
    public void dictionaryAdvanced() throws IOException {
        this.dictionaryManagement.insertFromFile();
        this.showAllWords();
        this.dictionaryManagement.dictionaryLookup();
    }
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
