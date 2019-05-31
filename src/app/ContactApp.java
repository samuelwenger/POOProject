package app;

import base.ContactGestion;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ContactApp extends JPanel {

    private MainFrame mainFrame;

    private ArrayList<Contact> contacts = new ArrayList<Contact>();

    // CardLayout pour l'application
    private CardLayout contactCardLayout = new CardLayout();
    private JPanel contactContentPanel = new JPanel(contactCardLayout);

    // Panels de la page d'accueil de l'application
    private JPanel contactStartPanel = new JPanel(new BorderLayout());
    private JPanel titrePanel = new JPanel(new BorderLayout());
    private JPanel contactsPanel = new JPanel(new BorderLayout());

    // Elements de l'application Contact
    private JButton back = new JButton("Quitter");
    private JLabel titreApp = new JLabel("Contacts");
    private JButton addContact = new JButton("+");

    // Espace pour affichage des contacts
    private JPanel contactsList = new JPanel();
    private JScrollPane contactsScrollPane = new JScrollPane(contactsList);


    // Applications
    private AddContact addContactApp = new AddContact();


    public ContactApp(MainFrame mainFrame) {

        this.mainFrame = mainFrame;
        deserializeObject();

        back.addActionListener(new Back());
        addContact.addActionListener(new MoveToAddContact());

        // Ajouter les éléments au panel titre
        titreApp.setHorizontalAlignment(JLabel.CENTER);

        titrePanel.add(back, BorderLayout.WEST);
        titrePanel.add(titreApp, BorderLayout.CENTER);
        titrePanel.add(addContact, BorderLayout.EAST);


        // Ajouter les éléments au panel des contacts
        contactsPanel.setLayout(new BoxLayout(contactsPanel,BoxLayout.Y_AXIS));

        contactsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        contactsPanel.add(contactsScrollPane, BorderLayout.CENTER);

        JButton test = new JButton("Test");
        contactsList.add(test);

        afficheContacts();


        // Ajouter les éléments au panel d'accueil
        contactStartPanel.add(titrePanel, BorderLayout.NORTH);
        contactStartPanel.add(contactsPanel, BorderLayout.CENTER);


        // Ajouter les divers panels au JPanel de l'application
        contactContentPanel.add(contactStartPanel, "Start");
        contactContentPanel.add(addContactApp,"Add");

        // Afficher le JPanel d'accueil
        contactCardLayout.show(contactContentPanel,"Start");

        // Ajouter le conteneur au JPanel
        add(contactContentPanel);

    }


    public void afficheContacts() {
        ArrayList<Contact> contactsTries = (ArrayList<Contact>) contacts.clone();
   //     contactsTries = TriageContacts(contactsTries);

        JButton contact;

        for(int i=0; i<contactsTries.size();i++){
            contact = new JButton(contactsTries.get(i).getFirstname() + " " + contactsTries.get(i).getName());

            contactsList.add(contact);
          //  contact.addActionListener(new ContactClic(contactSorted.get(i)));
        }

        System.out.println("Coucou");


    }


    public ArrayList<Contact> TriageContacts (ArrayList<Contact> contacts) {
        Collections.sort(contacts, new Comparator<Contact>() {
            @Override
            public int compare(Contact o1, Contact o2) {
                return o1.getFirstname().toLowerCase().compareTo(o2.getFirstname().toLowerCase());
            }
        });
        return contacts;
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

    public void deserializeObject() {

        System.out.println("Je suis dans la méthode deserialize");
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
            addContactApp.clearFields();
            addContactApp.resetFieldsColor();
        }
    }

  //  public class

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

            addContactApp.resetFieldsColor();

            if (addContactApp.checkFields() == true) {
                addContactApp.saveNewContact();
                addContactApp.clearFields();
                addContactApp.resetFieldsColor();
                contactCardLayout.show(contactContentPanel, "Start");

            }
        }
    }


}
