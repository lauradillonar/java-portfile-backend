package com.confluenciacreativa.portfile.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "projects")
public class ProjectDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_project_pro")
    private Integer idProjectDB;

    @NotNull
    @Column(name = "person_id")
    private Integer idPersonDB;

    @NotNull
    @Column(name = "title_pro")
    private String titleDB;

    @Column(name = "fontawesome_pro")
    private String fontawesomeDB;

    @Column(name = "letter_pro")
    private String letterDB;

    @Column(name = "text_pro")
    private String textDB;

    @Column(name = "viewmore_pro")
    private String viewmoreDB;

    @ManyToOne
    @JoinColumn(name = "person_id", insertable = false, updatable = false)
    private PersonDB personDB;

    public ProjectDB() {
    }

    public ProjectDB(String titleDB) {
        this.titleDB = titleDB;
    }

    public ProjectDB(String titleDB,
                     String fontawesomeDB,
                     String letterDB,
                     String textDB,
                     String viewmoreDB) {
        this.titleDB = titleDB;
        this.fontawesomeDB = fontawesomeDB;
        this.letterDB = letterDB;
        this.textDB = textDB;
        this.viewmoreDB = viewmoreDB;
    }

    public Integer getIdProjectDB() {
        return idProjectDB;
    }

    public void setIdProjectDB(Integer idProjectDB) {
        this.idProjectDB = idProjectDB;
    }

    public String getTitleDB() {
        return titleDB;
    }

    public void setTitleDB(String titleDB) {
        this.titleDB = titleDB;
    }

    public String getFontawesomeDB() {
        return fontawesomeDB;
    }

    public void setFontawesomeDB(String fontawesomeDB) {
        this.fontawesomeDB = fontawesomeDB;
    }

    public String getLetterDB() {
        return letterDB;
    }

    public void setLetterDB(String letterDB) {
        this.letterDB = letterDB;
    }

    public String getTextDB() {
        return textDB;
    }

    public void setTextDB(String textDB) {
        this.textDB = textDB;
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
