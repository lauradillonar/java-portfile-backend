package com.confluenciacreativa.portfile.security.repository;

import com.confluenciacreativa.portfile.security.entity.PersonDB;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PersonCrudRepository extends CrudRepository<PersonDB, Integer> {
    Boolean existsByUserNameDB(String userNameDB);
    Boolean existsByEmailDB(String emailDB);
    Optional<PersonDB>findByUserNameDB(String userNameDB);
    Optional<PersonDB>findByEmailDB(String emailDB);
}
