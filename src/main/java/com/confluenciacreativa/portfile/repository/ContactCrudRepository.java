package com.confluenciacreativa.portfile.repository;

import com.confluenciacreativa.portfile.entity.ContactDB;
import org.springframework.data.repository.CrudRepository;

public interface ContactCrudRepository  extends CrudRepository<ContactDB, Integer> {
}
