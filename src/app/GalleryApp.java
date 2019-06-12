package app;

import base.GalleryPanel;
import base.OwnButton;
import base.OwnPanel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

/**
 * Cette classe gère l'application Galerie du téléphone
 */
public class GalleryApp extends JPanel {

    private MainFrame mainFrame;
    private ViewPhoto viewPhoto;

    ArrayList<Photo> photos = new ArrayList<Photo>();

    private GalleryStart galleryStart;

    // CardLayout pour l'application
    private CardLayout galleryCardLayout = new CardLayout();
    private JPanel galleryContentPanel = new JPanel(galleryCardLayout);


    public GalleryApp(MainFrame mainFrame) {

        this.mainFrame = mainFrame;

        deserializeObject();
      //  updateGalerie();

        galleryStart = new GalleryStart(photos, "Galerie");

        galleryContentPanel.add(galleryStart, "Start");
        galleryCardLayout.show(galleryContentPanel, "Start");

        add(galleryContentPanel);
    }


    /**
     * Cette classe gère l'écran d'accueil de l'application permettant l'affichage de toutes les images ajoutées dans la galerie
     */
    public class GalleryStart extends GalleryPanel {

        private OwnButton add = new OwnButton(new ImageIcon("img/icons/addimage.png"),40,40);


        public GalleryStart(ArrayList<Photo> photos, String titreEcran) {
            super(photos, titreEcran);

            getBack().addActionListener(new Back());

            add.addActionListener(new AddImage());
            getUp().add(add,BorderLayout.EAST);

        }

        @Override
        public JButton createBoutonPhoto(int cpt) {
            JButton photo = super.createBoutonPhoto(cpt);
            photo.addActionListener(new ShowImage(photos.get(cpt)));

            return photo;
        }
    }

    /**
     * Cette classe gère l'écran permettant d'afficher chaque photo uniquement et de passer d'une photo à l'autre
     */
    public class ViewPhoto extends JPanel {
        private JPanel contentPanel = new JPanel(new BorderLayout());

        // Titre
        private JPanel up = new JPanel(new BorderLayout());
        private OwnButton back = new OwnButton(new ImageIcon("img/icons/back.png"),40,40);
        private OwnButton delete = new OwnButton(new ImageIcon("img/icons/delete.png"),40,40);

        //Image
        private OwnPanel main = new OwnPanel(new GridBagLayout());
        private OwnPanel imagePanel;

        // Changement d'image
        private OwnPanel buttons = new OwnPanel(new BorderLayout());
        private OwnButton next = new OwnButton(new ImageIcon("img/icons/right.png"),40,40);
        private OwnButton previous = new OwnButton(new ImageIcon("img/icons/left.png"),40,40);


        public ViewPhoto(Photo photo) {

            contentPanel.setPreferredSize(new Dimension(400,700));

            setBackground(Color.BLACK);
            contentPanel.setBackground(Color.BLACK);

            back.addActionListener(new BackToGallery());
            delete.addActionListener(new DeleteImage(photo));
            next.addActionListener(new MoveToNext(photo));
            previous.addActionListener(new MoveToPrevious(photo));

            up.add(back,BorderLayout.WEST);
            up.add(delete,BorderLayout.EAST);

            int width = photo.getImage400().getImage().getWidth(null);
            int height = photo.getImage400().getImage().getHeight(null);

            imagePanel = new OwnPanel(photo.getImage400().getImage());
            imagePanel.setPreferredSize(new Dimension(width, height));
            imagePanel.add(previous, BorderLayout.WEST);
            imagePanel.add(next, BorderLayout.EAST);

            main.add(imagePanel);

            contentPanel.add(up, BorderLayout.NORTH);
            contentPanel.add(main,BorderLayout.CENTER);

            add(contentPanel);
        }

        /**
         * Cette méthode permet de supprimer une image de la gallerie du téléphone et appelle la méthode de sérialisation
         *
         * @param photo
         */
        public void deleteImage(Photo photo){
            photos.remove(photo);

            serializeObject();
            galleryStart.updateGallery(photos);
        }
    }


    /**
     * Cette méthode permet de sérialiser les objets Photo
     */
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

    /**
     * Cette méthode récupère tous les objets Photo déjà séialisés et les retour dans l'ArrayList
     */
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


    /**
     * Cette méthode permet d'ajouter une image à la gallerie
     *
     * @param nom
     * @param file
     */
    public void addImage(String nom, File file){
        int id = getNextId();

        Photo photo = new Photo(id,nom,file);
        photos.add(photo);

        galleryStart.updateGallery(photos);
        serializeObject();

    }


    /**
     * Cette méthode retourne l'id de l'image suivante
     *
     * @return
     */
    public int getNextId(){
        if(photos.size()==0){
            return 0;
        }
        else {
            return photos.size();
        }
    }


    /**
     * Cette classe gère l'écouteur permettant de retourner au Launcher
     */
    public class Back implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            mainFrame.getCardLayout().show(mainFrame.getContentPanel(),"app.Launcher");
        }
    }

    /**
     * Cette classe gère l'écouteur qui ouvre la fenêtre permettant le choix d'une image sur l'ordinateur
     */
    public class AddImage implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser choix = new JFileChooser("img/photos");
            choix.addChoosableFileFilter(new FileNameExtensionFilter("Images Files","jpg","png"));
            choix.setAcceptAllFileFilterUsed(true);

            int retour = choix.showOpenDialog(galleryStart.getPanelPhoto());
            if (retour == JFileChooser.APPROVE_OPTION){
                String nom = choix.getSelectedFile().getName();
                File file = choix.getSelectedFile().getAbsoluteFile();
                addImage(nom, file);
            }
        }
    }

    /**
     * Cette classe gère l'écouteur permettant la suppression d'une image de la galerie
     */
    public class DeleteImage implements ActionListener{

        private Photo photo;

        public DeleteImage (Photo photo) {
               this.photo = photo;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            viewPhoto.deleteImage(photo);
            galleryCardLayout.show(galleryContentPanel,"Start");
        }
    }


    /**
     * Cette classe gère l'écouteur permettant d'accéder à l'écran affichant une seule photo
     */
    public class ShowImage implements ActionListener {

        private Photo photo;

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

    /**
     * Cette classe gère l'écouteur permettant le retour sur l'écran d'accueil de l'application (affichage de toutes les photos)
     */
    public class BackToGallery implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            galleryCardLayout.show(galleryContentPanel,"Start");
        }
    }

    /**
     * Cette classe gère l'écouteur permettant le passage à l'image suivante
     */
    private class MoveToNext implements ActionListener {

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

    /**
     * Cette classe gère l'écouteur permettant le passage à l'image précédente
     */
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


    public ArrayList<Photo> getPhotos () {
        return photos;
    }

}
