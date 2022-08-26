package com.confluenciacreativa.portfile.entity;

import com.confluenciacreativa.portfile.security.entity.User;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

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
    @Column (name= "birth" ,columnDefinition = "DATETIME")
    private LocalDateTime birthdateDB;

    @NotNull
    @Column (name = "citizenship")
    private String nationalityDB;

    @NotNull
    @Column (name = "telephone")
    private String phoneDB;

    @NotNull
    @Column (name = "about_sub")
    private String aboutMeSubDB;

    @NotNull
    @Column (name = "about", columnDefinition = "VARCHAR(3000)")
    private String aboutMeDB;

    @NotNull
    @Column (name = "work")
    private String jobDB;

    @NotNull
    @Column (name = "from_location")
    private String locationDB;

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

    @Column (name = "facebook_link")
    private String facebookDB;

    @Column (name = "instagram_link")
    private String instagramDB;

    @Column (name = "twitter_link")
    private String twitterDB;

    @OneToMany(mappedBy = "personDB")
    private List<ExperienceDB>experiencesDB;

    @OneToMany(mappedBy = "personDB")
    private List<EducationDB>educationsDB;

    @OneToMany(mappedBy = "personDB")
    private List<SkillDB>skillsDB;

    @OneToMany(mappedBy = "personDB")
    private List<ProjectDB>projectsDB;

    @OneToMany(mappedBy = "personDB")
    private List<ContactDB>contactsDB;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_userDB")
    private User userDB;

    public PersonDB() {
    }

    public PersonDB(
            String nameDB,
            String lastnameDB,
            LocalDateTime birthdateDB,
            String nationalityDB,
            String phoneDB,
            String aboutMeSubDB,
            String aboutMeDB,
            String jobDB,
            String locationDB,
            User userDB) {
        this.nameDB = nameDB;
        this.lastnameDB = lastnameDB;
        this.birthdateDB = birthdateDB;
        this.nationalityDB = nationalityDB;
        this.phoneDB = phoneDB;
        this.aboutMeSubDB = aboutMeSubDB;
        this.aboutMeDB = aboutMeDB;
        this.jobDB = jobDB;
        this.locationDB = locationDB;
        this.userDB = userDB;
    }

    public PersonDB(
            String nameDB,
            String lastnameDB,
            LocalDateTime birthdateDB,
            String nationalityDB,
            String phoneDB,
            String aboutMeSubDB,
            String aboutMeDB,
            String jobDB,
            String locationDB,
            String imageHeaderDB,
            String imageDB,
            String logoSrcDB,
            String logoAltDB,
            String logoUrlDB,
            String facebookDB,
            String instagramDB,
            String twitterDB,
            User userDB) {
        this.nameDB = nameDB;
        this.lastnameDB = lastnameDB;
        this.birthdateDB = birthdateDB;
        this.nationalityDB = nationalityDB;
        this.phoneDB = phoneDB;
        this.aboutMeSubDB = aboutMeSubDB;
        this.aboutMeDB = aboutMeDB;
        this.jobDB = jobDB;
        this.locationDB = locationDB;
        this.imageHeaderDB = imageHeaderDB;
        this.imageDB = imageDB;
        this.logoSrcDB = logoSrcDB;
        this.logoAltDB = logoAltDB;
        this.logoUrlDB = logoUrlDB;
        this.facebookDB = facebookDB;
        this.instagramDB = instagramDB;
        this.twitterDB = twitterDB;
        this.userDB = userDB;
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


    public String getPhoneDB() {
        return phoneDB;
    }

    public void setPhoneDB(String phoneDB) {
        this.phoneDB = phoneDB;
    }

    public String getAboutMeSubDB() {
        return aboutMeSubDB;
    }

    public void setAboutMeSubDB(String aboutMeSubDB) {
        this.aboutMeSubDB = aboutMeSubDB;
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

    public String getLocationDB() {
        return locationDB;
    }

    public void setLocationDB(String locationDB) {
        this.locationDB = locationDB;
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

    public String getFacebookDB() {
        return facebookDB;
    }

    public void setFacebookDB(String facebookDB) {
        this.facebookDB = facebookDB;
    }

    public String getInstagramDB() {
        return instagramDB;
    }

    public void setInstagramDB(String instagramDB) {
        this.instagramDB = instagramDB;
    }

    public String getTwitterDB() {
        return twitterDB;
    }

    public void setTwitterDB(String twitterDB) {
        this.twitterDB = twitterDB;
    }

    public List<ExperienceDB> getExperiencesDB() {
        return experiencesDB;
    }

    public void setExperiencesDB(List<ExperienceDB> experiencesDB) {
        this.experiencesDB = experiencesDB;
    }

    public List<EducationDB> getEducationsDB() {
        return educationsDB;
    }

    public void setEducationsDB(List<EducationDB> educationsDB) {
        this.educationsDB = educationsDB;
    }

    public List<SkillDB> getSkillsDB() {
        return skillsDB;
    }

    public void setSkillsDB(List<SkillDB> skillsDB) {
        this.skillsDB = skillsDB;
    }

    public List<ProjectDB> getProjectsDB() {
        return projectsDB;
    }

    public void setProjectsDB(List<ProjectDB> projectsDB) {
        this.projectsDB = projectsDB;
    }

    public List<ContactDB> getContactsDB() {
        return contactsDB;
    }

    public void setContactsDB(List<ContactDB> contactsDB) {
        this.contactsDB = contactsDB;
    }

    public User getUserDB() {
        return userDB;
    }

    public void setUserDB(User userDB) {
        this.userDB = userDB;
    }
}
