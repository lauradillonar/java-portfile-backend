package com.confluenciacreativa.portfile.mapper;

import com.confluenciacreativa.portfile.domain.Contact;
import com.confluenciacreativa.portfile.entity.ContactDB;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PersonMapper.class})
public interface ContactMapper {

    @Mappings({
            @Mapping(source = "idContactDB", target = "idContact"),
            @Mapping(source = "idPersonDB", target = "idPerson"),
            @Mapping(source = "textNameDB", target = "textName"),
            @Mapping(source = "textEmailDB", target = "textEmail"),
            @Mapping(source = "textMessageDB", target = "textMessage")
    })
    Contact toContact(ContactDB contactDB);
    List<Contact> toContacts(List<ContactDB> contactsDB);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "personDB", ignore = true)
    })
    ContactDB toContactDB(Contact contact);
}
