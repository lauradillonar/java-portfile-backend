package com.confluenciacreativa.portfile.controller;

import com.confluenciacreativa.portfile.domain.Contact;
import com.confluenciacreativa.portfile.dto.ContactDto;
import com.confluenciacreativa.portfile.dto.Message;
import com.confluenciacreativa.portfile.service.ContactService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
@CrossOrigin(origins = "http://localhost:4200")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/all")
    public ResponseEntity<List<Contact>> getAll(){
        return new ResponseEntity<>(contactService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContact(@PathVariable("id") Integer idContact){
        if(!contactService.existsById(idContact))
            return new ResponseEntity(new Message("No existe"), HttpStatus.NOT_FOUND);
        Contact contact = contactService.getContact(idContact).get();
        return  new ResponseEntity<Contact>(contact, HttpStatus.OK);
    }

    @GetMapping("/person/{idPerson}")
    public ResponseEntity<List<Contact>> getByPerson(@PathVariable("idPerson") Integer idPerson){
        return contactService.getByPerson(idPerson)
                .map(contacts -> new ResponseEntity<>(contacts, HttpStatus.OK))
                .orElse(new ResponseEntity(new Message("No existe"), HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ContactDto contactDto){
        if(StringUtils.isBlank(contactDto.getIdPerson().toString()))
            return new ResponseEntity(new Message("El id de persona es requerido"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(contactDto.getTextName()))
            return new ResponseEntity(new Message("Un nombre es requerido"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(contactDto.getTextEmail()))
            return new ResponseEntity(new Message("Un email es requerido"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(contactDto.getTextEmail()))
            return new ResponseEntity(new Message("Un mensaje es requerido"), HttpStatus.BAD_REQUEST);
        Contact contact = new Contact(
                contactDto.getIdPerson(),
                contactDto.getTextName(),
                contactDto.getTextEmail(),
                contactDto.getTextMessage()
        );
        contactService.save(contact);
        return new ResponseEntity(new Message("Mensaje de contacto guardado"), HttpStatus.CREATED);
    }
}
