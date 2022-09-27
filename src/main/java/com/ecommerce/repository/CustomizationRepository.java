package com.ecommerce.repository;

import com.ecommerce.model.BaseCustomization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomizationRepository extends JpaRepository<BaseCustomization, Long> {


}

