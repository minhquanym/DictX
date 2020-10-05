import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EditButton {
    private int posX;
    private int posY;
    private int width;
    private int height;
    private JButton butt;

    EditButton() {
        butt = new JButton("Edit");
    }

    public void setPosition(int posX, int posY, int width, int height) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
    }

    JButton getCurrent() {
        return butt;
    }

    void addMouseListener(DictionaryApplication app) {
        butt.addMouseListener(new MouseListener() {
            void updateGUI() {
                app.controlPanelRemoveAll();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                updateGUI();
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

    JButton createEditButton(DictionaryApplication app) {
        butt.setBounds(posX, posY, width, height);
        addMouseListener(app);
        return butt;
    }
}
