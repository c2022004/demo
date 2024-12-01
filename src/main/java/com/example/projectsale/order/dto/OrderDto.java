package com.example.projectsale.order.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class OrderDto {

    private UUID orderId;

    private String email;

    private String fullName;

    private String phoneNumber;

    private String address;

    private String statusOrder;

    private Date orderDate;

    private BigDecimal totalPrice;

    private String description;

}
