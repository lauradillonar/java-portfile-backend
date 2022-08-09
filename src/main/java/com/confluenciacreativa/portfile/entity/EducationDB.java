package com.confluenciacreativa.portfile.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "educations")
public class EducationDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_education_edu")
    private Integer idEducationDB;

    @NotNull
    @Column(name = "person_id")
    private Integer idPersonDB;

    @NotNull
    @Column(name = "title_edu")
    private String titleDB;

    @NotNull
    @Column(name = "when_edu")
    private String whenDB;

    @NotNull
    @Column(name = "subtitle_edu")
    private String subtitleDB;

    @Column(name = "text1_edu", columnDefinition = "VARCHAR(1024)")
    private String text1DB;

    @Column(name = "link_edu")
    private String linkDB;

    @Column(name = "url_edu")
    private String urlDB;

    @Column(name = "text2_edu", columnDefinition = "VARCHAR(1024)")
    private String text2DB;

    @Column(name = "viewmore_edu")
    private String viewmoreDB;

    @ManyToOne
    @JoinColumn(name = "person_id", insertable = false, updatable = false)
    private PersonDB personDB;

    public EducationDB() {
    }

    public EducationDB(String titleDB,
                       String whenDB,
                       String subtitleDB) {
        this.titleDB = titleDB;
        this.whenDB = whenDB;
        this.subtitleDB = subtitleDB;
    }

    public EducationDB(String titleDB,
                       String whenDB,
                       String subtitleDB,
                       String text1DB,
                       String linkDB,
                       String urlDB,
                       String text2DB,
                       String viewmoreDB) {
        this.titleDB = titleDB;
        this.whenDB = whenDB;
        this.subtitleDB = subtitleDB;
        this.text1DB = text1DB;
        this.linkDB = linkDB;
        this.urlDB = urlDB;
        this.text2DB = text2DB;
        this.viewmoreDB = viewmoreDB;
    }

    public Integer getIdEducationDB() {
        return idEducationDB;
    }

    public void setIdEducationDB(Integer idEducationDB) {
        this.idEducationDB = idEducationDB;
    }

    public String getTitleDB() {
        return titleDB;
    }

    public void setTitleDB(String titleDB) {
        this.titleDB = titleDB;
    }

    public String getWhenDB() {
        return whenDB;
    }

    public void setWhenDB(String whenDB) {
        this.whenDB = whenDB;
    }

    public String getSubtitleDB() {
        return subtitleDB;
    }

    public void setSubtitleDB(String subtitleDB) {
        this.subtitleDB = subtitleDB;
    }

    public String getText1DB() {
        return text1DB;
    }

    public void setText1DB(String text1DB) {
        this.text1DB = text1DB;
    }

    public String getLinkDB() {
        return linkDB;
    }

    public void setLinkDB(String linkDB) {
        this.linkDB = linkDB;
    }

    public String getUrlDB() {
        return urlDB;
    }

    public void setUrlDB(String urlDB) {
        this.urlDB = urlDB;
    }

    public String getText2DB() {
        return text2DB;
    }

    public void setText2DB(String text2DB) {
        this.text2DB = text2DB;
    }

    public String getViewmoreDB() {
        return viewmoreDB;
    }

    public void setViewmoreDB(String viewmoreDB) {
        this.viewmoreDB = viewmoreDB;
    }

    public Integer getIdPersonDB() {
        return idPersonDB;
    }

    public void setIdPersonDB(Integer idPersonDB) {
        this.idPersonDB = idPersonDB;
    }

    public PersonDB getPersonDB() {
        return personDB;
    }

    public void setPersonDB(PersonDB personDB) {
        this.personDB = personDB;
    }
}
