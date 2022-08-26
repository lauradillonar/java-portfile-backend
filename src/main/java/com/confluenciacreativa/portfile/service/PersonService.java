package com.confluenciacreativa.portfile.service;

import com.confluenciacreativa.portfile.domain.Person;
import com.confluenciacreativa.portfile.dto.PersonRes;
import com.confluenciacreativa.portfile.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<PersonRes> getAll(){
        List<Person> persons = personRepository.getAll();
        List<PersonRes> personResList = new ArrayList<PersonRes>();
        persons.forEach(person -> {
                    personResList.add( new PersonRes(
                                    person.getIdPerson(),
                                    person.getName(),
                                    person.getLastname(),
                                    person.getBirthdate(),
                                    person.getNationality(),
                                    person.getPhone(),
                                    person.getAboutMeSub(),
                                    person.getAboutMe(),
                                    person.getJob(),
                                    person.getLocation(),
                                    person.getUser().getId(),
                                    person.getImageHeader(),
                                    person.getImage(),
                                    person.getLogoSrc(),
                                    person.getLogoAlt(),
                                    person.getLogoUrl(),
                                    person.getFacebook(),
                                    person.getInstagram(),
                                    person.getTwitter()
                            ));
                }
        );
        return personResList;
    }

    public Boolean existsById(Integer idPerson){
        return personRepository.existsById(idPerson);
    }

    public Optional<PersonRes> getPerson(Integer idPerson){
        Optional<Person> person = personRepository.getPerson(idPerson);
        return Optional.of(new PersonRes(
                person.get().getIdPerson(),
                person.get().getName(),
                person.get().getLastname(),
                person.get().getBirthdate(),
                person.get().getNationality(),
                person.get().getPhone(),
                person.get().getAboutMeSub(),
                person.get().getAboutMe(),
                person.get().getJob(),
                person.get().getLocation(),
                person.get().getUser().getId(),
                person.get().getImageHeader(),
                person.get().getImage(),
                person.get().getLogoSrc(),
                person.get().getLogoAlt(),
                person.get().getLogoUrl(),
                person.get().getFacebook(),
                person.get().getInstagram(),
                person.get().getTwitter()
        ));
    }


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
