package com.confluenciacreativa.portfile.repository;

import com.confluenciacreativa.portfile.domain.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactRepository {

    List<Contact> getAll();
    Optional<List<Contact>> getByPerson(Integer idPerson);
    Boolean existsByIdPerson(Integer idPerson);
    Boolean existsById(Integer idContact);
    Optional<Contact> getContact(Integer idContact);
    void save(Contact contact);
    void delete(Integer idContact);
}
