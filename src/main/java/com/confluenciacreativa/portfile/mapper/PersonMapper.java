package com.confluenciacreativa.portfile.mapper;

import com.confluenciacreativa.portfile.domain.Person;
import com.confluenciacreativa.portfile.entity.PersonDB;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    @Mappings({
            @Mapping(source = "idPersonDB", target = "idPerson"),
            @Mapping(source = "nameDB", target = "name"),
            @Mapping(source = "lastnameDB", target = "lastname"),
            @Mapping(source = "userNameDB", target = "userName"),
            @Mapping(source = "birthdateDB", target = "birthdate"),
            @Mapping(source = "nationalityDB", target = "nationality"),
            @Mapping(source = "emailDB", target = "email"),
            @Mapping(source = "passwordDB", target = "password"),
            @Mapping(source = "phoneDB", target = "phone"),
            @Mapping(source = "aboutMeDB", target = "aboutMe"),
            @Mapping(source = "jobDB", target = "job"),
            @Mapping(source = "imageHeaderDB", target = "imageHeader"),
            @Mapping(source = "imageDB", target = "image"),
            @Mapping(source = "logoSrcDB", target = "logoSrc"),
            @Mapping(source = "logoAltDB", target = "logoAlt"),
            @Mapping(source = "logoUrlDB", target = "logoUrl"),
    })
    Person toPerson(PersonDB personDB);
    List<Person> toPersons(List<PersonDB> personsDB);

    @InheritInverseConfiguration
    PersonDB toPersonDB(Person person);
}
