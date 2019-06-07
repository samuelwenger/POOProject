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
    private GridBagConstraints gc = new GridBagConstraints();
    private JPanel gallerie = new JPanel(new GridBagLayout());

    private JScrollPane galleriePane = new JScrollPane();



    public GalleryApp(MainFrame mainFrame) {

        this.mainFrame = mainFrame;

        affichePhotos();

        back.addActionListener(new Back());

        title.setHorizontalAlignment(JLabel.CENTER);
        titlePanel.add(back,BorderLayout.WEST);
        titlePanel.add(title, BorderLayout.CENTER);

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

        gallerie.setSize(new Dimension(380,100));

        galleriePane.setViewportView(gallerie);

        JButton photo;

        gc.gridwidth=1;
        gc.gridheight=1;

        gc.anchor = GridBagConstraints.NORTH;
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 0;
        gc.fill = GridBagConstraints.HORIZONTAL;

        gc.insets = new Insets(5,5,5,5);

        int nbbuttons = 11;
        int nblignes = nbbuttons/3;

        if (nbbuttons%3 != 0) {
            ++nblignes;
        }

        for(int i=0; i<nblignes;i++) {

            gc.gridy=i;

            if(i == nblignes-1){
               gc.weighty = 1;
            }

            for (int j = 0; j < 3 && nbbuttons>0; j++) {
                photo = new JButton();
                photo.setPreferredSize(new Dimension(0, 100));

                gc.gridx = j;
                gallerie.add(photo, gc);

                System.out.println(j + " " + i + "    " + gc.weighty);

                --nbbuttons;

            }

        }

    }


    public class Back implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            mainFrame.getCardLayout().show(mainFrame.getContentPanel(),"app.Launcher");
        }
    }

}
