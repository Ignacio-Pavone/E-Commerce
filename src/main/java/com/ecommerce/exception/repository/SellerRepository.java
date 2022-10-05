package com.ecommerce.exception.repository;

import com.ecommerce.model.Product;
import com.ecommerce.model.SellProduct;
import com.ecommerce.model.Seller;
import com.ecommerce.model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
        Optional<Seller> findById(Long id);

}

