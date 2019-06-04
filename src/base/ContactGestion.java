package base;

import javax.swing.*;
import java.awt.*;


/**
 * Cette classe abstraite permet la configuration de l'écran utilisé dans les classes AddContact et ModifyContact
 */

public abstract class ContactGestion extends JPanel {

    Validation validation = new Validation();

    private JPanel contentPanel = new JPanel(new BorderLayout());

    private OwnPanel up = new OwnPanel(new BorderLayout());


    // Panel de titre
    private JPanel titlePanel = new JPanel(new BorderLayout());

    private JButton backButton = new JButton("< Back");
    private JButton saveButton = new JButton("Save");
    private JLabel labelTitleApp = new JLabel();


    // Panel pour l'image du contact
    private ImageIcon contactImage = new ImageIcon("img/Background.jpg");
    private JButton imageButton = new JButton("edit photo");

    private OwnPanel imagePanel = new OwnPanel(contactImage.getImage());
    private OwnPanel imageButtonPanel = new OwnPanel(new BorderLayout());


    // Panel pour le formulaire
    private JPanel formPanel = new JPanel(new GridLayout(4,2,0,40));

    private JTextField fieldName = new JTextField();
    private JTextField fieldFirstname = new JTextField();
    private JTextField fieldTel = new JTextField();
    private JTextField fieldMail = new JTextField();

    private JLabel labelName = new JLabel("Nom");
    private JLabel labelFirstname = new JLabel("Prenom");
    private JLabel labelTel = new JLabel("Telephone");
    private JLabel labelMail= new JLabel("Mail");



    public ContactGestion(String nomapplication){


        // Panel titre
        labelTitleApp.setText(nomapplication);
        labelTitleApp.setHorizontalAlignment(JLabel.CENTER);

        titlePanel.add(backButton,BorderLayout.WEST);
        titlePanel.add(labelTitleApp, BorderLayout.CENTER);
        titlePanel.add(saveButton,BorderLayout.EAST);


        // Panel image
        imageButtonPanel.add(imageButton, BorderLayout.EAST);

        imagePanel.setSize(400,200);
        imagePanel.add(imageButtonPanel, BorderLayout.SOUTH);

        // Panel haut
        up.setPreferredSize(new Dimension(400,325));
        up.add(titlePanel, BorderLayout.NORTH);
        up.add(imagePanel, BorderLayout.CENTER);


        // Panel du formulaire
        formPanel.add(labelName);
        formPanel.add(fieldName);
        formPanel.add(labelFirstname);
        formPanel.add(fieldFirstname);
        formPanel.add(labelTel);
        formPanel.add(fieldTel);
        formPanel.add(labelMail);
        formPanel.add(fieldMail);


        // Panel principal
        contentPanel.setPreferredSize(new Dimension(400,700));
        contentPanel.add(up,BorderLayout.NORTH);
        contentPanel.add(formPanel, BorderLayout.CENTER);

        add(contentPanel);

    }


    /**
     * Cette méthode permet de vider tous les champs du formulaire
     */
    public void clearFields() {
        fieldName.setText("");
        fieldFirstname.setText("");
        fieldTel.setText("");
        fieldMail.setText("");
    }

    /**
     * Cette méthode permet de remettre tous les champs du formulaire à leur couleur de base
     */
    public void resetFieldsColor() {
        validation.resetValidation(fieldName);
        validation.resetValidation(fieldFirstname);
        validation.resetValidation(fieldTel);
        validation.resetValidation(fieldMail);
    }

    /**
     * Cette méthode permet de vérifier les données saisies par l'utilisateur dans les champs nom, prénom et téléphone du formulaire
     *
     * @return
     */
    public boolean checkFields() {

        Boolean res = true;

        if(validation.isNotEmpty(fieldName)==false)
            res = false;
        if(validation.isNotEmpty(fieldFirstname)==false)
            res = false;
        if(validation.isNotEmpty(fieldTel)==false)
            res = false;

        return res;
    }

    /**
     * Ce getter permet de récupérer le bouton backButton (retour)
     * @return
     */
    public JButton getBackButton () {
        return backButton;
    }


    /**
     * Ce getter permet de récupérer le bouton saveButton (sauvegarde)
     * @return
     */
    public JButton getSaveButton(){
        return saveButton;
    }

    /**
     * Ce getter permet de récupérer le nom du contact saisi
     * @return
     */
    public String getContactName(){
        return fieldName.getText();
    }


    /**
     * Ce getter permet de récupérer le prénom du contact saisi
     * @return
     */
    public String getContactFirstname(){
        return fieldFirstname.getText();
    }

    /**
     * Ce getter permet de récupérer le téléphone du contact saisi
     * @return
     */
    public String getContactTel(){
        return fieldTel.getText();
    }

    /**
     * Ce getter permet de récupérer l'adresse mail du contact saisi
     * @return
     */
    public String getContactMail(){
        return fieldMail.getText();
    }


    /**
     * Ce getter retourne le JTextField pour le nom
     * @return
     */
    public JTextField getFieldName(){
        return fieldName;
    }

    /**
     * Ce getter retourne le JTextField pour le prénom
     * @return
     */
    public JTextField getFieldFirstname(){
        return fieldFirstname;
    }

    /**
     * Ce getter retourne le JTextField pour le téléphone
     * @return
     */
    public JTextField getFieldTel(){
        return fieldTel;
    }

    /**
     * Ce getter retourne le JTextField pour le mail
     * @return
     */
    public JTextField getFieldMail(){
        return fieldMail;
    }

}
