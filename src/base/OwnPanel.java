package base;

import javax.swing.*;
import java.awt.*;

/**
 * Cette classe permet de créer un panel personnalisé
 */

public class OwnPanel extends JPanel {

    private Image image;

    /**
     * Constructeur permettant la création d'un panel à la taille de l'image, avec l'image en fond et un BorderLayout
     *
     * @param image
     */
    public OwnPanel(Image image){
        this.image=image;

        setBackground(new Color(0, true));
        setOpaque(true);

        Dimension size = new Dimension(image.getWidth(null), image.getHeight(null));
        setSize(size);
        setLayout(new BorderLayout());
    }


    /**
     * Constructeur permettant la création d'un panel sans background et avec le LayoutManager entré en paramètre
     *
     * @param layout
     */
    public OwnPanel(LayoutManager layout) {
        setBackground(new Color(0, true));
        setLayout(layout);
    }


    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0,null);
        super.paintComponent(g);
    }


}

