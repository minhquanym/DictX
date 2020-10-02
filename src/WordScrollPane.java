import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class WordScrollPane {
    private int posX;
    private int posY;
    private int width;
    private int height;
    private int visibleRowCount;
    private JScrollPane scrollPane;

    WordScrollPane() {
        scrollPane = new JScrollPane();
    }

    public void setPosition(int posX, int posY, int width, int height, int visibleRowCount) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.visibleRowCount = visibleRowCount;
    }

    void addMouseListener(DictionaryApplication app, JList<String> wordList) {
        wordList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String word = wordList.getSelectedValue();
                app.createTextPane(app.searchWord(word).getWord_explain(), Color.magenta);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    public JScrollPane createScrollPane(DictionaryApplication app, ArrayList<Word> suggestWords) {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Word word : suggestWords) {
            listModel.addElement(word.getWord_target());
        }

        JList<String> wordList = new JList<>(listModel);
        wordList.setVisibleRowCount(this.visibleRowCount);
        addMouseListener(app, wordList);

        scrollPane = new JScrollPane(wordList);
        scrollPane.setBounds(this.posX, this.posY, this.width, this.height);

        return scrollPane;
    }

    JScrollPane getCurrent() {
        return scrollPane;
    }
}
