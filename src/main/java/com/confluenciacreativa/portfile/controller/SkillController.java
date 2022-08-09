package com.confluenciacreativa.portfile.controller;

import com.confluenciacreativa.portfile.domain.Skill;
import com.confluenciacreativa.portfile.dto.Message;
import com.confluenciacreativa.portfile.dto.SkillDto;
import com.confluenciacreativa.portfile.service.SkillService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skills")
@CrossOrigin(origins = "https://lvd-portfile.web.app")
public class SkillController {

    private final Log LOGGER = LogFactory.getLog(SkillController.class);

    @Autowired
    private SkillService skillService;

    @GetMapping("/all")
    public ResponseEntity<List<Skill>> getAll() {
        return new ResponseEntity<>(skillService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Skill> getSkill (@PathVariable("id") Integer idSkill){
        if(!skillService.existsById(idSkill))
            return  new ResponseEntity(new Message("No existe"), HttpStatus.NOT_FOUND);
        Skill skill = skillService.getSkill(idSkill).get();
        return new ResponseEntity<Skill>(skill, HttpStatus.OK);
    }

    @GetMapping("/person/{idPerson}")
    public ResponseEntity<List<Skill>> getByPerson(@PathVariable("idPerson") Integer idPerson){
        return skillService.getByPerson(idPerson)
                .map(skills -> new ResponseEntity<>(skills, HttpStatus.OK))
                .orElse(new ResponseEntity(new Message("No existe"), HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save/{idPerson}")
    public ResponseEntity<?> save(@PathVariable("idPerson") Integer idPerson,@RequestBody SkillDto skillDto){
    try{
        if(StringUtils.isBlank(skillDto.getIdPerson().toString()))
            return new ResponseEntity(new Message("El id de persona es requerido"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(skillDto.getItem()))
            return new ResponseEntity(new Message("El nombre de una tecnología es requerida"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(skillDto.getProgress().toString()))
            return new ResponseEntity(new Message("Ingresa un valor de progreso adecuado"), HttpStatus.BAD_REQUEST);
        if(skillService.existsByItemAndIdPerson(skillDto.getItem(), idPerson))
            return new ResponseEntity(new Message("Esa tecnología ya existe"), HttpStatus.BAD_REQUEST);
    }catch(Exception e){
        return new ResponseEntity(new Message("Datos inválidos"), HttpStatus.BAD_REQUEST);
    }
        Skill skill = new Skill(
                skillDto.getIdPerson(),
                skillDto.getItem(),
                skillDto.getProgress()
        );
        skillService.save(skill);
        return new ResponseEntity(new Message("Tecnología agregada"), HttpStatus.CREATED);
    }

    @PutMapping("/update/{idPerson}/{idSkill}")
    public ResponseEntity<?> update(
            @PathVariable("idPerson") Integer idPerson,
            @PathVariable("idSkill") Integer idSkill,
            @RequestBody Skill skill
    ){
    try{
        if(!skillService.existsById(idSkill))
            return new ResponseEntity(new Message("No existe"), HttpStatus.NOT_FOUND);
        if(idSkill != skill.getIdSkill())
            return new ResponseEntity(new Message("Datos inválidos"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(skill.getIdPerson().toString()))
            return new ResponseEntity(new Message("Un id de persona es requerido"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(skill.getItem()))
            return new ResponseEntity(new Message("El nombre de la tecnología es requerido"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(skill.getProgress().toString()))
            return new ResponseEntity(new Message("Un valor de progreso adecuado es requerido"), HttpStatus.BAD_REQUEST);
        if(skillService.existsByItemAndIdPerson(skill.getItem(), idPerson)
            && idSkill != skillService.findByItem(skill.getItem()).get().getIdSkill())
            return  new ResponseEntity(new Message("Esa tecnología ya existe"), HttpStatus.BAD_REQUEST);
        if(idPerson != skillService.getSkill(skill.getIdSkill()).get().getIdPerson())
            return new ResponseEntity(new Message("No autorizado"), HttpStatus.BAD_REQUEST);
    }catch(Exception e){
        return new ResponseEntity(new Message("Datos inválidos"), HttpStatus.BAD_REQUEST);
    }
        Skill storedSkill = skillService.getSkill(idSkill).get();
        storedSkill.setIdPerson(skill.getIdPerson());
        storedSkill.setItem(skill.getItem());
        storedSkill.setProgress(skill.getProgress());

        skillService.save(storedSkill);
        return new ResponseEntity(new Message("Datos actualizados"), HttpStatus.OK);
    }
}
