package MainPageGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ParagraphTranslateButton {
    private int posX;
    private int posY;
    private int width;
    private int height;

    private JButton butt;

    /**
     * constructor for paragraph translate button.
     */
    public ParagraphTranslateButton() {
        this.posX = 0;
        this.posY = 0;
        this.width = 0;
        this.height = 0;
        butt = new JButton();
    }

    /**
     * set position in frame for button.
     * @param posX x coordinate.
     * @param posY y coordinate.
     * @param width button's width.
     * @param height button's height.
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
     * add mouse listener for button.
     * @param app main page gui.
     */
    void addMouseListener(MainPageGUI app) {
        butt.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                app.executeParagraphTranslateGUI();
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
     * create translate button.
     * @param app main page gui.
     * @return button component.
     */
    JButton createButton(MainPageGUI app) {
        ImageIcon translateIcon = new ImageIcon("resources/ParagraphTranslateGUIButton.png");
        butt = new JButton(resizeIcon(translateIcon, this.width, this.height));
        butt.setBounds(this.posX, this.posY, this.width, this.height);
        butt.setToolTipText("Paragraph Translator");

        addMouseListener(app);

        return butt;
    }
}
