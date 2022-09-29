package com.ecommerce.mapper;

import com.ecommerce.dto.PublicationDTO;
import com.ecommerce.model.Publication;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PublicationMapper {
    public PublicationDTO toPublicationDTO(Publication publication) {
        PublicationDTO publicationDTO = new PublicationDTO();
        publicationDTO.setId(publication.getId());
        publicationDTO.setPublicationName(publication.getPublicationName());
        publicationDTO.setIdSellProduct(publication.getId_sellproduct());
        publicationDTO.setStock(publication.getStock());
        publicationDTO.setPrice(publication.getPrice());
        publicationDTO.setIsActive(publication.getIsActive());
        return publicationDTO;
    }

    public Publication toPublication(PublicationDTO publicationDTO) {
        Publication publication = new Publication();
        publication.setPublicationName(publicationDTO.getPublicationName());
        publication.setStock(publicationDTO.getStock());
        publication.setPrice(publicationDTO.getPrice());
        publication.setIsActive(publicationDTO.getIsActive());
        publication.setId_sellproduct(publicationDTO.getIdSellProduct());
        return publication;
    }

    public List<Publication> listpublicationToDTo (List<PublicationDTO> publicationDTOList) {
        List<Publication> publicationList = new ArrayList<>();
        for (PublicationDTO publicationDTO : publicationDTOList) {
            publicationList.add(toPublication(publicationDTO));
        }
        return publicationList;
    }

    public List<PublicationDTO> toPublicationDTO(List<Publication> publicationList) {
        List<PublicationDTO> publicationDTOList = new ArrayList<>();
        for (Publication publication : publicationList) {
            publicationDTOList.add(toPublicationDTO(publication));
        }
        return publicationDTOList;
    }

}