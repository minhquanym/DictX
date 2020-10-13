package ParagraphTranslateGUI;

import SearchGUI.CustomizeScrollBar;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class TextArea {
    private int posX;
    private int posY;
    private int width;
    private int height;
    private JTextArea textArea;
    private JScrollPane scrollArea;
    private boolean activate;

    /**
     * constructor no param for text area.
     */
    public TextArea() {
        textArea = new JTextArea();
        scrollArea = new JScrollPane();
        activate = false;
    }

    /**
     * setter activate.
     * @return activate.
     */
    public boolean isActivate() {
        return activate;
    }

    /**
     * setter activate.
     * @param activate activate variable.
     */
    public void setActivate(boolean activate) {
        this.activate = activate;
    }

    /**
     * set position for text area.
     * @param posX x coordinate.
     * @param posY y coordinate.
     * @param width area 's width.
     * @param height area 's height.
     */
    public void setPosition(int posX, int posY, int width, int height) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
    }

    /**
     * disable editing of text area with some trick.
     */
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

    /**
     * get current scroll pane (scroll area).
     * @return scroll area.
     */
    public JScrollPane getCurrent() {
        return scrollArea;
    }

    /**
     * set color of text in area.
     * @param color text 's color.
     */
    public void setForeground(Color color) {
        textArea.setForeground(color);
    }

    /**
     * getter of text in area.
     * @return text in TextArea.
     */
    public String getText() {
        return textArea.getText();
    }

    /**
     * setter of text in area.
     * @param text text will be added to TextArea.
     */
    public void setText(String text) {
        textArea.setText(text);
    }

    /**
     * add mouse listener for text area.
     */
    void addMouseListener() {
        textArea.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!isActivate()) {
                    textArea.setText("");
                }
                setActivate(true);
                textArea.setForeground(Color.BLACK);
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

    /**
     * add key listener for text area.
     */
    void addKeyListener() {
        textArea.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!isActivate()) {
                    textArea.setText("");
                }
                setActivate(true);
                textArea.setForeground(Color.BLACK);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    /**
     * create text area.
     * @param text text will be added to area.
     * @param myColor color of text in area.
     * @return scroll area.
     */
    public JScrollPane createTextArea(String text, Color myColor) {
        textArea = new JTextArea();
        textArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        textArea.setForeground(Color.gray.brighter());

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        Border border = BorderFactory.createLineBorder(Color.BLACK);
        textArea.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

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

