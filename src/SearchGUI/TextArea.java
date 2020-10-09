package SearchGUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TextArea {
    private int posX;
    private int posY;
    private int width;
    private int height;
    private JTextArea textArea;
    private JScrollPane scrollArea;

    public TextArea() {
        textArea = new JTextArea();
        scrollArea = new JScrollPane();
    }

    public void setPosition(int posX, int posY, int width, int height) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
    }

    public void DisableEditing() {
        // Dirty hack to disable editing
        textArea.addFocusListener(new FocusListener() {
            @Override
            public void focusLost(FocusEvent e) {
                textArea.setEditable(true);
            }
            @Override
            public void focusGained(FocusEvent e) {
                textArea.setEditable(false);
            }
        });

        textArea.addMouseListener(new MouseListener() {
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

        textArea.setCaretColor(Color.WHITE);
    }

    public JScrollPane getCurrent() {
        return scrollArea;
    }

    public void setForeground(Color color) {
        textArea.setForeground(color);
    }

    public JScrollPane createTextArea(String text, Color myColor) {
        textArea = new JTextArea();
        textArea.setFont(new Font("SansSerif", Font.PLAIN, 14));

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        Border border = BorderFactory.createLineBorder(Color.BLACK);
        textArea.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        DisableEditing();

        textArea.setText("");
        textArea.append(text);

        // convert to scrollPane
        scrollArea = new JScrollPane(textArea);
        scrollArea.setBounds(this.posX, this.posY, this.width, this.height);
        CustomizeScrollBar.custom(scrollArea);

        return scrollArea;
    }

    public static void main(String[] args) {
        TextArea foo = new TextArea();

        JFrame frame = new JFrame();
        frame.setSize(100, 100);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 100, 100);

        panel.add(foo.createTextArea("Hello World", Color.BLACK));

        frame.add(panel);
        frame.setVisible(true);
    }
}

