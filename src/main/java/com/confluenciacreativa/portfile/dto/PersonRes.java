package com.confluenciacreativa.portfile.dto;

import jdk.vm.ci.meta.Local;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class PersonRes {

    @NotBlank
    private Integer idPerson;

    @NotBlank
    private String name;

    @NotBlank
    private String lastname;

    @NotBlank
    private LocalDateTime birthdate;

    @NotBlank
    private String nationality;

    @NotBlank
    private String phone;

    @NotBlank
    private String aboutMeSub;

    @NotBlank
    private String aboutMe;

    @NotBlank
    private String job;

    @NotBlank
    private String location;

    @NotBlank
    private Integer idUser;

    private String imageHeader;
    private String image;
    private String logoSrc;
    private String logoAlt;
    private String logoUrl;

    private String facebook;
    private String instagram;
    private String twitter;

    public PersonRes() {
    }

    public PersonRes(Integer idPerson,
                     String name,
                     String lastname,
                     LocalDateTime birthdate,
                     String nationality,
                     String phone,
                     String aboutMeSub,
                     String aboutMe,
                     String job,
                     String location,
                     Integer idUser,
                     String imageHeader,
                     String image,
                     String logoSrc,
                     String logoAlt,
                     String logoUrl,
                     String facebook,
                     String instagram,
                     String twitter) {
        this.idPerson = idPerson;
        this.name = name;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.nationality = nationality;
        this.phone = phone;
        this.aboutMeSub = aboutMeSub;
        this.aboutMe = aboutMe;
        this.job = job;
        this.location = location;
        this.idUser = idUser;
        this.imageHeader = imageHeader;
        this.image = image;
        this.logoSrc = logoSrc;
        this.logoAlt = logoAlt;
        this.logoUrl = logoUrl;
        this.facebook = facebook;
        this.instagram = instagram;
        this.twitter = twitter;
    }

    public Integer getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Integer idPerson) {
        this.idPerson = idPerson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDateTime getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDateTime birthdate) {
        this.birthdate = birthdate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAboutMeSub() {
        return aboutMeSub;
    }

    public void setAboutMeSub(String aboutMeSub) {
        this.aboutMeSub = aboutMeSub;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getImageHeader() {
        return imageHeader;
    }

    public void setImageHeader(String imageHeader) {
        this.imageHeader = imageHeader;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLogoSrc() {
        return logoSrc;
    }

    public void setLogoSrc(String logoSrc) {
        this.logoSrc = logoSrc;
    }

    public String getLogoAlt() {
        return logoAlt;
    }

    public void setLogoAlt(String logoAlt) {
        this.logoAlt = logoAlt;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }
}
