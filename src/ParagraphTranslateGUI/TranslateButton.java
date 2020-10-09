package ParagraphTranslateGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TranslateButton {
    private int posX;
    private int posY;
    private int width;
    private int height;

    private JButton translateButton;

    public TranslateButton() {
        this.posX = 0;
        this.posY = 0;
        this.width = 0;
        this.height = 0;
        translateButton = new JButton();
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

    public void addMouseListener(ParagraphTranslateGUI app) {
        translateButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String targetText = app.getTargetText();
                String explainText = app.getGGTranslate(targetText);
                app.setExplainAreaText(explainText);
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

    JButton createTranslateButton(ParagraphTranslateGUI app) {
        ImageIcon translateIcon = new ImageIcon("resources/TranslateButton.png");
        translateButton = new JButton(resizeIcon(translateIcon, this.width, this.height));
        translateButton.setBounds(this.posX, this.posY, this.width, this.height);

        addMouseListener(app);

        return translateButton;
    }
}
