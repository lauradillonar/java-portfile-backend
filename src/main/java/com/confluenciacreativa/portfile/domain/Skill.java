package com.confluenciacreativa.portfile.domain;

public class Skill {

    private Integer idSkill;
    private Integer idPerson;
    private String item;
    private Integer progress;

    public Skill() {
    }

    public Skill(Integer idPerson,
                 String item,
                 Integer progress) {
        this.idPerson = idPerson;
        this.item = item;
        this.progress = progress;
    }

    public Integer getIdSkill() {
        return idSkill;
    }

    public void setIdSkill(Integer idSkill) {
        this.idSkill = idSkill;
    }

    public Integer getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Integer idPerson) {
        this.idPerson = idPerson;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

}
