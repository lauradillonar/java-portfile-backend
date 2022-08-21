package com.confluenciacreativa.portfile.security.util;

import com.confluenciacreativa.portfile.security.entity.Role;
import com.confluenciacreativa.portfile.security.enums.RoleName;
import com.confluenciacreativa.portfile.security.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CreateRoles implements CommandLineRunner {

    @Autowired
    RoleService roleService;

    @Override
    public void run(String... args) throws Exception {
        Role roleAdmin = new Role(RoleName.ROLE_ADMIN);
        Role roleUser = new Role(RoleName.ROLE_USER);
        roleService.save(roleAdmin);
        roleService.save(roleUser);
    }
}
