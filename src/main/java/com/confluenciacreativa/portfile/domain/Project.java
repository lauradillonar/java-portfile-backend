package com.confluenciacreativa.portfile.domain;

public class Project {

    private Integer idProject;
    private Integer idPerson;
    private String title;
    private String fontawesome;
    private String letter;
    private String text;
    private String viewmore;
    private Person person;

    public Integer getIdProject() {
        return idProject;
    }

    public void setIdProject(Integer idProject) {
        this.idProject = idProject;
    }

    public Integer getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Integer idPerson) {
        this.idPerson = idPerson;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFontawesome() {
        return fontawesome;
    }

    public void setFontawesome(String fontawesome) {
        this.fontawesome = fontawesome;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getViewmore() {
        return viewmore;
    }

    public void setViewmore(String viewmore) {
        this.viewmore = viewmore;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}