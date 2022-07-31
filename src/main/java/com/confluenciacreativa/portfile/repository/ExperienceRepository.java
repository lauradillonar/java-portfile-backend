package com.confluenciacreativa.portfile.repository;

import com.confluenciacreativa.portfile.domain.Experience;

import java.util.List;
import java.util.Optional;

public interface ExperienceRepository {

    List<Experience> getAll();
    Optional<List<Experience>> getByPerson(Integer idPerson);
    Boolean existsById(Integer idExperience);
    Boolean existsByTitle(String title);
    Optional<Experience> getExperience(Integer idExperience);
    Optional<Experience> findByTitle(String title);
    void save(Experience experience);
    void delete(Integer idExperience);
}
