package com.example.projectsale.cartitem.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDtoRequest {

    private String productId;

    private int quantity;

    private String fullName;

    private String email;

    private String phoneNumber;

    private String address;

    private String description;

    private String color;

    private String size;
}
