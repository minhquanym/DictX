package SearchGUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BackgroundImage extends JLabel {
    public void load() {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("resources/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Image dimg = img.getScaledInstance(this.getWidth(), this.getHeight(),
                Image.SCALE_SMOOTH);
        this.setIcon(new ImageIcon(dimg));
    }
}

