package com.example.projectsale.cartitem.dto;

import lombok.*;

import javax.annotation.Nonnull;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDto {

    private UUID productId;

    private Integer quantity;

    private String color;

    private String size;

}
