package com.confluenciacreativa.portfile.dto;

import javax.validation.constraints.NotBlank;

public class ProjectDto {

    @NotBlank
    private Integer idPerson;
    @NotBlank
    private String title;

    private String fontawesome;
    private String letter;
    private String text;
    private String viewmore;

    public ProjectDto() {
    }

    public ProjectDto(Integer idPerson,
                      String title,
                      String fontawesome,
                      String letter,
                      String text,
                      String viewmore) {
        this.idPerson = idPerson;
        this.title = title;
        this.fontawesome = fontawesome;
        this.letter = letter;
        this.text = text;
        this.viewmore = viewmore;
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
}
