package DictionaryRoot;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import MainPageGUI.MainPageGUI;

public class DictionaryApplication extends Dictionary {
    private int frameWidth;
    private int frameHeight;
    private JFrame mainFrame;
    private JPanel controlPanel;

    /**
     * constructor no param for dictionary application.
     */
    public DictionaryApplication() {
        frameWidth = 0;
        frameHeight = 0;

        mainFrame = new JFrame("Dictionary");
        controlPanel = new JPanel();
    }

    /**
     * getter.
     * @return main frame of application.
     */
    public JFrame getMainFrame() {
        return mainFrame;
    }

    /**
     * getter.
     * @return control panel of application.
     */
    public JPanel getControlPanel() {
        return controlPanel;
    }

    /**
     * getter.
     * @return frame width.
     */
    public int getFrameWidth() {
        return frameWidth;
    }

    /**
     * getter.
     * @return get frame height.
     */
    public int getFrameHeight() {
        return frameHeight;
    }

    /**
     * assign parameter frame height and frame width for application.
     */
    public void AssignParameter() {
        frameWidth = 700;
        frameHeight = 600;
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
     * @param comp component which want to add.
     */
    public void addToPanel(Component comp) {
        controlPanel.add(comp);
    }

    /**
     * run application.
     */
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

        // main page GUI
        MainPageGUI mainPageGUI = new MainPageGUI(this);
        mainPageGUI.execute();

        // run
        mainFrame.add(controlPanel);
        mainFrame.setVisible(true);
    }

    /**
     * main function to run my dictionary app.
     * @param args args.
     */
    public static void main(String[] args) {
        DictionaryApplication dictionary = new DictionaryApplication();
        dictionary.runApplication();
    }
}
