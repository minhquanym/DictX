package DeleteGUI;

import DictionaryRoot.Dictionary;
import DictionaryRoot.DictionaryApplication;
import SearchGUI.BackgroundImage;

import javax.swing.*;
import java.io.IOException;

public class DeleteGUI extends Dictionary {
    private int frameWidth;
    private int frameHeight;

    private JFrame mainFrame;
    private JPanel controlPanel;

    private WordTextField wordTargetTextField;

    public DeleteGUI(DictionaryApplication app) {
        this.mainFrame = app.getMainFrame();
        this.controlPanel = app.getControlPanel();
        this.frameWidth = app.getFrameWidth();
        this.frameHeight = app.getFrameHeight();
    }

    JFrame getMainFrame() {
        return this.mainFrame;
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

        // word target textField
        wordTargetTextField = new WordTextField();
        wordTargetTextField.setPosition(20, 200, 500, 80);
        controlPanel.add(wordTargetTextField.createWordTextField(this, "Word Target: "));
    }
}

