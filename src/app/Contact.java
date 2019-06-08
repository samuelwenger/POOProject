package app;

import javax.swing.*;
import java.io.Serializable;

public class Contact implements Serializable {

    private int id;
    private String name;
    private String firstname;
    private String tel;
    private String mail;
    private ImageIcon image;

    public Contact(int id, String name, String firstname, String tel, String mail){
        this.id=id;
        this.name=name;
        this.firstname=firstname;
        this.tel=tel;
        this.mail=mail;
    }

    public Contact(int id, String name, String firstname, String tel, String mail, ImageIcon image){
        this.id=id;
        this.name=name;
        this.firstname=firstname;
        this.tel=tel;
        this.mail=mail;
        this.image=image;
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
}
