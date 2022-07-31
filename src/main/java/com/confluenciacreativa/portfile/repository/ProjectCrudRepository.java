package com.confluenciacreativa.portfile.repository;

import com.confluenciacreativa.portfile.entity.ProjectDB;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProjectCrudRepository extends CrudRepository<ProjectDB, Integer> {
    Boolean existsByTitleDB(String titleDB);
    Optional<ProjectDB> findByTitleDB(String titleDB);
}
