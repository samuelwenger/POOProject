package app;

import base.ContactGestion;
import base.OwnPanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.View;
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
    private JScrollPane contactsScrollPane = new JScrollPane();


    // Applications
    private AddContact addContactApp = new AddContact();
    private ModifyContact modifyContact;
    private ViewContact viewContact;


    public ContactApp(MainFrame mainFrame) {


        this.mainFrame = mainFrame;
        deserializeObject();

        updateContacts();
       // afficheContacts();


        back.addActionListener(new Back());
        addContact.addActionListener(new MoveToAddContact());

        // Ajouter les éléments au panel titre
        titreApp.setHorizontalAlignment(JLabel.CENTER);

        titrePanel.add(back, BorderLayout.WEST);
        titrePanel.add(titreApp, BorderLayout.CENTER);
        titrePanel.add(addContact, BorderLayout.EAST);


        // Ajouter les éléments au panel des contacts
       // contactsList.setLayout(new BoxLayout(contactsList,BoxLayout.Y_AXIS));

        contactsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        contactsPanel.add(contactsScrollPane, BorderLayout.CENTER);



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
        contactsTries = TriageContacts(contactsTries);

        JButton contact;

        for(int i=0; i<contactsTries.size();i++){
            contact = new JButton(contactsTries.get(i).getFirstname() + " " + contactsTries.get(i).getName());
            contact.setPreferredSize(new Dimension(380,80));

            contactsList.add(contact);
            contact.addActionListener(new ShowContact(contactsTries.get(i)));


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


    public void updateContacts(){
        contactsList.removeAll();

        int width = contacts.size()*90;
        contactStartPanel.setPreferredSize(new Dimension(400,700));
        contactsList.setPreferredSize(new Dimension(380,width));
        contactsScrollPane.setBounds(0,0,400,600);
        contactsScrollPane.setViewportView(contactsList);

        afficheContacts();

    }

    public class AddContact extends ContactGestion {
        public AddContact(){
            super("Ajouter un contact");

            getBackButton().addActionListener(new BackToContact());
            getSaveButton().addActionListener(new SaveNewContact());

        }

        public void saveNewContact() {
            int id;

            if(contacts.size()==0){
                id=0;
            }
            else
                id=contacts.get(contacts.size()-1).getId()+1;
            Contact newcontact = new Contact(id,getContactName(), getContactFirstname(), getContactTel(), getContactMail());
            contacts.add(newcontact);
            serializeObject();
        }

    }


    public class ViewContact extends JPanel {
        // Création des éléments
        private JButton back = new JButton("<");
        private JButton edit = new JButton("E");
        private JButton delete= new JButton("Delete");
        private JLabel name = new JLabel();
        private JLabel tel = new JLabel();
        private JLabel mail = new JLabel();
        private ImageIcon image = new ImageIcon("img/Background.jpg");

        // Création des pannels
        private JPanel contentPanel = new JPanel(new BorderLayout());

        private OwnPanel panelImage = new OwnPanel(image.getImage());
        private JPanel panelBack = new JPanel();
        private JPanel panelEdit = new JPanel();

        private JLayeredPane layeredPane = new JLayeredPane();

        private JPanel up = new JPanel();
        private JPanel infos = new JPanel(new GridLayout(3,1));


        public ViewContact(Contact contact) {

            contentPanel.setPreferredSize(new Dimension(400,700));


            back.addActionListener(new BackToContact());
            edit.addActionListener(new EditContact(contact));
            delete.addActionListener(new DeleteContact(contact));

            name.setText(contact.getFirstname() + " " + contact.getName());
            tel.setText(contact.getTel());
            mail.setText(contact.getMail());

            // Panneau du haut
/**
            panelImage.add(new JLabel("salut"));
            panelBack.add(back);
            panelEdit.add(edit);

            panelBack.setBounds(300,300,100,100);
            panelEdit.setBounds(380,10,100,100);

            layeredPane.setSize(new Dimension(400,350));
            layeredPane.add(panelImage,1);
            layeredPane.add(panelBack,2);

            up.add(layeredPane);
 layeredPane.add(panelEdit, 3);
 */

            panelImage.setPreferredSize(new Dimension(400,300));

            up.add(panelImage);
            up.add(back);
            up.add(edit);
            up.add(delete);

            up.setBackground(Color.RED);

            // Infos contact
            infos.add(name);
            infos.add(tel);
            infos.add(mail);

            // Ajout au panel général
            contentPanel.add(up,BorderLayout.NORTH);
            contentPanel.add(infos,BorderLayout.CENTER);

            add(contentPanel);

           // add(up);

            up.setPreferredSize(new Dimension(400,350));

        }

    }

    public class ModifyContact extends ContactGestion{

        public ModifyContact(Contact contact){
            super("Editcontact");

            getBackButton().addActionListener(new BackToViewContact());
            getSaveButton().addActionListener(new SaveModifiedContact(contact));

            super.getFieldName().setText(contact.getName());
            super.getFieldFirstname().setText(contact.getFirstname());
            super.getFieldTel().setText(contact.getTel());
            super.getFieldMail().setText(contact.getMail());
        }

        public void updateObjectContact(Contact contact){
            contact.setName(getFieldName().getText());
            contact.setFirstname(getFieldFirstname().getText());
            contact.setTel(getFieldTel().getText());
            contact.setMail(getFieldMail().getText());
            updateContacts();
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



    public class BackToContact implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            contactCardLayout.show(contactContentPanel,"Start");
            addContactApp.clearFields();
            addContactApp.resetFieldsColor();
        }
    }

    public class BackToViewContact implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            contactCardLayout.show(contactContentPanel,"View");
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



    public class ShowContact implements ActionListener {

        private Contact contact;

        public ShowContact(Contact contact){

            this.contact = contact;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            viewContact = new ViewContact(contact);

            contactContentPanel.add(viewContact,"View");

            contactCardLayout.show(contactContentPanel,"View");

        }

    }

    public class EditContact implements ActionListener{

        Contact contact;

        public EditContact(Contact contact){
            this.contact=contact;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            modifyContact= new ModifyContact(contact);
            contactContentPanel.add(modifyContact,"Edit");
            contactCardLayout.show(contactContentPanel,"Edit");
        }

    }

    public class DeleteContact implements ActionListener{

        private Contact contact;
        public DeleteContact(Contact contact){
            this.contact=contact;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            contacts.remove(contact);
            updateContacts();
            contactCardLayout.show(contactContentPanel,"Start");
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
                updateContacts();
            }
        }
    }

    public class SaveModifiedContact implements ActionListener {

        Contact contact;

        public SaveModifiedContact(Contact contact) {
            this.contact = contact;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            modifyContact.resetFieldsColor();


            if (modifyContact.checkFields() == true) {
                modifyContact.updateObjectContact(contact);
                modifyContact.clearFields();
                modifyContact.resetFieldsColor();

                viewContact = new ViewContact(contact);
                contactContentPanel.add(viewContact, "UpdatedView");
                contactCardLayout.show(contactContentPanel, "UpdatedView");
            }
        }
    }
}
