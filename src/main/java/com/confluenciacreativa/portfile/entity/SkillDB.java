package com.confluenciacreativa.portfile.entity;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "skills")
public class SkillDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_skill_ski")
    private Integer idSkillDB;

    @NotNull
    @Column(name = "item_ski", unique = true)
    private String itemDB;

    @NotNull
    @Column(name = "progress_ski")
    private Integer progressDB;

    public SkillDB() {
    }

    public SkillDB(String itemDB, Integer progressDB) {
        this.itemDB = itemDB;
        this.progressDB = progressDB;
    }

    public Integer getIdSkillDB() {
        return idSkillDB;
    }

    public void setIdSkillDB(Integer idSkillDB) {
        this.idSkillDB = idSkillDB;
    }

    public String getItemDB() {
        return itemDB;
    }

    public void setItemDB(String itemDB) {
        this.itemDB = itemDB;
    }

    public Integer getProgressDB() {
        return progressDB;
    }

    public void setProgressDB(Integer progressDB) {
        this.progressDB = progressDB;
    }
}
