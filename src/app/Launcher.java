package app;

import base.OwnButton;
import base.OwnPanel;
import base.Start;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Launcher extends Start {

    private MainFrame mainFrame;


    //header
    private JLabel heure = new JLabel(getFormattedtime());
    private JLabel batterie = new JLabel("B");
    private JLabel wifi = new JLabel ("W");
    private JLabel reseau = new JLabel("R");

    //Panels

    /*
    private JPanel screen = new JPanel(new BorderLayout());
    private JPanel header = new JPanel(new GridLayout(1,2));
    private JPanel footer = new JPanel(new GridLayout(1,4,10,0));
    private JPanel main = new JPanel(new GridLayout(4,1));
    */

    //SousPanels
    private OwnPanel leftheader = new OwnPanel(new FlowLayout(FlowLayout.LEFT));
    private OwnPanel rightheader = new OwnPanel(new FlowLayout(FlowLayout.RIGHT));
    private OwnPanel controlButtons = new OwnPanel(new FlowLayout());

    //Buttons
    private OwnButton lock = new OwnButton(new ImageIcon("img/icons/unlock.png"),70,70);

    private OwnButton contact = new OwnButton(new ImageIcon("img/icons/contacts.png"),100,100);
    private OwnButton galerie = new OwnButton(new ImageIcon("img/icons/gallery.png"),100,100);



    public Launcher (MainFrame mainFrame) {

        this.mainFrame = mainFrame;

        //ajout de la barre supérieur
        heure.setForeground(Color.WHITE);
        batterie.setForeground(Color.WHITE);
        wifi.setForeground(Color.WHITE);
        reseau.setForeground(Color.WHITE);

        leftheader.add(heure);
        rightheader.add(batterie);
        rightheader.add(wifi);
        rightheader.add(reseau);

        getHeader().add(leftheader);
        getHeader().add(rightheader);

        lock.addActionListener(new Lock());
        controlButtons.add(lock);
        controlButtons.add(getOff());

        getMain().add(controlButtons);

        getButtonpanel().add(contact);
        getButtonpanel().add(galerie);

        contact.addActionListener(new ClicContactApp());
        galerie.addActionListener(new ClicGalleryApp());

       /*
        header.setOpaque(false);
        main.setOpaque(false);
        footer.setOpaque(false);
        leftheader.setOpaque(false);
        rightheader.setOpaque(false);
        */

    }


    public class ClicContactApp implements ActionListener {

         @Override
         public void actionPerformed(ActionEvent e) {
            mainFrame.getCardLayout().show(mainFrame.getContentPanel(),"app.ContactApp");
        }
    }

    public class ClicGalleryApp implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            mainFrame.getCardLayout().show(mainFrame.getContentPanel(),"app.GalleryApp");
        }

    }

    public class Lock implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            mainFrame.getCardLayout().show(mainFrame.getContentPanel(),"lock");
        }
    }



}
