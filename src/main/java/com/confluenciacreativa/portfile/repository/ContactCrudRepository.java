package com.confluenciacreativa.portfile.repository;

import com.confluenciacreativa.portfile.entity.ContactDB;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContactCrudRepository  extends CrudRepository<ContactDB, Integer> {
    List<ContactDB> findByIdPerson(Integer idPerson);
}
