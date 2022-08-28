package com.confluenciacreativa.portfile.security.service;

import com.confluenciacreativa.portfile.security.dto.Person;
import com.confluenciacreativa.portfile.security.entity.MainUser;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    PersonService personService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Person person = personService.findByUserName(userName).get();
        return MainUser.build(person);
    }
}
