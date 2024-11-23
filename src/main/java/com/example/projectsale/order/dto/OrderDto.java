package com.example.projectsale.order.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class OrderDto {

    private String email;

    private String fullName;

    private String phoneNumber;

    private String address;

    private Date orderDate;

    private BigDecimal totalPrice;

    private String description;

}
