package com.ecommerce.mapper;

import com.ecommerce.dto.SimplePublicationDTO;
import com.ecommerce.model.Publication;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SimplePublicationMapper {

    public SimplePublicationDTO storeToSimpleStore(Publication store) {
        SimplePublicationDTO simpleStore = new SimplePublicationDTO();
        simpleStore.setPublicationName(store.getPublicationName());
        simpleStore.setIsActive(store.getIsActive());
        simpleStore.setStock(store.getStock());
        simpleStore.setPrice(store.getPrice());
        return simpleStore;
    }

    public List<SimplePublicationDTO> listpublicationToDTo (List<Publication> publicationDTOList) {
        List<SimplePublicationDTO> publicationList = new ArrayList<>();
        for (Publication publicationDTO : publicationDTOList) {
            publicationList.add(storeToSimpleStore(publicationDTO));
        }
        return publicationList;
    }
}
