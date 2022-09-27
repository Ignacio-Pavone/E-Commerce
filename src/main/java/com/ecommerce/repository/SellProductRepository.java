package com.ecommerce.repository;

import com.ecommerce.dto.ShowSellProductDTO;
import com.ecommerce.model.SellProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellProductRepository extends JpaRepository<SellProduct, Long> {

}

