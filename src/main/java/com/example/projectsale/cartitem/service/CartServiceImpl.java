package com.example.projectsale.cartitem.service;

import com.example.projectsale.cartitem.dto.Cart;
import com.example.projectsale.cartitem.dto.CartItemDto;
import com.example.projectsale.cartitem.dto.request.CartDtoRequest;
import com.example.projectsale.exception.ApiRequestException;
import com.example.projectsale.product.entity.Product;
import com.example.projectsale.product.repo.ProductRepo;
import com.example.projectsale.user.entity.User;
import com.example.projectsale.utils.AbsServiceUtil;
import com.example.projectsale.utils.response.Response;
import com.example.projectsale.utils.response.ResponseUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CartServiceImpl extends AbsServiceUtil implements CartService {

    private final ObjectMapper objectMapper;

    private final ProductRepo productRepo;

    private final ResponseUtil responseUtil;

    private final RedisTemplate<String, String> redisTemplate;

    private static final int CART_TTL_SECONDS = 60 * 60 * 24 * 7;

    @Override
    public ResponseEntity<Response> createCart(String token, CartDtoRequest request) {
        try {
            User user = getUser();
            Cart cart;
            String cartKey;

            if (Objects.nonNull(user)) {
                cartKey = String.valueOf(user.getId());
                String tempCartJson = redisTemplate.opsForValue().get(token);
                String userCartJson = redisTemplate.opsForValue().get(String.valueOf(user.getId()));

                Cart tempCart = Objects.nonNull(tempCartJson)
                        ? objectMapper.readValue(tempCartJson, new TypeReference<>() {})
                        : new Cart();

                Cart userCart = Objects.nonNull(userCartJson)
                        ? objectMapper.readValue(userCartJson, new TypeReference<>() {})
                        : new Cart();

                tempCart.getItems().forEach((productId, tempItem) -> userCart.addOrRemoveProduct(tempItem));

                redisTemplate.delete(token);
                cart = userCart;
            } else {
                cartKey = token;
                String key = redisTemplate.opsForValue().get(cartKey);
                cart = (key != null)
                        ? objectMapper.readValue(key, new TypeReference<>() {})
                        : new Cart();
            }

            Product product = productRepo.findByProductCondition(
                    UUID.fromString(request.getProductId()),
                    request.getQuantity()
            ).orElseThrow(() -> new ApiRequestException("ER_001"));

            cart.addOrRemoveProduct(new CartItemDto(product.getId(), request.getQuantity(), request.getColor(),request.getSize()));
            redisTemplate.opsForValue().set(cartKey, objectMapper.writeValueAsString(cart), Duration.ofSeconds(CART_TTL_SECONDS));

            return responseUtil.responseSuccess("CA_001", Map.of("cartSessionId", cartKey, "cart", cart));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ApiRequestException("CA_002");
        }
    }

}
