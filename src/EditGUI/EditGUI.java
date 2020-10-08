package EditGUI;

import DictionaryRoot.Dictionary;
import DictionaryRoot.DictionaryApplication;
import DictionaryRoot.Word;
import SearchGUI.BackgroundImage;

import javax.swing.*;
import java.io.IOException;

public class EditGUI extends Dictionary {
    private int frameWidth;
    private int frameHeight;

    private JFrame mainFrame;
    private JPanel controlPanel;

    private WordTextField wordTargetTextField;
    private WordTextField wordExplainTextField;

    private EditButton editButton;
    private HomePageLabel homePageLabel;

    public EditGUI(DictionaryApplication app) {
        this.mainFrame = app.getMainFrame();
        this.controlPanel = app.getControlPanel();
        this.frameWidth = app.getFrameWidth();
        this.frameHeight = app.getFrameHeight();
    }

    JFrame getMainFrame() {
        return this.mainFrame;
    }

    String getWordTarget() {
        return wordTargetTextField.getText();
    }

    String getWordExplain() {
        return wordExplainTextField.getText();
    }

    void setTextFieldActivate(boolean activate) {
        wordTargetTextField.setActivate(activate);
        wordExplainTextField.setActivate(activate);
    }

    public void execute() {
        // load word
        try {
            this.importFromFile();
        } catch (IOException err) {
            err.printStackTrace();
        }

        // background
        BackgroundImage backgroundImage = new BackgroundImage();
        backgroundImage.setBounds(10, 0, frameWidth, 160);
        backgroundImage.load();
        controlPanel.add(backgroundImage);

        // Word Target TextField
        wordTargetTextField = new WordTextField();
        wordTargetTextField.setPosition(20, 200, 500, 70);
        controlPanel.add(wordTargetTextField.createWordTextField(this, "Word Target"));

        // Word Explain TextField
        wordExplainTextField = new WordTextField();
        wordExplainTextField.setPosition(20, 300, 500, 70);
        controlPanel.add(wordExplainTextField.createWordTextField(this, "Word Explain"));

        // Edit Button
        editButton = new EditButton();
        editButton.setPosition(560, 230, 100, 100);
        controlPanel.add(editButton.createEditButton(this));

        // Home Page Label
        homePageLabel = new HomePageLabel();
        homePageLabel.setBounds(frameWidth/2-180, 420, 300, 130);
        homePageLabel.load();
        controlPanel.add(homePageLabel);
    }
}
