package com.confluenciacreativa.portfile.repository;

import com.confluenciacreativa.portfile.entity.EducationDB;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EducationCrudRepository extends CrudRepository<EducationDB, Integer> {
    Boolean existsByTitleDB(String titleDB);
    Optional<EducationDB> findByTitleDB(String titleDB);
}
