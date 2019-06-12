package base;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Cette classe permet de créer des boutons contenant une image
 */
public class OwnButton extends JButton {


    /**
     * Constructeur permettant la création d'un bouton contenant une icône
     *
     * @param image
     * @param width
     * @param height
     */
    public OwnButton (ImageIcon image, int width, int height) {
        super(image);

        setBorderPainted(false);
        setBorder(new EmptyBorder(0,0,0, 0));
        setFocusable(false);
        setBackground(new Color(0,0,0, 0));
        setContentAreaFilled(false);

        setLayout(new BorderLayout());

        setPreferredSize(new Dimension(width, height));
    }

    /**
     * Constructeur permettant la création d'un bouton par dessus un OwnPanel et contenant une icône
     *
     * @param image
     * @param width
     * @param height
     * @param text
     */
    public OwnButton (ImageIcon image, int width, int height, String text) {
        super(image);

        setBorderPainted(false);
        setBorder(new EmptyBorder(0,0,0, 0));
        setFocusable(false);
        setBackground(new Color(0,0,0, 0));
        setContentAreaFilled(true);
        setRolloverEnabled(false);

        setLayout(new BorderLayout());

        setPreferredSize(new Dimension(width, height));
    }

    /**
     * Constructeur permettant de créer un bouton contenant l'image du contact et son nom correctement aligné
     *
     * @param image
     * @param text
     * @param width
     * @param height
     * @param taillePolice
     */
    public OwnButton (ImageIcon image, String text, int width, int height, int taillePolice) {
        super(image);

        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(0,0,0,0));

        setIcon(image);
        setText(text);

        setFont(new Font("Bahnshrift",Font.BOLD, taillePolice));

        setBorderPainted(false);
        setFocusable(false);
        setBackground(new Color(0,0,0,0));
        setPreferredSize(new Dimension(width, height));
        setContentAreaFilled(true);
        setRolloverEnabled(false);


        setHorizontalAlignment(SwingConstants.LEFT);

        setPreferredSize(new Dimension(width,height));

    }


}

