package com.confluenciacreativa.portfile.repository;

import com.confluenciacreativa.portfile.entity.PersonDB;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PersonCrudRepository extends CrudRepository<PersonDB, Integer> {

}
