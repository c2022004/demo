package com.example.projectsale.product.dto.request;

import com.example.projectsale.utils.Paging;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;


@Getter
@Setter
public class ProductSearchDtoRequest extends Paging {

    private BigDecimal minPrice;

    private BigDecimal maxPrice;

    private String name;

    private List<String> categories;

    private String size;

}
