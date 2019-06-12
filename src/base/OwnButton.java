package base;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;

public class OwnButton extends JButton {

    public OwnButton () {
        setBorderPainted(false);
        setBorder(new EmptyBorder(0,0,0, 0));
        setFocusable(false);
        setBackground(new Color(0,0,0, 0));
        setContentAreaFilled(false);

        setLayout(new BorderLayout());
    }


    public OwnButton (ImageIcon image) {
       // super(image);


        setBorderPainted(false);
        setBorder(new EmptyBorder(0,0,0, 0));
       // setFocusable(false);
       // setBackground(new Color(0,0,0, 0));
      //  setContentAreaFilled(true);
       // setOpaque();

        setIcon(image);


        setLayout(new BorderLayout());
    }

    public OwnButton (ImageIcon image, int width, int height) {
        super(image);

        setBorderPainted(false);
        setBorder(new EmptyBorder(0,0,0, 0));
        setFocusable(false);
        setBackground(new Color(0,0,0, 0));
        setContentAreaFilled(false);
     //   setRolloverEnabled(false);

        setLayout(new BorderLayout());
       // setIcon(image);

      //  setPreferredSize(new Dimension(width, height));
    }

    public OwnButton (ImageIcon image, int width, int height, String text) {
        super(image);

        setBorderPainted(false);
        setBorder(new EmptyBorder(0,0,0, 0));
        setFocusable(false);
        setBackground(new Color(0,0,0, 0));
        setContentAreaFilled(true);
        setRolloverEnabled(false);

        setLayout(new BorderLayout());

        //  setPreferredSize(new Dimension(width, height));
    }

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

