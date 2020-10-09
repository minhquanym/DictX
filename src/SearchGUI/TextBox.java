package SearchGUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.*;
import DictionaryRoot.*;

public class TextBox {
    private int posX;
    private int posY;
    private int width;
    private int height;
    private boolean activate;
    private JTextField textBox;

    public TextBox() {
        activate = false;
        textBox = new JTextField();
    }

    public boolean isActivate() {
        return activate;
    }

    public void setActivate(boolean boolValue) {
        activate = boolValue;
    }

    public void setPosition(int posX, int posY, int width, int height) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
    }

    public void customizeTextField() {
        Font fileFont = new Font("Arial", Font.PLAIN, 20);
        textBox.setFont(fileFont);
        textBox.setBackground(Color.white);
        textBox.setForeground(Color.gray.brighter());

        Border border = BorderFactory.createLineBorder(Color.BLACK);
        textBox.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
    }

    public void addKeyListener(SearchGUI app) {
        // add key listener
        textBox.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (!isActivate()) {
                    textBox.setText("");
                }
                setActivate(true);
                textBox.setForeground(Color.BLACK);

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        String text = textBox.getDocument().getText(0, textBox.getDocument().getLength());
                        System.out.println(text);

                        Word word = app.searchWord(text);
                        app.setCurrentWordTarget(text);

                        if (word == null) {
                            app.textAreaPrint(text, "Không tìm thấy kết qủa");
                        } else {
                            app.textAreaPrint(text, word.getWord_explain());
                        }
                    } catch (BadLocationException err) {
                        err.printStackTrace();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public void addMouseListener() {
        textBox.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                if (!isActivate()) {
                    textBox.setText("");
                }
                setActivate(true);
                textBox.setForeground(Color.BLACK);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub
            }
        });
    }

    public void addDocumentListener(SearchGUI app) {
        textBox.getDocument().addDocumentListener(new DocumentListener() {
            public void updateScrollPane(DocumentEvent e) {
                try {
                    String prefix = e.getDocument().getText(0, e.getDocument().getLength());
                    app.removeScrollPane();
                    app.addToPanel(app.createScrollPane(app.suggestWord(prefix, -1)));
                    app.controlPanelRepaint();
                } catch (BadLocationException err) {
                    err.printStackTrace();
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateScrollPane(e);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                updateScrollPane(e);
            }
        });
    }

    public JTextField getCurrent() {
        return textBox;
    }

    public JTextField createTextBox(SearchGUI app) {
        // set activate false
        this.activate = false;

        // create
        textBox = new RoundJTextField(100);
        textBox.setBounds(this.posX, this.posY, this.width, this.height);
        textBox.setText("Search:");

        // custom textField
        customizeTextField();

        // add DocumentListener
        addDocumentListener(app);

        // add KeyListener
        addKeyListener(app);

        // add MouseListener
        addMouseListener();

        return textBox;
    }

    public static void main(String[] args) {
        SearchGUI myDict = new SearchGUI(new DictionaryApplication());

        TextBox textBox = new TextBox();
        textBox.setPosition(20, 20, 80, 80);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 300, 300);
        panel.add(textBox.createTextBox(myDict));

        JFrame frame = new JFrame();
        frame.setLayout(null);
        frame.setSize(510, 510);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.add(panel);
        frame.setVisible(true);
    }
}
