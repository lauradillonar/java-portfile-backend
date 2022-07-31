package com.confluenciacreativa.portfile.repository;

import com.confluenciacreativa.portfile.domain.Skill;

import java.util.List;
import java.util.Optional;

public interface SkillRepository {

    List<Skill> getAll();
    Boolean exitsById(Integer idSkill);
    Boolean existsByItem(String item);
    Optional<Skill> getSkill(Integer idSkill);
    Optional<Skill> findByItem(String item);
    void save(Skill skill);
    void delete(Integer idSkill);
}
