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
        frame_width = 600;
        frame_height = 600;
    }

    JTextPane createTextPane(String text, Color textColor) {
        return textPane.createTextPane(text, textColor);
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

        /// main panel
        controlPanel.setLayout(null);
        controlPanel.setBackground(Color.BLUE);
        controlPanel.setBounds(0, 0, frame_width, frame_height);

        // text box
        textBox = new TextBox();
        textBox.setPosition(10, 10, 100, 30);
        JTextField cc = textBox.createTextBox(this);
//        mainFrame.add(textBox.createTextBox());
//        controlPanel.add(textBox.createTextBox());
        controlPanel.add(cc);

        // scroll pane
        scrollPane = new WordScrollPane();
        scrollPane.setPosition(10, 50, 100, 300, 5);

        ArrayList<Word> suggestWords = this.suggestWord("", -1);
        controlPanel.add(scrollPane.createScrollPane(this, suggestWords));

        // textPane
        textPane = new TextPane();
        textPane.setPosition(120, 50, 200, 200);
        controlPanel.add(textPane.createTextPane("Hello", Color.RED));

        // run
        mainFrame.add(controlPanel);
        mainFrame.setVisible(true);

//        // test
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        controlPanel.remove(cc);
//        controlPanel.revalidate();
//        controlPanel.repaint();
//        mainFrame.add(controlPanel);
//
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        controlPanel.add(scrollPane.create(suggestWords));
////        controlPanel.revalidate();
////        controlPanel.repaint();
    }

    public static void main(String[] args) {
        DictionaryApplication dictionary = new DictionaryApplication();
        dictionary.runApplication();
    }
}
