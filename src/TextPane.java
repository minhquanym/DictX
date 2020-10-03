import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TextPane {
    private int posX;
    private int posY;
    private int width;
    private int height;
    JTextPane textPane;

    TextPane() {
        textPane = new JTextPane();
    }

    public void setPosition(int posX, int posY, int width, int height) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
    }

    public void addColoredText(JTextPane pane, String text, Color color) {
        StyledDocument doc = pane.getStyledDocument();

        Style style = pane.addStyle("Color style", null);
        StyleConstants.setForeground(style, color);

        try {
            doc.insertString(doc.getLength(), text, style);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public void DisableEditing() {
        // Dirty hack to disable editing
        textPane.addFocusListener(new FocusListener() {
            @Override
            public void focusLost(FocusEvent e) {
                textPane.setEditable(true);
            }
            @Override
            public void focusGained(FocusEvent e) {
                textPane.setEditable(false);
            }
        });

        textPane.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                e.consume();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                e.consume();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                e.consume();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                e.consume();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                e.consume();
            }
        });

        textPane.setCaretColor(Color.WHITE);
    }

    public JTextPane createTextPane(String text, Color myColor) {
        textPane = new JTextPane();
        textPane.setBounds(this.posX, this.posY, this.width, this.height);

        DisableEditing();

        textPane.setText("");
        addColoredText(textPane, text, myColor);
        return textPane;
    }

    JTextPane getCurrent() {
        return textPane;
    }
}
