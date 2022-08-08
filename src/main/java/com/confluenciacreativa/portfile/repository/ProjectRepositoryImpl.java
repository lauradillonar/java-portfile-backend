package com.confluenciacreativa.portfile.repository;

import com.confluenciacreativa.portfile.domain.Project;
import com.confluenciacreativa.portfile.entity.ProjectDB;
import com.confluenciacreativa.portfile.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProjectRepositoryImpl implements ProjectRepository{

    @Autowired
    private ProjectCrudRepository projectCrudRepository;

    @Autowired
    private ProjectMapper mapper;

    @Override
    public List<Project> getAll() {
        List<ProjectDB> projectsDB = (List<ProjectDB>) projectCrudRepository.findAll();
        return mapper.toProjects(projectsDB);

    }

    @Override
    public Optional<List<Project>> getByPerson(Integer idPerson) {
        List<ProjectDB> projectsDB = projectCrudRepository.findByIdPersonDB(idPerson);
        return  Optional.of(mapper.toProjects(projectsDB));
    }

    @Override
    public Boolean existsById(Integer idProject) {
        return projectCrudRepository.existsById(idProject);
    }

    @Override
    public Boolean existsByTitle(String title) {
        return projectCrudRepository.existsByTitleDB(title);
    }

    @Override
    public Optional<Project> getProject(Integer idProject) {
        return projectCrudRepository.findById(idProject).map(projectDB -> mapper.toProject(projectDB));
    }

    @Override
    public Optional<Project> findByTitle(String title) {
        return projectCrudRepository.findByTitleDB(title).map(projectDB -> mapper.toProject(projectDB));
    }

    @Override
    public void save(Project project) {
        ProjectDB projectDB = mapper.toProjectDB(project);
        projectCrudRepository.save(projectDB);
    }

    @Override
    public void delete(Integer idProject) {
        projectCrudRepository.deleteById(idProject);
    }

    @Override
    public Boolean existsByTitleAndIdPerson(String title, Integer idPerson) {
        return projectCrudRepository.existsByTitleDBAndIdPersonDB(title, idPerson);
    }
}
