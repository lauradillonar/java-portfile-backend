package com.confluenciacreativa.portfile.service;

import com.confluenciacreativa.portfile.domain.Contact;
import com.confluenciacreativa.portfile.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> getAll(){
        return contactRepository.getAll();
    }

    public Optional<List<Contact>> getByPerson(Integer idPerson){
        return contactRepository.getByPerson(idPerson);
    }

    public Boolean existsByIdPerson(Integer idPerson){
        return contactRepository.existsByIdPerson(idPerson);
    }

    public Boolean existsById(Integer idContact){
        return contactRepository.existsById(idContact);
    }

    public Optional<Contact> getContact(Integer idContact){
        return contactRepository.getContact(idContact);
    }

    public void save(Contact contact){
        contactRepository.save(contact);
    }

    public Boolean delete(Integer idContact){
        return  getContact(idContact).map(
                contact -> {
                    contactRepository.delete(idContact);
                    return true;
                }).orElse(false);
    }
}
