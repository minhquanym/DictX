public class DictionaryCommandline {
    private DictionaryManagement dictionaryManagement;

    public DictionaryCommandline() {
        dictionaryManagement = new DictionaryManagement();
    }
    public void showAllWords() {
        this.dictionaryManagement.showWords();
    }

    public void dictionaryBasic() {
        this.dictionaryManagement.insertFromCommandline();
        showAllWords();
    }

    public static void main(String[] args) {
        DictionaryCommandline dict1 = new DictionaryCommandline();
        dict1.dictionaryBasic();
    }
}
