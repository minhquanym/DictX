import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class TextPane {
    private int posX;
    private int posY;
    private int width;
    private int height;

    public void setPostion(int posX, int posY, int width, int height) {
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

    public JTextPane createTextPane() {
        JTextPane textPane = new JTextPane();

        addColoredText(textPane, "MyText\n", Color.RED);
        addColoredText(textPane, "HelloWorld", Color.BLUE);

        textPane.setBounds(this.posX, this.posY, this.width, this.height);
        return textPane;
    }
}
