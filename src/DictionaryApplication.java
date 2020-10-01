import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DictionaryApplication extends Dictionary {
    private int frame_width;
    private int frame_height;
    private JFrame mainFrame;
    private JPanel controlPanel;

    DictionaryApplication() {
        frame_width = 0;
        frame_height = 0;
        mainFrame = new JFrame("Dictionary");
        controlPanel = new JPanel();
    }

    public void AssignParameter() {
        frame_width = 600;
        frame_height = 600;
    }

    public void runApplication() {
        // my dictionary
//        Dictionary myDictionary = new Dictionary();

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
        TextBox textBox = new TextBox();
        textBox.setPosition(10, 10, 100, 30);
//        textBox.setPosition(10, 10, 100, 100);
        JTextField cc = textBox.createTextBox(this);
//        mainFrame.add(textBox.createTextBox());
//        controlPanel.add(textBox.createTextBox());
        controlPanel.add(cc);

        // scroll pane
        WordScrollPane scrollPane = new WordScrollPane();
        scrollPane.setPosition(10, 50, 100, 300, 5);

        ArrayList<Word> suggestWords = new ArrayList<>();// myDictionary.suggestWord("");
        controlPanel.add(scrollPane.createScrollPane(suggestWords));

        // textPane
        TextPane textPane = new TextPane();
        textPane.setPostion(120, 50, 200, 200);
        controlPanel.add(textPane.createTextPane());

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
