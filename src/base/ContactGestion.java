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
    private ImageIcon backImage = new ImageIcon("img/icons/back.png");
    private OwnButton backButton = new OwnButton(backImage,40,40);
    private ImageIcon saveImage = new ImageIcon("img/icons/save.png");
    private OwnButton saveButton = new OwnButton(saveImage, 40, 40);
    private JLabel labelTitleApp = new JLabel();


    // Panel pour l'image du contact
    private ImageIcon addImage = new ImageIcon("img/icons/addimage.png");
    private OwnButton imageButton = new OwnButton(addImage,40,40, "transparent");
    private ImageIcon imageContact;
    private OwnPanel imagePanel;
    private OwnPanel imageButtonPanel = new OwnPanel(new BorderLayout());


    // Panel pour le formulaire
    private JPanel formPanel = new JPanel(new GridLayout(4,2,0,40));

    private JTextField fieldName = new JTextField();
    private JTextField fieldFirstname = new JTextField();
    private JTextField fieldTel = new JTextField();
    private JTextField fieldMail = new JTextField();

    private JLabel labelName = new JLabel("Nom");
    private JLabel labelFirstname = new JLabel("Prénom");
    private JLabel labelTel = new JLabel("Téléphone");
    private JLabel labelMail= new JLabel("Mail");



    public ContactGestion(String nomapplication, ImageIcon imageContact){

        Color color = new Color(225,229,255);

        setBackground(color);
        formPanel.setBackground(color);
        titlePanel.setBackground(color);

        this.imageContact = new ImageIcon(imageContact.getImage());
        imagePanel = new OwnPanel(this.imageContact.getImage());

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
        labelName.setFont(new Font("Bahnshrift",Font.BOLD,20));
        labelFirstname.setFont(new Font("Bahnshrift",Font.BOLD,20));
        labelTel.setFont(new Font("Bahnshrift",Font.BOLD,20));
        labelMail.setFont(new Font("Bahnshrift",Font.BOLD,20));


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
     * Cette méthode met à jour le panel contenant l'image (temporaire) du contact
     *
     * @param imageContact
     */
    public void updateImagePanel(ImageIcon imageContact) {
        up.remove(imagePanel);
        imagePanel = new OwnPanel(imageContact.getImage());
        up.add(imagePanel, BorderLayout.CENTER);
        imagePanel.add(imageButtonPanel, BorderLayout.SOUTH);
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
        if(validation.phoneIsValid(fieldTel)==false)
            res = false;
        if(validation.emailIsValid(fieldMail)==false)
            res = false;

        return res;
    }


    public JButton getBackButton () {
        return backButton;
    }


    public JButton getSaveButton(){
        return saveButton;
    }


    public String getContactName(){
        return fieldName.getText();
    }


    public String getContactFirstname(){
        return fieldFirstname.getText();
    }


    public String getContactTel(){
        return fieldTel.getText();
    }


    public String getContactMail(){
        return fieldMail.getText();
    }


    public JTextField getFieldName(){
        return fieldName;
    }


    public JTextField getFieldFirstname(){
        return fieldFirstname;
    }


    public JTextField getFieldTel(){
        return fieldTel;
    }


    public JTextField getFieldMail(){
        return fieldMail;
    }


    public JButton getImageButton() {
        return imageButton;
    }


}
