package com.confluenciacreativa.portfile.repository;

import com.confluenciacreativa.portfile.domain.Contact;
import com.confluenciacreativa.portfile.entity.ContactDB;
import com.confluenciacreativa.portfile.mapper.ContactMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ContactRepositoryImpl implements ContactRepository{

    @Autowired
    private ContactCrudRepository contactCrudRepository;

    @Autowired
    private ContactMapper mapper;

    @Override
    public List<Contact> getAll() {

        List<ContactDB> contactsDB = (List<ContactDB>) contactCrudRepository.findAll();
        return mapper.toContacts(contactsDB);
    }

    @Override
    public Optional<List<Contact>> getByPerson(Integer idPerson) {
        List<ContactDB> contactsDB = contactCrudRepository.findByIdPersonDB(idPerson);
        return  Optional.of(mapper.toContacts(contactsDB));
    }

    @Override
    public Boolean existsById(Integer idContact) {
        return contactCrudRepository.existsById(idContact);
    }

    @Override
    public Optional<Contact> getContact(Integer idContact) {
        return contactCrudRepository.findById(idContact).map(contactDB -> mapper.toContact(contactDB));
    }

    @Override
    public void save(Contact contact) {
        ContactDB contactDB = mapper.toContactDB(contact);
        contactCrudRepository.save(contactDB);
    }

    @Override
    public void delete(Integer idContact) {
        contactCrudRepository.deleteById(idContact);
    }
}
