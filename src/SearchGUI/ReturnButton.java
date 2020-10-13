package SearchGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ReturnButton {
    private int posX;
    private int posY;
    private int width;
    private int height;

    private JButton returnButton;

    /**
     * constructor no param for return button.
     */
    public ReturnButton() {
        this.posX = 0;
        this.posY = 0;
        this.width = 0;
        this.height = 0;
        returnButton = new JButton();
    }

    /**
     * set position for return button.
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
     * add mouse listener to button.
     * @param app
     */
    void addMouseListener(SearchGUI app) {
        returnButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                app.executeMainPage();
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
     * create return button.
     * @param app search gui.
     * @return return button.
     */
    JButton createReturnButton(SearchGUI app) {
        ImageIcon enIcon = new ImageIcon("resources/ReturnButton.png");
        returnButton = new JButton(resizeIcon(enIcon, this.width, this.height));
        returnButton.setBounds(this.posX, this.posY, this.width, this.height);
        returnButton.setToolTipText("return to main page");

        addMouseListener(app);

        return returnButton;
    }
}
