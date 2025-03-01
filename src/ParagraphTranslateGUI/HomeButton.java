package ParagraphTranslateGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HomeButton {
    private int posX;
    private int posY;
    private int width;
    private int height;

    private JButton homeButton;

    /**
     * constructor for home button.
     */
    public HomeButton() {
        this.posX = 0;
        this.posY = 0;
        this.width = 0;
        this.height = 0;
        homeButton = new JButton();
    }

    /**
     * set position for home button.
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
     * fit icon to button/
     * @param icon icon image.
     * @param resizedWidth fit width.
     * @param resizedHeight fit height.
     * @return fit button.
     */
    private static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    /**
     * add mouse listener to button.
     * @param app Paragraph translate gui.
     */
    void addMouseListener(ParagraphTranslateGUI app) {
        homeButton.addMouseListener(new MouseListener() {
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
     * create home button.
     * @param app paragraph translate gui.
     * @return home button.
     */
    JButton createHomeButton(ParagraphTranslateGUI app) {
        ImageIcon enIcon = new ImageIcon("resources/HomeButton.png");
        homeButton = new JButton(resizeIcon(enIcon, this.width, this.height));
        homeButton.setBounds(this.posX, this.posY, this.width, this.height);
        homeButton.setToolTipText("return to main page");

        addMouseListener(app);

        return homeButton;
    }
}
