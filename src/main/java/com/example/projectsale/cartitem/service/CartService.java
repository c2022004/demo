package com.example.projectsale.cartitem.service;

import com.example.projectsale.cartitem.dto.request.CartDtoRequest;
import com.example.projectsale.utils.response.Response;
import org.springframework.http.ResponseEntity;

public interface CartService {
    ResponseEntity<Response> createCart(String token, CartDtoRequest request);
}
