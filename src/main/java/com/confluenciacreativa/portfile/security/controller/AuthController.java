package com.confluenciacreativa.portfile.security.controller;

import com.confluenciacreativa.portfile.dto.Message;
import com.confluenciacreativa.portfile.security.dto.JwtDto;
import com.confluenciacreativa.portfile.security.dto.LoginUser;
import com.confluenciacreativa.portfile.security.dto.NewUser;
import com.confluenciacreativa.portfile.security.entity.Role;
import com.confluenciacreativa.portfile.security.entity.User;
import com.confluenciacreativa.portfile.security.enums.RoleName;
import com.confluenciacreativa.portfile.security.jwt.JwtProvider;
import com.confluenciacreativa.portfile.security.service.RoleService;
import com.confluenciacreativa.portfile.security.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody NewUser newUser, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Message("Datos inválidos"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(newUser.getPassword()))
            return new ResponseEntity(new Message("La contraseña es requerida"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(newUser.getUserName()))
            return new ResponseEntity(new Message("El nombre de usuario es requerido"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(newUser.getEmail()))
            return new ResponseEntity(new Message("El email es requerido"), HttpStatus.BAD_REQUEST);
        if(userService.existsByUserName(newUser.getUserName()))
            return new ResponseEntity(new Message("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(userService.existsByEmail(newUser.getEmail()))
            return new ResponseEntity(new Message("Ese email ya existe"), HttpStatus.BAD_REQUEST);
        if (!emailValidator(newUser.getEmail()))
            return new ResponseEntity(new Message("Email inválido"), HttpStatus.BAD_REQUEST);

        User user = new User(
                newUser.getUserName(),
                newUser.getEmail(),
                passwordEncoder.encode(newUser.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getByRoleName(RoleName.ROLE_USER).get());
        if(newUser.getRoles().contains("admin"))
            roles.add(roleService.getByRoleName(RoleName.ROLE_ADMIN).get());
        user.setRoles(roles);
        userService.save(user);
        return new ResponseEntity(new Message("Usuario guardado"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUser loginUser, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return  new ResponseEntity(new Message("Datos inválidos"), HttpStatus.BAD_REQUEST);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUser.getUserName(),loginUser.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }

    private boolean emailValidator(String email){
        String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
        if (email == null) {
            return false;
        }

        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
        //https://www.techiedelight.com/es/validate-email-address-java/
    }
}
