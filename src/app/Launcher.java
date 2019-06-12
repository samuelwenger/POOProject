package app;

import base.OwnButton;
import base.OwnPanel;
import base.Start;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Cette classe gère l'écran de démarrage du smartphone
 */
public class Launcher extends Start {

    private MainFrame mainFrame;


    //header
    private JLabel operator = new JLabel("WENGAL");


    //SousPanels
    private OwnPanel leftheader = new OwnPanel(new FlowLayout(FlowLayout.LEFT));
    private OwnPanel rightheader = new OwnPanel(new FlowLayout(FlowLayout.RIGHT));
    private OwnPanel controlButtons = new OwnPanel(new FlowLayout());


    //Buttons
    private OwnButton lock = new OwnButton(new ImageIcon(this.getClass().getResource("/icons/unlock.png")),70,70);

    private OwnButton contact = new OwnButton(new ImageIcon(this.getClass().getResource("/icons/contacts.png")),100,100);
    private OwnButton galerie = new OwnButton(new ImageIcon(this.getClass().getResource("/icons/gallery.png")),100,100);
    private OwnButton heureapp = new OwnButton(new ImageIcon(this.getClass().getResource("/icons/hour.png")),100,100);



    public Launcher (MainFrame mainFrame) {

        this.mainFrame = mainFrame;

        //ajout de la barre supérieur
        getHeureSmall().setForeground(Color.WHITE);
        operator.setForeground(Color.WHITE);

        leftheader.add(getHeureSmall());
        rightheader.add(operator);

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


    }


    /**
     * Cet écouteur permet l'ouverture de l'application ContactApp
     */
    public class ClicContactApp implements ActionListener {

         @Override
         public void actionPerformed(ActionEvent e) {
            mainFrame.getCardLayout().show(mainFrame.getContentPanel(),"app.ContactApp");
        }
    }

    /**
     * Cet écouteur permet l'ouverture de l'application GalleryApp
     */
    public class ClicGalleryApp implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            mainFrame.getCardLayout().show(mainFrame.getContentPanel(),"app.GalleryApp");
        }

    }

    /**
     * Cet écouteur permet l'ouverture de l'application HeureApp
     */
    public class ClicHeureApp implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            mainFrame.getCardLayout().show(mainFrame.getContentPanel(),"app.HeureApp");
        }
    }

    /**
     * Cet écouteur permet le vérouillage du smartphone
     */
    public class Lock implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            mainFrame.getCardLayout().show(mainFrame.getContentPanel(),"lock");
        }
    }


}
