package com.confluenciacreativa.portfile.repository;

import com.confluenciacreativa.portfile.entity.ProjectDB;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectCrudRepository extends CrudRepository<ProjectDB, Integer> {
    Boolean existsByTitleDB(String titleDB);
    Optional<ProjectDB> findByTitleDB(String titleDB);
    List<ProjectDB> findByIdPersonDB(Integer idPersonDB);

    Boolean existsByTitleDBAndIdPersonDB (String titleDB, Integer idPersonDB);
}
