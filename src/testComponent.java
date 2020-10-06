import javax.swing.*;
import java.awt.*;

public class testComponent {
    public static void main(String[] args) {
        JTextField textField = new JTextField();
        textField.setBounds(0, 0, 100, 100);
        textField.setFont(new Font("SansSerif", Font.PLAIN, 20));
        textField.setText("Search:");

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 200, 200);
        panel.add(textField);

        JFrame frame = new JFrame();
        frame.setSize(110, 110);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.add(panel);
        frame.setVisible(true);
    }
}
