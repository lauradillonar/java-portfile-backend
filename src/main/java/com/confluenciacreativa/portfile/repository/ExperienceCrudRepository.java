package com.confluenciacreativa.portfile.repository;

import com.confluenciacreativa.portfile.entity.ExperienceDB;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ExperienceCrudRepository extends CrudRepository<ExperienceDB, Integer> {
    Boolean existsByTitleDB(String titleDB);
    Optional<ExperienceDB>findByTitleDB(String titleDB);
    List<ExperienceDB> findByIdPersonDB (Integer idPersonDB);
}
