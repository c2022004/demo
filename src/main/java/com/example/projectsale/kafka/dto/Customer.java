package com.example.projectsale.kafka.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {

    private Integer id;

    private String name;

    private String address;

    private String phone;
}
