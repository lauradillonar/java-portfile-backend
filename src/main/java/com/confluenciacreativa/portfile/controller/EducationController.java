package com.confluenciacreativa.portfile.controller;

import com.confluenciacreativa.portfile.domain.Contact;
import com.confluenciacreativa.portfile.domain.Education;
import com.confluenciacreativa.portfile.dto.EducationDto;
import com.confluenciacreativa.portfile.dto.Message;
import com.confluenciacreativa.portfile.service.EducationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/educations")
@CrossOrigin(origins = "https://lvd-portfile.web.app")
public class EducationController {

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

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody EducationDto educationDto){
        if(StringUtils.isBlank(educationDto.getIdPerson().toString()))
            return new ResponseEntity(new Message("El id de persona es requerido"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(educationDto.getTitle()))
            return new ResponseEntity(new Message("Un título con datos de educación es requerido"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(educationDto.getWhen()))
            return  new ResponseEntity(new Message("Un período es requerido"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(educationDto.getSubtitle()))
            return new ResponseEntity(new Message("Una descripción es requerida"), HttpStatus.BAD_REQUEST);
        if(educationService.existsByTitle(educationDto.getTitle()))
            return new ResponseEntity(new Message("Esos datos de educación ya existen"), HttpStatus.BAD_REQUEST);
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

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer idEducation, @RequestBody EducationDto educationDto){
        if(!educationService.existsById(idEducation))
            return new ResponseEntity(new Message("No existe"), HttpStatus.NOT_FOUND);
        if(StringUtils.isBlank(educationDto.getIdPerson().toString()))
            return new ResponseEntity(new Message("Un id de persona es requerido"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(educationDto.getTitle()))
            return new ResponseEntity(new Message("El título de educación es requerido"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(educationDto.getWhen()))
            return new ResponseEntity(new Message("Un periodo es requerido"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(educationDto.getSubtitle()))
            return new ResponseEntity(new Message("Una descripción es requerida"), HttpStatus.BAD_REQUEST);
        Education education = educationService.getEducation(idEducation).get();
        education.setIdPerson(educationDto.getIdPerson());
        education.setTitle(educationDto.getTitle());
        education.setWhen(educationDto.getWhen());
        education.setSubtitle(educationDto.getSubtitle());
        education.setText1(educationDto.getText1());
        education.setLink(educationDto.getLink());
        education.setUrl(educationDto.getUrl());
        education.setViewmore(educationDto.getViewmore());

        educationService.save(education);
        return new ResponseEntity(new Message("Datos actualizados"), HttpStatus.OK);
    }
}
