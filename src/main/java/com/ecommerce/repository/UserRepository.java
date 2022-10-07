package com.ecommerce.repository;


import com.ecommerce.dto.UserDTO;
import com.ecommerce.model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);

    User findByName(String name);
    List<User> findAll();

    List<User> findAllByName(String name);

}

