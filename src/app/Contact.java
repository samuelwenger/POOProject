package app;

import javax.swing.*;
import java.io.Serializable;

/**
 * Classe gérant la création des objets Contact
 */
public class Contact implements Serializable {

    private int id;
    private String name;
    private String firstname;
    private String tel;
    private String mail;
    private ImageIcon image;
    private ImageIcon image8080;

    /**
     *
     * Constructeur de l'objet Contact sans image assignée
     *
     * @param id
     * @param name
     * @param firstname
     * @param tel
     * @param mail
     */
    public Contact(int id, String name, String firstname, String tel, String mail){
        this.id=id;
        this.name=name;
        this.firstname=firstname;
        this.tel=tel;
        this.mail=mail;
    }


    /**
     *
     * Constructeur de l'objet Contact avec image assignée
     *
     * @param id
     * @param name
     * @param firstname
     * @param tel
     * @param mail
     * @param image
     * @param image8080
     */
    public Contact(int id, String name, String firstname, String tel, String mail, ImageIcon image, ImageIcon image8080){
        this.id=id;
        this.name=name;
        this.firstname=firstname;
        this.tel=tel;
        this.mail=mail;
        this.image=image;
        this.image8080=image8080;
    }


    public int getId(){
        return id;
    }
    public String getName() {
        return name;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getTel() {
        return tel;
    }

    public String getMail() {
        return mail;
    }

    public ImageIcon getImage() {
        return image;
    }

    public ImageIcon getImage8080(){
        return image8080;
    }

    public void setName(String name){
        this.name=name;
    }
    public void setFirstname(String firstname){
        this.firstname=firstname;
    }
    public void setTel(String tel){
        this.tel=tel;
    }
    public void setMail(String mail){
        this.mail=mail;
    }
    public void setImage(ImageIcon image){
        this.image = image;
    }
    public void setImage8080(ImageIcon image8080) {
        this.image8080 = image8080;
    }
}
