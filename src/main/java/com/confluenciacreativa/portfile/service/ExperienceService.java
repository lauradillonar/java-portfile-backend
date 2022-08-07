package com.confluenciacreativa.portfile.service;

import com.confluenciacreativa.portfile.domain.Experience;
import com.confluenciacreativa.portfile.repository.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ExperienceService {

    @Autowired
    private ExperienceRepository experienceRepository;

    public List<Experience> getAll(){
        return experienceRepository.getAll();
    }

    public Optional<List<Experience>> getByPerson(Integer idPerson){
        return experienceRepository.getByPerson(idPerson);
    }

    public Boolean existsById(Integer idExperience){
        return experienceRepository.existsById(idExperience);
    }

    public Boolean existsByTitle(String title){
        return experienceRepository.existsByTitle(title);
    }

    public Optional<Experience> getExperience(Integer idExperience){
        return experienceRepository.getExperience(idExperience);
    }

    public Optional<Experience> findByTitle(String title){
        return experienceRepository.findByTitle(title);
    }

    public void save(Experience experience){
        experienceRepository.save(experience);
    }

    public Boolean delete(Integer idExperience){
        return getExperience(idExperience).map(
                experience -> {
                    experienceRepository.delete(idExperience);
                    return true;
                }).orElse(false);
    }

    public Boolean existsByTitleAndIdPerson(String title, Integer idPerson){
        return experienceRepository.existsByTitleAndIdPerson(title, idPerson);
    }
}
