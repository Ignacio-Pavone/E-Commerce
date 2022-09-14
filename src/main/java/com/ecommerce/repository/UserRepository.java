package com.ecommerce.repository;


import com.ecommerce.dto.UserDTO;
import com.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
     Optional<User> findById(Long id);
     List<User> findAll();
     Optional<User> findByName(String name);
}

