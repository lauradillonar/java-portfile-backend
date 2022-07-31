package com.confluenciacreativa.portfile.dto;

import javax.validation.constraints.NotBlank;

public class SkillDto {

    @NotBlank
    private Integer idPerson;
    @NotBlank
    private String item;
    @NotBlank
    private Integer progress;

    public SkillDto() {
    }

    public SkillDto(Integer idPerson, String item, Integer progress) {
        this.idPerson = idPerson;
        this.item = item;
        this.progress = progress;
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
