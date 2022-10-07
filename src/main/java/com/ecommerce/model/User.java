package com.ecommerce.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;
    @JoinColumn(name = "role_id", referencedColumnName = "role_id",nullable = true,insertable=false, updatable=false)
    @ManyToOne(targetEntity= com.ecommerce.model.Role.class)
    private Role role;

    @JsonProperty("role_id")
    @JsonIgnoreProperties("role_id")
    @JsonBackReference
    private Long role_id;


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}