package com.ecommerce.service;

import com.ecommerce.model.Publication;
import com.ecommerce.exception.repository.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PublicationService {
    @Autowired
    private PublicationRepository publicationRepository;


    public Publication createPublication(Publication publication) {
        return publicationRepository.save(publication);
    }
}
