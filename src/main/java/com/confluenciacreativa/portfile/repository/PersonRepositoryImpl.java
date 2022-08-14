package com.confluenciacreativa.portfile.repository;

import com.confluenciacreativa.portfile.domain.Person;
import com.confluenciacreativa.portfile.entity.PersonDB;
import com.confluenciacreativa.portfile.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PersonRepositoryImpl implements PersonRepository{

    @Autowired
    private PersonCrudRepository personCrudRepository;

    @Autowired
    private PersonMapper mapper;

    @Override
    public List<Person> getAll() {

        List<PersonDB> personsDB = (List<PersonDB>) personCrudRepository.findAll();
        return mapper.toPersons(personsDB);
    }

    @Override
    public Boolean existsById(Integer idPerson) {
        return personCrudRepository.existsById(idPerson);
    }

    @Override
    public Boolean existsByUserName(String userName) {
        return personCrudRepository.existsByUserNameDB(userName);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return personCrudRepository.existsByEmailDB(email);
    }

    @Override
    public Optional<Person> getPerson(Integer idPerson) {
        return personCrudRepository.findById(idPerson).map(personDB -> mapper.toPerson(personDB));
    }

    @Override
    public Optional<Person> findByUserName(String userName) {
        return personCrudRepository.findByUserNameDB(userName).map(personDB -> mapper.toPerson(personDB));
    }

    @Override
    public Optional<Person> findByEmail(String email) {
        return personCrudRepository.findByEmailDB(email).map(personDB -> mapper.toPerson(personDB));
    }

    @Override
    public Person save(Person person) {
        PersonDB personDB = mapper.toPersonDB(person);
        PersonDB newPersonDB = personCrudRepository.save(personDB);
        return mapper.toPerson(newPersonDB);
    }

    @Override
    public void delete(Integer idPerson) {
        personCrudRepository.deleteById(idPerson);
    }
}
