package com.confluenciacreativa.portfile.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "contacts")
public class ContactDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contact_con")
    private Integer idContactDB;

    @NotNull
    @Column(name = "textName_con")
    private String textNameDB;

    @NotNull
    @Email
    @Column(name = "textEmail_con")
    private String textEmailDB;

    @NotNull
    @Column(name = "textMessage_con", columnDefinition = "VARCHAR(3000)")
    private String textMessageDB;

    public ContactDB() {
    }

    public ContactDB(String textNameDB,
                     String textEmailDB,
                     String textMessageDB) {
        this.textNameDB = textNameDB;
        this.textEmailDB = textEmailDB;
        this.textMessageDB = textMessageDB;
    }

    public Integer getIdContactDB() {
        return idContactDB;
    }

    public void setIdContactDB(Integer idContactDB) {
        this.idContactDB = idContactDB;
    }

    public String getTextNameDB() {
        return textNameDB;
    }

    public void setTextNameDB(String textNameDB) {
        this.textNameDB = textNameDB;
    }

    public String getTextEmailDB() {
        return textEmailDB;
    }

    public void setTextEmailDB(String textEmailDB) {
        this.textEmailDB = textEmailDB;
    }

    public String getTextMessageDB() {
        return textMessageDB;
    }

    public void setTextMessageDB(String textMessageDB) {
        this.textMessageDB = textMessageDB;
    }
}
