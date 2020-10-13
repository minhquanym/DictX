package SearchGUI;

import DictionaryRoot.Word;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class DeleteButton {
    private int posX;
    private int posY;
    private int width;
    private int height;

    private JButton delButton;

    /**
     * constructor no param for del button.
     */
    public DeleteButton() {
        this.posX = 0;
        this.posY = 0;
        this.width = 0;
        this.height = 0;
        delButton = new JButton();
    }

    /**
     * set position for button.
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
     * add mouse listener to del button.
     * @param app search gui.
     */
    void addMouseListener(SearchGUI app) {
        delButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String targetWord = app.getCurrentWordTarget();
                Word word = app.searchWord(targetWord);

                if (word != null) {
                    String msg = "Are you sure, want to delete '" + targetWord + "' from the dictionary ? ";

                    int result = JOptionPane.showConfirmDialog(app.getMainFrame(), msg, "Delete Word",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);

                    if (result == JOptionPane.YES_OPTION) {
                        app.removeWord(word);
                        try {
                            app.saveNewDictionary();
                        } catch (IOException err) {
                            err.printStackTrace();
                        }
                    }

                    app.removeScrollPane();
                    app.removeTextBox();
                    app.removeTextArea();

                    app.addToPanel(app.createScrollPane(app.suggestWord("", -1)));
                    app.addToPanel(app.createTextBox());
                    app.addToPanel(app.createTextArea("'" + targetWord + "' is removed from the dictionary !!", Color.red));

                    app.controlPanelRepaint();
                } else {
                    String msg = "'" + targetWord + "' doesn't exists in the dictionary";
                    JOptionPane.showMessageDialog(app.getMainFrame(), msg, "Error Dialog",
                            JOptionPane.ERROR_MESSAGE);
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
     * create delete button.
     * @param app search gui.
     * @return del button.
     */
    JButton createDeleteButton(SearchGUI app) {
        ImageIcon enIcon = new ImageIcon("resources/DeleteButton.png");
        delButton = new JButton(resizeIcon(enIcon, this.width, this.height));
        delButton.setBounds(this.posX, this.posY, this.width, this.height);

        delButton.setToolTipText("delete words in dictionary");

        addMouseListener(app);

        return delButton;
    }
}
