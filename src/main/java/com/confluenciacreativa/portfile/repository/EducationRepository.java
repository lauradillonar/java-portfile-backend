package com.confluenciacreativa.portfile.repository;

import com.confluenciacreativa.portfile.domain.Education;

import java.util.List;
import java.util.Optional;

public interface EducationRepository {

    List<Education> getAll();
    Optional<List<Education>> getByPerson(Integer idPerson);
    Boolean existsById(Integer idEducation);
    Boolean existsByTitle(String title);
    Optional<Education> getEducation(Integer idEducation);
    Optional<Education> findByTitle(String title);
    void save(Education education);
    void delete(Integer idEducation);
}
