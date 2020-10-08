package SearchGUI;

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

    void addMouseListener(SearchGUI app) {
        editButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JDialog editDialog = EditDialog.createDialog(app, 250, 250, 400, 250);
                editDialog.setVisible(true);
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

    JButton createEditButton(SearchGUI app) {
        ImageIcon enIcon = new ImageIcon("resources/EditButton.png");
        editButton = new JButton(resizeIcon(enIcon, this.width, this.height));
        editButton.setBounds(this.posX, this.posY, this.width, this.height);

        addMouseListener(app);

        return editButton;
    }
}
