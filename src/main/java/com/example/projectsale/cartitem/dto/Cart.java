package com.example.projectsale.cartitem.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
public class Cart {
    private Map<UUID, CartItemDto> items = new HashMap<>();

    public void addOrRemoveProduct(CartItemDto item) {
        if (items.containsKey(item.getProductId())) {
            if (item.getQuantity() <= 0) {
                items.remove(item.getProductId());
                return;
            }

            CartItemDto cartItem = items.get(item.getProductId());
            cartItem.setQuantity(item.getQuantity());
            return;
        }

        items.put(item.getProductId(), item);
    }

    public void removeProduct(UUID productId) {
        items.remove(productId);
    }
}
