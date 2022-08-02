package com.confluenciacreativa.portfile.controller;

import com.confluenciacreativa.portfile.domain.Contact;
import com.confluenciacreativa.portfile.domain.Experience;
import com.confluenciacreativa.portfile.dto.ExperienceDto;
import com.confluenciacreativa.portfile.dto.Message;
import com.confluenciacreativa.portfile.service.ExperienceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/experiences")
@CrossOrigin(origins = "https://lvd-portfile.web.app/portfile/")
public class ExperienceController {

    @Autowired
    private ExperienceService experienceService;

    @GetMapping("/all")
    public ResponseEntity<List<Experience>> getAll(){
        return new ResponseEntity<>(experienceService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Experience> getExperience (@PathVariable("id") Integer idExperience){
        if(!experienceService.existsById(idExperience))
            return new ResponseEntity(new Message("No existe"), HttpStatus.NOT_FOUND);
        Experience experience = experienceService.getExperience(idExperience).get();
        return new ResponseEntity<Experience>(experience, HttpStatus.OK);
    }

    @GetMapping("/person/{idPerson}")
    public ResponseEntity<List<Experience>> getByPerson(@PathVariable("idPerson") Integer idPerson){
        return experienceService.getByPerson(idPerson)
                .map(experiences -> new ResponseEntity<>(experiences, HttpStatus.OK))
                .orElse(new ResponseEntity(new Message("No existe"), HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ExperienceDto experienceDto){
        if(StringUtils.isBlank(experienceDto.getIdPerson().toString()))
            return new ResponseEntity(new Message("El id de persona es requerido"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(experienceDto.getTitle()))
            return new ResponseEntity(new Message("Un título de la experiencia es requerido"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(experienceDto.getSubtitle()))
            return new ResponseEntity(new Message("Un subtítulo de la experiencia es requerido"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(experienceDto.getWhen()))
            return new ResponseEntity(new Message("El período de la experiencia es requerido"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(experienceDto.getWhere()))
            return new ResponseEntity(new Message("El lugar de la experiencia es requerido"), HttpStatus.BAD_REQUEST);
        if(experienceService.existsByTitle(experienceDto.getTitle()))
            return new ResponseEntity(new Message("Esa experiencia ya existe"), HttpStatus.BAD_REQUEST);
        Experience experience = new Experience(
                experienceDto.getIdPerson(),
                experienceDto.getTitle(),
                experienceDto.getSubtitle(),
                experienceDto.getWhen(),
                experienceDto.getWhere(),
                experienceDto.getText(),
                experienceDto.getLink1(),
                experienceDto.getUrl1(),
                experienceDto.getLink2(),
                experienceDto.getUrl2(),
                experienceDto.getLink3(),
                experienceDto.getUrl3()
        );
        experienceService.save(experience);
        return new ResponseEntity(new Message("Experiencia agregada"), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer idExperience, @RequestBody ExperienceDto experienceDto){
        if(!experienceService.existsById(idExperience))
            return new ResponseEntity(new Message("No existe"), HttpStatus.NOT_FOUND);
        if(StringUtils.isBlank(experienceDto.getIdPerson().toString()))
            return new ResponseEntity(new Message("Un id de persona es requerido"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(experienceDto.getTitle()))
            return new ResponseEntity(new Message("El título de la experiencia es requerido"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(experienceDto.getSubtitle()))
            return new ResponseEntity(new Message("Una descripción de la experiencia es requerida"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(experienceDto.getWhen()))
            return new ResponseEntity(new Message("Un periodo es requerido"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(experienceDto.getWhere()))
            return new ResponseEntity(new Message("Un lugar es requerido"), HttpStatus.BAD_REQUEST);
        Experience experience = experienceService.getExperience(idExperience).get();
        experience.setIdPerson(experienceDto.getIdPerson());
        experience.setTitle(experienceDto.getTitle());
        experience.setSubtitle(experienceDto.getSubtitle());
        experience.setWhen(experienceDto.getWhen());
        experience.setWhere(experienceDto.getWhere());
        experience.setText(experienceDto.getText());
        experience.setLink1(experienceDto.getLink1());
        experience.setUrl1(experienceDto.getUrl1());
        experience.setLink2(experienceDto.getLink2());
        experience.setUrl2(experienceDto.getUrl2());
        experience.setUrl3(experienceDto.getUrl3());

        experienceService.save(experience);
        return new ResponseEntity(new Message("Datos actualizados"), HttpStatus.OK);
    }
}
