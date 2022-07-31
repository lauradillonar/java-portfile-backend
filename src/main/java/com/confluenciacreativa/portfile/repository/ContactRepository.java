package com.confluenciacreativa.portfile.repository;

import com.confluenciacreativa.portfile.domain.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactRepository {

    List<Contact> getAll();
    Boolean existsById(Integer idContact);
    Optional<Contact> getContact(Integer idContact);
    void save(Contact contact);
    void delete(Integer idContact);
}
