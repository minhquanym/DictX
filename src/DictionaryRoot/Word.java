package DictionaryRoot;

public class Word {
    private String word_target;
    private String word_explain;

    /**
     * Constructor for a word
     * @param word_target
     * @param word_explain
     */
    public Word(String word_target, String word_explain) {
        this.word_target = word_target;
        this.word_explain = word_explain;
    }
    public String getWord_target() {
        return this.word_target;
    }
    public String getWord_explain() {
        String returnText = word_explain;
        returnText = returnText.replace("#", "");
        returnText = returnText.replace("*", "\n*");
        returnText = returnText.replace("|-", "\n  -");
        returnText = returnText.replace("|=", "\n     » ");
        returnText = returnText.replace("|+", "\n       ");
        return returnText;
    }

    public String getPlain_explain() {
        return this.word_explain;
    }
}
