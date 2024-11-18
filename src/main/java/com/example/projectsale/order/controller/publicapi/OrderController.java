package com.example.projectsale.order.controller.publicapi;

import com.example.projectsale.constant.SystemConstant;
import com.example.projectsale.order.constant.OrderConstant;
import com.example.projectsale.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(SystemConstant.API_PUBLIC + SystemConstant.VERSION_ONE + OrderConstant.API_ORDER)
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{search}")
    public ResponseEntity<?> search(@PathVariable String search) {
        orderService.demo(search);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
