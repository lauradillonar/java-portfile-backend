package com.confluenciacreativa.portfile.service;

import com.confluenciacreativa.portfile.domain.Experience;
import com.confluenciacreativa.portfile.domain.Project;
import com.confluenciacreativa.portfile.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> getAll(){
        return projectRepository.getAll();
    }

    public Boolean existsById(Integer idProject){
        return projectRepository.existsById(idProject);
    }

    public Optional<List<Project>> getByPerson(Integer idPerson){
        return projectRepository.getByPerson(idPerson);
    }

    public Boolean existsByTitle(String title){
        return projectRepository.existsByTitle(title);
    }

    public Optional<Project> getProject(Integer idProject){
        return projectRepository.getProject(idProject);
    }

    public Optional<Project> findByTitle(String title){
        return projectRepository.findByTitle(title);
    }

    public void save(Project project){
        projectRepository.save(project);
    }

    public Boolean delete(Integer idProject){
        return getProject(idProject).map(
                project -> {
                    projectRepository.delete(idProject);
                    return  true;
                }).orElse(false);
    }

    public Boolean existsByTitleAndIdPerson(String title, Integer idPerson){
        return projectRepository.existsByTitleAndIdPerson(title, idPerson);
    }
}
