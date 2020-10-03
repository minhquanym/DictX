import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class DictionaryApplication extends Dictionary {
    private int frame_width;
    private int frame_height;
    private JFrame mainFrame;
    private JPanel controlPanel;

    private TextPane textPane;
    private TextBox textBox;
    private WordScrollPane scrollPane;

    DictionaryApplication() {
        frame_width = 0;
        frame_height = 0;
        mainFrame = new JFrame("Dictionary");
        controlPanel = new JPanel();
        textPane = new TextPane();
        textBox = new TextBox();
        scrollPane = new WordScrollPane();
    }

    public void AssignParameter() {
        frame_width = 800;
        frame_height = 700;
    }

    JTextField createTextBox() {
        return textBox.createTextBox(this);
    }

    JTextPane createTextPane(String text, Color textColor) {
        return textPane.createTextPane(text, textColor);
    }

    JScrollPane createScrollPane(ArrayList<Word> suggestWords) {
        return scrollPane.createScrollPane(this, suggestWords);
    }

    public void removeScrollPane() {
        controlPanel.remove(scrollPane.getCurrent());
    }

    public void removeTextBox(JTextField miniTextBox) {
        controlPanel.remove(textBox.getCurrent());
    }

    public void removeTextPane() {
        controlPanel.remove(textPane.getCurrent());
    }

    public void controlPanelRepaint() {
        controlPanel.revalidate();
        controlPanel.repaint();
    }

    public void addToPanel(Component comp) {
        controlPanel.add(comp);
    }

    public void runApplication() {
        /// load word
        try {
            this.importFromFile();
        } catch (IOException err) {
            err.printStackTrace();
        }

        // assign parameter
        AssignParameter();

        // create main frame
        mainFrame.setSize(frame_width, frame_height);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLayout(null);
        mainFrame.setResizable(false);

        /// main panel
        controlPanel.setLayout(null);
        controlPanel.setBackground(Color.CYAN);
        controlPanel.setBounds(0, 0, frame_width, frame_height);

        // background png
//        BackgroundImage background = new BackgroundImage();
//        background.setBounds(0, 0, frame_width, 100);
//        background.load();
//        controlPanel.add(background);

        // text box
        textBox = new TextBox();
        textBox.setPosition(10, 10, 100, 30);
        controlPanel.add(createTextBox());

        // scroll pane
        scrollPane = new WordScrollPane();
        scrollPane.setPosition(10, 50, 100, 300, 5);

        ArrayList<Word> suggestWords = this.suggestWord("", -1);
        controlPanel.add(createScrollPane(suggestWords));

        // textPane
        textPane = new TextPane();
        textPane.setPosition(120, 50, 200, 200);
        controlPanel.add(textPane.createTextPane("", Color.RED));

        // run
        mainFrame.add(controlPanel);
        mainFrame.setVisible(true);

//        // test
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        removeScrollPane();
//        controlPanel.add(createScrollPane(this.suggestWord("b", 1)));
//        controlPanel.revalidate();
//        controlPanel.repaint();
//        mainFrame.add(controlPanel);
//        mainFrame.setVisible(true);
//        mainFrame.add(controlPanel);
//
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        controlPanel.add(scrollPane.createScrollPane(suggestWords));
//        controlPanel.revalidate();
//        controlPanel.repaint();
    }

    public static void main(String[] args) {
        DictionaryApplication dictionary = new DictionaryApplication();
        dictionary.runApplication();
    }
}
