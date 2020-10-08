package EditGUI;

import DictionaryRoot.Word;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EditButton {
    private int posX;
    private int posY;
    private int width;
    private int height;

    private JButton editButton;

    public EditButton() {
        this.posX = 0;
        this.posY = 0;
        this.width = 0;
        this.height = 0;
        editButton = new JButton();
    }

    void setPosition(int posX, int posY, int width, int height) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
    }

    private static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    void addMouseListener(EditGUI app) {
        editButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String targetWord = app.getWordTarget();
                String explainWord = app.getWordExplain();
                String msg = "Are you sure to edit mean of '" + targetWord + "' to '" + explainWord + "'";

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

                    app.setTextFieldActivate(false);
                }
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

    JButton createEditButton(EditGUI app) {
        ImageIcon editIcon = new ImageIcon("resources/EditButton.png");
        editButton = new JButton(resizeIcon(editIcon, this.width, this.height));
        editButton.setBounds(this.posX, this.posY, this.width, this.height);

        addMouseListener(app);

        return editButton;
    }
}

