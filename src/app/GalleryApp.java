package app;

import base.OwnPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
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
    private JLabel title = new JLabel("Galerie");
    private JButton add = new JButton("+");

    // Panel photos
    private JPanel panelPhoto = new JPanel(new BorderLayout());
    private GridBagConstraints gc = new GridBagConstraints();
    private JPanel galerie = new JPanel(new GridBagLayout());
    private JScrollPane galleriePane = new JScrollPane();


    public GalleryApp(MainFrame mainFrame) {

        deserializeObject();
        updateGalerie();

        this.mainFrame = mainFrame;

        back.addActionListener(new Back());
        add.addActionListener(new AddImage());

        title.setHorizontalAlignment(JLabel.CENTER);
        titlePanel.add(back,BorderLayout.WEST);
        titlePanel.add(title, BorderLayout.CENTER);
        titlePanel.add(add,BorderLayout.EAST);

        galleriePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panelPhoto.add(galleriePane, BorderLayout.CENTER);

        galleryStartPanel.setBackground(Color.BLUE);
        galleryStartPanel.setPreferredSize(new Dimension(400,700));
        galleryStartPanel.add(titlePanel, BorderLayout.NORTH);
        galleryStartPanel.add(panelPhoto, BorderLayout.CENTER);

        galleryContentPanel.add(galleryStartPanel, "Start");
        galleryCardLayout.show(galleryContentPanel,"Start");

        add(galleryContentPanel);



    }


    public void affichePhotos(){

        //galerie.setSize(new Dimension(380,100));

        JButton photo;
        ImageIcon image;
        String label;

        // GridBagLayout
        gc.gridwidth=1;
        gc.gridheight=1;
        gc.weightx = 1;
        gc.weighty = 0;
        gc.anchor = GridBagConstraints.NORTHWEST;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(5,5,5,5);

        // Calculs
        int nbbuttons = photos.size();
        int nblignes = nbbuttons/3;
        int cpt = 0;


        if (nbbuttons>=3){
            gc.fill = GridBagConstraints.HORIZONTAL;
        }
        else {
            gc.weightx = 0;
        }

        if (nbbuttons%3 != 0) {
            ++nblignes;
        }

        for(int i=0; i<nblignes; i++) {

            gc.gridy=i;

            if(i == nblignes-1){
               gc.weighty = 1;
            }

            for (int j = 0; j < 3 && nbbuttons>0; j++) {
                image = photos.get(cpt).getImage100100();
                photo = new JButton();
                photo.setIcon(image);
                photo.setPreferredSize(new Dimension(110, 100));
                photo.addActionListener(new ShowImage(photos.get(cpt)));

                gc.gridx = j;
                galerie.add(photo, gc);

                --nbbuttons;
                ++cpt;
            }

        }

    }

    public void serializeObject()
    {
        try
        {
            FileOutputStream fichier = new FileOutputStream("serials/photos.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fichier);
            oos.writeObject(photos);
            oos.flush();
            oos.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void deserializeObject() {

        try
        {
            FileInputStream fichier = new FileInputStream("serials/photos.ser");
            ObjectInputStream ois = new ObjectInputStream(fichier);
            photos = (ArrayList<Photo>) ois.readObject();
            ois.close();
        }
        catch (IOException e)
        {
            photos = new ArrayList<Photo>();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }


    public void updateGalerie(){

        panelPhoto.removeAll();
        panelPhoto.add(galleriePane, BorderLayout.CENTER);

        galerie.removeAll();
        galleriePane.setViewportView(galerie);

        affichePhotos();
    }


    public void addImage(String nom, File file){
        int id = getNextId();

        Photo photo = new Photo(id,nom,file);
        photos.add(photo);

        serializeObject();
        updateGalerie();
    }


    public int getNextId(){
        if(photos.size()==0){
            return 0;
        }
        else {
            return photos.size();
        }
    }


    public class ViewPhoto extends JPanel {
        private JPanel contentPanel = new JPanel(new BorderLayout());

        // Titre
        private JPanel up = new JPanel(new BorderLayout());
        private JButton back = new JButton("Back");


        // Changement d'image
        private JPanel buttons = new JPanel(new BorderLayout());
        private JButton next = new JButton(">");
        private JButton previous = new JButton("<");


        public ViewPhoto(Photo photo) {

            OwnPanel imagePanel = new OwnPanel(photo.getImage400().getImage());
            imagePanel.setPreferredSize(new Dimension(400,500));

            back.addActionListener(new BackToGallery());
            up.add(back,BorderLayout.WEST);

            next.addActionListener(new MoveToNext(photo));
            previous.addActionListener(new MoveToPrevious(photo));
            buttons.add(next,BorderLayout.EAST);
            buttons.add(previous,BorderLayout.WEST);

            contentPanel.add(up,BorderLayout.NORTH);
            contentPanel.add(imagePanel,BorderLayout.CENTER);
            contentPanel.add(buttons,BorderLayout.SOUTH);

            add(contentPanel);
        }
    }

    public class Back implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            mainFrame.getCardLayout().show(mainFrame.getContentPanel(),"app.Launcher");
        }
    }

    public class AddImage implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser choix = new JFileChooser();
            int retour = choix.showOpenDialog(panelPhoto);
            if (retour == JFileChooser.APPROVE_OPTION){
                String nom = choix.getSelectedFile().getName();
                File file = choix.getSelectedFile().getAbsoluteFile();
                addImage(nom, file);
            }
        }
    }

    public class ShowImage implements ActionListener {

        private Photo photo;
        private ViewPhoto viewPhoto;

        public ShowImage(Photo photo){
            this.photo = photo;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            viewPhoto = new ViewPhoto(photo);
            galleryContentPanel.add(viewPhoto,"View");
            galleryCardLayout.show(galleryContentPanel,"View");
        }
    }

    public class BackToGallery implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Test");
            galleryCardLayout.show(galleryContentPanel,"Start");
        }
    }

    public class MoveToNext implements ActionListener {

        private int actualpostion;
        private int newposition;
        private ViewPhoto viewPhoto;

        public MoveToNext(Photo photo){
            actualpostion= photos.indexOf(photo);
            newposition= actualpostion+1;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(newposition<photos.size()) {
                viewPhoto = new ViewPhoto(photos.get(newposition));

            }
            else{
                viewPhoto = new ViewPhoto(photos.get(0));
            }
            galleryContentPanel.add(viewPhoto, "Next");
            galleryCardLayout.show(galleryContentPanel, "Next");
        }
    }

    public class MoveToPrevious implements ActionListener {

        private int actualposition;
        private int newposition;
        private ViewPhoto viewPhoto;

        public MoveToPrevious(Photo photo){
            actualposition= photos.indexOf(photo);
            newposition=actualposition-1;
    }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(newposition>=0) {
                viewPhoto = new ViewPhoto(photos.get(newposition));

            }
            else{
                viewPhoto= new ViewPhoto(photos.get(photos.size()-1));
            }

            galleryContentPanel.add(viewPhoto, "Previous");
            galleryCardLayout.show(galleryContentPanel, "Previous");

        }
    }

}
