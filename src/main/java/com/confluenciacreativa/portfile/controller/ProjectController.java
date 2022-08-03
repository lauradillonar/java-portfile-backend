package com.confluenciacreativa.portfile.controller;

import com.confluenciacreativa.portfile.domain.Contact;
import com.confluenciacreativa.portfile.domain.Project;
import com.confluenciacreativa.portfile.dto.Message;
import com.confluenciacreativa.portfile.dto.ProjectDto;
import com.confluenciacreativa.portfile.service.ProjectService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
@CrossOrigin(origins = "http://localhost:4200")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

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

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ProjectDto projectDto){
        if(StringUtils.isBlank(projectDto.getIdPerson().toString()))
            return new ResponseEntity(new Message("El id de persona es requerido"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(projectDto.getTitle()))
            return new ResponseEntity(new Message("Un título del proyecto es requerido"), HttpStatus.BAD_REQUEST);
        if(projectService.existsByTitle(projectDto.getTitle()))
            return new ResponseEntity(new Message("Ese título de proyecto ya existe"), HttpStatus.BAD_REQUEST);
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

}
