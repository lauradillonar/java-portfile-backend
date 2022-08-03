package com.confluenciacreativa.portfile.controller;

import com.confluenciacreativa.portfile.domain.Contact;
import com.confluenciacreativa.portfile.domain.Skill;
import com.confluenciacreativa.portfile.dto.Message;
import com.confluenciacreativa.portfile.dto.SkillDto;
import com.confluenciacreativa.portfile.service.SkillService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skills")
@CrossOrigin(origins = "https://lvd-portfile.web.app")
public class SkillController {

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

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody SkillDto skillDto){
        if(StringUtils.isBlank(skillDto.getIdPerson().toString()))
            return new ResponseEntity(new Message("El id de persona es requerido"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(skillDto.getItem()))
            return new ResponseEntity(new Message("El nombre de una tecnología es requerida"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(skillDto.getProgress().toString()))
            return new ResponseEntity(new Message("Ingresa un valor de progreso adecuado"), HttpStatus.BAD_REQUEST);
        if(skillService.existsByItem(skillDto.getItem()))
            return new ResponseEntity(new Message("Esa tecnología ya existe"), HttpStatus.BAD_REQUEST);
        Skill skill = new Skill(
                skillDto.getIdPerson(),
                skillDto.getItem(),
                skillDto.getProgress()
        );
        skillService.save(skill);
        return new ResponseEntity(new Message("Tecnología agregada"), HttpStatus.CREATED);
    }
}
