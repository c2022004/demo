package com.example.projectsale.order.dto.request;

import com.example.projectsale.utils.Paging;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OrderSearchDtoRequest extends Paging {
    private String email;

    private String fullName;

    private String phoneNumber;

    private String address;

    private String statusOrder;

    private Date orderDate;

    private String productName;
}
