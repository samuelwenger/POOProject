package base;

import app.Contact;
import app.Photo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class GalleryPanel extends JPanel {

    private JPanel content = new JPanel(new BorderLayout());

    private ArrayList<Photo> photos;
    private Contact contact;

    private JPanel up = new JPanel(new BorderLayout());
    private JLabel title = new JLabel();
    private OwnButton back = new OwnButton(new ImageIcon("img/icons/back.png"),40,40);


    private JPanel panelPhoto = new JPanel(new BorderLayout());
    private JScrollPane galleryPane = new JScrollPane();
    private JPanel gallery = new JPanel(new GridBagLayout());
    private GridBagConstraints gc = new GridBagConstraints();

    public GalleryPanel(ArrayList<Photo> photos, String titreEcran) {
        this.photos = photos;
        title.setText(titreEcran);

        init();
    }

    public GalleryPanel(ArrayList<Photo> photos, String titreEcran, Contact contact){
        this.photos = photos;
        this.contact = contact;
        title.setText(titreEcran);

        init();
    }

    public void init() {

        Color color = new Color(255,239, 210);

        setBackground(color);
        up.setBackground(color);
        panelPhoto.setBackground(color);
        gallery.setBackground(color);

        content.setPreferredSize(new Dimension(400,700));

        title.setHorizontalAlignment(JLabel.CENTER);
        up.add(back, BorderLayout.WEST);
        up.add(title, BorderLayout.CENTER);

        galleryPane.setViewportView(gallery);
        panelPhoto.add(galleryPane);

        content.add(up, BorderLayout.NORTH);
        content.add(panelPhoto, BorderLayout.CENTER);

        add(content);

        affichePhotos();
    }


    public void affichePhotos() {

        JButton photo;
        ImageIcon image;

        // GridBagLayout
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.weightx = 1;
        gc.weighty = 0;
        gc.anchor = GridBagConstraints.NORTHWEST;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(5, 5, 5, 5);

        // Calculs
        int nbbuttons = photos.size();
        int nblignes = nbbuttons / 3;
        int cpt = 0;

        //  Alignement moins de 3 boutons
        if (nbbuttons >= 3) {
            gc.fill = GridBagConstraints.HORIZONTAL;
        } else {
            gc.weightx = 0;
        }

        if (nbbuttons % 3 != 0) {
            ++nblignes;
        }

        //Ajout des boutons
        for (int i = 0; i < nblignes; i++) {

            gc.gridy = i;

            if (i == nblignes - 1) {
                gc.weighty = 1;
            }

            for (int j = 0; j < 3 && nbbuttons > 0; j++) {

                //Création du bouton
                photo = createBoutonPhoto(cpt);

                gc.gridx = j;
                gallery.add(photo, gc);

                --nbbuttons;
                ++cpt;
            }
        }
    }


    public JButton createBoutonPhoto(int cpt) {

        OwnButton photo = new OwnButton(photos.get(cpt).getImage100100(),80,80);
        photo.setPreferredSize(new Dimension(110, 110));

        return photo;

    }

    public void updateGallery(ArrayList<Photo> photos){
        this.photos = photos;

        panelPhoto.removeAll();
        panelPhoto.add(galleryPane, BorderLayout.CENTER);

        gallery.removeAll();
        galleryPane.setViewportView(gallery);

        affichePhotos();
    }

    public JButton getBack() {
        return back;
    }

    public Contact getContact() {
        return contact;
    }

    public ArrayList<Photo> getPhotos() {
        return photos;
    }

    public JPanel getContent(){
        return content;
    }

    public JPanel getPanelPhoto() {
        return panelPhoto;
    }

    public JPanel getGallery () {
        return gallery;
    }

    public JPanel getUp () {
        return up;
    }

}

