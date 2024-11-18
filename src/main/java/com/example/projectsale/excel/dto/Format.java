package com.example.projectsale.excel.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Format {

    private Long timezone;

    private String date;

    private String dateTime;

    private String fullDateTime;

    private String currency;

    private char thousand;

    private char point;

    private String currencySight;

    private String number;

    private String numberMaxSight;
}
