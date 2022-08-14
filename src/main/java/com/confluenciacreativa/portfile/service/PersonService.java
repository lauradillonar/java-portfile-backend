package com.confluenciacreativa.portfile.service;

import com.confluenciacreativa.portfile.domain.Person;
import com.confluenciacreativa.portfile.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<Person> getAll(){
        return personRepository.getAll();
    }

    public Boolean existsById(Integer idPerson){
        return personRepository.existsById(idPerson);
    }

    public Boolean existsByUserName(String userName){
        return personRepository.existsByUserName(userName);
    }

    public Boolean existsByEmail(String email){
        return personRepository.existsByEmail(email);
    }

    public Optional<Person> getPerson(Integer idPerson){
        return  personRepository.getPerson(idPerson);
    }

    public Optional<Person> findByUserName(String userName){return personRepository.findByUserName(userName);}

    public Optional<Person> findByEmail(String email){return personRepository.findByEmail(email);}

    public Integer save(Person person){
        try{
            return personRepository.save(person).getIdPerson();
        } catch (Exception e){
            return null;
        }
    }

    public Boolean delete(Integer idPerson){
        return getPerson(idPerson).map(person -> {
            personRepository.delete(idPerson);
            return true;
        }).orElse(false);
    }
}
