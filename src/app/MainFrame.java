package app;


import javax.swing.*;
import java.awt.*;

/**
 * Cette classe gère la fenêtre utilisée pour toutes les applications du smartphone
 */
public class MainFrame extends JFrame {

    // CardLayout principal
    private CardLayout cardLayout = new CardLayout();
    private JPanel contentPanel = new JPanel(cardLayout);

    // Applications
    private Launcher launcher = new Launcher(this);
    private ContactApp contactApp = new ContactApp(this);
    private GalleryApp galleryApp = new GalleryApp(this);
    private LockPanel  panelverouille = new LockPanel(this);
    private HeureApp heureApp = new HeureApp(this);


    public MainFrame(){

        // Paramètres d'affichage de la fenêtre
        setSize(new Dimension(400,700));
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Ajouter les différents pannels au CardLayout
        contentPanel.add(launcher,"app.Launcher");
        contentPanel.add(contactApp, "app.ContactApp");
        contentPanel.add(galleryApp, "app.GalleryApp");
        contentPanel.add(heureApp,"app.HeureApp" );
        contentPanel.add(panelverouille,"lock");


        // Afficher le pannel app.Launcher
        cardLayout.show(contentPanel,"lock");

        // Ajouter le conteneur principal à la fenêtre
        add(contentPanel);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        setUndecorated(true);
        pack();

    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }



}
