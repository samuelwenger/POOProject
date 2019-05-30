package base;

import javax.swing.*;
import java.awt.*;

public class OwnPanel extends JPanel {

    private Image image;

    public OwnPanel(Image image){
        this.image=image;
        Dimension size = new Dimension(image.getWidth(null), image.getHeight(null));
        setSize(size);
        setLayout(null);
    }

    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0,null);
    }

    }

