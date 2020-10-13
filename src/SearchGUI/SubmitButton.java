package SearchGUI;

import DictionaryRoot.Word;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class SubmitButton {
    private int posX;
    private int posY;
    private int width;
    private int height;

    private JButton submitButton;

    /**
     * set position for submit button.
     * @param posX x coordinate.
     * @param posY y coordinate.
     * @param width button 's width.
     * @param height button 's height.
     */
    void setPosition(int posX, int posY, int width, int height) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
    }

    /**
     * fit icon to button.
     * @param icon icon image.
     * @param resizedWidth fit width.
     * @param resizedHeight fit height.
     * @return fit icon.
     */
    private static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    /**
     * add mouse listener to submit button.
     * @param app search gui.
     * @param targetField target word.
     * @param explainFields explain word.
     * @param dialog current dialog which contain submit button.
     */
    void addMouseListener(SearchGUI app, WordTextField targetField, WordTextField explainFields, JDialog dialog) {
        submitButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String targetWord = targetField.getText();
                String explainWord = explainFields.getText();
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
     * create submit button.
     * @param app search gui.
     * @param targetField word target.
     * @param explainField word explain.
     * @param dialog current dialog submit button is in.
     * @return submit button.
     */
    JButton createSubmitButton(SearchGUI app, WordTextField targetField, WordTextField explainField, JDialog dialog) {
        ImageIcon submitIcon = new ImageIcon("resources/SubmitButton.png");
        submitButton = new JButton(resizeIcon(submitIcon, this.width, this.height));
        submitButton.setBounds(this.posX, this.posY, this.width, this.height);

        addMouseListener(app, targetField, explainField, dialog);

        return submitButton;
    }
}
