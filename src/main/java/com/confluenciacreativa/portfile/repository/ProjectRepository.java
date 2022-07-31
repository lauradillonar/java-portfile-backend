package com.confluenciacreativa.portfile.repository;

import com.confluenciacreativa.portfile.domain.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository {

    List<Project> getAll();
    Optional<List<Project>> getByPerson(Integer idPerson);
    Boolean existsById(Integer idProject);
    Boolean existsByTitle(String title);
    Optional<Project> getProject(Integer idProject);
    Optional<Project> findByTitle(String title);
    void save(Project project);
    void delete(Integer idProject);
}
