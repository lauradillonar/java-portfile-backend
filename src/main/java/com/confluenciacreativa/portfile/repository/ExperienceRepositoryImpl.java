package com.confluenciacreativa.portfile.repository;

import com.confluenciacreativa.portfile.domain.Experience;
import com.confluenciacreativa.portfile.entity.ExperienceDB;
import com.confluenciacreativa.portfile.mapper.ExperienceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ExperienceRepositoryImpl implements ExperienceRepository{

    @Autowired
    private ExperienceCrudRepository experienceCrudRepository;

    @Autowired
    private ExperienceMapper mapper;

    @Override
    public List<Experience> getAll() {
        List<ExperienceDB> experiencesDB = (List<ExperienceDB>) experienceCrudRepository.findAll();
        return mapper.toExperiences(experiencesDB);
    }

    @Override
    public Optional<List<Experience>> getByPerson(Integer idPerson) {
        List<ExperienceDB> experiencesDB = experienceCrudRepository.findByIdPerson(idPerson);
        return Optional.of(mapper.toExperiences(experiencesDB));
    }

    @Override
    public Boolean existsById(Integer idExperience) {
        return experienceCrudRepository.existsById(idExperience);
    }

    @Override
    public Boolean existsByTitle(String title) {
        return experienceCrudRepository.existsByTitleDB(title);
    }

    @Override
    public Optional<Experience> getExperience(Integer idExperience) {
        return experienceCrudRepository.findById(idExperience).map(experienceDB -> mapper.toExperience(experienceDB));
    }

    @Override
    public Optional<Experience> findByTitle(String title) {
        return experienceCrudRepository.findByTitleDB(title).map(experienceDB -> mapper.toExperience(experienceDB));
    }

    @Override
    public void save(Experience experience) {
        ExperienceDB experienceDB = mapper.toExperienceDB(experience);
    }

    @Override
    public void delete(Integer idExperience) {
        experienceCrudRepository.deleteById(idExperience);
    }
}
