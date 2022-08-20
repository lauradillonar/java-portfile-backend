package com.confluenciacreativa.portfile.security.service;

import com.confluenciacreativa.portfile.security.entity.Role;
import com.confluenciacreativa.portfile.security.enums.RoleName;
import com.confluenciacreativa.portfile.security.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public Optional<Role> getByRoleName (RoleName roleName){
        return  roleRepository.findByRoleName(roleName);
    }
}
