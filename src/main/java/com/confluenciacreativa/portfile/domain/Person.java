package com.confluenciacreativa.portfile.domain;

import java.time.LocalDateTime;

public class Person {

    private Integer idPerson;
    private String name;
    private String lastname;
    private String userName;
    private LocalDateTime birthdate;
    private String nationality;
    private String email;
    private String password;
    private String phone;
    private String aboutMeSub;
    private String aboutMe;
    private String job;
    private String location;
    private String imageHeader;
    private String image;
    private String logoSrc;
    private String logoAlt;
    private String logoUrl;

    private String facebook;

    private String instagram;

    private String twitter;

    public Person() {
    }

    public Person(
            String name,
            String lastname,
            String userName,
            LocalDateTime birthdate,
            String nationality,
            String email,
            String password,
            String phone,
            String aboutMeSub,
            String aboutMe,
            String job,
            String location,
            String imageHeader,
            String image,
            String logoSrc,
            String logoAlt,
            String logoUrl,
            String facebook,
            String instagram,
            String twitter) {
        this.name = name;
        this.lastname = lastname;
        this.userName = userName;
        this.birthdate = birthdate;
        this.nationality = nationality;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.aboutMeSub = aboutMeSub;
        this.aboutMe = aboutMe;
        this.job = job;
        this.location = location;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAboutMeSub() { return  aboutMeSub;}

    public void setAboutMeSub(String aboutMeSub) { this.aboutMeSub = aboutMeSub; }

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
