package SearchGUI;

import DictionaryRoot.Word;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class WordTextField {
    private int posX;
    private int posY;
    private int width;
    private int height;
    private boolean activate;
    private JTextField textField;

    /**
     * constructor no param for word text field.
     */
    public WordTextField()  {
        posX = 0;
        posY = 0;
        width = 0;
        height = 0;
        activate = false;
        textField = new JTextField();
    }

    /**
     * set position for word text field.
     * @param posX x coordinate.
     * @param posY y coordinate.
     * @param width field 's width.
     * @param height field 's height.
     */
    public void setPosition(int posX, int posY, int width, int height) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
    }

    /**
     * getter for activate variable.
     * @return activate.
     */
    public boolean isActivate() {
        return activate;
    }

    /**
     * setter for activate variable.
     * @param activate set value.
     */
    public void setActivate(boolean activate) {
        this.activate = activate;
    }

    /**
     * customize text field.
     */
    public void customizeTextField() {
        Font fileFont = new Font("Arial", Font.PLAIN, 20);
        textField.setFont(fileFont);
        textField.setBackground(Color.white);
        textField.setForeground(Color.gray.brighter());
    }

    /**
     * getter for current text field.
     * @return current text field.
     */
    JTextField getCurrent() {
        return textField;
    }

    /**
     * get text in field.
     * @return text.
     */
    String getText() {
        return textField.getText();
    }

    /**
     * add mouse listener to text field.
     */
    void addMouseListener() {
        textField.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!isActivate()) {
                    textField.setText("");
                }
                setActivate(true);
                textField.setForeground(Color.BLACK);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    /**
     * add key listener to text field.
     * @param app search gui.
     * @param targetField word target.
     * @param explainField word explain.
     * @param dialog current dialog which contains text field.
     */
    void addKeyListener(SearchGUI app, WordTextField targetField, WordTextField explainField, JDialog dialog) {
        // add key listener
        textField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (!isActivate()) {
                    textField.setText("");
                }
                setActivate(true);
                textField.setForeground(Color.BLACK);

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String targetWord = targetField.getText();
                    String explainWord = explainField.getText();
                    String msg = "Are you sure, want to edit mean of '" + targetWord + "' to '" + explainWord + "'";

                    int result = JOptionPane.showConfirmDialog(app.getMainFrame(), msg, "Edit Dialog",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);

                    if (result == JOptionPane.YES_OPTION) {
                        Word wordFinder = app.searchWord(targetWord);
                        if (wordFinder != null) {
                            app.removeWord(wordFinder);
                        }

                        Word newWord = new Word(targetWord, explainWord);
                        app.addWord(newWord);

                        try {
                            app.saveNewDictionary();
                        } catch (IOException err) {
                            err.printStackTrace();
                        }

                        app.removeScrollPane();
                        app.addToPanel(app.createScrollPane(app.suggestWord("", -1)));
                        app.controlPanelRepaint();

                        dialog.setVisible(false);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    /**
     * create word text field.
     * @param app search gui.
     * @param nameTextField name of text field.
     * @param targetField word target.
     * @param explainField word explain.
     * @param dialog dialog which contains text field.
     * @return text field.
     */
    public JTextField createWordTextField(SearchGUI app, String nameTextField, WordTextField targetField, WordTextField explainField, JDialog dialog) {
        textField = new RoundJTextField(100);
        textField.setBounds(this.posX, this.posY, this.width, this.height);
        textField.setText(nameTextField);

        customizeTextField();
        addMouseListener();
        addKeyListener(app, targetField, explainField, dialog);

        return textField;
    }
}
