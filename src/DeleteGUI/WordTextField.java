package DeleteGUI;

import DictionaryRoot.Word;
import SearchGUI.RoundJTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class WordTextField {
    private int posX;
    private int posY;
    private int width;
    private int height;
    private boolean activate;
    private JTextField textField;

    public WordTextField()  {
        posX = 0;
        posY = 0;
        width = 0;
        height = 0;
        activate = false;
        textField = new JTextField();
    }

    public void setPosition(int posX, int posY, int width, int height) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
    }

    public boolean isActivate() {
        return activate;
    }

    public void setActivate(boolean activate) {
        this.activate = activate;
    }

    public void customizeTextField() {
        Font fileFont = new Font("Arial", Font.PLAIN, 24);
        textField.setFont(fileFont);
        textField.setBackground(Color.white);
        textField.setForeground(Color.gray.brighter());
    }

    JTextField getCurrent() {
        return textField;
    }

    String getText() {
        return textField.getText();
    }

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

    void addKeyListener(DeleteGUI app) {
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
//                    String targetWord = app.getWordTarget();
//                    String explainWord = app.getWordExplain();
//                    String msg = "Are you sure to edit mean of '" + targetWord + "' to '" + explainWord + "'";
//
//                    int result = JOptionPane.showConfirmDialog(app.getMainFrame(), msg, "Edit Dialog",
//                            JOptionPane.YES_NO_OPTION,
//                            JOptionPane.QUESTION_MESSAGE);
//
//                    if (result == JOptionPane.YES_OPTION) {
//                        Word wordFinder = app.searchWord(targetWord);
//                        if (wordFinder != null) {
//                            app.removeWord(wordFinder);
//                        }
//
//                        Word newWord = new Word(targetWord, explainWord);
//                        app.addWord(newWord);
//
//                        app.setTextFieldActivate(false);
//                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public JTextField createWordTextField(DeleteGUI app, String nameTextField) {
        textField = new RoundJTextField(100);
        textField.setBounds(this.posX, this.posY, this.width, this.height);
        textField.setText(nameTextField);

        customizeTextField();
        addMouseListener();
        addKeyListener(app);

        return textField;
    }
}
