package SearchGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import DictionaryRoot.*;
import javazoom.jl.decoder.JavaLayerException;

public class VoiceButton {
    private int posX;
    private int posY;
    private int width;
    private int height;

    private JButton enButton;

    GooglePronounce googlePronounce;

    public VoiceButton() {
        enButton = new JButton();
        googlePronounce = new GooglePronounce();
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

    public void addMouseListener(SearchGUI app) {
        enButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String text = app.getCurrentWordTarget();
                try {
                    googlePronounce.pronounce("en", text);
                } catch (IOException err) {
                    err.printStackTrace();
                } catch (JavaLayerException err) {
                    err.printStackTrace();
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

    JButton createVoiceButtonEn(SearchGUI app) {
        ImageIcon enIcon = new ImageIcon("resources/VoiceButtonEn.png");
        enButton = new JButton(resizeIcon(enIcon, this.width, this.height));
        enButton.setBounds(this.posX, this.posY, this.width, this.height);
        enButton.setToolTipText("english voice");
        return enButton;
    }
}
