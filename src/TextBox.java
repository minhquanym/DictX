import org.w3c.dom.Text;

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

    public void customizeTextField(JTextField textBox) {
        textBox.setSize(this.width, this.height);

        Font fileFont = new Font("Arial", Font.PLAIN, 10);
        textBox.setFont(fileFont);
        textBox.setBackground(Color.white);
        textBox.setForeground(Color.gray.brighter());
        textBox.setColumns(300);
    }

    public void addKeyListener(DictionaryApplication app, JTextField textBox) {
        // add key listener
        textBox.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        String text = textBox.getDocument().getText(0, textBox.getDocument().getLength());
                        System.out.println(text);

                        Word word = app.searchWord(text);
                        if (word == null) {
                            app.createTextPane("No Word Found !!!", Color.RED);
                        } else {
                            System.out.println(word.getWord_explain());
                            app.createTextPane(word.getWord_explain(), Color.RED);
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

    public void addMouseListener(JTextField textBox) {
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

    public JTextField createTextBox(DictionaryApplication app) {
        // create
        textBox = new RoundJTextField(50);
        textBox.setText("Search:");

        // custom textField
        customizeTextField(textBox);

        // add DocumentListener
        textBox.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                System.out.print("Fuck this shit\n");
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                try {
                    String str = e.getDocument().getText(0, e.getDocument().getLength());
                    System.out.println(str);
                } catch (BadLocationException err) {
                    err.printStackTrace();
                }
            }

            @Override
            public void insertUpdate(DocumentEvent e) {

            }
        });

        // add KeyListener
        addKeyListener(app, textBox);

        // add MouseListener
        addMouseListener(textBox);

        return textBox;
    }
}
