import javax.swing.*;
import java.util.ArrayList;

public class WordScrollPane {
    private int posX;
    private int posY;
    private int width;
    private int height;
    private int visibleRowCount;

    public void setPosition(int posX, int posY, int width, int height, int visibleRowCount) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.visibleRowCount = visibleRowCount;
    }

    public JScrollPane createScrollPane(ArrayList<Word> suggestWords) {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Word word : suggestWords) {
            listModel.addElement(word.getWord_target());
        }

        listModel.addElement("TrungOre");
        for (int i = 1; i <= 50; ++i) {
            listModel.addElement("HelloWorld");
            listModel.addElement("HelloWorld");
            listModel.addElement("HelloWorld");
            listModel.addElement("HelloWorld");
            listModel.addElement("HelloWorld");
        }

        JList<String> wordList = new JList<>(listModel);
        wordList.setVisibleRowCount(this.visibleRowCount);

        JScrollPane scrollPane = new JScrollPane(wordList);
        scrollPane.setBounds(this.posX, this.posY, this.width, this.height);

        return scrollPane;
    }
}
