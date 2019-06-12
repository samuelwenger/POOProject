package base;

import javax.swing.*;
import java.awt.*;

/**
 *
 */

public class OwnPanel extends JPanel {

    private Image image;

    public OwnPanel(Image image){
        this.image=image;

        setBackground(new Color(0, true));
        setOpaque(true);

        Dimension size = new Dimension(image.getWidth(null), image.getHeight(null));
        setSize(size);
        setLayout(new BorderLayout());
    }


    public OwnPanel(LayoutManager layout) {
        setBackground(new Color(0, true));
        setLayout(layout);
    }

    public void paintComponent(Graphics g) {

        g.drawImage(image, 0, 0,null);
        super.paintComponent(g);
    }


}

