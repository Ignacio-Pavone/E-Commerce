package com.ecommerce.security.service;
import com.ecommerce.model.User;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class UserServiceConfig implements UserDetailsService {
    @Autowired
    private UserService repository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        log.info("Consultando USUARIO " + name + " en base de datos");
        User usuario = repository.findByName(name);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(usuario.getRole().getRole().toString()));
        log.info("Usuario autenticado: " + name);
        return new org.springframework.security.core.userdetails.User(usuario.getName(), usuario.getPassword(), authorities);
    }
}
