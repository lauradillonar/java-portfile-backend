package com.confluenciacreativa.portfile.repository;

import com.confluenciacreativa.portfile.domain.Person;

import java.util.List;
import java.util.Optional;

public interface PersonRepository {

    List<Person> getAll();
    Boolean existsById(Integer idPerson);
    Optional<Person> getPerson(Integer idPerson);
    Person save(Person person);
    void delete(Integer idPerson);
}
