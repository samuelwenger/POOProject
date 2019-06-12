package app;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 * Classe gérant la création des objets Photo
 */
public class Photo implements Serializable {

    private int id;
    private transient BufferedImage image;
    private String name;
    private String path;
    private double hauteur;
    private double largeur;

    private ImageIcon image100100;
    private ImageIcon image400300;
    private ImageIcon image400;
    private ImageIcon image8080;


    /**
     * Constructeur de l'objet
     *
     * @param id
     * @param name
     * @param file
     */
    public Photo(int id, String name, File file) {

        try {
            image = ImageIO.read(file);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        this.id = id;
        this.name = name;
        path = file.getPath();
        hauteur = image.getHeight();
        largeur = image.getWidth();

        image100100 = cropImage(110,110);
        image400300 = cropImage(400,300);
        image8080 = cropImage(80,80);
        image400 = resizeImage(400,600);
    }


    /**
     * Cette méthode retourne une nouvelle image resize à la taille souhaitée
     *
     * @param newLargeur
     * @param newHauteur
     * @return
     */
    public ImageIcon resizeImage(double newLargeur, double newHauteur) {

        Image resizedImage;
        double ratio = Math.min(newLargeur/largeur,newHauteur/hauteur);
        newLargeur = largeur * ratio;
        newHauteur = hauteur * ratio;
        resizedImage = image.getScaledInstance((int) newLargeur, (int) newHauteur, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);

    }

    /**
     * Cette màthode retourne une nouvelle image rognée à la taille souhaitée
     *
     * @param largeurDesiree
     * @param hauteurDesiree
     * @return
     */
    public ImageIcon cropImage (double largeurDesiree, double hauteurDesiree){

        BufferedImage scaledImage;

        Image cropedImage;
        double ratio = Math.min((2*largeurDesiree)/largeur,(2*hauteurDesiree)/hauteur);
        double scaledWidth = largeur*ratio;
        double scaledHeight = hauteur*ratio;

        scaledImage = new BufferedImage((int) scaledWidth,(int)scaledHeight, Image.SCALE_SMOOTH);
        Graphics2D g2d = scaledImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.drawImage(image,0,0,(int) scaledWidth, (int) scaledHeight, null);
        g2d.dispose();

        cropedImage = scaledImage.getSubimage((int) (scaledWidth-hauteurDesiree)/2,(int) (scaledHeight-hauteurDesiree)/2, (int) largeurDesiree, (int) hauteurDesiree);

        return new ImageIcon(cropedImage);


    }


    public ImageIcon getImage100100(){
        return image100100;
    }

    public ImageIcon getImage400300() {
        return image400300;
    }

    public ImageIcon getImage400(){
        return image400;
    }

    public ImageIcon getImage8080() {
        return image8080;
    }

    public int getId() {
        return id;
    }

}
