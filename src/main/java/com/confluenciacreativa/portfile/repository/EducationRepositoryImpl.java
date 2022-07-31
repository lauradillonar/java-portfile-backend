package com.confluenciacreativa.portfile.repository;

import com.confluenciacreativa.portfile.domain.Education;
import com.confluenciacreativa.portfile.entity.EducationDB;
import com.confluenciacreativa.portfile.mapper.EducationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EducationRepositoryImpl implements EducationRepository{

    @Autowired
    private EducationCrudRepository educationCrudRepository;

    private EducationMapper mapper;

    @Override
    public List<Education> getAll() {
        List<EducationDB> educationsDB = (List<EducationDB>) educationCrudRepository.findAll();
        return  mapper.toEducations(educationsDB);
    }

    @Override
    public Optional<List<Education>> getByPerson(Integer idPerson) {
        List<EducationDB> educationsDB = educationCrudRepository.findByIdPersonDB(idPerson);
        return Optional.of(mapper.toEducations(educationsDB));
    }

    @Override
    public Boolean existsById(Integer idEducation) {
        return educationCrudRepository.existsById(idEducation);
    }

    @Override
    public Boolean existsByTitle(String title) {
        return educationCrudRepository.existsByTitleDB(title);
    }

    @Override
    public Optional<Education> getEducation(Integer idEducation) {
        return educationCrudRepository.findById(idEducation).map(educationDB -> mapper.toEducation(educationDB));
    }

    @Override
    public Optional<Education> findByTitle(String title) {
        return educationCrudRepository.findByTitleDB(title).map(educationDB -> mapper.toEducation(educationDB));
    }

    @Override
    public void save(Education education) {
        EducationDB educationDB = mapper.toEducationDB(education);
        educationCrudRepository.save(educationDB);
    }

    @Override
    public void delete(Integer idEducation) {
        educationCrudRepository.deleteById(idEducation);
    }
}
