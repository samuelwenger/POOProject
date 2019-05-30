package base;

import javax.swing.*;
import java.awt.*;


public class ContactGestion extends JPanel {

    //Création des divers labels
    private JLabel labelName = new JLabel("Nom");
    private JLabel labelFirstname = new JLabel("Prenom");
    private JLabel labelTel = new JLabel("Telephone");
    private JLabel labelMail= new JLabel("Mail");

    //Création des Textfield
    private JTextField fieldName = new JTextField();
    private JTextField fieldFirstname = new JTextField();
    private JTextField fieldTel = new JTextField();
    private JTextField fieldMail = new JTextField();

    //Création composant pour le imagepanel
    private ImageIcon contactImage = new ImageIcon("img/contact.png");
    private JButton imageButton = new JButton("salut");

    //Création comcposant pour titlepanel
    private JButton backButton = new JButton("Back:))");
    private JButton saveButton = new JButton("Save :)");
    private JLabel labelTitleApp = new JLabel("Salut");

    //Création panels
    private JPanel formPanel = new JPanel(new GridLayout(4,2,0,40));
    private JLayeredPane imageLayered = new JLayeredPane();
    private OwnPanel imagePanel = new OwnPanel(contactImage.getImage());
    private JPanel imageButtonPanel = new JPanel();
    private JPanel titlePanel = new JPanel(new BorderLayout());
    private JPanel contentPanel = new JPanel(new BorderLayout());

    public ContactGestion(){

        //Ajout des composants au paneltitle
        labelTitleApp.setHorizontalAlignment(JLabel.CENTER);

        titlePanel.add(backButton,BorderLayout.WEST);
        titlePanel.add(labelTitleApp, BorderLayout.CENTER);
        titlePanel.add(saveButton,BorderLayout.EAST);

        //Ajout du bouton au buttonpanel
        imageButtonPanel.add(imageButton);

            //ajout au Layered
        imageLayered.setSize(new Dimension(349,350));
        imageLayered.add(imagePanel,2);
        imageButtonPanel.setBounds(300,300,100,100);
        imageLayered.add(imageButtonPanel, 1);

        //ajouter composant formPanel

        formPanel.add(labelName);
        formPanel.add(fieldName);
        formPanel.add(labelFirstname);
        formPanel.add(fieldFirstname);
        formPanel.add(labelTel);
        formPanel.add(fieldTel);
        formPanel.add(labelMail);
        formPanel.add(fieldMail);

        contentPanel.setPreferredSize(new Dimension(400,700));
        contentPanel.add(titlePanel,BorderLayout.NORTH);
        contentPanel.add(imageLayered, BorderLayout.CENTER);
        contentPanel.add(formPanel, BorderLayout.SOUTH);

        add(contentPanel);

    }

}
