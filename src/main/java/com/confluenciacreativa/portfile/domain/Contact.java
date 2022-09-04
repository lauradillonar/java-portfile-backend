package com.confluenciacreativa.portfile.domain;

public class Contact {

    private Integer idContact;
    private Integer idPerson;
    private String textName;
    private String textEmail;
    private String textMessage;

    public Contact() {
    }

    public Contact(Integer idPerson,
                   String textName,
                   String textEmail,
                   String textMessage) {
        this.idPerson = idPerson;
        this.textName = textName;
        this.textEmail = textEmail;
        this.textMessage = textMessage;
    }

    public Integer getIdContact() {
        return idContact;
    }

    public void setIdContact(Integer idContact) {
        this.idContact = idContact;
    }

    public Integer getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Integer idPerson) {
        this.idPerson = idPerson;
    }

    public String getTextName() {
        return textName;
    }

    public void setTextName(String textName) {
        this.textName = textName;
    }

    public String getTextEmail() {
        return textEmail;
    }

    public void setTextEmail(String textEmail) {
        this.textEmail = textEmail;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }
}
