package com.ecommerce.repository;

import com.ecommerce.model.Seller;
import com.ecommerce.model.Store;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> findAll();
    Store deleteStoreByIdStore(Long idStore);
}
