import java.io.IOException;

public class DictionaryCommandline {
    private DictionaryManagement dictionaryManagement;

    public DictionaryCommandline() {
        dictionaryManagement = new DictionaryManagement();
    }
    public void showAllWords() {
        this.dictionaryManagement.showWords();
    }

    public void dictionaryBasic() throws IOException {
        this.dictionaryManagement.insertFromCommandline();
        showAllWords();
    }

    public static void main(String[] args) throws IOException {
        DictionaryCommandline dict1 = new DictionaryCommandline();
        dict1.dictionaryBasic();
    }
}
