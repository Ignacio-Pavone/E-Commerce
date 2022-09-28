package com.ecommerce.service;

import com.ecommerce.dto.ProductFilterDTO;
import com.ecommerce.dto.ShowSellProductDTO;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;


@Component
public class ProductFiltersService {
    public Specification<ShowSellProductDTO> getByFilters(ProductFilterDTO sellProduct) {
        return (root, query, criteriaBuilder) -> {

            // Se crea la lista de predicados
            List<Predicate> predicates = new ArrayList<>();

            // Busqueda por nombre
            if (StringUtils.hasLength(sellProduct.getName())) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + sellProduct.getName().toLowerCase() + "%"));
            }

            // definir orden
            String orderByField = "name";
            query.orderBy(sellProduct.isASC() ? criteriaBuilder.asc(root.get(orderByField)) : criteriaBuilder.desc(root.get(orderByField)));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

    }
}
