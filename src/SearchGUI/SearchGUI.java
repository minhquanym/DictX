package SearchGUI;

import DictionaryRoot.*;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import MainPageGUI.MainPageGUI;
import org.json.JSONException;

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
    private DeleteButton deleteButton;
    private EditButton editButton;
    private ReturnButton returnButton;

    private GoogleTranslator ggTranslator;

    /**
     * constructor search gui.
     * @param app my dictionary app.
     */
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
        deleteButton = new DeleteButton();
        editButton = new EditButton();
        returnButton = new ReturnButton();

        ggTranslator = new GoogleTranslator();
    }

    /**
     * constructor search gui.
     * @param frameWidth frame 's width.
     * @param frameHeight frame 's height.
     * @param mainFrame main frame.
     * @param controlPanel control panel.
     */
    public SearchGUI(int frameWidth, int frameHeight, JFrame mainFrame, JPanel controlPanel) {
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;

        currentWordTarget = "Ore";
        currentWordExplain = "BeosU";

        this.mainFrame = mainFrame;
        this.controlPanel = controlPanel;

        textBox = new TextBox();
        scrollPane = new WordScrollPane();
        textArea = new TextArea();

        voiceButton = new VoiceButton();
        deleteButton = new DeleteButton();
        editButton = new EditButton();
        returnButton = new ReturnButton();

        ggTranslator = new GoogleTranslator();
    }

    /**
     * getter for current word target.
     * @return word target.
     */
    String getCurrentWordTarget() {
        return currentWordTarget;
    }

    /**
     * getter for current word explain.
     * @return word explain.
     */
    String getCurrentWordExplain() {
        return currentWordExplain;
    }

    /**
     * setter for current word target.
     * @param text word target will be assigned with text.
     */
    void setCurrentWordTarget(String text) {
        currentWordTarget = text;
    }

    /**
     * setter for current word explain.
     * @param text word explain will be assigned with text.
     */
    void setCurrentWordExplain(String text) {
        currentWordExplain = text;
    }

    /**
     * getter for main frame.
     * @return main frame.
     */
    public JFrame getMainFrame() {
        return mainFrame;
    }

    /**
     * create text box.
     * @return text box.
     */
    public JTextField createTextBox() {
        return textBox.createTextBox(this);
    }

    /**
     * create scroll text area.
     * @param text text will be appeared in area.
     * @param textColor text 's color.
     * @return scroll area.
     */
    public JScrollPane createTextArea(String text, Color textColor) {
        return textArea.createTextArea(text, textColor);
    }

    /**
     * create scroll pane with list word.
     * @param suggestWords list word.
     * @return scroll pane.
     */
    public JScrollPane createScrollPane(ArrayList<Word> suggestWords) {
        return scrollPane.createScrollPane(this, suggestWords);
    }

    /**
     * remover current scroll pane.
     */
    public void removeScrollPane() {
        controlPanel.remove(scrollPane.getCurrent());
    }

    /**
     * remove current text box.
     */
    public void removeTextBox() {
        controlPanel.remove(textBox.getCurrent());
    }

    /**
     * remove current text area.
     */
    public void removeTextArea() {
        controlPanel.remove(textArea.getCurrent());
    }

    /**
     * control panel repaint.
     */
    public void controlPanelRepaint() {
        controlPanel.revalidate();
        controlPanel.repaint();
    }

    /**
     * remove all component in control panel.
     */
    public void controlPanelRemoveAll() {
        controlPanel.removeAll();
    }

    /**
     * add component to control panel.
     * @param comp component.
     */
    public void addToPanel(Component comp) {
        controlPanel.add(comp);
    }

    /**
     * print content of word to text area.
     * @param word word which want to be printed.
     * @param matchWord translated word which have data in dictionary.
     */
    public void textAreaPrint(String word, String matchWord) {
//        String ggMeaningVi = "";
//        try {
//            ggMeaningVi = ggTranslator.translateSingleWord("en", "vi", word);
//        } catch (Exception err) {
//            err.printStackTrace();
//            if (err instanceof JSONException) {
//                    System.out.println("LAG A\n");
//                try {
//                    ggMeaningVi = ggTranslator.translateParagraph("en", "vi", word);
//                } catch (Exception anotherError) {
//                    anotherError.printStackTrace();
//                    ggMeaningVi = "Cannot connect to internet !!!";
//                }
//            } else if (err instanceof java.net.UnknownHostException) {
//                ggMeaningVi = "No internet connection !!!";
//            } else {
//                ggMeaningVi = "ERROR when connect to internet !!!";
//            }
//        }

        String text = "***** My Dictionary: \n" + matchWord;
//                + "\n\n\n***** Google Translate(vi): \n" + ggMeaningVi;

        removeTextArea();
        addToPanel(createTextArea(text, Color.magenta));
        controlPanelRepaint();

        setCurrentWordExplain(matchWord);
        setCurrentWordTarget(word);
    }

    /**
     * execute main page gui.
     */
    void executeMainPage() {
        controlPanel.removeAll();
        MainPageGUI mainPageGUI = new MainPageGUI(this.frameWidth, this.frameHeight, this.mainFrame, this.controlPanel);
        mainPageGUI.execute();

        controlPanel.repaint();
        controlPanel.revalidate();
    }

    /**
     * execute search gui.
     */
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
        voiceButton.addMouseListener(this);

        // delete button
        deleteButton = new DeleteButton();
        deleteButton.setPosition(frameWidth - 37 - 31, 140, 27, 27);
        controlPanel.add(deleteButton.createDeleteButton(this));

        // edit button
        editButton = new EditButton();
        editButton.setPosition(frameWidth - 37 - 31 - 31, 140, 27, 27);
        controlPanel.add(editButton.createEditButton(this));

        // return button
        returnButton = new ReturnButton();
        returnButton.setPosition(frameWidth - 37 - 31 - 31 - 31, 140, 27, 27);
        controlPanel.add(returnButton.createReturnButton(this));

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
