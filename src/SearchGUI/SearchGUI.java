package SearchGUI;

import DictionaryRoot.*;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import SearchGUI.*;
import SearchGUI.TextArea;

public class SearchGUI extends Dictionary {
    private int frameWidth;
    private int frameHeight;

    private String currentWordTarget;
    private String currentWordExplain;

    private JFrame mainFrame;
    private JPanel controlPanel;

    private TextBox textBox;
    private WordScrollPane scrollPane;
    private TextArea textArea;

    private VoiceButton voiceButton;

    public SearchGUI(DictionaryApplication app) {
        frameWidth = app.getFrameWidth();
        frameHeight = app.getFrameHeight();

        currentWordTarget = "Ore";
        currentWordExplain = "BeosU";

        mainFrame = app.getMainFrame();
        controlPanel = app.getControlPanel();

        textBox = new TextBox();
        scrollPane = new WordScrollPane();
        textArea = new TextArea();

        voiceButton = new VoiceButton();
    }

    String getCurrentWordTarget() {
        return currentWordTarget;
    }

    String getCurrentWordExplain() {
        return currentWordExplain;
    }

    void setCurrentWordTarget(String text) {
        currentWordTarget = text;
    }

    void setCurrentWordExplain(String text) {
        currentWordExplain = text;
    }

    public JTextField createTextBox() {
        return textBox.createTextBox(this);
    }

    public JScrollPane createTextArea(String text, Color textColor) {
        return textArea.createTextArea(text, textColor);
    }

    public JScrollPane createScrollPane(ArrayList<Word> suggestWords) {
        return scrollPane.createScrollPane(this, suggestWords);
    }

    public void removeScrollPane() {
        controlPanel.remove(scrollPane.getCurrent());
    }

    public void removeTextBox(JTextField miniTextBox) {
        controlPanel.remove(textBox.getCurrent());
    }

    public void removeTextArea() {
        controlPanel.remove(textArea.getCurrent());
    }

    public void controlPanelRepaint() {
        controlPanel.revalidate();
        controlPanel.repaint();
    }

    public void controlPanelRemoveAll() {
        controlPanel.removeAll();
    }

    public void addToPanel(Component comp) {
        controlPanel.add(comp);
    }

    public void execute() {
        // load word
        try {
            this.importFromFile();
        } catch (IOException err) {
            err.printStackTrace();
        }

        // voice button
        voiceButton = new VoiceButton();
        voiceButton.setPosition(frameWidth - 37, 140, 27, 27);
        controlPanel.add(voiceButton.createVoiceButtonEn(this));
        controlPanel.add(voiceButton.createVoiceButtonVi(this));
        voiceButton.addMouseListener(this);

        // background png
        BackgroundImage background = new BackgroundImage();
        background.setBounds(10, 0, frameWidth, 160);
        background.load();
        controlPanel.add(background);

        // text box
        textBox = new TextBox();
        textBox.setPosition(10, 170, 250, 50);
        controlPanel.add(createTextBox());

        // scroll pane
        scrollPane = new WordScrollPane();
        scrollPane.setPosition(10, 230, 250, frameHeight - 10 - 220, 5);
        ArrayList<Word> suggestWords = this.suggestWord("", -1);
        controlPanel.add(createScrollPane(suggestWords));

        // textArea
        textArea = new TextArea();
        textArea.setPosition(270, 170,  frameWidth - 10 - 270, frameHeight- 10 - 160);
        controlPanel.add(textArea.createTextArea("Welcome to Oe? and Beo'u dictionary", Color.RED));
    }
}
