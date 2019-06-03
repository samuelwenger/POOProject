package app;

import base.ContactGestion;
import base.OwnPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

/**
 * Cette classe gère l'application Contact
 */

public class ContactApp extends JPanel {

    private MainFrame mainFrame;
    private ArrayList<Contact> contacts = new ArrayList<Contact>();

    // Applications
    private AddContact addContactApp = new AddContact();
    private ModifyContact modifyContact;
    private ViewContact viewContact;

    // CardLayout de l'app
    private CardLayout contactCardLayout = new CardLayout();
    private JPanel contactContentPanel = new JPanel(contactCardLayout);

    // Ecran d'accueil
    private JPanel contactStartPanel = new JPanel(new BorderLayout());

    // Titre
    private JPanel titrePanel = new JPanel(new BorderLayout());

    private JButton back = new JButton("Quitter");
    private JLabel titreApp = new JLabel("Contacts");
    private JButton addContact = new JButton("+");

    // Liste contacts
    private JPanel contactsPanel = new JPanel(new BorderLayout());

    private JPanel contactsList = new JPanel();
    private JScrollPane contactsScrollPane = new JScrollPane();



    public ContactApp(MainFrame mainFrame) {

        this.mainFrame = mainFrame;
        deserializeObject();
        updateContacts();

        // Ajout des ActionListener
        back.addActionListener(new Back());
        addContact.addActionListener(new MoveToAddContact());

        // Ajout des éléments aux panels
        // Panel titre
        titreApp.setHorizontalAlignment(JLabel.CENTER);
        titrePanel.add(back, BorderLayout.WEST);
        titrePanel.add(titreApp, BorderLayout.CENTER);
        titrePanel.add(addContact, BorderLayout.EAST);

        // Panel des contacts
        contactsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        contactsPanel.add(contactsScrollPane, BorderLayout.CENTER);

        // Panel de l'application
        contactStartPanel.add(titrePanel, BorderLayout.NORTH);
        contactStartPanel.add(contactsPanel, BorderLayout.CENTER);


        // Ajouter les divers panels au JPanel de l'application
        contactContentPanel.add(contactStartPanel, "Start");
        contactContentPanel.add(addContactApp,"Add");

        // Afficher le JPanel d'accueil
        contactCardLayout.show(contactContentPanel,"Start");

        // Ajouter le panel de l'application au JPanel principal
        add(contactContentPanel);

    }


    /**
     * Cette méthode permet l'affichage des contacts sur l'écran d'accueil de l'app
     * en créant un bouton pour chaque contact.
     */

    public void afficheContacts() {
        ArrayList<Contact> contactsTries = (ArrayList<Contact>) contacts.clone();
        contactsTries = TriageContacts(contactsTries);

        JButton contact;

        for(int i=0; i<contactsTries.size();i++){
            contact = new JButton(contactsTries.get(i).getFirstname() + " " + contactsTries.get(i).getName());
            contact.setPreferredSize(new Dimension(380,80));

            contactsList.add(contact);
            contact.addActionListener(new ShowContact(contactsTries.get(i)));
        }
    }


    /**
     * Cette méthode permet de trier l'ArrayList contenant les contacts pour retourner une ArrayList triée par ordre alphabétique des prénoms.
     *
     * @param contacts
     * @return
     */

    public ArrayList<Contact> TriageContacts (ArrayList<Contact> contacts) {
        Collections.sort(contacts, new Comparator<Contact>() {
            @Override
            public int compare(Contact o1, Contact o2) {
                return o1.getFirstname().toLowerCase().compareTo(o2.getFirstname().toLowerCase());
            }
        });
        return contacts;
    }


    /**
     * Cette méthode permet de mettre à jour l'affichage des contacts sur l'écran d'accueil de l'application
     */

    public void updateContacts(){
        contactsList.removeAll();

        int width = contacts.size()*90;
        contactStartPanel.setPreferredSize(new Dimension(400,700));
        contactsList.setPreferredSize(new Dimension(380,width));
        contactsScrollPane.setBounds(0,0,400,600);
        contactsScrollPane.setViewportView(contactsList);

        afficheContacts();
    }


    /**
     * Cette classe gère l'écran permettant la saisie d'un nouveau contact
     */
    public class AddContact extends ContactGestion {
        public AddContact(){
            super("Ajouter un contact");

            getBackButton().addActionListener(new BackToContact());
            getSaveButton().addActionListener(new SaveNewContact());
        }

        /**
         * Cette méthode permet la sauvegarde du contact ajouté
         * en enregistrant les données dans l'ArrayList
         */
        public void saveNewContact() {
            int id;

            if(contacts.size()==0)
                id=0;
            else
                id=contacts.get(contacts.size()-1).getId()+1;

            Contact newcontact = new Contact(id,getContactName(), getContactFirstname(), getContactTel(), getContactMail());
            contacts.add(newcontact);
            serializeObject();
        }

    }


    /**
     * Cette classe gère l'écran permettant la vue détaillée d'un contact
     */
    public class ViewContact extends JPanel {

        private JPanel contentPanel = new JPanel(new BorderLayout());

        // Infos contact
        private JLabel name = new JLabel();
        private JLabel tel = new JLabel();
        private JLabel mail = new JLabel();

        private JPanel infos = new JPanel(new GridLayout(3,1));

        // Titre + image
        private JButton back = new JButton("<");
        private JButton edit = new JButton("E");
        private JButton delete= new JButton("Delete");
        private OwnPanel buttonsright = new OwnPanel(new FlowLayout());
        private OwnPanel buttons = new OwnPanel(new BorderLayout());

        private ImageIcon image = new ImageIcon("img/Background.jpg");
        private OwnPanel panelImage = new OwnPanel(image.getImage());

        private JPanel up = new JPanel();


        public ViewContact(Contact contact) {

            // Affichage
            contentPanel.setPreferredSize(new Dimension(400,700));
            panelImage.setPreferredSize(new Dimension(400,300));

            // Panel haut de l'écran
            back.addActionListener(new BackToContact());
            edit.addActionListener(new EditContact(contact));
            delete.addActionListener(new DeleteContact(contact));


            buttonsright.add(edit);
            buttonsright.add(delete);
            buttons.add(back, BorderLayout.WEST);
            buttons.add(buttonsright, BorderLayout.EAST);

            up.setPreferredSize(new Dimension(400,350));

            panelImage.add(buttons, BorderLayout.NORTH);

            up.add(panelImage);



            // Panel des données du contact
            name.setText(contact.getFirstname() + " " + contact.getName());
            tel.setText(contact.getTel());
            mail.setText(contact.getMail());

            name.setFont(new Font("Bahnshrift",Font.BOLD,30));
            infos.add(name);
            infos.add(tel);
            infos.add(mail);


            // Ajout au panel général
            contentPanel.add(up,BorderLayout.NORTH);
            contentPanel.add(infos,BorderLayout.CENTER);

            add(contentPanel);

        }

        /**
         * Cette méthode permet la suppression d'un contact de l'ArrayList et appelle la méthode pour mettre à jour l'écran d'accueil de l'app
         * @param contact
         */
        public void deleteContact(Contact contact){
            contacts.remove(contact);
            updateContacts();
        }

    }

    /**
     * Cette classe gère l'écran permettant la modification d'un contact de l'ArrayList
     */
    public class ModifyContact extends ContactGestion{

        public ModifyContact(Contact contact){
            super("Editer un contact");

            getBackButton().addActionListener(new BackToViewContact());
            getSaveButton().addActionListener(new SaveModifiedContact(contact));

            getFieldName().setText(contact.getName());
            getFieldFirstname().setText(contact.getFirstname());
            getFieldTel().setText(contact.getTel());
            getFieldMail().setText(contact.getMail());
        }


        /**
         * Cette méthode met à jour l'objet Contact,
         * puis appelle les méthodes permettant la serialisation et la
         * mise à jour de l'écran d'accueil de l'application.
         **
         * @param contact
         */
        public void updateObjectContact(Contact contact){
            contact.setName(getFieldName().getText());
            contact.setFirstname(getFieldFirstname().getText());
            contact.setTel(getFieldTel().getText());
            contact.setMail(getFieldMail().getText());
            updateContacts();
            serializeObject();
        }

    }


    /**
     * Cette méthode permet la sérialisation de l'ArrayList contenant tous les contacts.
     */
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


    /**
     * Cette méthode permet la désérialisation des données afin de récupérer les données déjà enregistrées.
     * Les contacts déjà enregistrés sont récupérés dans une ArrayList
     */
    public void deserializeObject() {

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


    /**
     * Cette classe gère l'écouteur permettant le retour à l'écran d'accueil.
     */
    public class BackToContact implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            contactCardLayout.show(contactContentPanel,"Start");
            addContactApp.clearFields();
            addContactApp.resetFieldsColor();
        }
    }

    /**
     * Cette classe gère l'écouteur permettant le retour à l'écran d'affichage des détails d'un contact.
     */
    public class BackToViewContact implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            contactCardLayout.show(contactContentPanel,"View");
            addContactApp.clearFields();
            addContactApp.resetFieldsColor();
        }
    }


    /**
     * Cette classe gère l'écouteur permettant la sortie de l'application Contact et le retour au Launcher
     */
    public class Back implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainFrame.getCardLayout().show(mainFrame.getContentPanel(),"app.Launcher");
        }
    }


    /**
     * Cette classe gère l'écouteur permettant d'accéder à l'écran d'ajout d'un contact
     */
    public class MoveToAddContact implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            contactCardLayout.show(contactContentPanel,"Add");
        }
    }


    /**
     * Cette classe gère l'écouteur permettant d'accéder aux détails d'un contact
     */
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

    /**
     * Cette classe gère l'écouteur permettant d'accéder à l'écran d'édition d'un contact
     */
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

    /**
     * Cette classe gère l'écouteur permettant la suppression d'un contact
     * et le retour sur l'écran d'accueil de l'application.
     */
    public class DeleteContact implements ActionListener{

        private Contact contact;
        public DeleteContact(Contact contact){
            this.contact=contact;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            viewContact.deleteContact(contact);
            contactCardLayout.show(contactContentPanel,"Start");
        }
    }


    /**
     * Cette classe gère l'écouteur permettant la sauvegarde d'un nouveau contact
     * et le retour sur l'écran d'accueil de l'application
     */
    public class SaveNewContact implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            addContactApp.resetFieldsColor();

            if (addContactApp.checkFields() == true) {
                addContactApp.saveNewContact();
                addContactApp.clearFields();
                addContactApp.resetFieldsColor();
                updateContacts();
                contactCardLayout.show(contactContentPanel, "Start");

            }
        }
    }

    /**
     * Cette classe gère l'écouteur permettant l'enregistrement des modifications
     * et le retour sur la vue détaillée du contact.
     */
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
