package com.example.projectsale.order.controller.admin;

import com.example.projectsale.constant.SystemConstant;
import com.example.projectsale.order.constant.OrderConstant;
import com.example.projectsale.order.dto.request.OrderSearchDtoRequest;
import com.example.projectsale.order.service.OrderService;
import com.example.projectsale.utils.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "adminOrder")
@RequiredArgsConstructor
@RequestMapping(SystemConstant.API_ADMIN + SystemConstant.VERSION_ONE + OrderConstant.API_ORDER)
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<Response> getAllOrders(@RequestBody OrderSearchDtoRequest request) {
        return orderService.getAllOrders(request);
    }
}
