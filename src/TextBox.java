import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.*;

public class TextBox {
    private int posX;
    private int posY;
    private int width;
    private int height;
    private boolean activate;
    private JTextField textBox;

    TextBox() {
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
        textBox.setBounds(this.posX, this.posY, this.width, this.height);
    }

    public void customizeTextField() {
        textBox.setSize(this.width, this.height);

        Font fileFont = new Font("Arial", Font.PLAIN, 10);
        textBox.setFont(fileFont);
        textBox.setBackground(Color.white);
        textBox.setForeground(Color.gray.brighter());
        textBox.setColumns(300);
    }

    public void addKeyListener(DictionaryApplication app) {
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
                        if (word == null) {
                            app.removeTextPane();
                            app.addToPanel(app.createTextPane("No Word Found !!!", Color.RED));
                            app.controlPanelRepaint();
                        } else {
//                            System.out.println(word.getWord_explain());
//                            app.createTextPane(word.getWord_explain(), Color.RED);
                            app.removeTextPane();
                            app.addToPanel(app.createTextPane(word.getWord_explain(), Color.RED));
                            app.controlPanelRepaint();
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

    public void addDocumentListener(DictionaryApplication app) {
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

    public JTextField createTextBox(DictionaryApplication app) {
        // create
        textBox = new RoundJTextField(50);
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

    JTextField getCurrent() {
        return textBox;
    }
}