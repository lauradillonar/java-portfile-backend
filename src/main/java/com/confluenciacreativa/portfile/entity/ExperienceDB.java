package com.confluenciacreativa.portfile.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "experiences")
public class ExperienceDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_experience_exp")
    private Integer idExperienceDB;

    @NotNull
    @Column(name = "person_id")
    private Integer idPersonDB;

    @NotNull
    @Column(name = "title_exp")
    private String titleDB;

    @NotNull
    @Column(name = "subtitle_exp")
    private String subtitleDB;

    @NotNull
    @Column(name = "when_exp")
    private String whenDB;

    @NotNull
    @Column(name = "where_exp")
    private String whereDB;

    @Column(name = "text_exp", columnDefinition = "VARCHAR(1024)")
    private String textDB;

    @Column(name = "link1_exp")
    private String link1DB;

    @Column(name = "url1_exp")
    private String url1DB;

    @Column(name = "link2_exp")
    private String link2DB;

    @Column(name = "url2_exp")
    private String url2DB;

    @Column(name = "link3_exp")
    private String link3DB;

    @Column(name = "url3_exp")
    private String url3DB;

    @ManyToOne
    @JoinColumn(name = "person_id", insertable = false, updatable = false)
    private PersonDB personDB;

    public ExperienceDB() {
    }

    public ExperienceDB(String titleDB,
                        String subtitleDB,
                        String whenDB,
                        String whereDB) {
        this.titleDB = titleDB;
        this.subtitleDB = subtitleDB;
        this.whenDB = whenDB;
        this.whereDB = whereDB;
    }

    public ExperienceDB(String titleDB,
                        String subtitleDB,
                        String whenDB,
                        String whereDB,
                        String textDB,
                        String link1DB,
                        String url1DB,
                        String link2DB,
                        String url2DB,
                        String link3DB,
                        String url3DB) {
        this.titleDB = titleDB;
        this.subtitleDB = subtitleDB;
        this.whenDB = whenDB;
        this.whereDB = whereDB;
        this.textDB = textDB;
        this.link1DB = link1DB;
        this.url1DB = url1DB;
        this.link2DB = link2DB;
        this.url2DB = url2DB;
        this.link3DB = link3DB;
        this.url3DB = url3DB;
    }

    public Integer getIdExperienceDB() {
        return idExperienceDB;
    }

    public void setIdExperienceDB(Integer idExperienceDB) {
        this.idExperienceDB = idExperienceDB;
    }

    public String getTitleDB() {
        return titleDB;
    }

    public void setTitleDB(String titleDB) {
        this.titleDB = titleDB;
    }

    public String getSubtitleDB() {
        return subtitleDB;
    }

    public void setSubtitleDB(String subtitleDB) {
        this.subtitleDB = subtitleDB;
    }

    public String getWhenDB() {
        return whenDB;
    }

    public void setWhenDB(String whenDB) {
        this.whenDB = whenDB;
    }

    public String getWhereDB() {
        return whereDB;
    }

    public void setWhereDB(String whereDB) {
        this.whereDB = whereDB;
    }

    public String getTextDB() {
        return textDB;
    }

    public void setTextDB(String textDB) {
        this.textDB = textDB;
    }

    public String getLink1DB() {
        return link1DB;
    }

    public void setLink1DB(String link1DB) {
        this.link1DB = link1DB;
    }

    public String getUrl1DB() {
        return url1DB;
    }

    public void setUrl1DB(String url1DB) {
        this.url1DB = url1DB;
    }

    public String getLink2DB() {
        return link2DB;
    }

    public void setLink2DB(String link2DB) {
        this.link2DB = link2DB;
    }

    public String getUrl2DB() {
        return url2DB;
    }

    public void setUrl2DB(String url2DB) {
        this.url2DB = url2DB;
    }

    public String getLink3DB() {
        return link3DB;
    }

    public void setLink3DB(String link3DB) {
        this.link3DB = link3DB;
    }

    public String getUrl3DB() {
        return url3DB;
    }

    public void setUrl3DB(String url3DB) {
        this.url3DB = url3DB;
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
