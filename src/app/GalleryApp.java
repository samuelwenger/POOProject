package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GalleryApp extends JPanel {

    private MainFrame mainFrame;

    // CardLayout pour l'application
    private CardLayout galleryCardLayout = new CardLayout();
    private JPanel galleryContentPanel = new JPanel(galleryCardLayout);

    // Elements de l'application Gallery
    private JPanel galleryStartPanel = new JPanel();
    private JLabel label = new JLabel("Bienvenue dans l'app Gallerie");
    private JButton quit = new JButton("Quitter");

    public GalleryApp(MainFrame mainFrame) {

        this.mainFrame = mainFrame;

        quit.addActionListener(new Back());

        // Ajouter les éléments au panel d'accueil
        galleryStartPanel.add(label);
        galleryStartPanel.add(quit);

        // Ajouter les divers panels au JPanel de l'application
        galleryContentPanel.add(galleryStartPanel,"Start");

        // Afficher le JPanel d'accueil
        galleryCardLayout.show(galleryContentPanel,"Start");

        // Ajouter le conteneur au JPanel
        add(galleryContentPanel);

    }

    public class Back implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            mainFrame.getCardLayout().show(mainFrame.getContentPanel(),"app.Launcher");
        }
    }

}
