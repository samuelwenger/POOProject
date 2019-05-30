package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactApp extends JPanel {

    private MainFrame mainFrame;

    // CardLayout pour l'application
    private CardLayout contactCardLayout = new CardLayout();
    private JPanel contactContentPanel = new JPanel(contactCardLayout);

    // Elements de l'application Contact
    private JPanel contactStartPanel = new JPanel();
    private JLabel label = new JLabel("Bienvenue dans l'application Contact");
    private JButton quit = new JButton("Quitter");


    public ContactApp(MainFrame mainFrame) {

        this.mainFrame = mainFrame;

        quit.addActionListener(new Back());

        // Ajouter les éléments au panel d'accueil
        contactStartPanel.add(label);
        contactStartPanel.add(quit);

        // Ajouter les divers panels au JPanel de l'application
        contactContentPanel.add(contactStartPanel, "Start");

        // Afficher le JPanel d'accueil
        contactCardLayout.show(contactContentPanel,"Start");

        // Ajouter le conteneur au JPanel
        add(contactContentPanel);

    }

    public class Back implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            mainFrame.getCardLayout().show(mainFrame.getContentPanel(),"app.Launcher");
        }
    }

}
