import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    // CardLayout principal
    private CardLayout cardLayout = new CardLayout();
    private JPanel contentPanel = new JPanel(cardLayout);

    // Applications
    private Launcher launcher = new Launcher(this);
    private ContactApp contactApp = new ContactApp(this);
    private GalleryApp galleryApp = new GalleryApp(this);


    public MainFrame(){

        // Paramètres d'affichage de la fenêtre
        setPreferredSize(new Dimension(400,700));
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Ajouter les différents pannels au CardLayout
        contentPanel.add(launcher,"Launcher");
        contentPanel.add(contactApp, "ContactApp");
        contentPanel.add(galleryApp, "GalleryApp");

        // Afficher le pannel Launcher
        cardLayout.show(contentPanel,"Launcher");

        // Ajouter le conteneur principal à la fenêtre
        add(contentPanel);
        pack();

    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }



}
