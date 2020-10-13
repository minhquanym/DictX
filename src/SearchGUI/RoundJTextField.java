package SearchGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundJTextField extends JTextField {
    private Shape shape;

    /**
     * constructor no param for rounded text field.
     * @param size size of text field.
     */
    public RoundJTextField(int size) {
        super(size);
        setOpaque(false);
    }

    /**
     * paint component.
     * @param g graphic.
     */
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
        g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
        super.paintComponent(g);
    }

    /**
     * paint border.
     * @param g graphic.
     */
    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
    }

    /**
     * contain point.
     * @param x x coordinate.
     * @param y y coordinate.
     * @return contain point or not.
     */
    @Override
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15);
        }
        return shape.contains(x, y);
    }
}
