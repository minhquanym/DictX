package DictionaryRoot;

public class Word {
    private String word_target;
    private String word_explain;

    /**
     * Constructor for a word.
     * @param word_target target string.
     * @param word_explain explain string.
     */
    public Word(String word_target, String word_explain) {
        this.word_target = word_target;
        this.word_explain = word_explain;
    }

    /**
     * getter for word target.
     * @return word target.
     */
    public String getWord_target() {
        return this.word_target;
    }

    /**
     * getter for word explain.
     * @return word explain.
     */
    public String getWord_explain() {
        String returnText = word_explain;
        returnText = returnText.replace("#", "");
        returnText = returnText.replace("*", "\n*");
        returnText = returnText.replace("|-", "\n  -");
        returnText = returnText.replace("|=", "\n     » ");
        returnText = returnText.replace("|+", "\n       ");
        return returnText;
    }

    /**
     * get plain version of word explain.
     * @return plain text of word explain.
     */
    public String getPlain_explain() {
        return this.word_explain;
    }
}
