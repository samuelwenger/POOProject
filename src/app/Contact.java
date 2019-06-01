package app;

import java.io.Serializable;

public class Contact implements Serializable {

    private int id;
    private String name;
    private String firstname;
    private String tel;
    private String mail;

    public Contact(int id, String name, String firstname, String tel, String mail){
        this.id=id;
        this.name=name;
        this.firstname=firstname;
        this.tel=tel;
        this.mail=mail;
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
}
