package com.confluenciacreativa.portfile.security.controller;

import com.confluenciacreativa.portfile.security.dto.JwtDto;
import com.confluenciacreativa.portfile.security.dto.LoginUser;
import com.confluenciacreativa.portfile.security.dto.Person;
import com.confluenciacreativa.portfile.dto.Message;
import com.confluenciacreativa.portfile.security.dto.PersonDto;
import com.confluenciacreativa.portfile.security.entity.Role;
import com.confluenciacreativa.portfile.security.enums.RoleName;
import com.confluenciacreativa.portfile.security.jwt.JwtProvider;
import com.confluenciacreativa.portfile.security.service.PersonService;
import com.confluenciacreativa.portfile.security.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/persons")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonController {

    private final Log LOGGER = LogFactory.getLog(PersonController.class);

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private PersonService personService;

    @Autowired
    RoleService roleService;

    @Autowired
    JwtProvider jwtProvider;

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

    //@PreAuthorize("hasRole('USER')")
    @GetMapping("/user/{user}")
    public ResponseEntity<Integer> getId(@PathVariable("user") String userName){
        if(!personService.existsByUserName(userName)){
            return new ResponseEntity(new Message("No existe"), HttpStatus.NOT_FOUND);
        }else{
            Person person = personService.findByUserName(userName).get();
            return new ResponseEntity<Integer>(person.getIdPerson(), HttpStatus.OK);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUser loginUser, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return  new ResponseEntity(new Message("Datos inválidos"), HttpStatus.BAD_REQUEST);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUser.getUserName(),loginUser.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save")
    public ResponseEntity<Integer> save(@Valid @RequestBody PersonDto personDto, BindingResult bindingResult){
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
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Message("Datos inválidos"), HttpStatus.BAD_REQUEST);
        Person person = new Person(
                                personDto.getName(),
                                personDto.getLastname(),
                                personDto.getUserName(),
                                LocalDateTime.parse(personDto.getBirthdate()),
                                personDto.getNationality(),
                                personDto.getEmail(),
                                passwordEncoder.encode(personDto.getPassword()),
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
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getByRoleName(RoleName.ROLE_USER).get());
        if(personDto.getRoles().contains("admin"))
            roles.add(roleService.getByRoleName(RoleName.ROLE_ADMIN).get());
        person.setRoles(roles);

        try{
            return new ResponseEntity<Integer>(personService.save(person), HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity(new Message("No guardado"), HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable("id") Integer idPerson, @RequestBody Person person, BindingResult bindingResult){
        if(idPerson != person.getIdPerson())
            return new ResponseEntity(new Message("Datos inválidos"), HttpStatus.BAD_REQUEST);
        if(!personService.existsById(idPerson))
            return  new ResponseEntity(new Message("No existe"), HttpStatus.NOT_FOUND);
        if(personService.existsByUserName(person.getUserName())
            && personService.findByUserName(person.getUserName()).get().getIdPerson() != idPerson)
            return  new ResponseEntity(new Message("Ese nombre de usuario ya existe"), HttpStatus.BAD_REQUEST);
        if(personService.existsByEmail(person.getEmail())
            && personService.findByEmail(person.getEmail()).get().getIdPerson() != idPerson)
            return new ResponseEntity(new Message("Ese email ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(person.getName()))
            return new ResponseEntity(new Message("El nombre es requerido"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(person.getLastname()))
            return new ResponseEntity(new Message("El apellido es requerido"), HttpStatus.BAD_REQUEST);
        // if(StringUtils.isBlank(person.getUserName()))
        //    return new ResponseEntity(new Message("El nombre de usuario es requerido"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(person.getBirthdate().toString()))
            return new ResponseEntity(new Message("La fecha de nacimiento es requerida"), HttpStatus.BAD_REQUEST);
        try{
            LocalDateTime dateTime = person.getBirthdate();
        } catch (DateTimeParseException ex) {
            return new ResponseEntity(new Message("Error en el formato de la fecha (YYYY-mm-dd HH:mm:ss)"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(person.getNationality()))
            return new ResponseEntity(new Message("La nacionalidad es requerida"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(person.getEmail()))
            return new ResponseEntity(new Message("El email es requerido"), HttpStatus.BAD_REQUEST);

        if (!emailValidator(person.getEmail()))
            return new ResponseEntity(new Message("Email inválido"), HttpStatus.BAD_REQUEST);

        // if(StringUtils.isBlank(person.getPassword()))
        //    return new ResponseEntity(new Message("La contraseña es requerida"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(person.getPhone()))
            return new ResponseEntity(new Message("El teléfono es requerido"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(person.getAboutMeSub()))
            return new ResponseEntity(new Message("Una frase breve es requerida"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(person.getAboutMe()))
            return new ResponseEntity(new Message("Una descripción breve es requerida"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(person.getJob()))
            return new ResponseEntity(new Message("La profesión es requerida"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(person.getLocation()))
            return new ResponseEntity(new Message("El lugar es requerido"), HttpStatus.BAD_REQUEST);
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Message("Datos inválidos"), HttpStatus.BAD_REQUEST);

        Person newPerson = personService.getPerson(idPerson).get();
        newPerson.setName(person.getName());
        newPerson.setLastname(person.getLastname());
        //newPerson.setUserName(person.getUserName());
        newPerson.setBirthdate(person.getBirthdate());
        newPerson.setNationality(person.getNationality());
        newPerson.setEmail(person.getEmail());
        //if(newPerson.getPassword() != person.getPassword()) {
        //    newPerson.setPassword(passwordEncoder.encode(person.getPassword()));
        //}
        newPerson.setPhone(person.getPhone());
        newPerson.setAboutMeSub(person.getAboutMeSub());
        newPerson.setAboutMe(person.getAboutMe());
        newPerson.setJob(person.getJob());
        newPerson.setLocation(person.getLocation());
        newPerson.setImageHeader(person.getImageHeader());
        newPerson.setImage(person.getImage());
        newPerson.setLogoSrc(person.getLogoSrc());
        newPerson.setLogoAlt(person.getLogoAlt());
        newPerson.setLogoUrl(person.getLogoUrl());
        newPerson.setFacebook(person.getFacebook());
        newPerson.setInstagram(person.getInstagram());
        newPerson.setTwitter(person.getTwitter());

        LOGGER.info("La clave es: "+newPerson.getPassword());
        personService.save(newPerson);
        LOGGER.info("La clave es: "+newPerson.getPassword());
        try {
            return new ResponseEntity(new Message("Datos actualizados"), HttpStatus.OK);
        } catch (Exception e) {
            return  new ResponseEntity(new Message("No actualizado"), HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
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
