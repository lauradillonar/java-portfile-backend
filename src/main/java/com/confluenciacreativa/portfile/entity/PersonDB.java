package com.confluenciacreativa.portfile.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "persons")
public class PersonDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "person_id")
    private Integer idPersonDB;

    @NotNull
    @Column (name = "first_name")
    private String nameDB;

    @NotNull
    @Column (name = "surname")
    private String lastnameDB;

    @NotNull
    @Column (name = "user", unique = true)
    private String userNameDB;

    @NotNull
    @Column (name= "birth" ,columnDefinition = "DATETIME")
    private LocalDateTime birthdateDB;

    @NotNull
    @Column (name = "citizenship")
    private String nationalityDB;

    @NotNull
    @Email
    @Column (name = "mail", unique = true)
    private String emailDB;

    @NotNull
    @Column (name = "user_key")
    private String passwordDB;

    @NotNull
    @Column (name = "telephone")
    private String phoneDB;

    @NotNull
    @Column (name = "about")
    private String aboutMeDB;

    @NotNull
    @Column (name = "work")
    private String jobDB;

    @Column (name = "image_bg_header")
    private String imageHeaderDB;

    @Column (name = "image_profile")
    private String imageDB;

    @Column (name = "logo_src")
    private String logoSrcDB;

    @Column (name = "logo_alt")
    private String logoAltDB;

    @Column (name = "logo_url")
    private String logoUrlDB;

    public PersonDB() {
    }

    public PersonDB(
            String nameDB,
            String lastnameDB,
            String userNameDB,
            LocalDateTime birthdateDB,
            String nationalityDB,
            String emailDB,
            String passwordDB,
            String phoneDB,
            String aboutMeDB,
            String jobDB) {
        this.nameDB = nameDB;
        this.lastnameDB = lastnameDB;
        this.userNameDB = userNameDB;
        this.birthdateDB = birthdateDB;
        this.nationalityDB = nationalityDB;
        this.emailDB = emailDB;
        this.passwordDB = passwordDB;
        this.phoneDB = phoneDB;
        this.aboutMeDB = aboutMeDB;
        this.jobDB = jobDB;
    }

    public PersonDB(
            String nameDB,
            String lastnameDB,
            String userNameDB,
            LocalDateTime birthdateDB,
            String nationalityDB,
            String emailDB,
            String passwordDB,
            String phoneDB,
            String aboutMeDB,
            String jobDB,
            String imageHeaderDB,
            String imageDB,
            String logoSrcDB,
            String logoAltDB,
            String logoUrlDB) {
        this.nameDB = nameDB;
        this.lastnameDB = lastnameDB;
        this.userNameDB = userNameDB;
        this.birthdateDB = birthdateDB;
        this.nationalityDB = nationalityDB;
        this.emailDB = emailDB;
        this.passwordDB = passwordDB;
        this.phoneDB = phoneDB;
        this.aboutMeDB = aboutMeDB;
        this.jobDB = jobDB;
        this.imageHeaderDB = imageHeaderDB;
        this.imageDB = imageDB;
        this.logoSrcDB = logoSrcDB;
        this.logoAltDB = logoAltDB;
        this.logoUrlDB = logoUrlDB;
    }

    public Integer getIdPersonDB() {
        return idPersonDB;
    }

    public void setIdPersonDB(Integer idPersonDB) {
        this.idPersonDB = idPersonDB;
    }

    public String getNameDB() {
        return nameDB;
    }

    public void setNameDB(String nameDB) {
        this.nameDB = nameDB;
    }

    public String getLastnameDB() {
        return lastnameDB;
    }

    public void setLastnameDB(String lastnameDB) {
        this.lastnameDB = lastnameDB;
    }

    public String getUserNameDB() {
        return userNameDB;
    }

    public void setUserNameDB(String userNameDB) {
        this.userNameDB = userNameDB;
    }

    public LocalDateTime getBirthdateDB() {
        return birthdateDB;
    }

    public void setBirthdateDB(LocalDateTime birthdateDB) {
        this.birthdateDB = birthdateDB;
    }

    public String getNationalityDB() {
        return nationalityDB;
    }

    public void setNationalityDB(String nationalityDB) {
        this.nationalityDB = nationalityDB;
    }

    public String getEmailDB() {
        return emailDB;
    }

    public void setEmailDB(String emailDB) {
        this.emailDB = emailDB;
    }

    public String getPasswordDB() {
        return passwordDB;
    }

    public void setPasswordDB(String passwordDB) {
        this.passwordDB = passwordDB;
    }

    public String getPhoneDB() {
        return phoneDB;
    }

    public void setPhoneDB(String phoneDB) {
        this.phoneDB = phoneDB;
    }

    public String getAboutMeDB() {
        return aboutMeDB;
    }

    public void setAboutMeDB(String aboutMeDB) {
        this.aboutMeDB = aboutMeDB;
    }

    public String getJobDB() {
        return jobDB;
    }

    public void setJobDB(String jobDB) {
        this.jobDB = jobDB;
    }

    public String getImageHeaderDB() {
        return imageHeaderDB;
    }

    public void setImageHeaderDB(String imageHeaderDB) {
        this.imageHeaderDB = imageHeaderDB;
    }

    public String getImageDB() {
        return imageDB;
    }

    public void setImageDB(String imageDB) {
        this.imageDB = imageDB;
    }

    public String getLogoSrcDB() {
        return logoSrcDB;
    }

    public void setLogoSrcDB(String logoSrcDB) {
        this.logoSrcDB = logoSrcDB;
    }

    public String getLogoAltDB() {
        return logoAltDB;
    }

    public void setLogoAltDB(String logoAltDB) {
        this.logoAltDB = logoAltDB;
    }

    public String getLogoUrlDB() {
        return logoUrlDB;
    }

    public void setLogoUrlDB(String logoUrlDB) {
        this.logoUrlDB = logoUrlDB;
    }
}
