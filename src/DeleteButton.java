import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DeleteButton {
    private int posX;
    private int posY;
    private int width;
    private int height;
    private JButton butt;

    DeleteButton() {
        butt = new JButton("Delete");
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

    JButton createDeleteButton(DictionaryApplication app) {
        butt.setBounds(posX, posY, width, height);
        addMouseListener(app);
        return butt;
    }
}
