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
}
