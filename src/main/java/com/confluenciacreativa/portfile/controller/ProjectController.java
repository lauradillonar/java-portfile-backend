package com.confluenciacreativa.portfile.controller;

import com.confluenciacreativa.portfile.domain.Project;
import com.confluenciacreativa.portfile.dto.Message;
import com.confluenciacreativa.portfile.dto.ProjectDto;
import com.confluenciacreativa.portfile.service.ProjectService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
@CrossOrigin(origins = "http://localhost:4200")
public class ProjectController {

    private final Log LOGGER = LogFactory.getLog(ExperienceController.class);

    @Autowired
    private ProjectService projectService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<Project>> getAll() {
        return new ResponseEntity<>(projectService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProject(@PathVariable("id") Integer idProject){
        if(!projectService.existsById(idProject))
            return new ResponseEntity(new Message("No existe"), HttpStatus.NOT_FOUND);
        Project project = projectService.getProject(idProject).get();
        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }

    @GetMapping("/person/{idPerson}")
    public ResponseEntity<List<Project>> getByPerson(@PathVariable("idPerson") Integer idPerson){
        return projectService.getByPerson(idPerson)
                .map(projects -> new ResponseEntity<>(projects, HttpStatus.OK))
                .orElse(new ResponseEntity(new Message("No existe"), HttpStatus.NOT_FOUND));
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/save/{idPerson}")
    public ResponseEntity<?> save(@PathVariable("idPerson") Integer idPerson, @RequestBody ProjectDto projectDto){
    try{
        if(StringUtils.isBlank(projectDto.getIdPerson().toString()))
            return new ResponseEntity(new Message("El id de persona es requerido"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(projectDto.getTitle()))
            return new ResponseEntity(new Message("Un título del proyecto es requerido"), HttpStatus.BAD_REQUEST);
        if(projectService.existsByTitleAndIdPerson(projectDto.getTitle(), idPerson))
            return new ResponseEntity(new Message("Ese título de proyecto ya existe"), HttpStatus.BAD_REQUEST);
        if(idPerson != projectDto.getIdPerson())
            return new ResponseEntity(new Message("No autorizado"), HttpStatus.BAD_REQUEST);
    }catch(Exception e){
        return new ResponseEntity(new Message("Datos inválidos"), HttpStatus.BAD_REQUEST);
    }
        Project project = new Project(
                projectDto.getIdPerson(),
                projectDto.getTitle(),
                projectDto.getFontawesome(),
                projectDto.getLetter(),
                projectDto.getText(),
                projectDto.getViewmore()
        );
        projectService.save(project);
        return new ResponseEntity(new Message("Proyecto agregado"), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/update/{idPerson}/{idProject}")
    public ResponseEntity<?> update(
            @PathVariable("idPerson") Integer idPerson,
            @PathVariable("idProject") Integer idProject,
            @RequestBody Project project
    ){
        try{
            if(!projectService.existsById(idProject))
                return new ResponseEntity(new Message("No existe"), HttpStatus.NOT_FOUND);
            if(idProject != project.getIdProject())
                return new ResponseEntity(new Message("Datos invalidos"), HttpStatus.BAD_REQUEST);
            if(StringUtils.isBlank(project.getIdPerson().toString()))
                return new ResponseEntity(new Message("Un id de persona es requerido"), HttpStatus.BAD_REQUEST);
            if(StringUtils.isBlank(project.getTitle()))
                return new ResponseEntity(new Message("El título del proyecto es requerido"), HttpStatus.BAD_REQUEST);
            if(projectService.existsByTitleAndIdPerson(project.getTitle(), idPerson)
                && idProject != projectService.findByTitle(project.getTitle()).get().getIdProject())
                return new ResponseEntity(new Message("Ese proyecto ya existe"), HttpStatus.BAD_REQUEST);
            if(idPerson != projectService.getProject(project.getIdProject()).get().getIdPerson())
                return new ResponseEntity(new Message("No autorizado"), HttpStatus.BAD_REQUEST);
            if(idPerson != project.getIdPerson())
                return new ResponseEntity(new Message("No autorizado"), HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            return new ResponseEntity(new Message("Datos inválidos"), HttpStatus.BAD_REQUEST);
        }
        Project storedProject = projectService.getProject(idProject).get();
        storedProject.setIdPerson(project.getIdPerson());
        storedProject.setTitle(project.getTitle());
        storedProject.setFontawesome(project.getFontawesome());
        storedProject.setLetter(project.getLetter());
        storedProject.setText(project.getText());
        storedProject.setViewmore(project.getViewmore());

        projectService.save(storedProject);
        return new ResponseEntity(new Message("Datos actualizados"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer idProject){
        if(!projectService.existsById(idProject))
            return  new ResponseEntity(new Message("No existe"), HttpStatus.OK);
        try {
            projectService.delete(idProject);
            return new ResponseEntity(new Message("Proyecto borrado"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new Message("No se puede borrar este proyecto"), HttpStatus.BAD_REQUEST);
        }
    }

}
