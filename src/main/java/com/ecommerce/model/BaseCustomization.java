package com.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BaseCustomization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sector_custom;
    private String option_custom;

    @Override
    public String toString() {
        return "BaseCustomization{" +
                "id=" + id +
                ", sector_custom='" + sector_custom + '\'' +
                ", option_custom='" + option_custom + '\'' +
                '}';
    }
}
