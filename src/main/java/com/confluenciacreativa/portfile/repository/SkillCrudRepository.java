package com.confluenciacreativa.portfile.repository;

import com.confluenciacreativa.portfile.entity.SkillDB;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SkillCrudRepository extends CrudRepository<SkillDB, Integer> {
    Boolean existsByItemDB(String itemDB);
    Optional<SkillDB> findByItemDB(String itemDB);
    List<SkillDB> findByIdPersonDB(Integer idPersonDB);
    Boolean existsByItemDBAndIdPersonDB(String titleDB, Integer idPersonDB);
}
