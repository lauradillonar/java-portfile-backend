package com.confluenciacreativa.portfile.controller;

import com.confluenciacreativa.portfile.domain.Experience;
import com.confluenciacreativa.portfile.dto.ExperienceDto;
import com.confluenciacreativa.portfile.dto.Message;
import com.confluenciacreativa.portfile.service.ExperienceService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/experiences")
@CrossOrigin(origins = "https://lvd-portfile.web.app")
public class ExperienceController {

    private final Log LOGGER = LogFactory.getLog(ExperienceController.class);

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

    @PostMapping("/save/{idPerson}")
    public ResponseEntity<?> save(@PathVariable("idPerson") Integer idPerson, @RequestBody ExperienceDto experienceDto){
    try {
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
        if(experienceService.existsByTitleAndIdPerson(experienceDto.getTitle(), idPerson))
            return new ResponseEntity(new Message("Esa experiencia ya existe"), HttpStatus.BAD_REQUEST);
    }catch(Exception e){
        return new ResponseEntity(new Message("Datos inválidos"), HttpStatus.BAD_REQUEST);
    }
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

    @PutMapping("/update/{idPerson}/{idExperience}")
    public ResponseEntity<?> update(
            @PathVariable("idPerson") Integer idPerson ,
            @PathVariable("idExperience") Integer idExperience,
            @RequestBody Experience experience){
        // LOGGER.info("Estoy en update");
        // LOGGER.info("El id de la experiencia es "+ experience.getIdExperience());
    try{
        if(!experienceService.existsById(idExperience))
            return new ResponseEntity(new Message("No existe"), HttpStatus.NOT_FOUND);
        if(idExperience != experience.getIdExperience())
            return new ResponseEntity(new Message("Datos inválidos"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(experience.getIdPerson().toString()))
            return new ResponseEntity(new Message("Un id de persona es requerido"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(experience.getTitle()))
            return new ResponseEntity(new Message("El título de la experiencia es requerido"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(experience.getSubtitle()))
            return new ResponseEntity(new Message("Una descripción de la experiencia es requerida"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(experience.getWhen()))
            return new ResponseEntity(new Message("Un periodo es requerido"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(experience.getWhere()))
            return new ResponseEntity(new Message("Un lugar es requerido"), HttpStatus.BAD_REQUEST);
        if (experienceService.existsByTitleAndIdPerson(experience.getTitle(), idPerson)
                && idExperience != experienceService.findByTitle(experience.getTitle()).get().getIdExperience())
                return new ResponseEntity(new Message("Esa experiencia ya existe"), HttpStatus.BAD_REQUEST);
        if(idPerson != experienceService.getExperience(experience.getIdExperience()).get().getIdPerson())
            return new ResponseEntity(new Message("No autorizado"), HttpStatus.BAD_REQUEST);
    }catch(Exception e){
        return new ResponseEntity(new Message("Datos inválidos"), HttpStatus.BAD_REQUEST);
    }
        Experience storedExperience = experienceService.getExperience(idExperience).get();
        storedExperience.setIdPerson(experience.getIdPerson());
        storedExperience.setTitle(experience.getTitle());
        storedExperience.setSubtitle(experience.getSubtitle());
        storedExperience.setWhen(experience.getWhen());
        storedExperience.setWhere(experience.getWhere());
        storedExperience.setText(experience.getText());
        storedExperience.setLink1(experience.getLink1());
        storedExperience.setUrl1(experience.getUrl1());
        storedExperience.setLink2(experience.getLink2());
        storedExperience.setUrl2(experience.getUrl2());
        storedExperience.setUrl3(experience.getUrl3());

        experienceService.save(storedExperience);
        return new ResponseEntity(new Message("Datos actualizados"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer idExperience){
        if(!experienceService.existsById(idExperience))
            return  new ResponseEntity(new Message("No existe"), HttpStatus.OK);
        try {
            experienceService.delete(idExperience);
            return new ResponseEntity(new Message("Experiencia borrada"), HttpStatus.OK);
        }catch(Exception e){
            return  new ResponseEntity(new Message("No se puede borrar esta experiencia"), HttpStatus.BAD_REQUEST);
        }
    }
}
