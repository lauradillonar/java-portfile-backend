package com.confluenciacreativa.portfile.repository;

import com.confluenciacreativa.portfile.domain.Person;

import java.util.List;
import java.util.Optional;

public interface PersonRepository {

    List<Person> getAll();
    Boolean existsById(Integer idPerson);
    Boolean existsByUserName(String userName);
    Boolean existsByEmail(String email);
    Optional<Person> getPerson(Integer idPerson);
    Optional<Person> findByUserName(String userName);
    Optional<Person> findByEmail(String email);
    void save(Person person);
    void delete(Integer idPerson);
}