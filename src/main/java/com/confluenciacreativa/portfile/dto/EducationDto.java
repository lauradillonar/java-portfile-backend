package com.confluenciacreativa.portfile.dto;

import javax.validation.constraints.NotBlank;

public class EducationDto {

    @NotBlank
    private Integer idPerson;
    @NotBlank
    private String title;
    @NotBlank
    private String when;
    @NotBlank
    private String subtitle;

    private String text1;
    private String link;
    private String url;
    private String text2;
    private String viewmore;

    public EducationDto() {
    }

    public EducationDto(Integer idPerson,
                        String title,
                        String when,
                        String subtitle,
                        String text1,
                        String link,
                        String url,
                        String text2,
                        String viewmore) {
        this.idPerson = idPerson;
        this.title = title;
        this.when = when;
        this.subtitle = subtitle;
        this.text1 = text1;
        this.link = link;
        this.url = url;
        this.text2 = text2;
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

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public String getViewmore() {
        return viewmore;
    }

    public void setViewmore(String viewmore) {
        this.viewmore = viewmore;
    }
}
