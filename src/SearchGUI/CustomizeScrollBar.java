package SearchGUI;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class CustomizeScrollBar {
    /**
     * customize scroll pane.
     * @param scrollPane scroll pane which want to be customized.
     */
    public static void custom(JScrollPane scrollPane) {
        scrollPane.setComponentZOrder(scrollPane.getVerticalScrollBar(), 0);
        scrollPane.setComponentZOrder(scrollPane.getViewport(), 1);
        scrollPane.getVerticalScrollBar().setOpaque(false);

        scrollPane.setLayout(new ScrollPaneLayout() {
            @Override
            public void layoutContainer(Container parent) {
                JScrollPane scrollPane = (JScrollPane) parent;

                Rectangle availR = scrollPane.getBounds();
                availR.x = availR.y = 0;

                Insets parentInsets = parent.getInsets();
                availR.x = parentInsets.left;
                availR.y = parentInsets.top;
                availR.width -= parentInsets.left + parentInsets.right;
                availR.height -= parentInsets.top + parentInsets.bottom;

                Rectangle vsbR = new Rectangle();
                vsbR.width = 12;
                vsbR.height = availR.height;
                vsbR.x = availR.x + availR.width - vsbR.width;
                vsbR.y = availR.y;

                if (viewport != null) {
                    viewport.setBounds(availR);
                }
                if (vsb != null) {
                    vsb.setVisible(true);
                    vsb.setBounds(vsbR);
                }
            }
        });
        scrollPane.getVerticalScrollBar().setUI(new MyScrollBarUI());
    }
}

class MyScrollBarUI extends BasicScrollBarUI {
    private final Dimension d = new Dimension();

    /**
     * create decrease button.
     * @param orientation direction.
     * @return decreased button.
     */
    @Override
    protected JButton createDecreaseButton(int orientation) {
        return new JButton() {
            @Override
            public Dimension getPreferredSize() {
                return d;
            }
        };
    }

    /**
     * create increase button.
     * @param orientation direction.
     * @return increased button.
     */
    @Override
    protected JButton createIncreaseButton(int orientation) {
        return new JButton() {
            @Override
            public Dimension getPreferredSize() {
                return d;
            }
        };
    }

    /**
     * auto generated.
     * @param g graphic.
     * @param c component.
     * @param r rectangle.
     */
    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
    }

    /**
     * paint thumb.
     * @param g graphic.
     * @param c component.
     * @param r rectangle.
     */
    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        Color color = null;
        JScrollBar sb = (JScrollBar) c;
        if (!sb.isEnabled() || r.width > r.height) {
            return;
        } else if (isDragging) {
            color = Color.DARK_GRAY;
        } else if (isThumbRollover()) {
            color = Color.LIGHT_GRAY;
        } else {
            color = Color.GRAY;
        }
        g2.setPaint(color);
        g2.fillRoundRect(r.x, r.y, r.width, r.height, 10, 10);
        g2.setPaint(Color.WHITE);
        g2.drawRoundRect(r.x, r.y, r.width, r.height, 10, 10);
        g2.dispose();
    }

    /**
     * setter for thumb bounds.
     * @param x coordinate x.
     * @param y coordinate y.
     * @param width thumb bounds 's width.
     * @param height thumb bounds 's height.
     */
    @Override
    protected void setThumbBounds(int x, int y, int width, int height) {
        super.setThumbBounds(x, y, width, height);
        scrollbar.repaint();
    }
}

