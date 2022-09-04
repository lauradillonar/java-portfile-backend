package com.confluenciacreativa.portfile.domain;

public class Experience {

    private Integer idExperience;
    private Integer idPerson;
    private String title;
    private String subtitle;
    private String when;
    private String where;
    private String text;
    private String link1;
    private String url1;
    private String link2;
    private String url2;
    private String link3;
    private String url3;

    public Experience() {
    }

    public Experience(Integer idPerson,
                      String title,
                      String subtitle,
                      String when,
                      String where,
                      String text,
                      String link1,
                      String url1,
                      String link2,
                      String url2,
                      String link3,
                      String url3) {
        this.idPerson = idPerson;
        this.title = title;
        this.subtitle = subtitle;
        this.when = when;
        this.where = where;
        this.text = text;
        this.link1 = link1;
        this.url1 = url1;
        this.link2 = link2;
        this.url2 = url2;
        this.link3 = link3;
        this.url3 = url3;
    }

    public Integer getIdExperience() {
        return idExperience;
    }

    public void setIdExperience(Integer idExperience) {
        this.idExperience = idExperience;
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

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLink1() {
        return link1;
    }

    public void setLink1(String link1) {
        this.link1 = link1;
    }

    public String getUrl1() {
        return url1;
    }

    public void setUrl1(String url1) {
        this.url1 = url1;
    }

    public String getLink2() {
        return link2;
    }

    public void setLink2(String link2) {
        this.link2 = link2;
    }

    public String getUrl2() {
        return url2;
    }

    public void setUrl2(String url2) {
        this.url2 = url2;
    }

    public String getLink3() {
        return link3;
    }

    public void setLink3(String link3) {
        this.link3 = link3;
    }

    public String getUrl3() {
        return url3;
    }

    public void setUrl3(String url3) {
        this.url3 = url3;
    }
}
