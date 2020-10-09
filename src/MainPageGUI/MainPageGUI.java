package MainPageGUI;

import DictionaryRoot.DictionaryApplication;
import ParagraphTranslateGUI.ParagraphTranslateGUI;
import SearchGUI.BackgroundImage;
import SearchGUI.SearchGUI;

import javax.swing.*;

public class MainPageGUI {
    private int frameWidth;
    private int frameHeight;

    private JFrame mainFrame;
    private JPanel controlPanel;

    private SearchGUI searchGUI;
    private ParagraphTranslateGUI paragraphTranslateGUI;

    public MainPageGUI(DictionaryApplication app) {
        frameWidth = app.getFrameWidth();
        frameHeight = app.getFrameHeight();

        mainFrame = app.getMainFrame();
        controlPanel = app.getControlPanel();

        searchGUI = new SearchGUI(app);
        paragraphTranslateGUI = new ParagraphTranslateGUI(app);
    }

    public MainPageGUI(int frameWidth, int frameHeight, JFrame mainFrame, JPanel controlPanel) {
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.mainFrame = mainFrame;
        this.controlPanel = controlPanel;

        searchGUI = new SearchGUI(frameWidth, frameHeight, mainFrame, controlPanel);
        paragraphTranslateGUI = new ParagraphTranslateGUI(frameWidth, frameHeight, mainFrame, controlPanel);
    }

    public void executeSearchGUI() {
        controlPanel.removeAll();
        searchGUI.execute();
        controlPanel.repaint();
        controlPanel.revalidate();
    }

    public void executeParagraphTranslateGUI() {
        controlPanel.removeAll();
        paragraphTranslateGUI.execute();
        controlPanel.repaint();
        controlPanel.revalidate();
    }

    public void execute() {
        // background png
        BackgroundImage background = new BackgroundImage();
        background.setBounds(10, 0, frameWidth, 160);
        background.load();
        controlPanel.add(background);

        // SearchGUI button
        SearchGUIButton searchGUIButton = new SearchGUIButton();
        searchGUIButton.setPosition(50, 190, 260,frameHeight - 190 - 80);
        controlPanel.add(searchGUIButton.createButton(this));

        // ParagraphTranslate button
        ParagraphTranslateButton paragraphTranslateButton = new ParagraphTranslateButton();
        paragraphTranslateButton.setPosition(390, 190, 260, frameHeight - 190 - 80);
        controlPanel.add(paragraphTranslateButton.createButton(this));
    }
}
