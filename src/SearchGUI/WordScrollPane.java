package SearchGUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import DictionaryRoot.*;

public class WordScrollPane {
    private int posX;
    private int posY;
    private int width;
    private int height;
    private int visibleRowCount;
    private JScrollPane scrollPane;

    public WordScrollPane() {
        scrollPane = new JScrollPane();
    }

    public void setPosition(int posX, int posY, int width, int height, int visibleRowCount) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.visibleRowCount = visibleRowCount;
    }

    void addMouseListener(SearchGUI app, JList<String> wordList) {
        wordList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String word = wordList.getSelectedValue();
                String matchWord = app.searchWord(word).getWord_explain();

                app.removeTextArea();
                app.addToPanel(app.createTextArea(matchWord, Color.magenta));
                app.controlPanelRepaint();

                app.setCurrentWordExplain(matchWord);
                app.setCurrentWordTarget(word);
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

    void addArrowKeyListener() {
        JScrollBar vertical = scrollPane.getVerticalScrollBar();
        InputMap im = vertical.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        im.put(KeyStroke.getKeyStroke("DOWN"), "positiveUnitIncrement");
        im.put(KeyStroke.getKeyStroke("UP"), "negativeUnitIncrement");
    }

    public JScrollPane createScrollPane(SearchGUI app, ArrayList<Word> suggestWords) {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Word word : suggestWords) {
            listModel.addElement(word.getWord_target());
        }

        JList<String> wordList = new JList<>(listModel);
        wordList.setVisibleRowCount(this.visibleRowCount);
        addMouseListener(app, wordList);

        wordList.setFont(new Font("SansSerif", Font.BOLD, 16));

        scrollPane = new JScrollPane(wordList);
        scrollPane.setBounds(this.posX, this.posY, this.width, this.height);

        Border border = BorderFactory.createLineBorder(Color.BLACK);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(4, 4, 4, 2)));

        CustomizeScrollBar.custom(scrollPane);
        addArrowKeyListener();

        return scrollPane;
    }

    public JScrollPane getCurrent() {
        return scrollPane;
    }
}

