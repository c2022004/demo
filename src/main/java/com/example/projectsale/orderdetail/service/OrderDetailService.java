package com.example.projectsale.orderdetail.service;

import com.example.projectsale.orderdetail.dto.OrderDetailDto;
import com.example.projectsale.utils.response.Response;
import org.springframework.http.ResponseEntity;

public interface OrderDetailService {

    ResponseEntity<Response> createOrderDetail(String cartSessionId, OrderDetailDto orderDetailDto);

}
