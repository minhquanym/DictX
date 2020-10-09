package MainPageGUI;

import ParagraphTranslateGUI.ParagraphTranslateGUI;

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

    public ParagraphTranslateButton() {
        this.posX = 0;
        this.posY = 0;
        this.width = 0;
        this.height = 0;
        butt = new JButton();
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

    JButton createButton(MainPageGUI app) {
        ImageIcon translateIcon = new ImageIcon("resources/ParagraphTranslateGUIButton.png");
        butt = new JButton(resizeIcon(translateIcon, this.width, this.height));
        butt.setBounds(this.posX, this.posY, this.width, this.height);
        butt.setToolTipText("Paragraph Translator");

        addMouseListener(app);

        return butt;
    }
}
