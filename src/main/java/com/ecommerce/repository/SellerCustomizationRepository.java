package com.ecommerce.repository;

import com.ecommerce.model.SellerCustomization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerCustomizationRepository extends JpaRepository<SellerCustomization, Long> {
}

