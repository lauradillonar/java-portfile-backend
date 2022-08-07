package com.confluenciacreativa.portfile.service;

import com.confluenciacreativa.portfile.domain.Education;
import com.confluenciacreativa.portfile.repository.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EducationService {

    @Autowired
    private EducationRepository educationRepository;

    public List<Education> getAll(){
        return educationRepository.getAll();
    }

    public Optional<List<Education>> getByPerson(Integer idPerson){

        return educationRepository.getByPerson(idPerson);
    }

    public Boolean existsById(Integer idEducation){

        return educationRepository.existsById(idEducation);
    }

    public Boolean existsByTitle(String title){
        return educationRepository.existsByTitle(title);
    }

    public Optional<Education> getEducation(Integer idEducation){
        return educationRepository.getEducation(idEducation);
    }

    public Optional<Education> findByTitle(String title){
        return educationRepository.findByTitle(title);
    }

    public void save(Education education){
        educationRepository.save(education);
    }

    public Boolean delete(Integer idEducation){
        return getEducation(idEducation).map(
                education -> {
                    educationRepository.delete(idEducation);
                    return true;
                }).orElse(false);
    }

    public Boolean existsByTitleAndIdPerson(String title, Integer idPerson){
        return educationRepository.existsByTitleAndIdPerson(title, idPerson);
    }
}
