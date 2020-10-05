import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class DictionaryApplication extends Dictionary {
    private int frame_width;
    private int frame_height;
    private JFrame mainFrame;
    private JPanel controlPanel;

    private TextBox textBox;
    private WordScrollPane scrollPane;
    private TextArea textArea;

    private SearchButton searchButton;
    private EditButton editButton;
    private DeleteButton deleteButton;

    DictionaryApplication() {
        frame_width = 0;
        frame_height = 0;

        mainFrame = new JFrame("Dictionary");
        controlPanel = new JPanel();

        textBox = new TextBox();
        scrollPane = new WordScrollPane();
        textArea = new TextArea();

        searchButton = new SearchButton();
        editButton = new EditButton();
        deleteButton = new DeleteButton();
    }

    public void AssignParameter() {
        frame_width = 700;
        frame_height = 600;
    }

    JTextField createTextBox() {
        return textBox.createTextBox(this);
    }

    JScrollPane createTextArea(String text, Color textColor) {
        return textArea.createTextArea(text, textColor);
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

    private void functionButtonGUI() {
        // search button
        searchButton = new SearchButton();
        searchButton.setPosition(0, 0, 90, frame_height/3);
        controlPanel.add(searchButton.createSearchButton(this));
        // edit button
        editButton = new EditButton();
        editButton.setPosition(0, frame_height/3, 90, frame_height/3);
        controlPanel.add(editButton.createEditButton(this));

        // delete button
        deleteButton = new DeleteButton();
        deleteButton.setPosition(0, frame_height/3*2, 90, frame_height/3);
        controlPanel.add(deleteButton.createDeleteButton(this));
    }

    public void searchGUI() {
//        functionButtonGUI();

        // background png
        BackgroundImage background = new BackgroundImage();
        background.setBounds(10, 0, frame_width, 160);
        background.load();
        controlPanel.add(background);

        // text box
        textBox = new TextBox();
        textBox.setPosition(10, 170, 250, 50);
        controlPanel.add(createTextBox());

        // scroll pane
        scrollPane = new WordScrollPane();
        scrollPane.setPosition(10, 230, 250, frame_height - 10 - 220, 5);
        ArrayList<Word> suggestWords = this.suggestWord("", -1);
        controlPanel.add(createScrollPane(suggestWords));

        // textArea
        textArea = new TextArea();
        textArea.setPosition(270, 170,  frame_width - 10 - 270, frame_height- 10 - 160);
        controlPanel.add(textArea.createTextArea("Welcome to Oe? and Beo'u dictionary", Color.RED));
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
        mainFrame.setSize(frame_width, frame_height+40);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLayout(null);
        mainFrame.setResizable(false);

        // create main panel
        controlPanel.setLayout(null);
        controlPanel.setBackground(new Color(141, 202, 255));
        controlPanel.setBounds(0, 0, frame_width, frame_height+40);

        // search GUI
        searchGUI();

        // run
        mainFrame.add(controlPanel);
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        DictionaryApplication dictionary = new DictionaryApplication();
        dictionary.runApplication();
    }
}
