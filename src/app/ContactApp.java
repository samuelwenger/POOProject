package app;

import base.ContactGestion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class ContactApp extends JPanel {

    private MainFrame mainFrame;

    private ArrayList<Contact> contacts = new ArrayList<Contact>();

    // CardLayout pour l'application
    private CardLayout contactCardLayout = new CardLayout();
    private JPanel contactContentPanel = new JPanel(contactCardLayout);

    // Elements de l'application Contact
    private JPanel contactStartPanel = new JPanel();
    private JLabel label = new JLabel("Bienvenue dans l'application Contact");
    private JButton quit = new JButton("Quitter");
    private JButton addContact = new JButton("+");

    private AddContact addContactApp = new AddContact();


    public ContactApp(MainFrame mainFrame) {

        this.mainFrame = mainFrame;

        deSerializeObject();

        quit.addActionListener(new Back());
        addContact.addActionListener(new MoveToAddContact());

        // Ajouter les éléments au panel d'accueil
        contactStartPanel.add(label);
        contactStartPanel.add(quit);
        contactStartPanel.add(addContact);

        // Ajouter les divers panels au JPanel de l'application
        contactContentPanel.add(contactStartPanel, "Start");
        contactContentPanel.add(addContactApp,"Add");

        // Afficher le JPanel d'accueil
        contactCardLayout.show(contactContentPanel,"Start");

        // Ajouter le conteneur au JPanel
        add(contactContentPanel);

    }

    public class AddContact extends ContactGestion {
        public AddContact(){
            super("Ajouter un contact");
            contactCardLayout.show(contactContentPanel,"Start");

            getBackButton().addActionListener(new BackToContactFromAdd());
            getSaveButton().addActionListener(new SaveNewContact());

        }

        public void saveNewContact() {
            int id= contacts.size()+1;
            Contact newcontact = new Contact(id,getContactName(), getContactFirstname(), getContactTel(), getContactMail());
            contacts.add(newcontact);
            serializeObject();
        }

    }

    public void serializeObject()
    {
        try
        {
            FileOutputStream fichier = new FileOutputStream("serials/contactslist.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fichier);
            oos.writeObject(contacts);
            oos.flush();
            oos.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void deSerializeObject()
    {
        try
        {
            FileInputStream fichier = new FileInputStream("serials/contactslist.ser");
            ObjectInputStream ois = new ObjectInputStream(fichier);
            contacts = (ArrayList<Contact>) ois.readObject();
            ois.close();
        }
        catch (IOException e)
        {
            contacts = new ArrayList<Contact>();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();

        }
    }



    public class BackToContactFromAdd implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            contactCardLayout.show(contactContentPanel,"Start");
        }
    }

    public class Back implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainFrame.getCardLayout().show(mainFrame.getContentPanel(),"app.Launcher");
        }
    }

    public class MoveToAddContact implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            contactCardLayout.show(contactContentPanel,"Add");
        }
    }

    public class SaveNewContact implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            addContactApp.saveNewContact();
        }
    }


}
