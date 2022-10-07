package com.ecommerce.repository;

import com.ecommerce.model.dto.ShowSellProductDTO;
import com.ecommerce.model.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);
    List<Product> findAll(Specification<ShowSellProductDTO> spec);
}

