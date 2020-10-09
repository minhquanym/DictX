package ParagraphTranslateGUI;

import DictionaryRoot.DictionaryApplication;
import DictionaryRoot.GoogleTranslator;
import MainPageGUI.MainPageGUI;
import SearchGUI.BackgroundImage;

import javax.swing.*;
import java.awt.*;

public class ParagraphTranslateGUI {
    private int frameWidth;
    private int frameHeight;

    private JFrame mainFrame;
    private JPanel controlPanel;

    private TextArea targetArea;
    private TextArea explainArea;

    private TranslateButton translateButton;
    private HomeButton homeButton;

    private GoogleTranslator ggTranslator;

    public ParagraphTranslateGUI(DictionaryApplication app) {
        frameWidth = app.getFrameWidth();
        frameHeight = app.getFrameHeight();

        mainFrame = app.getMainFrame();
        controlPanel = app.getControlPanel();

        targetArea = new TextArea();
        explainArea = new TextArea();
        translateButton = new TranslateButton();

        ggTranslator = new GoogleTranslator();
    }

    public ParagraphTranslateGUI(int frameWidth, int frameHeight, JFrame mainFrame, JPanel controlPanel) {
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;

        this.mainFrame = mainFrame;
        this.controlPanel = controlPanel;

        targetArea = new TextArea();
        explainArea = new TextArea();
        translateButton = new TranslateButton();

        ggTranslator = new GoogleTranslator();
    }

    public String getTargetText() {
        return targetArea.getText();
    }

    public String getGGTranslate(String text) {
        String result = "";
        boolean internetConnection = true;

        for (int i = 0; i < text.length(); ++i) {
            if (!internetConnection) {
                break;
            }

            if (text.charAt(i) == '\n') {
                result += "\n";
                continue;
            }

            String subText = "";
            while (i < text.length() && text.charAt(i) != '\n') {
                subText += text.charAt(i);
                ++i;
            }
            --i;

            try {
                result += ggTranslator.translateParagraph("en", "vi", subText);
            } catch (Exception err) {
                err.printStackTrace();
                internetConnection = false;
                result = "No Internet Connection !!!";
            }
        }
        return result;
    }

    public void setExplainAreaText(String text) {
        explainArea.setForeground(Color.BLACK);
        explainArea.setText(text);
    }

    void executeMainPage() {
        controlPanel.removeAll();
        MainPageGUI mainPageGUI = new MainPageGUI(this.frameWidth, this.frameHeight, this.mainFrame, this.controlPanel);
        mainPageGUI.execute();

        controlPanel.repaint();
        controlPanel.revalidate();
    }

    public void execute() {
        // translate button
        translateButton = new TranslateButton();
        translateButton.setPosition(260, 500, 200, 60);
        controlPanel.add(translateButton.createTranslateButton(this));

        // home button
        homeButton = new HomeButton();
        homeButton.setPosition(200, 500, 60, 60);
        controlPanel.add(homeButton.createHomeButton(this));

        // background png
        BackgroundImage background = new BackgroundImage();
        background.setBounds(10, 0, frameWidth, 160);
        background.load();
        controlPanel.add(background);

        // target area
        targetArea = new TextArea();
        targetArea.setPosition(50, 190,  frameWidth - 10 - 100, 120);
        controlPanel.add(targetArea.createTextArea("English Paragraph", Color.BLACK));
        targetArea.addMouseListener();
        targetArea.addKeyListener();

        // exlain area
        explainArea = new TextArea();
        explainArea.setPosition(50, 350,  frameWidth - 10 - 100, 120);
        controlPanel.add(explainArea.createTextArea("Đoạn Văn Tiếng Việt", Color.RED));
        explainArea.DisableEditing();
    }
}
