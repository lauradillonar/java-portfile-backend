package com.confluenciacreativa.portfile.dto;

import javax.validation.constraints.NotBlank;

public class ContactDto {

    @NotBlank
    private Integer idPerson;
    @NotBlank
    private String textName;
    @NotBlank
    private String textEmail;
    @NotBlank
    private String textMessage;

    public ContactDto() {
    }

    public ContactDto(Integer idPerson,
                      String textName,
                      String textEmail,
                      String textMessage) {
        this.idPerson = idPerson;
        this.textName = textName;
        this.textEmail = textEmail;
        this.textMessage = textMessage;
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
