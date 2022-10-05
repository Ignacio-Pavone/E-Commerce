package com.ecommerce.service;

import com.ecommerce.model.BaseCustomization;
import com.ecommerce.exception.repository.CustomizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomizationService {
    @Autowired
    private CustomizationRepository customizationRepository;


    public BaseCustomization createCustomization(BaseCustomization baseCustomization) {
        return customizationRepository.save(baseCustomization);
    }

    public List<BaseCustomization> findAll() {
        return customizationRepository.findAll();
    }

    public boolean deleteCustomization(Long id) {
        customizationRepository.deleteById(id);
        return true;
    }

}
