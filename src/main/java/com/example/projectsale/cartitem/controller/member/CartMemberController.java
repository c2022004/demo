package com.example.projectsale.cartitem.controller.member;

import com.example.projectsale.cartitem.service.CartService;
import com.example.projectsale.constant.SystemConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(SystemConstant.API_MEMBER + SystemConstant.VERSION_ONE + "/cart")
public class CartMemberController {

    private final CartService cartService;


}
