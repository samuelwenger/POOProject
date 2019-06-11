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


    //SousPanels
    private OwnPanel leftheader = new OwnPanel(new FlowLayout(FlowLayout.LEFT));
    private OwnPanel rightheader = new OwnPanel(new FlowLayout(FlowLayout.RIGHT));
    private OwnPanel controlButtons = new OwnPanel(new FlowLayout());


    //Buttons
    private OwnButton lock = new OwnButton(new ImageIcon("img/icons/unlock.png"),70,70);

    private OwnButton contact = new OwnButton(new ImageIcon("img/icons/contacts.png"),100,100);
    private OwnButton galerie = new OwnButton(new ImageIcon("img/icons/gallery.png"),100,100);
    private OwnButton heureapp = new OwnButton(new ImageIcon("img/icons/hour.png"),100,100);



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
        getButtonpanel().add(heureapp);

        contact.addActionListener(new ClicContactApp());
        galerie.addActionListener(new ClicGalleryApp());
        heureapp.addActionListener(new ClicHeureApp());

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

    public class ClicHeureApp implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            mainFrame.getCardLayout().show(mainFrame.getContentPanel(),"app.HeureApp");
        }
    }

    public class Lock implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            mainFrame.getCardLayout().show(mainFrame.getContentPanel(),"lock");
        }
    }



}
