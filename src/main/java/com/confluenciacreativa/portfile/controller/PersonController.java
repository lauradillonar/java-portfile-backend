package com.confluenciacreativa.portfile.controller;

import com.confluenciacreativa.portfile.domain.Person;
import com.confluenciacreativa.portfile.dto.Message;
import com.confluenciacreativa.portfile.dto.PersonDto;
import com.confluenciacreativa.portfile.service.PersonService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/persons")
@CrossOrigin(origins = "https://lvd-portfile.web.app")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/all")
    public ResponseEntity<List<Person>> getAll() {
        return new ResponseEntity<>(personService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson (@PathVariable("id") Integer idPerson) {
        if(!personService.existsById(idPerson))
            return new ResponseEntity(new Message("No existe"), HttpStatus.NOT_FOUND);
        Person person = personService.getPerson(idPerson).get();
        return new ResponseEntity<Person>(person, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Integer> save(@RequestBody PersonDto personDto){
        if(StringUtils.isBlank(personDto.getName()))
            return new ResponseEntity(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(personDto.getLastname()))
            return new ResponseEntity(new Message("El apellido es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(personDto.getUserName()))
            return new ResponseEntity(new Message("El nombre de usuario es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(personDto.getBirthdate()))
            return new ResponseEntity(new Message("La fecha de nacimiento es obligatoria"), HttpStatus.BAD_REQUEST);
        try{
            LocalDateTime dateTime = LocalDateTime.parse(personDto.getBirthdate());
        } catch (DateTimeParseException ex) {
            return new ResponseEntity(new Message("Error en el formato de la fecha (YYYY-mm-dd HH:mm:ss)"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(personDto.getNationality()))
            return new ResponseEntity(new Message("La nacionalidad es requerida"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(personDto.getEmail()))
            return new ResponseEntity(new Message("El email es requerido"), HttpStatus.BAD_REQUEST);
        if (!emailValidator(personDto.getEmail()))
            return new ResponseEntity(new Message("Email inválido"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(personDto.getPassword()))
            return new ResponseEntity(new Message("La contraseña es requerida"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(personDto.getPhone()))
            return new ResponseEntity(new Message("El teléfono es requerido"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(personDto.getAboutMeSub()))
            return new ResponseEntity(new Message("Una breve descripción es requerida"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(personDto.getAboutMe()))
            return new ResponseEntity(new Message("Una breve descripción es requerida"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(personDto.getJob()))
            return new ResponseEntity(new Message("La profesión actual es requerida"), HttpStatus.BAD_REQUEST);
        if(personService.existsByUserName(personDto.getUserName()))
            return new ResponseEntity(new Message("Ese nombre de usuario ya existe"), HttpStatus.BAD_REQUEST);
        if(personService.existsByEmail(personDto.getEmail()))
            return new ResponseEntity(new Message("Ese email ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(personDto.getLocation()))
            return new ResponseEntity(new Message("El lugar es requerido"), HttpStatus.BAD_REQUEST);
        Person person = new Person(
                                personDto.getName(),
                                personDto.getLastname(),
                                personDto.getUserName(),
                                LocalDateTime.parse(personDto.getBirthdate()),
                                personDto.getNationality(),
                                personDto.getEmail(),
                                personDto.getPassword(),
                                personDto.getPhone(),
                                personDto.getAboutMeSub(),
                                personDto.getAboutMe(),
                                personDto.getJob(),
                                personDto.getLocation(),
                                personDto.getImageHeader(),
                                personDto.getImage(),
                                personDto.getLogoSrc(),
                                personDto.getLogoAlt(),
                                personDto.getLogoUrl(),
                                personDto.getFacebook(),
                                personDto.getInstagram(),
                                personDto.getTwitter()
        );
        try{
            return new ResponseEntity<Integer>(personService.save(person), HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity(new Message("No guardado"), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer idPerson, @RequestBody PersonDto personDto){
        if(!personService.existsById(idPerson))
            return  new ResponseEntity(new Message("No existe"), HttpStatus.NOT_FOUND);
        if(personService.existsByUserName(personDto.getUserName())
            && personService.findByUserName(personDto.getUserName()).get().getIdPerson() != idPerson)
            return  new ResponseEntity(new Message("Ese nombre de usuario ya existe"), HttpStatus.BAD_REQUEST);
        if(personService.existsByEmail(personDto.getEmail())
            && personService.findByEmail(personDto.getEmail()).get().getIdPerson() != idPerson)
            return new ResponseEntity(new Message("Ese email ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(personDto.getName()))
            return new ResponseEntity(new Message("El nombre es requerido"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(personDto.getLastname()))
            return new ResponseEntity(new Message("El apellido es requerido"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(personDto.getUserName()))
            return new ResponseEntity(new Message("El nombre de usuario es requerido"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(personDto.getBirthdate()))
            return new ResponseEntity(new Message("La fecha de nacimiento es requerida"), HttpStatus.BAD_REQUEST);
        try{
            LocalDateTime dateTime = LocalDateTime.parse(personDto.getBirthdate());
        } catch (DateTimeParseException ex) {
            return new ResponseEntity(new Message("Error en el formato de la fecha (YYYY-mm-dd HH:mm:ss)"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(personDto.getNationality()))
            return new ResponseEntity(new Message("La nacionalidad es requerida"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(personDto.getEmail()))
            return new ResponseEntity(new Message("El email es requerido"), HttpStatus.BAD_REQUEST);

        if (!emailValidator(personDto.getEmail()))
            return new ResponseEntity(new Message("Email inválido"), HttpStatus.BAD_REQUEST);

        if(StringUtils.isBlank(personDto.getPassword()))
            return new ResponseEntity(new Message("La contraseña es requerida"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(personDto.getPhone()))
            return new ResponseEntity(new Message("El teléfono es requerido"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(personDto.getAboutMeSub()))
            return new ResponseEntity(new Message("Una frase breve es requerida"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(personDto.getAboutMe()))
            return new ResponseEntity(new Message("Una descripción breve es requerida"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(personDto.getJob()))
            return new ResponseEntity(new Message("La profesión es requerida"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(personDto.getLocation()))
            return new ResponseEntity(new Message("El lugar es requerido"), HttpStatus.BAD_REQUEST);

        Person person = personService.getPerson(idPerson).get();
        person.setName(personDto.getName());
        person.setLastname(personDto.getLastname());
        person.setUserName(personDto.getUserName());
        person.setBirthdate(LocalDateTime.parse(personDto.getBirthdate()));
        person.setNationality(personDto.getNationality());
        person.setEmail(personDto.getEmail());
        person.setPassword(personDto.getPassword());
        person.setPhone(personDto.getPhone());
        person.setAboutMeSub(personDto.getAboutMeSub());
        person.setAboutMe(personDto.getAboutMe());
        person.setJob(personDto.getJob());
        person.setLocation(personDto.getLocation());
        person.setImageHeader(personDto.getImageHeader());
        person.setImage(personDto.getImage());
        person.setLogoSrc(personDto.getLogoSrc());
        person.setLogoAlt(personDto.getLogoAlt());
        person.setLogoUrl(personDto.getLogoUrl());
        person.setFacebook(personDto.getFacebook());
        person.setInstagram(personDto.getInstagram());
        person.setTwitter(personDto.getTwitter());

        personService.save(person);
        return new ResponseEntity(new Message("Datos actualizados"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer idPerson) {
        if (!personService.existsById(idPerson))
            return new ResponseEntity(new Message("No existe"), HttpStatus.OK);
        try {
            personService.delete(idPerson);
            return new ResponseEntity(new Message("Persona dada de baja"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new Message("No se puede dar de baja esta persona"), HttpStatus.BAD_REQUEST);
        }
    }

    private boolean emailValidator(String email){
        String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
        if (email == null) {
            return false;
        }

        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
        //https://www.techiedelight.com/es/validate-email-address-java/
    }
}
