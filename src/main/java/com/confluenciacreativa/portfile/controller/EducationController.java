package com.confluenciacreativa.portfile.controller;

import com.confluenciacreativa.portfile.domain.Education;
import com.confluenciacreativa.portfile.dto.EducationDto;
import com.confluenciacreativa.portfile.dto.Message;
import com.confluenciacreativa.portfile.service.EducationService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/educations")
@CrossOrigin(origins = "http://localhost:4200")
public class EducationController {

    private final Log LOGGER = LogFactory.getLog(EducationController.class);

    @Autowired
    private EducationService educationService;

    @GetMapping("/all")
    public ResponseEntity<List<Education>> getAll(){
        return new ResponseEntity<>(educationService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Education> getEducation(@PathVariable("id") Integer idEducation){
        if(!educationService.existsById(idEducation))
            return new ResponseEntity(new Message("No existe"), HttpStatus.NOT_FOUND);
        Education education = educationService.getEducation(idEducation).get();
        return new ResponseEntity<Education>(education, HttpStatus.OK);
    }

    @GetMapping("/person/{idPerson}")
    public ResponseEntity<List<Education>> getByPerson(@PathVariable("idPerson") Integer idPerson){
        return educationService.getByPerson(idPerson)
                .map(educations -> new ResponseEntity<>(educations, HttpStatus.OK))
                .orElse(new ResponseEntity(new Message("No existe"), HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save/{idPerson}")
    public ResponseEntity<?> save(@PathVariable("idPerson") Integer idPerson, @RequestBody EducationDto educationDto){
    try{
        if(StringUtils.isBlank(educationDto.getIdPerson().toString()))
            return new ResponseEntity(new Message("El id de persona es requerido"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(educationDto.getTitle()))
            return new ResponseEntity(new Message("Un título con datos de educación es requerido"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(educationDto.getWhen()))
            return  new ResponseEntity(new Message("Un período es requerido"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(educationDto.getSubtitle()))
            return new ResponseEntity(new Message("Una descripción es requerida"), HttpStatus.BAD_REQUEST);
        if(educationService.existsByTitleAndIdPerson(educationDto.getTitle(), idPerson))
            return new ResponseEntity(new Message("Esos datos de educación ya existen"), HttpStatus.BAD_REQUEST);
    }catch(Exception e){
        return new ResponseEntity(new Message("Datos inválidos"), HttpStatus.BAD_REQUEST);
    }
        Education education = new Education(
                educationDto.getIdPerson(),
                educationDto.getTitle(),
                educationDto.getWhen(),
                educationDto.getSubtitle(),
                educationDto.getText1(),
                educationDto.getLink(),
                educationDto.getUrl(),
                educationDto.getText2(),
                educationDto.getViewmore()
        );
        educationService.save(education);
        return new ResponseEntity(new Message("Datos de educación agregados"), HttpStatus.CREATED);
    }

    @PutMapping("/update/{idPerson}/{idEducation}")
    public ResponseEntity<?> update(
            @PathVariable("idPerson") Integer idPerson,
            @PathVariable("idEducation") Integer idEducation,
            @RequestBody Education education){

    try{
        if(!educationService.existsById(idEducation))
            return new ResponseEntity(new Message("No existe"), HttpStatus.NOT_FOUND);
        if(idEducation != education.getIdEducation())
            return new ResponseEntity(new Message("Datos inválidos"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(education.getIdPerson().toString()))
            return new ResponseEntity(new Message("Un id de persona es requerido"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(education.getTitle()))
            return new ResponseEntity(new Message("El título de educación es requerido"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(education.getWhen()))
            return new ResponseEntity(new Message("Un periodo es requerido"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(education.getSubtitle()))
            return new ResponseEntity(new Message("Una descripción es requerida"), HttpStatus.BAD_REQUEST);
        if(educationService.existsByTitleAndIdPerson(education.getTitle(), idPerson)
            && idEducation != educationService.findByTitle(education.getTitle()).get().getIdEducation())
            return new ResponseEntity(new Message("Esos datos de educación ya existen"), HttpStatus.BAD_REQUEST);
        if(idPerson != educationService.getEducation(education.getIdEducation()).get().getIdPerson())
            return new ResponseEntity(new Message("No autorizado"), HttpStatus.BAD_REQUEST);
    }catch(Exception e){
        return new ResponseEntity(new Message("Datos inválidos"), HttpStatus.BAD_REQUEST);
    }
        Education storedEducation = educationService.getEducation(idEducation).get();
        storedEducation.setIdPerson(education.getIdPerson());
        storedEducation.setTitle(education.getTitle());
        storedEducation.setWhen(education.getWhen());
        storedEducation.setSubtitle(education.getSubtitle());
        storedEducation.setText1(education.getText1());
        storedEducation.setLink(education.getLink());
        storedEducation.setUrl(education.getUrl());
        storedEducation.setText2(education.getText2());
        storedEducation.setViewmore(education.getViewmore());

        educationService.save(storedEducation);
        return new ResponseEntity(new Message("Datos actualizados"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer idEducation){
        if(!educationService.existsById(idEducation))
            return new ResponseEntity(new Message("No existe"), HttpStatus.OK);
        try {
            educationService.delete(idEducation);
            return new ResponseEntity(new Message("Datos de educación borrados"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new Message("No se pueden borrar estos datos"), HttpStatus.BAD_REQUEST);
        }
    }
}
