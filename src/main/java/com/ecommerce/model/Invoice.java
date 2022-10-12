package com.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {
    private Long id;

    private String sellername;
    private Integer totalProducts;
    private LocalDate date;
    private Double totalAmount;
    private List<Item> products = new ArrayList<>();


}
