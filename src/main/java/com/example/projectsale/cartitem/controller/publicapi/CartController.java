package com.example.projectsale.cartitem.controller.publicapi;

import com.example.projectsale.cartitem.dto.request.CartDtoRequest;
import com.example.projectsale.cartitem.service.CartService;
import com.example.projectsale.constant.SystemConstant;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(SystemConstant.API_PUBLIC + SystemConstant.VERSION_ONE + "/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartTempService;

    @PostMapping
    public ResponseEntity<?> createCart(@RequestHeader(value = "cartSessionId", required = false) String token,
                                        HttpServletResponse response,
                                        @RequestBody CartDtoRequest request) {
        String sessionId = StringUtils.isNotBlank(token) ? token : UUID.randomUUID().toString();

        Cookie cookie = new Cookie("cartSessionId", sessionId);
        cookie.setHttpOnly(true); // Cookie chỉ được gửi qua HTTP
        cookie.setSecure(false);  // Bật "true" nếu sử dụng HTTPS
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24 * 7); // Cookie sống trong 7 ngày
        response.addCookie(cookie);

        return cartTempService.createCart(sessionId, request);
    }

}
