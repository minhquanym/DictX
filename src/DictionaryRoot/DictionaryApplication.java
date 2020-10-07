package DictionaryRoot;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import EditGUI.EditGUI;
import SearchGUI.*;
import SearchGUI.TextArea;

public class DictionaryApplication extends Dictionary {
    private int frameWidth;
    private int frameHeight;
    private JFrame mainFrame;
    private JPanel controlPanel;

    public DictionaryApplication() {
        frameWidth = 0;
        frameHeight = 0;

        mainFrame = new JFrame("Dictionary");
        controlPanel = new JPanel();
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    public JPanel getControlPanel() {
        return controlPanel;
    }

    public int getFrameWidth() {
        return frameWidth;
    }

    public int getFrameHeight() {
        return frameHeight;
    }

    public void AssignParameter() {
        frameWidth = 700;
        frameHeight = 600;
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

    public void runApplication() {
        // load word
        try {
            this.importFromFile();
        } catch (IOException err) {
            err.printStackTrace();
        }

        // assign parameter
        AssignParameter();

        // create main frame
        mainFrame.setSize(frameWidth, frameHeight+40);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLayout(null);
        mainFrame.setResizable(false);

        // create main panel
        controlPanel.setLayout(null);
        controlPanel.setBackground(new Color(141, 202, 255));
        controlPanel.setBounds(0, 0, frameWidth, frameHeight+40);

//        // search GUI
//        SearchGUI searchGUI = new SearchGUI(this);
//        searchGUI.execute();

        // edit GUI
        EditGUI editGUI = new EditGUI(this);
        editGUI.execute();

        // run
        mainFrame.add(controlPanel);
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        DictionaryApplication dictionary = new DictionaryApplication();
        dictionary.runApplication();
    }
}
