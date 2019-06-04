package app;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.ByteOrder;
import java.util.ArrayList;

public class GalleryApp extends JPanel {

    private MainFrame mainFrame;

    ArrayList<Photo> photos = new ArrayList<Photo>();

    // CardLayout pour l'application
    private CardLayout galleryCardLayout = new CardLayout();
    private JPanel galleryContentPanel = new JPanel(galleryCardLayout);

    // Elements de l'application Gallery
    private JPanel galleryStartPanel = new JPanel(new BorderLayout());

    // Panel titre
    private JPanel titlePanel = new JPanel(new BorderLayout());

    private JButton back = new JButton("<");
    private JLabel title = new JLabel("Gallerie");


    // Panel photos
    private JPanel panelPhoto = new JPanel(new BorderLayout());
    private JPanel gallerie = new JPanel(new GridLayout(0,3));

    private JScrollPane galleriePane = new JScrollPane();



    public GalleryApp(MainFrame mainFrame) {

        this.mainFrame = mainFrame;

        affichePhotos();

        back.addActionListener(new Back());

        title.setHorizontalAlignment(JLabel.CENTER);
        titlePanel.add(back,BorderLayout.WEST);
        titlePanel.add(title, BorderLayout.CENTER);

        titlePanel.setBackground(Color.RED);

        galleriePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panelPhoto.add(galleriePane, BorderLayout.CENTER);

        galleryStartPanel.setBackground(Color.BLUE);
        galleryStartPanel.setPreferredSize(new Dimension(400,700));
        galleryStartPanel.add(titlePanel, BorderLayout.NORTH);
        galleryStartPanel.add(panelPhoto, BorderLayout.CENTER);

        galleryContentPanel.add(galleryStartPanel);

        add(galleryContentPanel);

    }


    public void affichePhotos(){

        gallerie.setSize(new Dimension(380,700));

        galleriePane.setViewportView(gallerie);

        JButton photo;


        for(int i=0; i<100; i++) {
            photo = new JButton("Bouton"+i);
            photo.setPreferredSize(new Dimension(30,70));

            gallerie.add(photo);
        }



    }


    public class Back implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            mainFrame.getCardLayout().show(mainFrame.getContentPanel(),"app.Launcher");
        }
    }

}
