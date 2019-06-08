package app;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class Photo implements Serializable {

    private int id;
    private transient BufferedImage image;
    private String name;
    private String path;
    private double hauteur;
    private double largeur;

    private ImageIcon image100100;
    private ImageIcon image400;


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

        image100100 = resizeImage(100,100);
        image400 = resizeImage(400,600);
    }


    public ImageIcon resizeImage(double newLargeur, double newHauteur) {

        Image resizedImage;
        double ratio = Math.min(newLargeur/largeur,newHauteur/hauteur);
        newLargeur = largeur * ratio;
        newHauteur = hauteur * ratio;
        resizedImage = image.getScaledInstance((int) newLargeur, (int) newHauteur, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);

    }


    public String getName() {
        return name;
    }

    public ImageIcon getImage100100(){
        return image100100;
    }

    public ImageIcon getImage400(){
        return image400;
    }

    public int getId() {
        return id;
    }

}
